package net.benji.bettertools;

import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.data.loot.BetterToolsLootModifiers;
import net.benji.bettertools.enchantment.BetterToolsEnchantments;
import net.benji.bettertools.item.BetterToolsCreativeModeTabs;
import net.benji.bettertools.item.BetterToolsItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod(BetterToolsNeoforge.MOD_ID)
public class BetterToolsNeoforge {

    public static final String MOD_ID = "bettertools";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BetterToolsNeoforge(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        BetterToolsBlocks.registerBlocks(modEventBus);
        BetterToolsItems.registerItems(modEventBus);
        BetterToolsCreativeModeTabs.registerCreativeModeTabs(modEventBus);
        BetterToolsEnchantments.registerEnchantment(modEventBus);
        BetterToolsLootModifiers.register(modEventBus);

        modEventBus.register(BetterToolsDataGenerator.class);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
