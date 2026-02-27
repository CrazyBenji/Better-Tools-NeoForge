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
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BetterToolsBlockTagProvider extends BlockTagsProvider {
    public BetterToolsBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BetterToolsNeoforge.MOD_ID, existingFileHelper);
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

        this.tag(BetterToolsTags.Blocks.MACHETE_MINEABLE)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.FLOWERS)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.FERN)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.LARGE_FERN)
                .add(Blocks.BAMBOO)
                .add(Blocks.NETHER_WART_BLOCK)
                .add(Blocks.WARPED_WART_BLOCK)
                .add(Blocks.CRIMSON_ROOTS)
                .add(Blocks.WARPED_ROOTS);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BetterToolsBlocks.SMASHED_BEDROCK.get());
    }
}
