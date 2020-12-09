package cassiokf.industrialrenewal.tileentity;

import cassiokf.industrialrenewal.blocks.BlockSmallWindTurbine;
import cassiokf.industrialrenewal.blocks.abstracts.BlockHorizontalFacing;
import cassiokf.industrialrenewal.config.IRConfig;
import cassiokf.industrialrenewal.item.ItemWindBlade;
import cassiokf.industrialrenewal.tileentity.abstracts.TileEntitySync;
import cassiokf.industrialrenewal.util.Utils;
import cassiokf.industrialrenewal.util.VoltsEnergyContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class TileEntitySmallWindTurbine extends TileEntitySync implements ITickable
{
    private final VoltsEnergyContainer energyContainer;
    public ItemStackHandler bladeInv = new ItemStackHandler(1)
    {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack)
        {
            if (stack.isEmpty()) return false;
            return stack.getItem() instanceof ItemWindBlade;
        }

        @Override
        protected void onContentsChanged(int slot)
        {
            TileEntitySmallWindTurbine.this.sync();
        }
    };
    private float rotation;
    private float oldRotation;
    private int tickToDamage;
    private EnumFacing blockFacing;

    private final Random random = new Random();

    public TileEntitySmallWindTurbine()
    {
        this.energyContainer = new VoltsEnergyContainer(32000, 1024, 1024)
        {
            @Override
            public boolean canReceive()
            {
                return false;
            }
        };
    }

    public void dropAllItems()
    {
        Utils.dropInventoryItems(world, pos, bladeInv);
    }

    public static int getMaxGeneration()
    {
        return IRConfig.MainConfig.Main.maxEnergySWindTurbine;
    }

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            int energyGen = 0;
            //Generate Energy
            if (hasBlade())
            {
                energyGen = Math.round(getMaxGeneration() * getEfficiency());
                //damage blade
                if (tickToDamage >= 1200 && energyGen > 0)
                {
                    tickToDamage = 0;
                    if (bladeInv.getStackInSlot(0).attemptDamageItem(1, random, null))
                        bladeInv.setStackInSlot(0, ItemStack.EMPTY);
                }
                if (tickToDamage < 1201) tickToDamage++;
            }
            //OutPut Energy
            if (energyGen > 0)
            {
                TileEntity te = world.getTileEntity(pos.down());
                if (te != null)
                {
                    IEnergyStorage downE = te.getCapability(CapabilityEnergy.ENERGY, EnumFacing.UP);
                    if (downE != null && downE.canReceive())
                    {
                        downE.receiveEnergy(energyGen, false);
                    }
                }
            }
        } else
        {
            if (hasBlade())
            {
                oldRotation = rotation;
                rotation += 6f * getEfficiency();
                if (rotation >= 360f)
                {
                    rotation -= 360f;
                    oldRotation -= 360f;
                }
            }
        }
    }

    public IItemHandler getBladeHandler()
    {
        return this.bladeInv;
    }

    public float getRotation()
    {
        //float inverted = Utils.normalize(partialTicks, 1, 0);
        //rotation = rotation + (4f * inverted) * getEfficiency();
        //if (rotation >= 360) rotation -= 360;
        return rotation;
    }

    public float getOldRotation()
    {
        return oldRotation;
    }

    @Override
    public double getMaxRenderDistanceSquared()
    {
        return super.getMaxRenderDistanceSquared() * IRConfig.MainConfig.Render.windBladesRenderDistanceMult;
    }

    public boolean hasBlade()
    {
        return !this.bladeInv.getStackInSlot(0).isEmpty();
    }

    private float getEfficiency()
    {
        float weatherModifier;
        if (world.isThundering())
        {
            weatherModifier = 1f;
        } else if (world.isRaining())
        {
            weatherModifier = 0.9f;
        } else
        {
            weatherModifier = 0.8f;
        }

        float heightModifier;
        float posMin = -2040f;
        if (pos.getY() - 62 <= 0) heightModifier = 0;
        else heightModifier = (pos.getY() - posMin) / (255 - posMin);
        heightModifier = MathHelper.clamp(heightModifier, 0, 1);

        return weatherModifier * heightModifier;
    }

    public EnumFacing getBlockFacing()
    {
        if (blockFacing != null) return blockFacing;
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockSmallWindTurbine)
        {
            return blockFacing = state.getValue(BlockHorizontalFacing.FACING);
        }
        return EnumFacing.NORTH;
    }

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
            return CapabilityEnergy.ENERGY.cast(this.energyContainer);
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        this.bladeInv.deserializeNBT(compound.getCompoundTag("bladeInv"));
        this.tickToDamage = compound.getInteger("damageTick");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag("bladeInv", this.bladeInv.serializeNBT());
        compound.setInteger("damageTick", tickToDamage);
        return super.writeToNBT(compound);
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return new AxisAlignedBB(pos.add(-4D, -4D, -4D), pos.add(5D, 5D, 5D));
    }
}
