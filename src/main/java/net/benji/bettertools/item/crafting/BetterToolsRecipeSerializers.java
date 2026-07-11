package net.benji.bettertools.item.crafting;

import net.benji.bettertools.BetterToolsNeoforge;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BetterToolsRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, BetterToolsNeoforge.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> PAXEL_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            "paxel",
            PaxelRecipe.PaxelRecipeSerializer::new
    );

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> WOOD_CHISELING_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register(
            "wood_chiseling",
            () -> new SimpleCraftingRecipeSerializer<>(WoodChiselingRecipe::new)
    );

    public static void registerRecipeSerializers(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering recipe serializers for " + BetterToolsNeoforge.MOD_ID);
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
