package net.benji.bettertools.data.loot;

import com.mojang.serialization.MapCodec;
import net.benji.bettertools.BetterToolsNeoforge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class BetterToolsLootModifiers {
    public static DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, BetterToolsNeoforge.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static void registerLootModifiers(IEventBus eventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering loot modifiers for " + BetterToolsNeoforge.MOD_ID);
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
