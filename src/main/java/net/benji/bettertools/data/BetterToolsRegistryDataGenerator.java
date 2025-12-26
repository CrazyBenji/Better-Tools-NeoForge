package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.enchantment.BetterToolsEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BetterToolsRegistryDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, BetterToolsEnchantments::bootstrap);

    public BetterToolsRegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(BetterToolsNeoforge.MOD_ID));
    }

    @Override
    public @NotNull String getName() {
        return "BetterToolsRegistryDataGenerator";
    }
}
