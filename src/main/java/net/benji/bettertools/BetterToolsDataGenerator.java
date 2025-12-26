package net.benji.bettertools;

import net.benji.bettertools.data.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BetterToolsNeoforge.MOD_ID)
public class BetterToolsDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new BetterToolsRecipeProvider.RecipeProviderRunner(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new BetterToolsGlobalLootModifierProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new BetterToolsAdvancementProvider(packOutput, lookupProvider, existingFileHelper));

        BetterToolsBlockTagProvider blockTagGenerator = generator.addProvider(event.includeServer(),
                new BetterToolsBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new BetterToolsItemTagProvider(packOutput, lookupProvider, blockTagGenerator.contentsGetter()));

        generator.addProvider(event.includeClient(), new BetterToolsModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new BetterToolsBlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), new BetterToolsRegistryDataGenerator(packOutput, lookupProvider));
    }


}
