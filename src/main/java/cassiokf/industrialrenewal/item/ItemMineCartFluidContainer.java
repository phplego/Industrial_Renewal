package cassiokf.industrialrenewal.item;

import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemMineCartFluidContainer extends ItemSpawnableCart
{


    public ItemMineCartFluidContainer(Item.Properties properties)
    {
        super(properties);
    }

    @Override
    public AbstractMinecartEntity getMinecartEntity(World world, double x, double y, double z)
    {
        return null;// new EntityHopperCart(world, x, y, z);
    }
}
