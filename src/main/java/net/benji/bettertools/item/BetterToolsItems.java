package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BetterToolsItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterToolsNeoforge.MOD_ID);

    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.registerItem(
            "iron_hammer",
            properties -> new HammerItem(Tiers.IRON, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_HAMMER = ITEMS.registerItem(
            "golden_hammer",
            properties -> new HammerItem(Tiers.GOLD, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.registerItem(
            "diamond_hammer",
            properties -> new HammerItem(Tiers.DIAMOND, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_HAMMER = ITEMS.registerItem(
            "netherite_hammer",
            properties -> new HammerItem(Tiers.NETHERITE, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_HAMMER = ITEMS.registerItem(
            "copper_hammer",
            properties -> new HammerItem(BetterToolsTiers.COPPER, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_HAMMER = ITEMS.registerItem(
            "amethyst_hammer",
            properties -> new HammerItem(BetterToolsTiers.AMETHYST, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_PAXEL = ITEMS.registerItem(
            "iron_paxel",
            properties -> new PaxelItem(Tiers.IRON, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_PAXEL = ITEMS.registerItem(
            "golden_paxel",
            properties -> new PaxelItem(Tiers.GOLD, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_PAXEL = ITEMS.registerItem(
            "diamond_paxel",
            properties -> new PaxelItem(Tiers.DIAMOND, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_PAXEL = ITEMS.registerItem(
            "netherite_paxel",
            properties -> new PaxelItem(Tiers.NETHERITE, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_PAXEL = ITEMS.registerItem(
            "copper_paxel",
            properties -> new PaxelItem(BetterToolsTiers.COPPER, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_PAXEL = ITEMS.registerItem(
            "amethyst_paxel",
            properties -> new PaxelItem(BetterToolsTiers.AMETHYST, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_SCYTHE = ITEMS.registerItem(
            "iron_scythe",
            properties -> new ScytheItem(Tiers.IRON, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_SCYTHE = ITEMS.registerItem(
            "golden_scythe",
            properties -> new ScytheItem(Tiers.GOLD, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_SCYTHE = ITEMS.registerItem(
            "diamond_scythe",
            properties -> new ScytheItem(Tiers.DIAMOND, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_SCYTHE = ITEMS.registerItem(
            "netherite_scythe",
            properties -> new ScytheItem(Tiers.NETHERITE, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_SCYTHE = ITEMS.registerItem(
            "copper_scythe",
            properties -> new ScytheItem(BetterToolsTiers.COPPER, properties),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_SCYTHE = ITEMS.registerItem(
            "amethyst_scythe",
            properties -> new ScytheItem(BetterToolsTiers.AMETHYST, properties),
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
    public static final DeferredItem<Item> COPPER_LUMBER_AXE = ITEMS.registerItem(
            "copper_lumber_axe",
            properties -> new LumberAxeItem(BetterToolsTiers.COPPER, properties, 32),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_LUMBER_AXE = ITEMS.registerItem(
            "amethyst_lumber_axe",
            properties -> new LumberAxeItem(BetterToolsTiers.AMETHYST, properties, 64),
            new Item.Properties()
    );

    public static final DeferredItem<Item> IRON_MACHETE = ITEMS.registerItem(
            "iron_machete",
            properties -> new MacheteItem(Tiers.IRON, properties, 16),
            new Item.Properties()
    );
    public static final DeferredItem<Item> GOLDEN_MACHETE = ITEMS.registerItem(
            "golden_machete",
            properties -> new MacheteItem(Tiers.GOLD, properties, 32),
            new Item.Properties()
    );
    public static final DeferredItem<Item> DIAMOND_MACHETE = ITEMS.registerItem(
            "diamond_machete",
            properties -> new MacheteItem(Tiers.DIAMOND, properties, 48),
            new Item.Properties()
    );
    public static final DeferredItem<Item> NETHERITE_MACHETE = ITEMS.registerItem(
            "netherite_machete",
            properties -> new MacheteItem(Tiers.NETHERITE, properties, 64),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_MACHETE = ITEMS.registerItem(
            "copper_machete",
            properties -> new MacheteItem(BetterToolsTiers.COPPER, properties, 16),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_MACHETE = ITEMS.registerItem(
            "amethyst_machete",
            properties -> new MacheteItem(BetterToolsTiers.AMETHYST, properties, 32),
            new Item.Properties()
    );

    public static final DeferredItem<Item> GLASS_CHIPPER = ITEMS.registerItem(
            "glass_chipper",
            properties -> new GlassChipperItem(Tiers.IRON, properties),
            new Item.Properties()
    );

    public static final DeferredItem<Item> BEDROCK_SMASHER = ITEMS.registerItem(
            "bedrock_smasher",
            properties -> new BlockSmasherItem(() -> Blocks.BEDROCK, BetterToolsBlocks.SMASHED_BEDROCK, properties),
            new Item.Properties().durability(16)
    );

    public static final DeferredItem<Item> WOOD_CHISEL = ITEMS.registerItem(
            "wood_chisel",
            properties -> new CraftingToolItem(properties, "desc.bettertools.wood_chisel"),
            new Item.Properties().durability(256)
    );

    public static final DeferredItem<Item> COPPER_SWORD = ITEMS.registerItem(
            "copper_sword",
            properties -> new SwordItem(BetterToolsTiers.COPPER, properties.attributes(SwordItem.createAttributes(BetterToolsTiers.COPPER, 3, -2.4F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_SHOVEL = ITEMS.registerItem(
            "copper_shovel",
            properties -> new ShovelItem(BetterToolsTiers.COPPER, properties.attributes(ShovelItem.createAttributes(BetterToolsTiers.COPPER, 1.5F, -3.0F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_PICKAXE = ITEMS.registerItem(
            "copper_pickaxe",
            properties -> new PickaxeItem(BetterToolsTiers.COPPER, properties.attributes(PickaxeItem.createAttributes(BetterToolsTiers.COPPER, 1.0F, -2.8F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_AXE = ITEMS.registerItem(
            "copper_axe",
            properties -> new AxeItem(BetterToolsTiers.COPPER, properties.attributes(AxeItem.createAttributes(BetterToolsTiers.COPPER, 7.0F, -3.2F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> COPPER_HOE = ITEMS.registerItem(
            "copper_hoe",
            properties -> new HoeItem(BetterToolsTiers.COPPER, properties.attributes(HoeItem.createAttributes(BetterToolsTiers.COPPER, -1.0F, -2.0F))),
            new Item.Properties()
    );

    public static final DeferredItem<Item> AMETHYST_SWORD = ITEMS.registerItem(
            "amethyst_sword",
            properties -> new SwordItem(BetterToolsTiers.AMETHYST, properties.attributes(SwordItem.createAttributes(BetterToolsTiers.AMETHYST, 3, -2.4F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_SHOVEL = ITEMS.registerItem(
            "amethyst_shovel",
            properties -> new ShovelItem(BetterToolsTiers.AMETHYST, properties.attributes(ShovelItem.createAttributes(BetterToolsTiers.AMETHYST, 1.5F, -3.0F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_PICKAXE = ITEMS.registerItem(
            "amethyst_pickaxe",
            properties -> new PickaxeItem(BetterToolsTiers.AMETHYST, properties.attributes(PickaxeItem.createAttributes(BetterToolsTiers.AMETHYST, 1.0F, -2.8F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_AXE = ITEMS.registerItem(
            "amethyst_axe",
            properties -> new AxeItem(BetterToolsTiers.AMETHYST, properties.attributes(AxeItem.createAttributes(BetterToolsTiers.AMETHYST, 7.0F, -3.2F))),
            new Item.Properties()
    );
    public static final DeferredItem<Item> AMETHYST_HOE = ITEMS.registerItem(
            "amethyst_hoe",
            properties -> new HoeItem(BetterToolsTiers.AMETHYST, properties.attributes(HoeItem.createAttributes(BetterToolsTiers.AMETHYST, -1.0F, -2.0F))),
            new Item.Properties()
    );

    public static void registerItems(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering items for " + BetterToolsNeoforge.MOD_ID);
        ITEMS.register(modEventBus);
    }
}
