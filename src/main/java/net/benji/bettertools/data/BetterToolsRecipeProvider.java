package net.benji.bettertools.data;

import net.benji.bettertools.data.recipes.PaxelRecipeBuilder;
import net.benji.bettertools.item.BetterToolsItems;
import net.benji.bettertools.item.crafting.WoodChiselingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BetterToolsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public BetterToolsRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public void generatePaxelRecipe(RecipeOutput recipeOutput, Item pickaxe, Item axe, Item shovel, Item output) {
        PaxelRecipeBuilder.paxel(RecipeCategory.TOOLS, output, 1)
                .pattern("pas")
                .pattern(" r ")
                .pattern(" r ")
                .define('p', pickaxe)
                .define('a', axe)
                .define('s', shovel)
                .define('r', Tags.Items.RODS_WOODEN)
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
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    public void generateScytheRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("mmm")
                .pattern(" s ")
                .pattern("s  ")
                .define('m', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
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
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    public void generateMacheteRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("  i")
                .pattern(" i ")
                .pattern("s  ")
                .define('i', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    public void generateSwordRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern(" i ")
                .pattern(" i ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    public void generateShovelRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern(" i ")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    public void generatePickaxeRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    public void generateAxeRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("ii ")
                .pattern("is ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }
    public void generateHoeRecipe(RecipeOutput recipeOutput, Item ingot, Item output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output, 1)
                .pattern("ii ")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', ingot)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(recipeOutput);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        generateHammerRecipe(recipeOutput, Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_HAMMER.get());
        generateHammerRecipe(recipeOutput, Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_HAMMER.get());
        generateHammerRecipe(recipeOutput, Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_HAMMER.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_HAMMER.get());
        generateHammerRecipe(recipeOutput, Items.COPPER_INGOT, Items.COPPER_BLOCK, BetterToolsItems.COPPER_HAMMER.get());
        generateHammerRecipe(recipeOutput, Items.AMETHYST_SHARD, Items.AMETHYST_BLOCK, BetterToolsItems.AMETHYST_HAMMER.get());

        generateScytheRecipe(recipeOutput, Items.IRON_INGOT, BetterToolsItems.IRON_SCYTHE.get());
        generateScytheRecipe(recipeOutput, Items.GOLD_INGOT, BetterToolsItems.GOLDEN_SCYTHE.get());
        generateScytheRecipe(recipeOutput, Items.DIAMOND, BetterToolsItems.DIAMOND_SCYTHE.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_SCYTHE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_SCYTHE.get());
        generateScytheRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_SCYTHE.get());
        generateScytheRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_SCYTHE.get());

        generatePaxelRecipe(recipeOutput, Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, BetterToolsItems.IRON_PAXEL.get());
        generatePaxelRecipe(recipeOutput, Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, BetterToolsItems.GOLDEN_PAXEL.get());
        generatePaxelRecipe(recipeOutput, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, BetterToolsItems.DIAMOND_PAXEL.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_PAXEL.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_PAXEL.get());
        generatePaxelRecipe(recipeOutput, BetterToolsItems.COPPER_PICKAXE.get(), BetterToolsItems.COPPER_AXE.get(), BetterToolsItems.COPPER_SHOVEL.get(), BetterToolsItems.COPPER_PAXEL.get());
        generatePaxelRecipe(recipeOutput, BetterToolsItems.AMETHYST_PICKAXE.get(), BetterToolsItems.AMETHYST_AXE.get(), BetterToolsItems.AMETHYST_SHOVEL.get(), BetterToolsItems.AMETHYST_PAXEL.get());

        generateLumberAxeRecipe(recipeOutput, Items.IRON_INGOT, Items.IRON_BLOCK, BetterToolsItems.IRON_LUMBER_AXE.get());
        generateLumberAxeRecipe(recipeOutput, Items.GOLD_INGOT, Items.GOLD_BLOCK, BetterToolsItems.GOLDEN_LUMBER_AXE.get());
        generateLumberAxeRecipe(recipeOutput, Items.DIAMOND, Items.DIAMOND_BLOCK, BetterToolsItems.DIAMOND_LUMBER_AXE.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_LUMBER_AXE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_LUMBER_AXE.get());
        generateLumberAxeRecipe(recipeOutput, Items.COPPER_INGOT, Items.COPPER_BLOCK, BetterToolsItems.COPPER_LUMBER_AXE.get());
        generateLumberAxeRecipe(recipeOutput, Items.AMETHYST_SHARD, Items.AMETHYST_BLOCK, BetterToolsItems.AMETHYST_LUMBER_AXE.get());

        generateMacheteRecipe(recipeOutput, Items.IRON_INGOT, BetterToolsItems.IRON_MACHETE.get());
        generateMacheteRecipe(recipeOutput, Items.GOLD_INGOT, BetterToolsItems.GOLDEN_MACHETE.get());
        generateMacheteRecipe(recipeOutput, Items.DIAMOND, BetterToolsItems.DIAMOND_MACHETE.get());
        netheriteSmithing(recipeOutput, BetterToolsItems.DIAMOND_MACHETE.get(), RecipeCategory.TOOLS, BetterToolsItems.NETHERITE_MACHETE.get());
        generateMacheteRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_MACHETE.get());
        generateMacheteRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_MACHETE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BetterToolsItems.GLASS_CHIPPER.get(), 1)
                .pattern(" i")
                .pattern("s ")
                .define('i', Items.IRON_INGOT)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BetterToolsItems.BEDROCK_SMASHER.get(), 1)
                .pattern("ini")
                .pattern(" s ")
                .pattern(" s ")
                .define('i', Items.NETHERITE_INGOT)
                .define('n', Items.NETHER_STAR)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BetterToolsItems.WOOD_CHISEL, 1)
                .pattern("n")
                .pattern("i")
                .pattern("l")
                .define('n', Items.IRON_NUGGET)
                .define('i', Items.IRON_INGOT)
                .define('l', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);

        generateSwordRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_SWORD.get());
        generateShovelRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_SHOVEL.get());
        generatePickaxeRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_PICKAXE.get());
        generateAxeRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_AXE.get());
        generateHoeRecipe(recipeOutput, Items.COPPER_INGOT, BetterToolsItems.COPPER_HOE.get());

        generateSwordRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_SWORD.get());
        generateShovelRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_SHOVEL.get());
        generatePickaxeRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_PICKAXE.get());
        generateAxeRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_AXE.get());
        generateHoeRecipe(recipeOutput, Items.AMETHYST_SHARD, BetterToolsItems.AMETHYST_HOE.get());

        SpecialRecipeBuilder.special(WoodChiselingRecipe::new).save(recipeOutput, "wood_chiseling");
    }
}
