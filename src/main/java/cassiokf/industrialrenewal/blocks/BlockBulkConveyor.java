package cassiokf.industrialrenewal.blocks;

import cassiokf.industrialrenewal.blocks.abstracts.BlockHorizontalFacing;
import cassiokf.industrialrenewal.init.ModBlocks;
import cassiokf.industrialrenewal.init.ModItems;
import cassiokf.industrialrenewal.item.ItemPowerScrewDrive;
import cassiokf.industrialrenewal.tileentity.TileEntityBulkConveyor;
import cassiokf.industrialrenewal.tileentity.TileEntityBulkConveyorHopper;
import cassiokf.industrialrenewal.tileentity.TileEntityBulkConveyorInserter;
import cassiokf.industrialrenewal.util.enums.EnumBulkConveyorType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockBulkConveyor extends BlockHorizontalFacing
{
    public static final PropertyInteger MODE = PropertyInteger.create("mode", 0, 2);
    public static final PropertyBool FRONT = PropertyBool.create("front");
    public static final PropertyBool BACK = PropertyBool.create("back");
    protected static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 1.0D);
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
    private static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.625D, 1D);
    public final EnumBulkConveyorType type;

    public BlockBulkConveyor(String name, CreativeTabs tab, EnumBulkConveyorType type)
    {
        super(name, tab, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(0.8f);
        this.type = type;
    }

    public static double getMotionX(EnumFacing facing)
    {
        return facing == EnumFacing.EAST ? 0.2 : -0.2;
    }

    public static double getMotionZ(EnumFacing facing)
    {
        return facing == EnumFacing.SOUTH ? 0.2 : -0.2;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (type.equals(EnumBulkConveyorType.NORMAL) && entityIn instanceof EntityLivingBase)
        {
            EnumFacing facing = worldIn.getBlockState(pos).getValue(FACING);
            if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) entityIn.motionZ += getMotionZ(facing);
            else entityIn.motionX += getMotionX(facing);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack heldItem = playerIn.getHeldItem(hand);
        if (!heldItem.isEmpty() && hand.equals(EnumHand.MAIN_HAND))
        {
            if (type.equals(EnumBulkConveyorType.NORMAL))
            {
                if (heldItem.getItem().equals(Item.getItemFromBlock(Blocks.HOPPER)))
                {
                    EnumFacing facing1 = state.getValue(FACING);
                    worldIn.setBlockState(pos, ModBlocks.conveyorVHopper.getDefaultState().withProperty(FACING, facing1), 3);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                    if (!playerIn.isCreative()) heldItem.shrink(1);
                    return true;
                }
                if (heldItem.getItem().equals(Item.getItemFromBlock(Blocks.DISPENSER)))
                {
                    EnumFacing facing1 = state.getValue(FACING);
                    worldIn.setBlockState(pos, ModBlocks.conveyorVInserter.getDefaultState().withProperty(FACING, facing1), 3);
                    worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                    if (!playerIn.isCreative()) heldItem.shrink(1);
                    return true;
                }
            }
            else if (heldItem.getItem().equals(ModItems.screwDrive))
            {
                if (type.equals(EnumBulkConveyorType.HOPPER))
                {
                    EnumFacing facing1 = state.getValue(FACING);
                    worldIn.setBlockState(pos, ModBlocks.conveyorV.getDefaultState().withProperty(FACING, facing1), 3);
                    ItemPowerScrewDrive.playDrillSound(worldIn, pos);
                    return true;
                }
                if (type.equals(EnumBulkConveyorType.INSERTER))
                {
                    EnumFacing facing1 = state.getValue(FACING);
                    worldIn.setBlockState(pos, ModBlocks.conveyorV.getDefaultState().withProperty(FACING, facing1), 3);
                    ItemPowerScrewDrive.playDrillSound(worldIn, pos);
                    return true;
                }
            }
            else if (Block.getBlockFromItem(heldItem.getItem()) instanceof BlockBulkConveyor)
            {
                IBlockState actualState = state.getActualState(worldIn, pos);
                EnumFacing face = state.getValue(FACING);
                int mode = actualState.getValue(MODE);
                if (mode == 2 && worldIn.getBlockState(pos.offset(face).down()).getBlock().isReplaceable(worldIn, pos))
                {
                    if (!worldIn.isRemote)
                    {
                        worldIn.setBlockState(pos.offset(face).down(), state, 3);
                        if (!playerIn.isCreative()) heldItem.shrink(1);
                    }
                    return true;
                }
                if (worldIn.getBlockState(pos.offset(face)).getBlock().isReplaceable(worldIn, pos))
                {
                    if (!worldIn.isRemote)
                    {
                        worldIn.setBlockState(pos.offset(face), state, 3);
                        if (!playerIn.isCreative()) heldItem.shrink(1);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityBulkConveyor)
        {
            ((TileEntityBulkConveyor) tileentity).dropInventory();
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        if (!type.equals(EnumBulkConveyorType.NORMAL))
        {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            ItemStack itemst = type.equals(EnumBulkConveyorType.HOPPER)
                    ? new ItemStack(Item.getItemFromBlock(Blocks.HOPPER))
                    : new ItemStack(Item.getItemFromBlock(Blocks.DISPENSER));
            EntityItem entity = new EntityItem(worldIn, x, y, z, itemst);
            if (!worldIn.isRemote)
            {
                worldIn.spawnEntity(entity);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random par2Random, int par3)
    {
        return new ItemStack(ItemBlock.getItemFromBlock(ModBlocks.conveyorV)).getItem();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, MODE, FRONT, BACK);
    }

    private int getMode(IBlockAccess world, BlockPos pos, IBlockState ownState)
    {
        if (type != EnumBulkConveyorType.NORMAL) return 0;
        EnumFacing facing = ownState.getValue(FACING);
        IBlockState frontState = world.getBlockState(pos.offset(facing));
        IBlockState upState = world.getBlockState(pos.offset(facing).up());
        IBlockState directUpState = world.getBlockState(pos.up());
        IBlockState downState = world.getBlockState(pos.offset(facing).down());
        IBlockState backUpState = world.getBlockState(pos.offset(facing.getOpposite()).up());
        IBlockState backState = world.getBlockState(pos.offset(facing.getOpposite()));

        //if (frontState.getBlock() instanceof BlockBulkConveyor && frontState.getValue(FACING) == facing) return 0;
        if ((upState.getBlock() instanceof BlockBulkConveyor && upState.getValue(FACING).equals(facing)) && !(directUpState.getBlock() instanceof BlockBulkConveyor) && !(frontState.getBlock() instanceof BlockBulkConveyor && frontState.getValue(FACING).equals(facing)))
            return 1;
        if ((downState.getBlock() instanceof BlockBulkConveyor && downState.getValue(FACING).equals(facing)
                && backUpState.getBlock() instanceof BlockBulkConveyor && backUpState.getValue(FACING).equals(facing))
                || (!(backState.getBlock() instanceof BlockBulkConveyor && backState.getValue(FACING).equals(facing))
                && (backUpState.getBlock() instanceof BlockBulkConveyor && backUpState.getValue(FACING).equals(facing))))
            return 2;
        return 0;
    }

    private boolean getFront(IBlockAccess world, BlockPos pos, IBlockState ownState, final int mode)
    {
        if (type.equals(EnumBulkConveyorType.INSERTER)) return false;

        EnumFacing facing = ownState.getValue(FACING);
        IBlockState frontState = world.getBlockState(pos.offset(ownState.getValue(FACING)));
        IBlockState downState = world.getBlockState(pos.offset(facing).down());

        if (mode == 0)
            return !(frontState.getBlock() instanceof BlockBulkConveyor) || (!(frontState.getBlock() instanceof BlockBulkConveyor && frontState.getValue(FACING).equals(facing)) && downState.getBlock() instanceof BlockBulkConveyor);
        if (mode == 1) return false;
        if (mode == 2)
            return !(frontState.getBlock() instanceof BlockBulkConveyor) && !(downState.getBlock() instanceof BlockBulkConveyor);
        return false;
    }

    private boolean getBack(IBlockAccess world, BlockPos pos, IBlockState ownState, final int mode)
    {
        EnumFacing facing = ownState.getValue(FACING);
        IBlockState backState = world.getBlockState(pos.offset(facing.getOpposite()));
        IBlockState downState = world.getBlockState(pos.offset(facing.getOpposite()).down());

        if (mode == 0)
            return !(backState.getBlock() instanceof BlockBulkConveyor && backState.getValue(FACING).equals(facing)) && !(downState.getBlock() instanceof BlockBulkConveyor && downState.getValue(FACING).equals(facing));
        if (mode == 1)
            return !(downState.getBlock() instanceof BlockBulkConveyor && downState.getValue(FACING).equals(facing)) && !(backState.getBlock() instanceof BlockBulkConveyor && backState.getValue(FACING).equals(facing));
        if (mode == 2) return false;
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        int mode = getMode(worldIn, pos, state);
        boolean front = getFront(worldIn, pos, state, mode);
        boolean back = getBack(worldIn, pos, state, mode);
        return state.withProperty(MODE, mode).withProperty(FRONT, front).withProperty(BACK, back);
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
    {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityBulkConveyor && super.rotateBlock(world, pos, axis))
        {
            EnumFacing facing = world.getBlockState(pos).getValue(FACING);
            ((TileEntityBulkConveyor) te).setFacing(facing);
            return true;
        }
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (type == EnumBulkConveyorType.NORMAL) return BLOCK_AABB;
        return FULL_BLOCK_AABB;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, final World worldIn, final BlockPos pos, final AxisAlignedBB entityBox, final List<AxisAlignedBB> collidingBoxes, @Nullable final Entity entityIn, final boolean isActualState)
    {
        IBlockState actualState = getActualState(state, worldIn, pos);
        EnumFacing face = actualState.getValue(FACING);
        int mode = actualState.getValue(MODE);
        boolean ramp = mode == 1 || mode == 2;
        if (type == EnumBulkConveyorType.NORMAL)
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB);
            if (ramp)
            {
                if (face == EnumFacing.NORTH) addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
                if (face == EnumFacing.SOUTH) addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
                if (face == EnumFacing.WEST) addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
                if (face == EnumFacing.EAST) addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
            }
        }
        else
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
        }
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.DOWN;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.conveyorV));
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        if (type == EnumBulkConveyorType.NORMAL) return new TileEntityBulkConveyor();
        if (type == EnumBulkConveyorType.HOPPER) return new TileEntityBulkConveyorHopper();
        return new TileEntityBulkConveyorInserter();
    }
}
