package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IOmnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.jurassicraft.common.entity.ai.EntityAIJCWander;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimSoundBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntitySpinosaurus extends EntityDinosaurAggressive implements IEntityAICreature, IOmnivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[] { "spinosaurus_hurt_1" };
    private static final String[] livingSounds = new String[] { "spinosaurus_living_1", "spinosaurus_living_2", "spinosaurus_living_3", "spinosaurus_living_4" };
    private static final String[] deathSounds = new String[] { "spinosaurus_death_1", "spinosaurus_death_2" };

    public EntitySpinosaurus(World world)
    {
        super(world);

        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPig.class, false));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

        this.tasks.addTask(6, new EntityAIJCWander(this, 20));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        tasks.addTask(2, new JCAutoAnimSoundBase(this, 75, 1, "")); //Call
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(this.getAnimID() == 0)
            AnimationAPI.sendAnimPacket(this, 1);
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 10, 4.0F, this);
    }

    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    private boolean isAIDisabled()
    {
        return false;
    }
}