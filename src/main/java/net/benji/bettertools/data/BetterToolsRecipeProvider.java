package net.benji.bettertools.data;

import net.benji.bettertools.data.recipes.PaxelRecipeBuilder;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BetterToolsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public BetterToolsRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public void generatePaxelRecipe(RecipeOutput recipeOutput, Item pickaxe, Item axe, Item shovel, Item result) {
        PaxelRecipeBuilder.paxel(pickaxe, axe, shovel, result)
                .unlockedBy(getHasName(pickaxe), has(pickaxe))
                .save(recipeOutput);
    }

    public void generateHammerRecipe(RecipeOutput recipeOutput, Item ingot, Item block, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
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
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("mmm")
                .pattern(" s ")
                .pattern("s  ")
                .define('m', ingot)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    public void generateLumberAxeRecipe(RecipeOutput recipeOutput, Item ingot, Item block, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("ib")
                .pattern("is")
                .pattern(" s")
                .define('i', ingot)
                .define('b', block)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        generateHammerRecipe(recipeOutput, Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_HAMMER.get());
        generateHammerRecipe(recipeOutput, Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_HAMMER.get());
        generateHammerRecipe(recipeOutput, Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_HAMMER.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_HAMMER.get());

        generateSickleRecipe(recipeOutput, Items.IRON_INGOT, BetterToolsItems.IRON_SCYTHE.get());
        generateSickleRecipe(recipeOutput, Items.GOLD_INGOT, BetterToolsItems.GOLDEN_SCYTHE.get());
        generateSickleRecipe(recipeOutput, Items.DIAMOND, BetterToolsItems.DIAMOND_SCYTHE.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_SCYTHE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_SCYTHE.get());

        generatePaxelRecipe(recipeOutput, Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, BetterToolsItems.IRON_PAXEL.get());
        generatePaxelRecipe(recipeOutput, Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, BetterToolsItems.GOLDEN_PAXEL.get());
        generatePaxelRecipe(recipeOutput, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, BetterToolsItems.DIAMOND_PAXEL.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_PAXEL.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_PAXEL.get());

        generateLumberAxeRecipe(recipeOutput, Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_LUMBER_AXE.get());
        generateLumberAxeRecipe(recipeOutput, Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_LUMBER_AXE.get());
        generateLumberAxeRecipe(recipeOutput, Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_LUMBER_AXE.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_LUMBER_AXE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_LUMBER_AXE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BetterToolsItems.GLASS_CHIPPER.get(), 1)
                .pattern(" i")
                .pattern("s ")
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BetterToolsItems.BEDROCK_SMASHER.get(), 1)
                .pattern("ini")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', Items.NETHERITE_INGOT)
                .define('n', Items.NETHER_STAR)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(recipeOutput);
    }
}
