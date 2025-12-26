package net.benji.bettertools;

import net.benji.bettertools.data.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BetterToolsNeoforge.MOD_ID)
public class BetterToolsDataGenerator {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new BetterToolsRecipeProvider.RecipeProviderRunner(packOutput, lookupProvider));
        generator.addProvider(true, new BetterToolsGlobalLootModifierProvider(packOutput, lookupProvider));
        generator.addProvider(true, new BetterToolsAdvancementProvider(packOutput, lookupProvider));

        generator.addProvider(true, new BetterToolsBlockTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new BetterToolsItemTagProvider(packOutput, lookupProvider));

        generator.addProvider(true, new BetterToolsModelProvider(packOutput));

        generator.addProvider(true, new BetterToolsRegistryDataGenerator(packOutput, lookupProvider));
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new BetterToolsRecipeProvider.RecipeProviderRunner(packOutput, lookupProvider));
        generator.addProvider(true, new BetterToolsGlobalLootModifierProvider(packOutput, lookupProvider));
        generator.addProvider(true, new BetterToolsAdvancementProvider(packOutput, lookupProvider));

        generator.addProvider(true, new BetterToolsBlockTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new BetterToolsItemTagProvider(packOutput, lookupProvider));

        generator.addProvider(true, new BetterToolsModelProvider(packOutput));

        generator.addProvider(true, new BetterToolsRegistryDataGenerator(packOutput, lookupProvider));
    }

}
