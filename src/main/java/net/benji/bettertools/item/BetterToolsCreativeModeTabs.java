package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BetterToolsCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterToolsNeoforge.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BETTER_TOOLS_TAB = CREATIVE_MODE_TABS.register("bettertools-itemgroup", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemgroup.bettertools"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> BetterToolsItems.IRON_HAMMER.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(BetterToolsItems.COPPER_SWORD);
                output.accept(BetterToolsItems.COPPER_SHOVEL);
                output.accept(BetterToolsItems.COPPER_PICKAXE);
                output.accept(BetterToolsItems.COPPER_AXE);
                output.accept(BetterToolsItems.COPPER_HOE);
                output.accept(BetterToolsItems.COPPER_HAMMER);
                output.accept(BetterToolsItems.COPPER_PAXEL);
                output.accept(BetterToolsItems.COPPER_SCYTHE);
                output.accept(BetterToolsItems.COPPER_LUMBER_AXE);
                output.accept(BetterToolsItems.COPPER_MACHETE);

                output.accept(BetterToolsItems.IRON_HAMMER);
                output.accept(BetterToolsItems.IRON_PAXEL);
                output.accept(BetterToolsItems.IRON_SCYTHE);
                output.accept(BetterToolsItems.IRON_LUMBER_AXE);
                output.accept(BetterToolsItems.IRON_MACHETE);

                output.accept(BetterToolsItems.GOLDEN_HAMMER);
                output.accept(BetterToolsItems.GOLDEN_PAXEL);
                output.accept(BetterToolsItems.GOLDEN_SCYTHE);
                output.accept(BetterToolsItems.GOLDEN_LUMBER_AXE);
                output.accept(BetterToolsItems.GOLDEN_MACHETE);

                output.accept(BetterToolsItems.AMETHYST_SWORD);
                output.accept(BetterToolsItems.AMETHYST_SHOVEL);
                output.accept(BetterToolsItems.AMETHYST_PICKAXE);
                output.accept(BetterToolsItems.AMETHYST_AXE);
                output.accept(BetterToolsItems.AMETHYST_HOE);
                output.accept(BetterToolsItems.AMETHYST_HAMMER);
                output.accept(BetterToolsItems.AMETHYST_PAXEL);
                output.accept(BetterToolsItems.AMETHYST_SCYTHE);
                output.accept(BetterToolsItems.AMETHYST_LUMBER_AXE);
                output.accept(BetterToolsItems.AMETHYST_MACHETE);

                output.accept(BetterToolsItems.DIAMOND_HAMMER);
                output.accept(BetterToolsItems.DIAMOND_PAXEL);
                output.accept(BetterToolsItems.DIAMOND_SCYTHE);
                output.accept(BetterToolsItems.DIAMOND_LUMBER_AXE);
                output.accept(BetterToolsItems.DIAMOND_MACHETE);

                output.accept(BetterToolsItems.NETHERITE_HAMMER);
                output.accept(BetterToolsItems.NETHERITE_PAXEL);
                output.accept(BetterToolsItems.NETHERITE_SCYTHE);
                output.accept(BetterToolsItems.NETHERITE_LUMBER_AXE);
                output.accept(BetterToolsItems.NETHERITE_MACHETE);

                output.accept(BetterToolsItems.GLASS_CHIPPER);

                output.accept(BetterToolsItems.BEDROCK_SMASHER);

                output.accept(BetterToolsItems.WOOD_CHISEL);

                output.accept(BetterToolsBlocks.SMASHED_BEDROCK);
            }).build());

    public static void registerCreativeModeTabs(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering creative mode tabs for " + BetterToolsNeoforge.MOD_ID);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
