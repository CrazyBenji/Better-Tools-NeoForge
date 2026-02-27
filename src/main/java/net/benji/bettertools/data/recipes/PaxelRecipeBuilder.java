package net.benji.bettertools.data.recipes;

import net.benji.bettertools.item.crafting.PaxelRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PaxelRecipeBuilder implements RecipeBuilder {
    private final Ingredient pickaxe;
    private final Ingredient axe;
    private final Ingredient shovel;
    private final Ingredient stick1;
    private final Ingredient stick2;
    private final Item result;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public PaxelRecipeBuilder(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike stick1, ItemLike stick2, ItemLike result) {
        this.pickaxe = Ingredient.of(pickaxe);
        this.axe = Ingredient.of(axe);
        this.shovel = Ingredient.of(shovel);
        this.stick1 = Ingredient.of(stick1);
        this.stick2 = Ingredient.of(stick2);
        this.result = result.asItem();
    }

    public static PaxelRecipeBuilder paxel(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike result) {
        return paxel(pickaxe, axe, shovel, Items.STICK, result);
    }

    public static PaxelRecipeBuilder paxel(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike sticks, ItemLike result) {
        return paxel(pickaxe, axe, shovel, sticks, sticks, result);
    }

    public static PaxelRecipeBuilder paxel(ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike stick1, ItemLike stick2, ItemLike result) {
        return new PaxelRecipeBuilder(pickaxe, axe, shovel, stick1, stick2, result);
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String string, @NotNull Criterion<?> criterion) {
        this.advancement.addCriterion(string, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String string) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, @NotNull ResourceLocation resourceLocation) {
        Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(AdvancementRequirements.Strategy.OR);
        PaxelRecipe recipe = new PaxelRecipe(this.pickaxe, this.axe, this.shovel, this.stick1, this.stick2, new ItemStack(this.result));
        recipeOutput.accept(resourceLocation, recipe, advancement$builder.build(resourceLocation.withPrefix("recipes/" + RecipeCategory.TOOLS.getFolderName() + "/")));
    }
}
