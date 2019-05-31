package cassiokf.industrialrenewal.tileentity.machines.steamboiler;

import cassiokf.industrialrenewal.config.IRConfig;
import cassiokf.industrialrenewal.init.FluidInit;
import cassiokf.industrialrenewal.item.ItemFireBox;
import cassiokf.industrialrenewal.tileentity.Fluid.TileFluidHandlerBase;
import cassiokf.industrialrenewal.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TileEntitySteamBoiler extends TileFluidHandlerBase implements ICapabilityProvider, ITickable
{
    public FluidTank waterTank = new FluidTank(32000)
    {
        @Override
        public boolean canFillFluidType(FluidStack fluid)
        {
            return fluid.getFluid().equals(FluidRegistry.WATER);
        }

        @Override
        public void onContentsChanged()
        {
            TileEntitySteamBoiler.this.Sync();
        }
    };
    public FluidTank steamTank = new FluidTank(320000)
    {
        @Override
        public boolean canFill()
        {
            return false;
        }

        @Override
        public void onContentsChanged()
        {
            TileEntitySteamBoiler.this.Sync();
        }
    };

    public ItemStackHandler fireBoxInv = new ItemStackHandler(1)
    {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack)
        {
            if (stack.isEmpty()) return false;
            return stack.getItem() instanceof ItemFireBox;
        }

        @Override
        protected void onContentsChanged(int slot)
        {
            TileEntitySteamBoiler.this.Sync();
        }
    };

    public ItemStackHandler solidFuelInv = new ItemStackHandler(1)
    {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack)
        {
            if (stack.isEmpty()) return false;
            return TileEntityFurnace.isItemFuel(stack);
        }

        @Override
        protected void onContentsChanged(int slot)
        {
            TileEntitySteamBoiler.this.Sync();
        }
    };

    private boolean master;
    private int type;

    private int maxHeat = 32000;
    private int heat;
    private int oldHeat;
    private int waterPtick = 76;

    private int solidFuelTime;
    private int maxSolidFuelTime;
    private int oldSolidFuelTime;
    private int solidPerTick = 4;

    private boolean breaking;

    @Override
    public void update()
    {
        if (this.isMaster() && !this.world.isRemote && this.type > 0)
        {
            switch (this.type)
            {
                default:
                case 1:
                    if (solidFuelTime >= solidPerTick || !this.solidFuelInv.getStackInSlot(0).isEmpty())
                    {
                        ItemStack fuel = this.solidFuelInv.getStackInSlot(0);
                        if (solidFuelTime == 0)
                        {
                            solidFuelTime = TileEntityFurnace.getItemBurnTime(fuel);
                            maxSolidFuelTime = solidFuelTime;
                            fuel.shrink(1);
                        }
                        heat += 8;
                        solidFuelTime -= solidPerTick;
                    }
                    break;
            }

            if (heat >= 10000 && this.waterTank.getFluidAmount() >= waterPtick && this.steamTank.getFluidAmount() < this.steamTank.getCapacity())
            {
                FluidStack stack = this.waterTank.drain(waterPtick, true);
                int amount = stack != null ? stack.amount : 0;
                float factor = (heat / 100f) / (maxHeat / 100f);
                amount = Math.round(amount * factor);
                FluidStack steamStack = new FluidStack(FluidInit.STEAM, amount * IRConfig.steamBoilerConvertionFactor);
                this.steamTank.fillInternal(steamStack, true);
                heat -= 2;
            }

            heat -= 2;
            heat = MathHelper.clamp(heat, 2420, maxHeat);
            solidFuelTime = Math.max(0, solidFuelTime);

            TileEntity upTE = this.world.getTileEntity(pos.up(2));
            if (this.steamTank.getFluidAmount() > 0 && upTE != null && upTE.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN))
            {
                IFluidHandler upTank = upTE.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);
                this.steamTank.drain(upTank.fill(this.steamTank.drain(10000, false), true), true);
            }

            if (this.steamTank.getFluidAmount() > 0 && heat < 9000)
            {
                FluidStack stack = this.steamTank.drain(10, true);
                stack.amount = stack.amount / IRConfig.steamBoilerConvertionFactor;
                this.waterTank.fill(stack, true);
            }

            if (oldHeat != heat || solidFuelTime != oldSolidFuelTime)
            {
                this.Sync();
                oldHeat = heat;
                oldSolidFuelTime = solidFuelTime;
            }
        }
    }

    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {
        if (!this.getIsMaster())
        {
            this.getMaster().setType(type);
            return;
        }
        this.type = type;
        IBlockState state = this.world.getBlockState(this.pos).withProperty(BlockSteamBoiler.TYPE, type);
        this.world.setBlockState(this.pos, state, 2);
        this.markDirty();
    }

    @Override
    public void onLoad()
    {
        this.getIsMaster();
    }

    public TileEntitySteamBoiler getMaster()
    {
        List<BlockPos> list = Utils.getBlocksIn3x3x3Centered(this.pos);
        for (BlockPos currentPos : list)
        {
            Block block = world.getBlockState(currentPos).getBlock();
            if (block instanceof BlockSteamBoiler && ((TileEntitySteamBoiler) world.getTileEntity(currentPos)).getIsMaster())
            {
                return ((TileEntitySteamBoiler) world.getTileEntity(currentPos));
            }
        }
        return null;
    }

    public void breakMultiBlocks()
    {
        if (!this.getIsMaster())
        {
            if (getMaster() != null)
            {
                getMaster().breakMultiBlocks();
            }
            return;
        }
        if (!this.world.isRemote && !breaking)
        {
            breaking = true;
            ItemStack stack = this.fireBoxInv.getStackInSlot(0);
            if (!stack.isEmpty())
            {
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                this.world.spawnEntity(item);
            }
            ItemStack stack2 = this.solidFuelInv.getStackInSlot(0);
            if (!stack2.isEmpty())
            {
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack2);
                this.world.spawnEntity(item);
            }
            List<BlockPos> list = Utils.getBlocksIn3x3x3Centered(this.pos);
            for (BlockPos currentPos : list)
            {
                Block block = world.getBlockState(currentPos).getBlock();
                if (block instanceof BlockSteamBoiler) world.setBlockToAir(currentPos);
            }
        }
    }

    public boolean isMaster()//TESR uses this
    {
        return this.master;
    }

    private boolean getIsMaster()
    {
        IBlockState state = this.world.getBlockState(this.pos);
        if (!(state.getBlock() instanceof BlockSteamBoiler)) this.master = false;
        else this.master = state.getValue(BlockSteamBoiler.MASTER);
        return this.master;
    }

    public EnumFacing getBlockFacing()
    {
        IBlockState state = this.world.getBlockState(this.pos);
        return state.getValue(BlockSteamBoiler.FACING);
    }

    public String getWaterText()
    {
        return FluidRegistry.WATER.getName();
    }

    public String getSteamText()
    {
        return FluidInit.STEAM.getName();
    }

    public String getEnergyText()
    {
        switch (getType())
        {
            default:
            case 0:
                return "No Firebox";
            case 1:
                int energy = this.solidFuelInv.getStackInSlot(0).getCount();
                if (energy == 0) return "No Fuel";
                return energy + " Fuel";
            case 2:
                return "";
        }
    }

    public String getHeatText()
    {
        String st;
        switch (IRConfig.temperatureScale)
        {
            default:
            case 0:
                st = " ºC";
                break;
            case 1:
                st = " ºF";
                break;
            case 2:
                st = " K";
                break;
        }
        return (int) Utils.getConvertedTemperature(heat / 100F) + st;
    }

    public float GetEnergyFill() //0 ~ 180
    {
        switch (getType())
        {
            default:
            case 0:
                return 0;
            case 1:
                float currentAmount = (float) this.solidFuelTime;
                currentAmount = currentAmount / (float) maxSolidFuelTime;
                return currentAmount * 180f;
            case 2:
                return 0;
        }
    }

    public float GetWaterFill() //0 ~ 180
    {
        float currentAmount = this.waterTank.getFluidAmount() / 1000F;
        float totalCapacity = this.waterTank.getCapacity() / 1000F;
        currentAmount = currentAmount / totalCapacity;
        return currentAmount * 180f;
    }

    public float GetSteamFill() //0 ~ 180
    {
        float currentAmount = this.steamTank.getFluidAmount() / 1000F;
        float totalCapacity = this.steamTank.getCapacity() / 1000F;
        currentAmount = currentAmount / totalCapacity;
        return currentAmount * 180f;
    }

    public float getHeatFill() //0 ~ 180
    {
        float currentAmount = heat;
        float totalCapacity = maxHeat;
        currentAmount = currentAmount / totalCapacity;
        return currentAmount * 140f;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        NBTTagCompound waterTag = new NBTTagCompound();
        NBTTagCompound steamTag = new NBTTagCompound();
        this.waterTank.writeToNBT(waterTag);
        this.steamTank.writeToNBT(steamTag);
        compound.setTag("water", waterTag);
        compound.setTag("steam", steamTag);
        compound.setBoolean("master", this.getIsMaster());
        compound.setInteger("type", this.type);
        compound.setTag("firebox", this.fireBoxInv.serializeNBT());
        compound.setTag("solidfuel", this.solidFuelInv.serializeNBT());
        compound.setInteger("heat", heat);
        compound.setInteger("fueltime", solidFuelTime);
        compound.setInteger("maxtime", maxSolidFuelTime);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagCompound waterTag = compound.getCompoundTag("water");
        NBTTagCompound steamTag = compound.getCompoundTag("steam");
        this.waterTank.readFromNBT(waterTag);
        this.steamTank.readFromNBT(steamTag);
        this.master = compound.getBoolean("master");
        this.type = compound.getInteger("type");
        this.fireBoxInv.deserializeNBT(compound.getCompoundTag("firebox"));
        this.solidFuelInv.deserializeNBT(compound.getCompoundTag("solidfuel"));
        this.heat = compound.getInteger("heat");
        this.solidFuelTime = compound.getInteger("fueltime");
        this.maxSolidFuelTime = compound.getInteger("maxtime");
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(final Capability<?> capability, @Nullable final EnumFacing facing)
    {
        EnumFacing face = this.getBlockFacing();
        TileEntitySteamBoiler masterTE = this.getMaster();
        if (masterTE == null) return false;

        return (facing == EnumFacing.UP && this.pos.equals(masterTE.getPos().up()) && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                || (facing == face && this.pos.equals(masterTE.getPos().down().offset(this.getBlockFacing())) && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                || (this.getMaster().getType() == 1 && (facing == face.getOpposite() || facing == face.rotateYCCW()) && this.pos.equals(masterTE.getPos().down().offset(face.getOpposite()).offset(face.rotateYCCW())) && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
    }

    @Nullable
    @Override
    public <T> T getCapability(final Capability<T> capability, @Nullable final EnumFacing facing)
    {
        EnumFacing face = this.getBlockFacing();
        TileEntitySteamBoiler masterTE = this.getMaster();
        if (masterTE == null) return super.getCapability(capability, facing);

        if (facing == EnumFacing.UP && this.pos.equals(masterTE.getPos().up()) && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(masterTE.steamTank);
        if (facing == face && this.pos.equals(masterTE.getPos().down().offset(this.getBlockFacing())) && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(masterTE.waterTank);
        if (masterTE.getType() == 1 && (facing == face.getOpposite() || facing == face.rotateYCCW()) && this.pos.equals(masterTE.getPos().down().offset(face.getOpposite()).offset(face.rotateYCCW())) && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(masterTE.solidFuelInv);

        return super.getCapability(capability, facing);
    }

    public IItemHandler getFireBoxHandler()
    {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.getMaster().fireBoxInv);
    }
}