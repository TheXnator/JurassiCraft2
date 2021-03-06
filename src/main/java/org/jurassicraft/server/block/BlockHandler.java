package org.jurassicraft.server.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.api.SubBlocksBlock;
import org.jurassicraft.server.block.machine.CleaningStationBlock;
import org.jurassicraft.server.block.machine.CultivatorBottomBlock;
import org.jurassicraft.server.block.machine.CultivatorTopBlock;
import org.jurassicraft.server.block.machine.DNACombinatorHybridizerBlock;
import org.jurassicraft.server.block.machine.DNAExtractorBlock;
import org.jurassicraft.server.block.machine.DNASequencerBlock;
import org.jurassicraft.server.block.machine.DNASynthesizerBlock;
import org.jurassicraft.server.block.machine.EmbryoCalcificationMachineBlock;
import org.jurassicraft.server.block.machine.EmbryonicMachineBlock;
import org.jurassicraft.server.block.machine.FeederBlock;
import org.jurassicraft.server.block.machine.FossilGrinderBlock;
import org.jurassicraft.server.block.machine.IncubatorBlock;
import org.jurassicraft.server.block.plant.AjuginuculaSmithiiBlock;
import org.jurassicraft.server.block.plant.AncientPlantBlock;
import org.jurassicraft.server.block.plant.BennettitaleanCycadeoideaBlock;
import org.jurassicraft.server.block.plant.CycadZamitesBlock;
import org.jurassicraft.server.block.plant.DicksoniaBlock;
import org.jurassicraft.server.block.plant.DicroidiumZuberiBlock;
import org.jurassicraft.server.block.plant.GracilariaBlock;
import org.jurassicraft.server.block.plant.MossBlock;
import org.jurassicraft.server.block.plant.ScalyTreeFernBlock;
import org.jurassicraft.server.block.plant.SmallChainFernBlock;
import org.jurassicraft.server.block.plant.SmallCycadBlock;
import org.jurassicraft.server.block.plant.SmallRoyalFernBlock;
import org.jurassicraft.server.block.plant.WildOnionBlock;
import org.jurassicraft.server.block.tree.AncientDoubleSlabBlock;
import org.jurassicraft.server.block.tree.AncientLeavesBlock;
import org.jurassicraft.server.block.tree.AncientLogBlock;
import org.jurassicraft.server.block.tree.AncientPlanksBlock;
import org.jurassicraft.server.block.tree.AncientSaplingBlock;
import org.jurassicraft.server.block.tree.AncientSlabHalfBlock;
import org.jurassicraft.server.block.tree.AncientStairsBlock;
import org.jurassicraft.server.block.tree.TreeType;
import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.EntityHandler;
import org.jurassicraft.server.tab.TabHandler;
import org.jurassicraft.server.tile.ActionFigureTile;
import org.jurassicraft.server.tile.CleaningStationTile;
import org.jurassicraft.server.tile.CultivatorTile;
import org.jurassicraft.server.tile.DNACombinatorHybridizerTile;
import org.jurassicraft.server.tile.DNAExtractorTile;
import org.jurassicraft.server.tile.DNASequencerTile;
import org.jurassicraft.server.tile.DNASynthesizerTile;
import org.jurassicraft.server.tile.EmbryoCalcificationMachineTile;
import org.jurassicraft.server.tile.EmbryonicMachineTile;
import org.jurassicraft.server.tile.FeederTile;
import org.jurassicraft.server.tile.FossilGrinderTile;
import org.jurassicraft.server.tile.IncubatorTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockHandler
{
    public static final Map<TreeType, AncientPlanksBlock> ANCIENT_PLANKS = new HashMap<>();
    public static final Map<TreeType, AncientLogBlock> ANCIENT_LOGS = new HashMap<>();
    public static final Map<TreeType, AncientLeavesBlock> ANCIENT_LEAVES = new HashMap<>();
    public static final Map<TreeType, AncientSaplingBlock> ANCIENT_SAPLINGS = new HashMap<>();

    public static final Map<TreeType, AncientSlabHalfBlock> ANCIENT_SLABS = new HashMap<>();
    public static final Map<TreeType, AncientDoubleSlabBlock> ANCIENT_DOUBLE_SLABS = new HashMap<>();
    public static final Map<TreeType, AncientStairsBlock> ANCIENT_STAIRS = new HashMap<>();

    public static final Map<TreeType, AncientLogBlock> PETRIFIED_LOGS = new HashMap<>();

    public static final List<FossilBlock> FOSSILS = new ArrayList<>();
    public static final List<EncasedFossilBlock> ENCASED_FOSSILS = new ArrayList<>();

    public static final PlantFossilBlock PLANT_FOSSIL = new PlantFossilBlock();

    public static final CleaningStationBlock CLEANING_STATION = new CleaningStationBlock();
    public static final FossilGrinderBlock FOSSIL_GRINDER = new FossilGrinderBlock();
    public static final DNASequencerBlock DNA_SEQUENCER = new DNASequencerBlock();
    public static final DNASynthesizerBlock DNA_SYNTHESIZER = new DNASynthesizerBlock();
    public static final EmbryonicMachineBlock EMBRYONIC_MACHINE = new EmbryonicMachineBlock();
    public static final EmbryoCalcificationMachineBlock EMBRYO_CALCIFICATION_MACHINE = new EmbryoCalcificationMachineBlock();
    public static final IncubatorBlock INCUBATOR = new IncubatorBlock();
    public static final DNAExtractorBlock DNA_EXTRACTOR = new DNAExtractorBlock();
    public static final DNACombinatorHybridizerBlock DNA_COMBINATOR_HYBRIDIZER = new DNACombinatorHybridizerBlock();

    public static final AmberBlock AMBER_ORE = new AmberBlock();
    public static final IceShardBlock ICE_SHARD = new IceShardBlock();

    public static final GypsumStoneBlock GYPSUM_STONE = new GypsumStoneBlock();
    public static final Block GYPSUM_COBBLESTONE = new BasicBlock(Material.ROCK).setHardness(1.5F);
    public static final Block GYPSUM_BRICKS = new BasicBlock(Material.ROCK).setHardness(2.0F);

    public static final Block REINFORCED_STONE = new BasicBlock(Material.ROCK).setHardness(2.0F);
    public static final Block REINFORCED_BRICKS = new BasicBlock(Material.ROCK).setHardness(3.0F);

    public static final CultivatorTopBlock CULTIVATOR_TOP = new CultivatorTopBlock();
    public static final CultivatorBottomBlock CULTIVATOR_BOTTOM = new CultivatorBottomBlock();

    public static final ActionFigureBlock ACTION_FIGURE = new ActionFigureBlock();

    public static final ClearGlassBlock CLEAR_GLASS = new ClearGlassBlock();

    public static final FossilizedTrackwayBlock FOSSILIZED_TRACKWAY = new FossilizedTrackwayBlock();

    public static final SmallRoyalFernBlock SMALL_ROYAL_FERN = new SmallRoyalFernBlock();
    public static final SmallChainFernBlock SMALL_CHAIN_FERN = new SmallChainFernBlock();
    public static final SmallCycadBlock SMALL_CYCAD = new SmallCycadBlock();
    public static final BennettitaleanCycadeoideaBlock CYCADEOIDEA = new BennettitaleanCycadeoideaBlock();
    public static final AncientPlantBlock CRY_PANSY = new AncientPlantBlock();
    public static final ScalyTreeFernBlock SCALY_TREE_FERN = new ScalyTreeFernBlock();
    public static final CycadZamitesBlock ZAMITES = new CycadZamitesBlock();
    public static final DicksoniaBlock DICKSONIA = new DicksoniaBlock();
    public static final DicroidiumZuberiBlock DICROIDIUM_ZUBERI = new DicroidiumZuberiBlock();
    public static final AjuginuculaSmithiiBlock AJUGINUCULA_SMITHII = new AjuginuculaSmithiiBlock();
    public static final WildOnionBlock WILD_ONION = new WildOnionBlock();
    public static final GracilariaBlock GRACILARIA = new GracilariaBlock();

    public static final PeatBlock PEAT = new PeatBlock();
    public static final Block PEAT_MOSS = new BasicBlock(Material.GROUND, SoundType.GROUND).setHardness(0.5F).setCreativeTab(TabHandler.PLANTS);
    public static final MossBlock MOSS = new MossBlock();

    public static final FeederBlock FEEDER = new FeederBlock();

    public static void init()
    {
        for (int i = 0; i < (int) Math.ceil(EntityHandler.getDinosaurs().size() / 16.0F); i++)
        {
            FossilBlock fossil = new FossilBlock(i * 16);
            EncasedFossilBlock encasedFossil = new EncasedFossilBlock(i * 16);

            FOSSILS.add(fossil);
            ENCASED_FOSSILS.add(encasedFossil);

            registerBlock(fossil, "Fossil Block " + i);
            registerBlock(encasedFossil, "Encased Fossil " + i);

            OreDictionary.registerOre("fossil", fossil);
        }

        registerBlock(PLANT_FOSSIL, "Plant Fossil Block");
        registerBlock(FOSSILIZED_TRACKWAY, "Fossilized Trackway");

        for (TreeType type : TreeType.values())
        {
            registerTreeType(type);
        }

        registerBlock(AMBER_ORE, "Amber Ore");
        registerBlock(ICE_SHARD, "Ice Shard");
        registerBlock(GYPSUM_STONE, "Gypsum Stone");
        registerBlock(GYPSUM_COBBLESTONE, "Gypsum Cobblestone");
        registerBlock(GYPSUM_BRICKS, "Gypsum Bricks");
        registerBlock(REINFORCED_STONE, "Reinforced Stone");
        registerBlock(REINFORCED_BRICKS, "Reinforced Bricks");

        registerBlock(AJUGINUCULA_SMITHII, "Ajuginucula Smithii");
        registerBlock(SMALL_ROYAL_FERN, "Small Royal Fern");
        registerBlock(SMALL_CHAIN_FERN, "Small Chain Fern");
        registerBlock(SMALL_CYCAD, "Small Cycad");
        registerBlock(CYCADEOIDEA, "Bennettitalean Cycadeoidea");
        registerBlock(CRY_PANSY, "Cry Pansy");
        registerBlock(SCALY_TREE_FERN, "Scaly Tree Fern");
        registerBlock(ZAMITES, "Cycad Zamites");
        registerBlock(DICKSONIA, "Dicksonia");
        registerBlock(WILD_ONION, "Wild Onion Plant");
        registerBlock(GRACILARIA, "Gracilaria Seaweed");
        registerBlock(DICROIDIUM_ZUBERI, "Dicroidium Zuberi");

        registerBlock(MOSS, "Moss");
        registerBlock(PEAT, "Peat");
        registerBlock(PEAT_MOSS, "Peat Moss");

        registerBlock(CLEAR_GLASS, "Clear Glass");

        registerBlockTileEntity(CultivatorTile.class, CULTIVATOR_BOTTOM, "Cultivate Bottom");
        registerBlock(CULTIVATOR_TOP, "Cultivate Top");
        registerBlockTileEntity(CleaningStationTile.class, CLEANING_STATION, "Cleaning Station");
        registerBlockTileEntity(FossilGrinderTile.class, FOSSIL_GRINDER, "Fossil Grinder");
        registerBlockTileEntity(DNASequencerTile.class, DNA_SEQUENCER, "DNA Sequencer");
        registerBlockTileEntity(DNASynthesizerTile.class, DNA_SYNTHESIZER, "DNA Synthesizer");
        registerBlockTileEntity(EmbryonicMachineTile.class, EMBRYONIC_MACHINE, "Embryonic Machine");
        registerBlockTileEntity(EmbryoCalcificationMachineTile.class, EMBRYO_CALCIFICATION_MACHINE, "Embryo Calcification Machine");
        registerBlockTileEntity(DNAExtractorTile.class, DNA_EXTRACTOR, "DNA Extractor");
        registerBlockTileEntity(DNACombinatorHybridizerTile.class, DNA_COMBINATOR_HYBRIDIZER, "DNA Combinator Hybridizer");
        registerBlockTileEntity(IncubatorTile.class, INCUBATOR, "Incubator");
        registerBlockTileEntity(ActionFigureTile.class, ACTION_FIGURE, "Action Figure Block");
        registerBlockTileEntity(FeederTile.class, FEEDER, "Feeder");
    }

    public static void registerTreeType(TreeType type)
    {
        AncientPlanksBlock planks = new AncientPlanksBlock(type);
        AncientLogBlock log = new AncientLogBlock(type, false);
        AncientLogBlock petrified_log = new AncientLogBlock(type, true);
        AncientLeavesBlock leaves = new AncientLeavesBlock(type);
        AncientSaplingBlock sapling = new AncientSaplingBlock(type);
        AncientStairsBlock stair = new AncientStairsBlock(type, planks.getDefaultState());
        AncientSlabHalfBlock slab = new AncientSlabHalfBlock(type, planks.getDefaultState());
        AncientDoubleSlabBlock double_slab = new AncientDoubleSlabBlock(type, slab, planks.getDefaultState());

        ANCIENT_PLANKS.put(type, planks);
        ANCIENT_LOGS.put(type, log);
        ANCIENT_LEAVES.put(type, leaves);
        ANCIENT_SAPLINGS.put(type, sapling);
        ANCIENT_STAIRS.put(type, stair);
        ANCIENT_SLABS.put(type, slab);
        ANCIENT_DOUBLE_SLABS.put(type, double_slab);
        PETRIFIED_LOGS.put(type, petrified_log);

        String typeName = type.name();

        registerBlock(planks, typeName + " Planks");
        registerBlock(log, typeName + " Log");
        registerBlock(petrified_log, typeName + " Log Petrified");
        registerBlock(leaves, typeName + " Leaves");
        registerBlock(sapling, typeName + " Sapling");
        registerBlock(stair, typeName + " Stairs");
        registerBlock(slab, typeName + " Slab");
        registerBlock(double_slab, typeName + " Double Slab");

        OreDictionary.registerOre("logWood", log);
        OreDictionary.registerOre("logWood", petrified_log);
        OreDictionary.registerOre("plankWood", planks);
        OreDictionary.registerOre("treeLeaves", leaves);
        OreDictionary.registerOre("treeSapling", sapling);
        OreDictionary.registerOre("slabWood", slab);
        OreDictionary.registerOre("stairWood", stair);

        Blocks.FIRE.setFireInfo(leaves, 30, 60);
        Blocks.FIRE.setFireInfo(planks, 5, 20);
        Blocks.FIRE.setFireInfo(log, 5, 5);
        Blocks.FIRE.setFireInfo(petrified_log, 5, 5);
        Blocks.FIRE.setFireInfo(double_slab, 5, 20);
        Blocks.FIRE.setFireInfo(slab, 5, 20);
        Blocks.FIRE.setFireInfo(stair, 5, 20);
    }

    public static FossilBlock getFossilBlock(Dinosaur dinosaur)
    {
        return getFossilBlock(EntityHandler.getDinosaurId(dinosaur));
    }

    private static int getBlockId(int id)
    {
        return (int) (Math.floor((((float) id + 1.0F) / 16.0F) - 0.0625F));
    }

    public static EncasedFossilBlock getEncasedFossil(Dinosaur dinosaur)
    {
        return getEncasedFossil(EntityHandler.getDinosaurId(dinosaur));
    }

    public static EncasedFossilBlock getEncasedFossil(int id)
    {
        return ENCASED_FOSSILS.get(getBlockId(id));
    }

    public static FossilBlock getFossilBlock(int id)
    {
        return FOSSILS.get(getBlockId(id));
    }

    public static int getDinosaurId(FossilBlock fossil, int metadata)
    {
        return (FOSSILS.indexOf(fossil) * 16) + metadata;
    }

    public static int getDinosaurId(EncasedFossilBlock fossil, int metadata)
    {
        return (ENCASED_FOSSILS.indexOf(fossil) * 16) + metadata;
    }

    public static int getMetadata(int id)
    {
        return id % 16;
    }

    public static int getMetadata(Dinosaur dinosaur)
    {
        return getMetadata(EntityHandler.getDinosaurId(dinosaur));
    }

    public static void registerBlockTileEntity(Class<? extends TileEntity> tileEntity, Block block, String name)
    {
        registerBlock(block, name);

        GameRegistry.registerTileEntity(tileEntity, "jurassicraft:" + name.toLowerCase().replaceAll(" ", "_"));
    }

    public static void registerBlock(Block block, String name)
    {
        name = name.toLowerCase().replaceAll(" ", "_");

        block.setUnlocalizedName(name);

        ResourceLocation resource = new ResourceLocation(JurassiCraft.MODID, name);

        if (block instanceof SubBlocksBlock)
        {
            GameRegistry.register(block, resource);
            GameRegistry.register(((SubBlocksBlock) block).getItemBlock(), resource);
        }
        else
        {
            GameRegistry.register(block, resource);
            GameRegistry.register(new ItemBlock(block), resource);
        }
    }
}
