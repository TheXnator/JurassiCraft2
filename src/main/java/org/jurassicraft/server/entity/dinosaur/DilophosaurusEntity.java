package org.jurassicraft.server.entity.dinosaur;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jurassicraft.server.entity.VenomEntity;
import org.jurassicraft.server.entity.ai.DilophosaurusMeleeEntityAI;
import org.jurassicraft.server.entity.base.DinosaurEntity;

public class DilophosaurusEntity extends DinosaurEntity implements IRangedAttackMob
{
    private static final DataParameter<Boolean> WATCHER_HAS_TARGET = EntityDataManager.createKey(DinosaurEntity.class, DataSerializers.BOOLEAN);
    private int targetCooldown;

    public DilophosaurusEntity(World world)
    {
        super(world);
        this.target(EntityPlayer.class, EntityVillager.class, EntityAnimal.class, GallimimusEntity.class, ParasaurolophusEntity.class, TriceratopsEntity.class, VelociraptorEntity.class);
        this.tasks.addTask(1, new DilophosaurusMeleeEntityAI(this, dinosaur.getAttackSpeed()));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distance)
    {
        VenomEntity venom = new VenomEntity(this.worldObj, this);
        double deltaX = target.posX - venom.posX;
        double deltaY = target.posY + (double) target.getEyeHeight() - 1.100000023841858D - venom.posY;
        double deltaZ = target.posZ - venom.posZ;
        float yOffset = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ) * 0.2F;
        venom.setThrowableHeading(deltaX, deltaY + (double) yOffset, deltaZ, 1.5F, 0F);
        this.worldObj.spawnEntityInWorld(venom);
    }

    @Override
    public EntityAIBase getAttackAI()
    {
        return new EntityAIAttackRanged(this, dinosaur.getAttackSpeed(), 40, 10);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();

        this.dataManager.register(WATCHER_HAS_TARGET, false);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!worldObj.isRemote)
        {
            if (getAttackTarget() != null && targetCooldown < 100)
            {
                targetCooldown = 100 + getRNG().nextInt(30);
            }
            else if (targetCooldown > 0)
            {
                targetCooldown--;
            }

            this.dataManager.set(WATCHER_HAS_TARGET, hasTarget());
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        if (super.attackEntityAsMob(entity))
        {
            if (entity instanceof EntityLivingBase)
            {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 300, 1, false, false));
            }

            return true;
        }

        return false;
    }

    public boolean hasTarget()
    {
        return worldObj.isRemote ? dataManager.get(WATCHER_HAS_TARGET) : getAttackTarget() != null || targetCooldown > 0;
    }
}