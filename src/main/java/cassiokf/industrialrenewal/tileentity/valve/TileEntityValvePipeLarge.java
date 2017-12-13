package cassiokf.industrialrenewal.tileentity.valve;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.*;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Set;

public class TileEntityValvePipeLarge extends TileFluidHandler implements IFluidHandler {


    public TileEntityValvePipeLarge() {
        tank = new ValveUtils(this, 1000);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return (oldState.getBlock() != newState.getBlock());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }
    //test
    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if(resource == null)
            return 0;
        int canAccept = resource.amount;
        if(canAccept <= 0)
            return 0;

        return this.tank.fill(resource, doFill);
    }
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain)
    {
        return this.tank.drain(resource.amount, doDrain);
    }
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain)
    {
        //needsUpdate = true;
        return this.tank.drain(maxDrain, doDrain);
    }
    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[] { new FluidTankProperties(tank.getInfo().fluid, tank.getInfo().capacity) };
    }

    //Test
    private final Set<EnumFacing> enabledFacings = EnumSet.of(EnumFacing.NORTH,EnumFacing.SOUTH,EnumFacing.EAST,EnumFacing.WEST);

    public boolean toggleFacing(final EnumFacing facing) {
        if (enabledFacings.contains(facing)) {
            enabledFacings.remove(facing);
            return false;
        } else {
            enabledFacings.add(facing);
            return true;
        }
    }
    public boolean isFacingEnabled(final @Nullable EnumFacing facing) {
        return enabledFacings.contains(facing) || facing == null;
    }
    public Set<EnumFacing> getEnabledFacings() {
        return enabledFacings;
    }
    @Override
    public void readFromNBT(final NBTTagCompound tag) {
        super.readFromNBT(tag);

        enabledFacings.clear();

        final int[] enabledFacingIndices = tag.getIntArray("EnabledFacings");
        for (final int index : enabledFacingIndices) {
            enabledFacings.add(EnumFacing.getFront(index));
        }
    }
    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound tag) {
        final int[] enabledFacingIndices = enabledFacings.stream()
                .mapToInt(EnumFacing::getIndex)
                .toArray();

        tag.setIntArray("EnabledFacings", enabledFacingIndices);

        return super.writeToNBT(tag);
    }
    @Override
    public boolean hasCapability(final Capability<?> capability, @Nullable final EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return isFacingEnabled(facing);
        }

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(final Capability<T> capability, @Nullable final EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (isFacingEnabled(facing)) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
            }

            return null;
        }

        return super.getCapability(capability, facing);
    }
}