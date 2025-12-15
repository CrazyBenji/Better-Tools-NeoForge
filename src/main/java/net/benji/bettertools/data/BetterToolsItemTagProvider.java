package net.benji.bettertools.data;

import net.benji.bettertools.item.BetterToolsItems;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class BetterToolsItemTagProvider extends ItemTagsProvider {
    public BetterToolsItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(BetterToolsTags.Items.HAMMERS)
                .add(BetterToolsItems.IRON_HAMMER.get())
                .add(BetterToolsItems.GOLDEN_HAMMER.get())
                .add(BetterToolsItems.DIAMOND_HAMMER.get())
                .add(BetterToolsItems.NETHERITE_HAMMER.get());

        this.tag(BetterToolsTags.Items.PAXELS)
                .add(BetterToolsItems.IRON_PAXEL.get())
                .add(BetterToolsItems.GOLDEN_PAXEL.get())
                .add(BetterToolsItems.DIAMOND_PAXEL.get())
                .add(BetterToolsItems.NETHERITE_PAXEL.get());

        this.tag(BetterToolsTags.Items.SCYTHES)
                .add(BetterToolsItems.IRON_SCYTHE.get())
                .add(BetterToolsItems.GOLDEN_SCYTHE.get())
                .add(BetterToolsItems.DIAMOND_SCYTHE.get())
                .add(BetterToolsItems.NETHERITE_SCYTHE.get());

        this.tag(BetterToolsTags.Items.GLASS_CHIPPERS)
                .add(BetterToolsItems.GLASS_CHIPPER.get());

        this.tag(BetterToolsTags.Items.LUMBER_AXES)
                .add(BetterToolsItems.IRON_LUMBER_AXE.get())
                .add(BetterToolsItems.GOLDEN_LUMBER_AXE.get())
                .add(BetterToolsItems.DIAMOND_LUMBER_AXE.get())
                .add(BetterToolsItems.NETHERITE_LUMBER_AXE.get());
    }
}
