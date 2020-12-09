package cassiokf.industrialrenewal.tileentity;

import cassiokf.industrialrenewal.tileentity.abstracts.TileEntityToggleableBase;
import cassiokf.industrialrenewal.util.CustomFluidTank;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

public class TileEntityValvePipeLarge extends TileEntityToggleableBase implements ITickableTileEntity
{

    public static final CustomFluidTank dummyTank = new CustomFluidTank(0)
    {
        @Override
        public boolean canFill(FluidStack stack)
        {
            return false;
        }
    };

    public final CustomFluidTank tank = new CustomFluidTank(2000)
    {
        @Override
        protected void onContentsChanged()
        {
            TileEntityValvePipeLarge.this.markDirty();
        }
    };

    private static final int amountPerTick = 1000;

    public TileEntityValvePipeLarge(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick()
    {
        if (this.hasWorld() && !world.isRemote && active)
        {
            Direction faceToFill = getOutPutFace();
            TileEntity teOut = world.getTileEntity(pos.offset(faceToFill));
            TileEntity teIn = world.getTileEntity(pos.offset(faceToFill.getOpposite()));

            boolean hasFluidInternally = tank.getFluidAmount() > 0;

            if (teOut != null && (hasFluidInternally || (teIn != null)))
            {
                IFluidHandler inTank = hasFluidInternally
                        ? tank
                        : teIn.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, faceToFill).orElse(null);
                IFluidHandler outTank = teOut.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
                        faceToFill.getOpposite()).orElse(null);
                if (inTank != null && outTank != null)
                {
                    FluidStack amountCanFill = inTank.drain(amountPerTick, IFluidHandler.FluidAction.SIMULATE);
                    if (amountCanFill != null) inTank.drain(outTank.fill(amountCanFill, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        }
    }

    @Override
    public void playSwitchSound()
    {
        float pitch = rand.nextFloat() * (1.2f - 0.8f) + 0.8f;
        this.getWorld().playSound(null, this.getPos(), SoundsRegistration.TILEENTITY_VALVE_CHANGE, SoundCategory.BLOCKS, 1F,
                pitch);
    }

    @Override
    public void read(final CompoundNBT tag)
    {
        tank.readFromNBT(tag);
        super.read(tag);
    }

    @Override
    public CompoundNBT write(final CompoundNBT tag)
    {
        tank.writeToNBT(tag);
        return super.write(tag);
    }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(final Capability<T> capability, @Nullable final Direction facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            if (facing == getOutPutFace())
                return LazyOptional.of(() -> dummyTank).cast();
            if (facing == getOutPutFace().getOpposite())
                return LazyOptional.of(() -> tank).cast();
        }
        return super.getCapability(capability, facing);
    }
}