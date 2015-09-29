package org.jurassicraft.client.render.entity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.EntityVelociraptor;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDinosaurMultilayer extends RenderLiving implements IDinosaurRenderer
{
    private static final DynamicTexture dynamicTexture = new DynamicTexture(16, 16);

    public Dinosaur dinosaur;
    public RenderDinosaurDefinition renderDef;
    public ResourceLocation[][] maleTextures;
    public ResourceLocation[][] femaleTextures;
    public ResourceLocation[][] maleOverlayTextures;
    public ResourceLocation[][] femaleOverlayTextures;
    public Random random;

    public RenderDinosaurMultilayer(RenderDinosaurDefinition renderDef)
    {
        super(Minecraft.getMinecraft().getRenderManager(), renderDef.getModel(0, EnumGrowthStage.INFANT), renderDef.getShadowSize());
        this.addLayer(new LayerDinosaurFeatures(this));

        this.dinosaur = renderDef.getDinosaur();
        this.random = new Random();
        this.renderDef = renderDef;

        this.maleTextures = new ResourceLocation[dinosaur.getMaleTextures(0, EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length]; //TODO
        this.femaleTextures = new ResourceLocation[dinosaur.getFemaleTextures(0, EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];
        this.maleOverlayTextures = new ResourceLocation[dinosaur.getMaleOverlayTextures(0, EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];
        this.femaleOverlayTextures = new ResourceLocation[dinosaur.getFemaleOverlayTextures(0, EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];

        for (EnumGrowthStage stage : EnumGrowthStage.values())
        {
            int i = 0;

            for (String texture : dinosaur.getMaleTextures(0, stage))
            {
                this.maleTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }

            i = 0;

            for (String texture : dinosaur.getFemaleTextures(0, stage))
            {
                this.femaleTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }

            i = 0;

            for (String texture : dinosaur.getMaleOverlayTextures(0, stage))
            {
                this.maleOverlayTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }

            i = 0;

            for (String texture : dinosaur.getFemaleOverlayTextures(0, stage))
            {
                this.femaleOverlayTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }
        }
    }

    @Override
    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        EntityDinosaur entityDinosaur = (EntityDinosaur) entity;

        int geneticVariant = entityDinosaur.getGeneticVariant();

        float scale = (float) entityDinosaur.transitionFromAge(renderDef.getBabyScaleAdjustment(), renderDef.getAdultScaleAdjustment());

//        scale *= (((float) entityDinosaur.getScaleOffset()) * 0.09F);

//        float color = (((float) entityDinosaur.getColorOffset()) * 0.004F);
//
//        if(entityDinosaur.getColorOffset() % 2 == 0)
//        {
//            GL11.glColor3f(1.0F + color, 1.0F - color, 1.0F + color);
//        }
//        else
//        {
//            GL11.glColor3f(1.0F - color, 1.0F + color, 1.0F - color);
//        }

        shadowSize = scale * renderDef.getShadowSize();

        GL11.glTranslatef(renderDef.getRenderXOffset(geneticVariant) * scale, renderDef.getRenderYOffset(geneticVariant) * scale, renderDef.getRenderZOffset(geneticVariant) * scale);

        String name = entity.getCustomNameTag();

        if (entity instanceof EntityVelociraptor && (name.equals("iLexiconn") || name.equals("JTGhawk137")))
            GL11.glScalef(scale - 0.86F, scale, scale);
        else if (name.equals("Gegy"))
        {
            int ticksExisted = entity.ticksExisted / 25 + entity.getEntityId();
            int colorTypes = EnumDyeColor.values().length;
            int k = ticksExisted % colorTypes;
            int l = (ticksExisted + 1) % colorTypes;
            float time = ((float) (entity.ticksExisted % 25) + 2) / 25.0F;
            float[] colors = EntitySheep.func_175513_a(EnumDyeColor.byMetadata(k));
            float[] colors2 = EntitySheep.func_175513_a(EnumDyeColor.byMetadata(l));
            GlStateManager.color(colors[0] * (1.0F - time) + colors2[0] * time, colors[1] * (1.0F - time) + colors2[1] * time, colors[2] * (1.0F - time) + colors2[2] * time);

            if (time > 0.5F)
                time = 1 - time;

            GL11.glScalef(scale * (0.5F + time * 0.5F), scale * (1 + time * 0.5F), scale * (0.9F + time * 0.25F));
        }
        else if (name.equals("Notch") || name.equals("Jumbo"))
            GL11.glScalef(scale * 2, scale * 2, scale * 2);
        else if (name.equals("jglrxavpok"))
            GL11.glScalef(scale, scale, scale * -1);
        else
            GL11.glScalef(scale, scale, scale);
    }

    // need to override so that red overlay doesn't persist after death
    @Override
    protected boolean setBrightness(EntityLivingBase entitylivingbaseIn, float partialTicks, boolean combineTextures)
    {
        float f1 = entitylivingbaseIn.getBrightness(partialTicks);
        int i = this.getColorMultiplier(entitylivingbaseIn, f1, partialTicks);
        boolean flag1 = (i >> 24 & 255) > 0;
        boolean flag2 = false; // entitylivingbaseIn.hurtTime > 0 || entitylivingbaseIn.deathTime > 0;

        if (!flag1 && !flag2)
        {
            return false;
        }
        else if (!flag1 && !combineTextures)
        {
            return false;
        }
        else
        {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableTexture2D();
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, OpenGlHelper.GL_COMBINE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, GL11.GL_MODULATE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB, GL11.GL_SRC_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND1_RGB, GL11.GL_SRC_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, GL11.GL_REPLACE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA, GL11.GL_SRC_ALPHA);
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, OpenGlHelper.GL_COMBINE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB, GL11.GL_SRC_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND1_RGB, GL11.GL_SRC_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND2_RGB, GL11.GL_SRC_ALPHA);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, GL11.GL_REPLACE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA, GL11.GL_SRC_ALPHA);
            this.brightnessBuffer.position(0);

            if (flag2)
            {
                this.brightnessBuffer.put(1.0F);
                this.brightnessBuffer.put(0.0F);
                this.brightnessBuffer.put(0.0F);
                this.brightnessBuffer.put(0.3F);
            }
            else
            {
                float f2 = (i >> 24 & 255) / 255.0F;
                float f3 = (i >> 16 & 255) / 255.0F;
                float f4 = (i >> 8 & 255) / 255.0F;
                float f5 = (i & 255) / 255.0F;
                this.brightnessBuffer.put(f3);
                this.brightnessBuffer.put(f4);
                this.brightnessBuffer.put(f5);
                this.brightnessBuffer.put(1.0F - f2);
            }

            this.brightnessBuffer.flip();
            GL11.glTexEnv(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_COLOR, this.brightnessBuffer);
            GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(dynamicTexture.getGlTextureId());
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, OpenGlHelper.GL_COMBINE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, GL11.GL_MODULATE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB, GL11.GL_SRC_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND1_RGB, GL11.GL_SRC_COLOR);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, GL11.GL_REPLACE);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA, GL11.GL_SRC_ALPHA);
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            return true;
        }
    }

    public ResourceLocation getEntityTexture(EntityDinosaur entity)
    {
        return entity.isMale() ? maleTextures[entity.getTexture()][entity.getGrowthStage().ordinal()] : femaleTextures[entity.getTexture()][entity.getGrowthStage().ordinal()];
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityDinosaur) entity);
    }

    @SideOnly(Side.CLIENT)
    public class LayerDinosaurFeatures implements LayerRenderer
    {
        private final RenderDinosaurMultilayer renderer;

        public LayerDinosaurFeatures(RenderDinosaurMultilayer renderer)
        {
            this.renderer = renderer;
        }

        public void render(EntityDinosaur entity, float armSwing, float armSwingAmount, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float partialTicks)
        {
            if (!entity.isInvisible())
            {
                int texture = entity.getTexture();
                int variation = entity.getGeneticVariant();

                if (entity.isMale())
                {
                    if (texture > maleOverlayTextures.length)
                        texture = maleOverlayTextures.length;

                    this.renderer.bindTexture(maleOverlayTextures[texture][entity.getGrowthStage().ordinal()]);
                }
                else
                {
                    if (texture > femaleOverlayTextures.length)
                        texture = femaleOverlayTextures.length;

                    this.renderer.bindTexture(femaleOverlayTextures[texture][entity.getGrowthStage().ordinal()]);
                }

                this.renderer.getMainModel().render(entity, armSwing, armSwingAmount, p_177148_5_, p_177148_6_, p_177148_7_, partialTicks);
                this.renderer.func_177105_a(entity, p_177148_4_);
            }
        }

        @Override
        public boolean shouldCombineTextures()
        {
            return true;
        }

        @Override
        public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
        {
            this.render((EntityDinosaur) entity, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
        }
    }

    @Override
    public void setModel(ModelBase model)
    {
        this.mainModel = model;
    }

    @Override
    public RenderDinosaurDefinition getRenderDef()
    {
        return renderDef;
    }

    @Override
    protected void rotateCorpse(EntityLivingBase parEntity, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        if (!(parEntity.deathTime > 0))
        {
            super.rotateCorpse(parEntity, p_77043_2_, p_77043_3_, p_77043_4_);
        }
        else
        {
            GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
        }
    }
}