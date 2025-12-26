package net.benji.bettertools;

import net.benji.bettertools.data.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = BetterToolsNeoforge.MOD_ID)
public class BetterToolsDataGenerator {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        event.createProvider(BetterToolsRecipeProvider.RecipeProviderRunner::new);
        event.createProvider(BetterToolsGlobalLootModifierProvider::new);
        event.createProvider(BetterToolsAdvancementProvider::new);

        event.createProvider(BetterToolsBlockTagProvider::new);
        event.createProvider(BetterToolsItemTagProvider::new);

        event.createProvider(BetterToolsModelProvider::new);

        event.createProvider(BetterToolsRegistryDataGenerator::new);
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        event.createProvider(BetterToolsRecipeProvider.RecipeProviderRunner::new);
        event.createProvider(BetterToolsGlobalLootModifierProvider::new);
        event.createProvider(BetterToolsAdvancementProvider::new);

        event.createProvider(BetterToolsBlockTagProvider::new);
        event.createProvider(BetterToolsItemTagProvider::new);

        event.createProvider(BetterToolsModelProvider::new);

        event.createProvider(BetterToolsRegistryDataGenerator::new);
    }
}
