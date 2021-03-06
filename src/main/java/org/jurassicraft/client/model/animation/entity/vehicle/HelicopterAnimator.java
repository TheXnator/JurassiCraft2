package org.jurassicraft.client.model.animation.entity.vehicle;

import net.ilexiconn.llibrary.client.model.tabula.ITabulaModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.TabulaModel;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.entity.vehicle.HelicopterBaseEntity;

@SideOnly(Side.CLIENT)
public class HelicopterAnimator implements ITabulaModelAnimator<HelicopterBaseEntity>
{
    @Override
    public void setRotationAngles(TabulaModel model, HelicopterBaseEntity entity, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks)
    {
        AdvancedModelRenderer rotor = model.getCube("rotorbase_rotatehere");
        AdvancedModelRenderer tailrotor = model.getCube("tailrotor_rotatehere");
        rotor.rotateAngleY = entity.getRotorRotation();
        tailrotor.rotateAngleX = entity.getRotorRotation();
    }
}
