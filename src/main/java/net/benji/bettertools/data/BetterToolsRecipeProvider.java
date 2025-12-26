package net.benji.bettertools.data;

import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BetterToolsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public BetterToolsRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
        super(registryLookup, output);
    }

    public static class RecipeProviderRunner extends RecipeProvider.Runner {
        public RecipeProviderRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput output) {
            return new BetterToolsRecipeProvider(provider, output);
        }

        @Override
        public @NotNull String getName() {
            return "BetterToolsRecipeProvider";
        }
    }

    @Override
    protected void buildRecipes() {
        generateHammerRecipe(output, Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_HAMMER.get());
        generateHammerRecipe(output, Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_HAMMER.get());
        generateHammerRecipe(output, Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_HAMMER.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_HAMMER.get());

        generateSickleRecipe(output, Items.IRON_INGOT, BetterToolsItems.IRON_SCYTHE.get());
        generateSickleRecipe(output, Items.GOLD_INGOT, BetterToolsItems.GOLDEN_SCYTHE.get());
        generateSickleRecipe(output, Items.DIAMOND, BetterToolsItems.DIAMOND_SCYTHE.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_SCYTHE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_SCYTHE.get());

        generatePaxelRecipe(output, Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, BetterToolsItems.IRON_PAXEL.get());
        generatePaxelRecipe(output, Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, BetterToolsItems.GOLDEN_PAXEL.get());
        generatePaxelRecipe(output, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, BetterToolsItems.DIAMOND_PAXEL.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_PAXEL.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_PAXEL.get());

        generateLumberAxeRecipe(output, Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_LUMBER_AXE.get());
        generateLumberAxeRecipe(output, Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_LUMBER_AXE.get());
        generateLumberAxeRecipe(output, Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_LUMBER_AXE.get());
        netheriteSmithing(BetterToolsItems.DIAMOND_LUMBER_AXE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_LUMBER_AXE.get());

        shaped(RecipeCategory.TOOLS, BetterToolsItems.GLASS_CHIPPER.get(), 1)
                .pattern(" i")
                .pattern("s ")
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(output);

        shaped(RecipeCategory.TOOLS, BetterToolsItems.BEDROCK_SMASHER.get(), 1)
                .pattern("ini")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', Items.NETHERITE_INGOT)
                .define('n', Items.NETHER_STAR)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(output);
    }

    public void generatePaxelRecipe(RecipeOutput recipeOutput, Item pickaxe, Item axe, Item shovel, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("abc")
                .pattern(" s ")
                .pattern(" s ")
                .define('a', pickaxe)
                .define('b', axe)
                .define('c', shovel)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(pickaxe), has(pickaxe))
                .save(recipeOutput);
    }

    public void generateHammerRecipe(RecipeOutput recipeOutput, Item ingot, Item block, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("mbm")
                .pattern("msm")
                .pattern(" s ")
                .define('m', ingot)
                .define('b', block)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    public void generateSickleRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("mmm")
                .pattern(" s ")
                .pattern("s  ")
                .define('m', ingot)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    public void generateLumberAxeRecipe(RecipeOutput recipeOutput, Item ingot, Item block, Item output) {
        shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("ib")
                .pattern("is")
                .pattern(" s")
                .define('i', ingot)
                .define('b', block)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
}