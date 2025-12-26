package net.benji.bettertools.item;

import net.benji.bettertools.BetterToolsNeoforge;
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
                output.accept(BetterToolsItems.COPPER_HAMMER.get());
                output.accept(BetterToolsItems.IRON_HAMMER.get());
                output.accept(BetterToolsItems.GOLDEN_HAMMER.get());
                output.accept(BetterToolsItems.DIAMOND_HAMMER.get());
                output.accept(BetterToolsItems.NETHERITE_HAMMER.get());

                output.accept(BetterToolsItems.COPPER_PAXEL.get());
                output.accept(BetterToolsItems.IRON_PAXEL.get());
                output.accept(BetterToolsItems.GOLDEN_PAXEL.get());
                output.accept(BetterToolsItems.DIAMOND_PAXEL.get());
                output.accept(BetterToolsItems.NETHERITE_PAXEL.get());

                output.accept(BetterToolsItems.COPPER_SCYTHE.get());
                output.accept(BetterToolsItems.IRON_SCYTHE.get());
                output.accept(BetterToolsItems.GOLDEN_SCYTHE.get());
                output.accept(BetterToolsItems.DIAMOND_SCYTHE.get());
                output.accept(BetterToolsItems.NETHERITE_SCYTHE.get());

                output.accept(BetterToolsItems.COPPER_LUMBER_AXE.get());
                output.accept(BetterToolsItems.IRON_LUMBER_AXE.get());
                output.accept(BetterToolsItems.GOLDEN_LUMBER_AXE.get());
                output.accept(BetterToolsItems.DIAMOND_LUMBER_AXE.get());
                output.accept(BetterToolsItems.NETHERITE_LUMBER_AXE.get());

                output.accept(BetterToolsItems.GLASS_CHIPPER.get());

                output.accept(BetterToolsItems.BEDROCK_SMASHER.get());
            }).build());

    public static void registerCreativeModeTabs(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering creative mode tabs for " + BetterToolsNeoforge.MOD_ID);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
