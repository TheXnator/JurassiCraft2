package org.jurassicraft.server.api;

import org.jurassicraft.server.entity.base.GrowthStage;

public class GrowthStageGenderContainer
{
    public GrowthStage growthStage;
    public boolean isMale;

    public GrowthStageGenderContainer(GrowthStage stage, boolean isMale)
    {
        this.growthStage = stage;
        this.isMale = isMale;
    }

    public GrowthStage getGrowthStage()
    {
        return growthStage;
    }

    public boolean isMale()
    {
        return isMale;
    }

    public boolean isFemale()
    {
        return !isMale();
    }

    @Override
    public int hashCode()
    {
        return growthStage.ordinal() * (isMale() ? 1 : 0) * 54;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof GrowthStageGenderContainer)
        {
            GrowthStageGenderContainer container = (GrowthStageGenderContainer) object;

            return container.growthStage == growthStage && container.isMale == isMale;
        }

        return false;
    }
}
