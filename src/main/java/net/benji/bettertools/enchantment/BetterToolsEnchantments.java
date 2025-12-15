package net.benji.bettertools.enchantment;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.enchantment.custom.ReapingEnchantment;
import net.benji.bettertools.item.custom.ScytheItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BetterToolsEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, BetterToolsNeoforge.MOD_ID);

    public static final EnchantmentCategory SCYTHES = EnchantmentCategory.create("scythes", item -> item instanceof ScytheItem);

    public static final Supplier<Enchantment> REAPING = ENCHANTMENTS.register(
            "reaping",
            () -> new ReapingEnchantment(Enchantment.Rarity.VERY_RARE, SCYTHES, EquipmentSlot.MAINHAND)
    );

    public static void registerEnchantment(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering enchantments for " + BetterToolsNeoforge.MOD_ID);
        ENCHANTMENTS.register(modEventBus);
    }
}
