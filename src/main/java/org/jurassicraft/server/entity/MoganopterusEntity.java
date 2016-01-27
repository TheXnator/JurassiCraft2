package org.jurassicraft.server.entity;

import net.minecraft.world.World;
import org.jurassicraft.server.entity.base.AggressiveFlyingDinosaurEntity;

public class MoganopterusEntity extends AggressiveFlyingDinosaurEntity
{
    public MoganopterusEntity(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
