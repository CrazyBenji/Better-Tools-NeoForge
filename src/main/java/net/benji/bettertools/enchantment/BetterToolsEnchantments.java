package net.benji.bettertools.enchantment;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.enchantment.custom.ReapingEnchantment;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BetterToolsEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, BetterToolsNeoforge.MOD_ID);

    public static final Supplier<Enchantment> REAPING = ENCHANTMENTS.register(
            "reaping",
            () -> new ReapingEnchantment(
                    Enchantment.definition(
                            BetterToolsTags.Items.SCYTHES,
                            5,
                            4,
                            Enchantment.dynamicCost(10, 8),
                            Enchantment.dynamicCost(18, 8),
                            1,
                            EquipmentSlot.MAINHAND
                    )
            )
    );

    public static void registerEnchantment(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering enchantments for " + BetterToolsNeoforge.MOD_ID);
        ENCHANTMENTS.register(modEventBus);
    }
}
