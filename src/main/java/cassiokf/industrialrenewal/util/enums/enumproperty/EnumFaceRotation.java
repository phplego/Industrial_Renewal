package cassiokf.industrialrenewal.util.enums.enumproperty;

import net.minecraft.util.IStringSerializable;

public enum EnumFaceRotation implements IStringSerializable
{
    UP("up"),
    RIGHT("right"),
    DOWN("down"),
    LEFT("left");

    private static final EnumFaceRotation[] VALUES = values();

    private final String name;

    EnumFaceRotation(final String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public EnumFaceRotation rotateClockwise()
    {
        return VALUES[(ordinal() + 1) % VALUES.length];
    }

    public EnumFaceRotation rotateCounterClockwise()
    {
        return VALUES[(ordinal() - 1) % VALUES.length];
    }
}
