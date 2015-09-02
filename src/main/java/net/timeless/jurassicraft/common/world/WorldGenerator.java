package net.timeless.jurassicraft.common.world;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

import java.util.List;
import java.util.Random;

public class WorldGenerator implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.getDimensionId() == 0)
        {
            generateOverworld(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    public void generateOverworld(World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 32; i++)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosY = random.nextInt(64);
            int randPosZ = chunkZ + random.nextInt(16);

            EnumTimePeriod period = EnumTimePeriod.CRETACEOUS;

            List<Dinosaur> dinos = JCEntityRegistry.getDinosaursFromPeriod(period);

            //FIXME offset by one? sometimes you can get a Jurassic Fossil when at the moment it is hard-coded to Cretaceous (Also allows hybrids to be found)
            Dinosaur dinosaur = dinos.get(random.nextInt(dinos.size()));
            int meta = JurassiCraft.blockRegistry.getMetadata(dinosaur);

            new WorldGenMinable(JurassiCraft.blockRegistry.getFossilBlock(dinosaur).getStateFromMeta(meta), 5).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }

        for (int i = 0; i < 16; i++)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosY = random.nextInt(20);
            int randPosZ = chunkZ + random.nextInt(16);

            new WorldGenMinable(JCBlockRegistry.amber_ore.getDefaultState(), 3).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }

        for (int i = 0; i < 32; i++)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosY = random.nextInt(64);
            int randPosZ = chunkZ + random.nextInt(16);

            new WorldGenMinable(JCBlockRegistry.ice_shard.getDefaultState(), 1).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }

        for (int i = 0; i < 2; i++)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosY = random.nextInt(128);
            int randPosZ = chunkZ + random.nextInt(16);

            (new WorldGenMinable(JCBlockRegistry.gypsum_stone.getDefaultState(), 10)).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }
    }
}