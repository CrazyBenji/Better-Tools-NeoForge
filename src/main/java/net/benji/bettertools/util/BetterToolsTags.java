package net.benji.bettertools.util;

import net.benji.bettertools.BetterToolsNeoforge;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BetterToolsTags {
    public static class Blocks {
        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");
        public static final TagKey<Block> GLASS_CHIPPER_MINEABLE = createTag("mineable/glass_chipper");
        public static final TagKey<Block> MACHETE_MINEABLE = createTag("mineable/machete");

        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_copper_tool");
        public static final TagKey<Block> INCORRECT_FOR_AMETHYST_TOOL = createTag("incorrect_for_amethyst_tool");

        public static final TagKey<Block> MACHETE_VEIN_MINES = createTag("machete_vein_mines");
        public static final TagKey<Block> LUMBER_AXE_VEIN_MINES = createTag("lumber_axe_vein_mines");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(BetterToolsNeoforge.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HAMMERS = createTag("hammers");
        public static final TagKey<Item> PAXELS = createTag("paxels");
        public static final TagKey<Item> SCYTHES = createTag("scythes");
        public static final TagKey<Item> GLASS_CHIPPERS = createTag("glass_chippers");
        public static final TagKey<Item> LUMBER_AXES = createTag("lumber_axes");
        public static final TagKey<Item> MACHETES = createTag("machetes");

        public static final TagKey<Item> COPPER_TOOL_MATERIALS = createTag("copper_tool_materials");
        public static final TagKey<Item> AMETHYST_TOOL_MATERIALS = createTag("amethyst_tool_materials");

        public static final TagKey<Item> COPPER_TOOLS = createTag("copper_tools");
        public static final TagKey<Item> AMETHYST_TOOLS = createTag("amethyst_tools");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BetterToolsNeoforge.MOD_ID, name));
        }
    }
}
