package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BetterToolsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterToolsNeoforge.MOD_ID);

    public static final DeferredItem<Item> COPPER_HAMMER = ITEMS.registerItem(
            "copper_hammer",
            properties -> new HammerItem(ToolMaterial.COPPER, 1.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.registerItem(
            "iron_hammer",
            properties -> new HammerItem(ToolMaterial.IRON, 1.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.registerItem(
            "golden_hammer",
            properties -> new HammerItem(ToolMaterial.GOLD, 1.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.registerItem(
            "diamond_hammer",
            properties -> new HammerItem(ToolMaterial.DIAMOND, 1.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.registerItem(
            "netherite_hammer",
            properties -> new HammerItem(ToolMaterial.NETHERITE, 1.0f, -2.8f, properties)
    );

    public static final DeferredItem<Item> COPPER_PAXEL = ITEMS.registerItem(
            "copper_paxel",
            properties -> new PaxelItem(ToolMaterial.COPPER, 2.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> IRON_PAXEL = ITEMS.registerItem(
            "iron_paxel",
            properties -> new PaxelItem(ToolMaterial.IRON, 2.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.registerItem(
            "golden_paxel",
            properties -> new PaxelItem(ToolMaterial.GOLD, 2.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.registerItem(
            "diamond_paxel",
            properties -> new PaxelItem(ToolMaterial.DIAMOND, 2.0f, -2.8f, properties)
    );
    public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.registerItem(
            "netherite_paxel",
            properties -> new PaxelItem(ToolMaterial.NETHERITE, 2.0f, -2.8f, properties)
    );

    public static final DeferredItem<Item> COPPER_SCYTHE = ITEMS.registerItem(
            "copper_scythe",
            properties -> new ScytheItem(ToolMaterial.COPPER, 3.0f, -3.2f, properties)
    );
    public static final DeferredItem<Item> IRON_SCYTHE = ITEMS.registerItem(
            "iron_scythe",
            properties -> new ScytheItem(ToolMaterial.IRON, 3.0f, -3.2f, properties)
    );
    public static final DeferredItem<Item> GOLDEN_SCYTHE = ITEMS.registerItem(
            "golden_scythe",
            properties -> new ScytheItem(ToolMaterial.GOLD, 3.0f, -3.2f, properties)
    );
    public static final DeferredItem<Item> DIAMOND_SCYTHE = ITEMS.registerItem(
            "diamond_scythe",
            properties -> new ScytheItem(ToolMaterial.DIAMOND, 3.0f, -3.2f, properties)
    );
    public static final DeferredItem<Item> NETHERITE_SCYTHE = ITEMS.registerItem(
            "netherite_scythe",
            properties -> new ScytheItem(ToolMaterial.NETHERITE, 3.0f, -3.2f, properties)
    );
    public static final DeferredItem<Item> COPPER_LUMBER_AXE = ITEMS.registerItem(
            "copper_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.COPPER, 6.0f, -3.2f, properties, 32)
    );
    public static final DeferredItem<Item> IRON_LUMBER_AXE = ITEMS.registerItem(
            "iron_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.IRON, 6.0f, -3.2f, properties, 32)
    );
    public static final DeferredItem<Item> GOLDEN_LUMBER_AXE = ITEMS.registerItem(
            "golden_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.GOLD, 6.0f, -3.2f, properties, 64)
    );
    public static final DeferredItem<Item> DIAMOND_LUMBER_AXE = ITEMS.registerItem(
            "diamond_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.DIAMOND, 6.0f, -3.2f, properties, 96)
    );
    public static final DeferredItem<Item> NETHERITE_LUMBER_AXE = ITEMS.registerItem(
            "netherite_lumber_axe",
            properties -> new LumberAxeItem(ToolMaterial.NETHERITE, 6.0f, -3.2f, properties, 128)
    );

    public static final DeferredItem<Item> GLASS_CHIPPER = ITEMS.registerItem(
            "glass_chipper",
            properties -> new GlassChipperItem(ToolMaterial.IRON, 0, -3.2f, properties)
    );

    public static final DeferredItem<Item> BEDROCK_SMASHER = ITEMS.registerItem(
            "bedrock_smasher",
            properties -> new BlockSmasherItem(() -> Blocks.BEDROCK, BetterToolsBlocks.SMASHED_BEDROCK, properties.durability(16))
    );

    public static void registerItems(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering items for " + BetterToolsNeoforge.MOD_ID);
        ITEMS.register(modEventBus);
    }
}
