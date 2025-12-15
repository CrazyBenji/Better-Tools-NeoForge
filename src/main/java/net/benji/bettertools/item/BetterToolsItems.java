package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BetterToolsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterToolsNeoforge.MOD_ID);

    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.registerItem(
            "iron_hammer",
            properties -> new HammerItem(Tiers.IRON, 1, -2.8f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.registerItem(
            "golden_hammer",
            properties -> new HammerItem(Tiers.GOLD, 1, -2.8f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.registerItem(
            "diamond_hammer",
            properties -> new HammerItem(Tiers.DIAMOND, 1, -2.8f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.registerItem(
            "netherite_hammer",
            properties -> new HammerItem(Tiers.NETHERITE, 1, -2.8f, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_PAXEL = ITEMS.registerItem(
            "iron_paxel",
            properties -> new PaxelItem(Tiers.IRON, 2, -2.8f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.registerItem(
            "golden_paxel",
            properties -> new PaxelItem(Tiers.GOLD, 2, -2.8f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.registerItem(
            "diamond_paxel",
            properties -> new PaxelItem(Tiers.DIAMOND, 2, -2.8f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.registerItem(
            "netherite_paxel",
            properties -> new PaxelItem(Tiers.NETHERITE, 2, -2.8f, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_SCYTHE = ITEMS.registerItem(
            "iron_scythe",
            properties -> new ScytheItem(Tiers.IRON, 3, -3.2f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_SCYTHE = ITEMS.registerItem(
            "golden_scythe",
            properties -> new ScytheItem(Tiers.GOLD, 3, -3.2f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_SCYTHE = ITEMS.registerItem(
            "diamond_scythe",
            properties -> new ScytheItem(Tiers.DIAMOND, 3, -3.2f, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_SCYTHE = ITEMS.registerItem(
            "netherite_scythe",
            properties -> new ScytheItem(Tiers.NETHERITE, 3, -3.2f, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_LUMBER_AXE = ITEMS.registerItem(
            "iron_lumber_axe",
            properties -> new LumberAxeItem(Tiers.IRON, properties, 32),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_LUMBER_AXE = ITEMS.registerItem(
            "golden_lumber_axe",
            properties -> new LumberAxeItem(Tiers.GOLD, properties, 64),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_LUMBER_AXE = ITEMS.registerItem(
            "diamond_lumber_axe",
            properties -> new LumberAxeItem(Tiers.DIAMOND, properties, 96),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_LUMBER_AXE = ITEMS.registerItem(
            "netherite_lumber_axe",
            properties -> new LumberAxeItem(Tiers.NETHERITE, properties, 128),
            new Item.Properties()
    );

    public static final DeferredItem<Item> GLASS_CHIPPER = ITEMS.registerItem(
            "glass_chipper",
            properties -> new GlassChipperItem(Tiers.IRON, 0, -3.2f, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> BEDROCK_SMASHER = ITEMS.registerItem(
            "bedrock_smasher",
            properties -> new BlockSmasherItem(() -> Blocks.BEDROCK, BetterToolsBlocks.SMASHED_BEDROCK, properties),
            new Item.Properties().durability(16)
    );

    public static void registerItems(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering items for " + BetterToolsNeoforge.MOD_ID);
        ITEMS.register(modEventBus);
    }
}
