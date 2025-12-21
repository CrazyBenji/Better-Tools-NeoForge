package net.benji.bettertools.enchantment;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;

public class BetterToolsEnchantments {
    public static final ResourceKey<Enchantment> REAPING =
            ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(BetterToolsNeoforge.MOD_ID, "reaping"));

    public static void bootstrap(BootstrapContext<Enchantment> registerable) {
        var enchantments = registerable.lookup(Registries.ENCHANTMENT);
        var items = registerable.lookup(Registries.ITEM);

        register(registerable, REAPING, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(BetterToolsTags.Items.SCYTHES),
                        5,
                        4,
                        Enchantment.dynamicCost(10, 8),
                        Enchantment.dynamicCost(18, 8),
                        1,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.DAMAGE, new AddValue(LevelBasedValue.perLevel(1.5f))));

    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
