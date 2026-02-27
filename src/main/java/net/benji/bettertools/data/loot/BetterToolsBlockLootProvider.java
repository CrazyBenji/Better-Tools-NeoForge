package net.benji.bettertools.data.loot;

import net.benji.bettertools.block.BetterToolsBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class BetterToolsBlockLootProvider extends BlockLootSubProvider {
    public BetterToolsBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(BetterToolsBlocks.SMASHED_BEDROCK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BetterToolsBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
