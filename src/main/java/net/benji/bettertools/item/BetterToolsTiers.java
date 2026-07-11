package net.benji.bettertools.item;

import com.google.common.base.Suppliers;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

public enum BetterToolsTiers implements Tier {
    COPPER(BetterToolsTags.Blocks.INCORRECT_FOR_COPPER_TOOL, 190, 5.0F, 1.0F, 13, () -> Ingredient.of(BetterToolsTags.Items.COPPER_TOOL_MATERIALS)),
    AMETHYST(BetterToolsTags.Blocks.INCORRECT_FOR_AMETHYST_TOOL, 800, 7.0F, 2.5F, 20, () -> Ingredient.of(BetterToolsTags.Items.AMETHYST_TOOL_MATERIALS));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    BetterToolsTiers(final TagKey<Block> tagKey, final int j, final float f, final float g, final int k, final Supplier<Ingredient> supplier) {
        this.incorrectBlocksForDrops = tagKey;
        this.uses = j;
        this.speed = f;
        this.damage = g;
        this.enchantmentValue = k;
        Objects.requireNonNull(supplier);
        this.repairIngredient = Suppliers.memoize(supplier::get);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}