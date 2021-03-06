package org.jurassicraft.server.entity.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.entity.VenomEntity;

@SideOnly(Side.CLIENT)
public class VenomParticle extends Particle
{
    private VenomEntity entity;
    private double offsetX;
    private double offsetY;
    private double offsetZ;

    public VenomParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, VenomEntity entity)
    {
        super(world, entity.posX + x, entity.posY + y, entity.posZ + z, 0.0D, 0.0D, 0.0D);
        this.entity = entity;
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.particleRed = this.particleGreen = this.particleBlue = (float) (Math.random() * 0.30000001192092896D);
        this.particleScale *= 0.75F;
        this.particleScale *= scale;
        this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int) ((float) this.particleMaxAge * scale);
    }

    @Override
    public void renderParticle(VertexBuffer buffer, Entity entity, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        super.renderParticle(buffer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.posX = entity.posX + offsetX;
        this.posY = entity.posY + offsetY;
        this.posZ = entity.posZ + offsetZ;

        if (entity.isDead)
        {
            this.setExpired();
        }

        this.setParticleTextureIndex(7);
    }
}