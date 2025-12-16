package net.benji.bettertools.enchantment.custom;

import net.benji.bettertools.item.custom.ScytheItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nonnull;

public class ReapingEnchantment extends Enchantment {

    public ReapingEnchantment(EnchantmentDefinition definition) {
        super(definition);
    }

    @Override
    public boolean checkCompatibility(@Nonnull Enchantment other) {
        return !(other instanceof DamageEnchantment);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ScytheItem;
    }


}
