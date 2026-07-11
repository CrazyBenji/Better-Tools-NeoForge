package net.benji.bettertools.data.recipes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.benji.bettertools.item.crafting.PaxelRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PaxelRecipeBuilder extends ShapedRecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;
    private boolean showNotification = true;

    public PaxelRecipeBuilder(RecipeCategory recipeCategory, ItemLike result, int amount) {
        super(recipeCategory, result, amount);
        this.category = recipeCategory;
        this.result = result.asItem();
        this.count = amount;
    }

    public static PaxelRecipeBuilder paxel(RecipeCategory recipeCategory, ItemLike result, int amount) {
        return new PaxelRecipeBuilder(recipeCategory, result, amount);
    }

    @Override
    public @NotNull ShapedRecipeBuilder define(@NotNull Character character, @NotNull Ingredient ingredient) {
        super.define(character, ingredient);
        if (this.key.containsKey(character)) {
            throw new IllegalArgumentException("Symbol '" + character + "' is already defined!");
        } else if (character == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(character, ingredient);
            return this;
        }
    }

    @Override
    public @NotNull ShapedRecipeBuilder pattern(@NotNull String string) {
        super.pattern(string);
        if (!this.rows.isEmpty() && string.length() != this.rows.getFirst().length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(string);
            return this;
        }
    }

    @Override
    public @NotNull PaxelRecipeBuilder showNotification(boolean bl) {
        super.showNotification(bl);
        this.showNotification = bl;
        return this;
    }

    @Override
    public @NotNull PaxelRecipeBuilder group(@Nullable String string) {
        super.group(string);
        this.group = string;
        return this;
    }

    @Override
    public @NotNull PaxelRecipeBuilder unlockedBy(@NotNull String string, @NotNull Criterion<?> criterion) {
        super.unlockedBy(string, criterion);
        this.criteria.put(string, criterion);
        return this;
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceLocation resourceLocation) {
        ShapedRecipePattern shapedRecipePattern = this.ensureValid(resourceLocation);
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        PaxelRecipe shapedRecipe = new PaxelRecipe(
                Objects.requireNonNullElse(this.group, ""),
                RecipeBuilder.determineBookCategory(this.category),
                shapedRecipePattern,
                new ItemStack(this.result, this.count),
                this.showNotification
        );
        recipeOutput.accept(resourceLocation, shapedRecipe, builder.build(resourceLocation.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private ShapedRecipePattern ensureValid(ResourceLocation resourceLocation) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + resourceLocation);
        } else {
            return ShapedRecipePattern.of(this.key, this.rows);
        }
    }
}