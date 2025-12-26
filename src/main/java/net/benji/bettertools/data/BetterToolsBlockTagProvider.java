package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BetterToolsBlockTagProvider extends BlockTagsProvider {
    public BetterToolsBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BetterToolsNeoforge.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(BetterToolsTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);

        this.tag(BetterToolsTags.Blocks.GLASS_CHIPPER_MINEABLE)
                .addTag(Tags.Blocks.GLASS_BLOCKS)
                .addTag(Tags.Blocks.GLASS_PANES)
                .add(Blocks.SEA_LANTERN)
                .add(Blocks.GLOWSTONE)
                .add(Blocks.REDSTONE_LAMP);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BetterToolsBlocks.SMASHED_BEDROCK.get());
    }

    @Override
    public @NotNull String getName() {
        return "BetterToolsBlockTagProvider";
    }
}
