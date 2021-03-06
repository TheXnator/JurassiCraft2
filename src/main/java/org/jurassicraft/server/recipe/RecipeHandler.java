package org.jurassicraft.server.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.jurassicraft.server.block.BlockHandler;
import org.jurassicraft.server.block.tree.AncientPlanksBlock;
import org.jurassicraft.server.block.tree.TreeType;
import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.base.EntityHandler;
import org.jurassicraft.server.item.ItemHandler;

import java.util.Map;

public class RecipeHandler
{
    public static void init()
    {
        for (Dinosaur dinosaur : EntityHandler.getDinosaurs())
        {
            if (dinosaur.shouldRegister())
            {
                int meta = EntityHandler.getDinosaurId(dinosaur);

                GameRegistry.addSmelting(new ItemStack(ItemHandler.DINOSAUR_MEAT, 1, meta), new ItemStack(ItemHandler.DINOSAUR_STEAK, 1, meta), 5F);
            }
        }

        for (Map.Entry<TreeType, AncientPlanksBlock> entry : BlockHandler.ANCIENT_PLANKS.entrySet())
        {
            TreeType type = entry.getKey();
            AncientPlanksBlock planks = entry.getValue();

            GameRegistry.addShapelessRecipe(new ItemStack(planks, 4), BlockHandler.ANCIENT_LOGS.get(type));

            GameRegistry.addRecipe(new ItemStack(BlockHandler.ANCIENT_STAIRS.get(type), 4), "w  ", "ww ", "www", 'w', planks);
            GameRegistry.addRecipe(new ItemStack(BlockHandler.ANCIENT_STAIRS.get(type), 4), "  w", " ww", "www", 'w', planks);
            GameRegistry.addRecipe(new ItemStack(BlockHandler.ANCIENT_SLABS.get(type), 6), "www", 'w', planks);
        }

        GameRegistry.addSmelting(new ItemStack(BlockHandler.GYPSUM_COBBLESTONE), new ItemStack(BlockHandler.GYPSUM_STONE), 1.5F);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.IRON_BLADES), "I I", " S ", "I I", 'I', Items.IRON_INGOT, 'S', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.IRON_ROD), "ISI", "ISI", "ISI", 'I', Items.IRON_INGOT, 'S', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.FOSSIL_GRINDER), "lBl", "rRr", "IPI", 'I', Items.IRON_INGOT, 'R', ItemHandler.IRON_ROD, 'B', ItemHandler.IRON_BLADES, 'r', Items.REDSTONE, 'l', new ItemStack(Items.DYE, 1, 4), 'P', Blocks.PISTON);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.PLASTER_AND_BANDAGE, 9), "PGP", "GWG", "PGP", 'P', Items.PAPER, 'W', Blocks.WOOL, 'G', ItemHandler.GYPSUM_POWDER);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.GYPSUM_STONE), "GGG", "GGG", "GGG", 'G', ItemHandler.GYPSUM_POWDER);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.GYPSUM_BRICKS, 4), "SS", "SS", 'S', BlockHandler.GYPSUM_STONE);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.CLEANING_STATION), "iii", "RGR", "IBI", 'i', Items.IRON_INGOT, 'B', Items.BUCKET, 'G', Blocks.GLASS_PANE, 'R', Items.REDSTONE, 'I', Blocks.IRON_BLOCK);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.REINFORCED_STONE, 8), "PPP", "PWP", "PPP", 'P', Blocks.STONE, 'W', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.REINFORCED_BRICKS, 8), "PPP", "PWP", "PPP", 'P', Blocks.STONEBRICK, 'W', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.REINFORCED_BRICKS, 4), "SS", "SS", 'S', BlockHandler.REINFORCED_STONE);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.EMPTY_TEST_TUBE, 8), "G", "G", 'G', Blocks.GLASS);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.PETRI_DISH, 4), "G G", "GGG", 'G', Blocks.GLASS_PANE);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.PETRI_DISH_AGAR, 4), ItemHandler.PETRI_DISH, ItemHandler.PETRI_DISH, ItemHandler.PETRI_DISH, ItemHandler.PETRI_DISH, ItemHandler.LIQUID_AGAR);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.BLUEPRINT), "BBB", "BPB", "BBB", 'B', new ItemStack(Items.DYE, 1, 4), 'P', Items.PAPER);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.EMPTY_SYRINGE), "I", "T", "I", 'I', Items.IRON_INGOT, 'T', ItemHandler.EMPTY_TEST_TUBE);
        GameRegistry.addSmelting(new ItemStack(Items.POTIONITEM, 1, 0), new ItemStack(ItemHandler.DNA_NUCLEOTIDES), 1.0F);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.DNA_EXTRACTOR), "III", "SAP", "HII", 'I', Items.IRON_INGOT, 'S', ItemHandler.COMPUTER_SCREEN, 'A', ItemHandler.DNA_ANALYZER, 'P', Blocks.GLASS_PANE, 'H', ItemHandler.HARD_DRIVE);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.DNA_SEQUENCER), "IAI", "SAI", "HAK", 'I', Items.IRON_INGOT, 'A', ItemHandler.DNA_ANALYZER, 'S', ItemHandler.COMPUTER_SCREEN, 'H', ItemHandler.HARD_DRIVE, 'K', ItemHandler.KEYBOARD);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.DNA_SYNTHESIZER), "IIS", "ICD", "IIC", 'I', Items.IRON_INGOT, 'S', ItemHandler.COMPUTER_SCREEN, 'C', ItemHandler.ADVANCED_CIRCUIT, 'D', ItemHandler.HARD_DRIVE);

        for (int i = 0; i < 16; i++)
        {
            GameRegistry.addRecipe(new ItemStack(BlockHandler.CULTIVATOR_BOTTOM, 1, i), "GGG", "GWG", "ICI", 'G', new ItemStack(Blocks.STAINED_GLASS_PANE, 1, i), 'W', Items.WATER_BUCKET, 'I', Items.IRON_INGOT, 'C', ItemHandler.ADVANCED_CIRCUIT);
        }

        GameRegistry.addRecipe(new ItemStack(BlockHandler.EMBRYONIC_MACHINE), "GIG", "GIG", "III", 'G', Blocks.GLASS_PANE, 'I', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.EMBRYO_CALCIFICATION_MACHINE), "iBK", "RCR", "RIR", 'i', Items.IRON_INGOT, 'I', Blocks.IRON_BLOCK, 'B', Items.BOWL, 'K', ItemHandler.KEYBOARD, 'R', ItemHandler.IRON_ROD, 'C', ItemHandler.BASIC_CIRCUIT);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.INCUBATOR), "GIG", "RRR", "IKI", 'I', Items.IRON_INGOT, 'R', Items.COMPARATOR, 'G', Blocks.GLASS, 'K', ItemHandler.KEYBOARD);
        GameRegistry.addRecipe(new ItemStack(BlockHandler.DNA_COMBINATOR_HYBRIDIZER), "SCS", "IDI", " K ", 'S', ItemHandler.COMPUTER_SCREEN, 'C', ItemHandler.BASIC_CIRCUIT, 'I', Items.IRON_INGOT, 'D', ItemHandler.HARD_DRIVE, 'L', ItemHandler.KEYBOARD);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.PLANT_CELLS_PETRI_DISH), ItemHandler.PLANT_CELLS, ItemHandler.PETRI_DISH_AGAR);

        GameRegistry.addRecipe(new ItemStack(ItemHandler.IRON_NUGGET, 9), "i", 'i', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.BASIC_CIRCUIT, 2), "nuggetIron", "nuggetIron", "nuggetIron", "nuggetIron", Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.GOLD_NUGGET, Items.REDSTONE));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.ADVANCED_CIRCUIT, 1), ItemHandler.BASIC_CIRCUIT, ItemHandler.BASIC_CIRCUIT, "nuggetIron", Items.GOLD_NUGGET, Items.REDSTONE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.STORAGE_DISC), " I ", "ICI", " I ", 'I', "nuggetIron", 'C', ItemHandler.BASIC_CIRCUIT));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.LASER), "IDI", "ICI", "IRI", 'I', "nuggetIron", 'C', ItemHandler.BASIC_CIRCUIT, 'D', Items.DIAMOND, 'R', Items.REDSTONE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.HARD_DRIVE), "CLI", "i  ", "iib", 'i', "nuggetIron", 'C', ItemHandler.ADVANCED_CIRCUIT, 'L', ItemHandler.LASER, 'I', Items.IRON_INGOT, 'b', Blocks.STONE_BUTTON));

        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.AJUGINUCULA_SMITHII_OIL), ItemHandler.AJUGINUCULA_SMITHII_LEAVES, ItemHandler.AJUGINUCULA_SMITHII_LEAVES, ItemHandler.AJUGINUCULA_SMITHII_LEAVES, ItemHandler.AJUGINUCULA_SMITHII_LEAVES, Items.GLASS_BOTTLE);

        GameRegistry.addRecipe(new ItemStack(Blocks.MOSSY_COBBLESTONE, 4), "MCM", "CMC", "MCM", 'M', BlockHandler.MOSS, 'C', Blocks.COBBLESTONE);
        GameRegistry.addRecipe(new ItemStack(Blocks.STONEBRICK, 4, 1), "MCM", "CMC", "MCM", 'M', BlockHandler.MOSS, 'C', new ItemStack(Blocks.STONEBRICK, 1, 0));

        GameRegistry.addRecipe(new ItemStack(ItemHandler.COMPUTER_SCREEN), "IGI", "RPB", "CQI", 'I', Items.IRON_INGOT, 'G', new ItemStack(Items.DYE, 1, 2), 'R', new ItemStack(Items.DYE, 1, 1), 'P', Blocks.GLASS_PANE, 'B', new ItemStack(Items.DYE, 1, 4), 'C', ItemHandler.BASIC_CIRCUIT, 'Q', Items.QUARTZ);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.KEYBOARD), "bbb", "bbb", "psc", 'b', Blocks.STONE_BUTTON, 'p', Blocks.STONE_PRESSURE_PLATE, 's', Blocks.STONE_SLAB, 'c', ItemHandler.BASIC_CIRCUIT);

        GameRegistry.addRecipe(new ItemStack(BlockHandler.PEAT_MOSS), "MMM", "MMM", "MMM", 'M', BlockHandler.MOSS);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.DNA_ANALYZER), "iGi", "CLi", "iii", 'i', "nuggetIron", 'G', Blocks.GLASS, 'C', ItemHandler.ADVANCED_CIRCUIT, 'L', ItemHandler.LASER));

        GameRegistry.addRecipe(new ItemStack(ItemHandler.CAR_CHASSIS), "III", "I I", "III", 'I', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.CAR_ENGINE_SYSTEM), "BIL", "cRC", "BpP", 'B', Blocks.IRON_BARS, 'I', Items.IRON_INGOT, 'L', Blocks.LEVER, 'c', Items.COMPARATOR, 'R', Items.REPEATER, 'C', Items.CAULDRON, 'p', Blocks.STONE_PRESSURE_PLATE, 'P', Blocks.PISTON);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.CAR_ENGINE_SYSTEM), "LIB", "CRc", "PpB", 'B', Blocks.IRON_BARS, 'I', Items.IRON_INGOT, 'L', Blocks.LEVER, 'c', Items.COMPARATOR, 'R', Items.REPEATER, 'C', Items.CAULDRON, 'p', Blocks.STONE_PRESSURE_PLATE, 'P', Blocks.PISTON);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.CAR_SEATS), "LWL", "LSL", "III", 'L', Items.LEATHER, 'W', Blocks.WOOL, 'S', Items.SADDLE, 'I', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.CAR_WINDSCREEN), " I ", "IPI", " I ", 'I', Items.IRON_INGOT, 'P', Blocks.GLASS_PANE);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.CAR_TIRE, 2), "ISI", "SPS", "ISI", 'I', new ItemStack(Items.DYE, 1, 0), 'S', Items.SLIME_BALL, 'P', Blocks.PISTON);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.UNFINISHED_CAR), "ICW", "TEI", "TTT", 'I', Items.IRON_INGOT, 'C', ItemHandler.CAR_CHASSIS, 'W', ItemHandler.CAR_WINDSCREEN, 'T', ItemHandler.CAR_TIRE, 'E', ItemHandler.CAR_ENGINE_SYSTEM);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.UNFINISHED_CAR), "WCI", "IET", "TTT", 'I', Items.IRON_INGOT, 'C', ItemHandler.CAR_CHASSIS, 'W', ItemHandler.CAR_WINDSCREEN, 'T', ItemHandler.CAR_TIRE, 'E', ItemHandler.CAR_ENGINE_SYSTEM);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.JEEP_WRANGLER), "LRL", "TCR", "LRL", 'L', new ItemStack(Items.DYE, 1, 7), 'R', new ItemStack(Items.DYE, 1, 1), 'C', ItemHandler.UNFINISHED_CAR, 'T', ItemHandler.CAR_TIRE);
        GameRegistry.addRecipe(new ItemStack(ItemHandler.JEEP_WRANGLER), "LRL", "RCT", "LRL", 'L', new ItemStack(Items.DYE, 1, 7), 'R', new ItemStack(Items.DYE, 1, 1), 'C', ItemHandler.UNFINISHED_CAR, 'T', ItemHandler.CAR_TIRE);

        GameRegistry.addRecipe(new ItemStack(BlockHandler.FEEDER), "TDT", "ICI", "SSS", 'T', Blocks.IRON_TRAPDOOR, 'D', Blocks.DISPENSER, 'I', Items.IRON_INGOT, 'C', Blocks.CHEST, 'S', Blocks.COBBLESTONE);

        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.CHILEAN_SEA_BASS), Items.COOKED_FISH, ItemHandler.WILD_ONION, Items.CARROT, ItemHandler.AJUGINUCULA_SMITHII_LEAVES);

        GameRegistry.addSmelting(ItemHandler.GRACILARIA, new ItemStack(ItemHandler.LIQUID_AGAR), 0);

        addGrowthSerumRecipe(Items.COOKED_BEEF);
        addGrowthSerumRecipe(Items.COOKED_CHICKEN);
        addGrowthSerumRecipe(Items.COOKED_FISH);
        addGrowthSerumRecipe(Items.COOKED_MUTTON);
        addGrowthSerumRecipe(Items.COOKED_PORKCHOP);
        addGrowthSerumRecipe(Items.COOKED_RABBIT);

        for (int i = 0; i < EntityHandler.getDinosaurs().size(); i++)
        {
            addGrowthSerumRecipe(new ItemStack(ItemHandler.DINOSAUR_STEAK, 1, i));
        }
    }

    private static void addGrowthSerumRecipe(Item meat)
    {
        addGrowthSerumRecipe(new ItemStack(meat));
    }

    private static void addGrowthSerumRecipe(ItemStack meat)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ItemHandler.GROWTH_SERUM), Items.GOLDEN_CARROT, ItemHandler.EMPTY_SYRINGE, Items.WATER_BUCKET, meat);
    }
}
