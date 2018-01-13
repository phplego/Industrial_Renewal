package cassiokf.industrialrenewal.tileentity.filter;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidTankTile extends FluidTank {

    public FluidTankTile(int capacity) {

        super(capacity);
    }

    public FluidTankTile(FluidStack fluidStack, int capacity) {

        super(fluidStack, capacity);
    }

    public FluidTankTile(Fluid fluid, int amount, int capacity) {

        super(fluid, amount, capacity);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid) {

        return super.canFillFluidType(fluid);
    }

    @Override
    public void onContentsChanged() {
        if (this.tile != null) {
            final IBlockState state = this.tile.getWorld().getBlockState(this.tile.getPos());
            this.tile.getWorld().notifyBlockUpdate(this.tile.getPos(), state, state, 3);
            this.tile.markDirty();
        }
    }
}