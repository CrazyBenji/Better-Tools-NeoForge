package net.benji.bettertools.data;

import net.benji.bettertools.item.BetterToolsItems;
import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
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
                .add(BetterToolsItems.NETHERITE_HAMMER.get())
                .add(BetterToolsItems.COPPER_HAMMER.get())
                .add(BetterToolsItems.AMETHYST_HAMMER.get());

        this.tag(BetterToolsTags.Items.PAXELS)
                .add(BetterToolsItems.IRON_PAXEL.get())
                .add(BetterToolsItems.GOLDEN_PAXEL.get())
                .add(BetterToolsItems.DIAMOND_PAXEL.get())
                .add(BetterToolsItems.NETHERITE_PAXEL.get())
                .add(BetterToolsItems.COPPER_PAXEL.get())
                .add(BetterToolsItems.AMETHYST_PAXEL.get());

        this.tag(BetterToolsTags.Items.SCYTHES)
                .add(BetterToolsItems.IRON_SCYTHE.get())
                .add(BetterToolsItems.GOLDEN_SCYTHE.get())
                .add(BetterToolsItems.DIAMOND_SCYTHE.get())
                .add(BetterToolsItems.NETHERITE_SCYTHE.get())
                .add(BetterToolsItems.COPPER_SCYTHE.get())
                .add(BetterToolsItems.AMETHYST_SCYTHE.get());

        this.tag(BetterToolsTags.Items.GLASS_CHIPPERS)
                .add(BetterToolsItems.GLASS_CHIPPER.get());

        this.tag(BetterToolsTags.Items.LUMBER_AXES)
                .add(BetterToolsItems.IRON_LUMBER_AXE.get())
                .add(BetterToolsItems.GOLDEN_LUMBER_AXE.get())
                .add(BetterToolsItems.DIAMOND_LUMBER_AXE.get())
                .add(BetterToolsItems.NETHERITE_LUMBER_AXE.get())
                .add(BetterToolsItems.COPPER_LUMBER_AXE.get())
                .add(BetterToolsItems.AMETHYST_LUMBER_AXE.get());

        this.tag(BetterToolsTags.Items.MACHETES)
                .add(BetterToolsItems.IRON_MACHETE.get())
                .add(BetterToolsItems.GOLDEN_MACHETE.get())
                .add(BetterToolsItems.DIAMOND_MACHETE.get())
                .add(BetterToolsItems.NETHERITE_MACHETE.get())
                .add(BetterToolsItems.COPPER_MACHETE.get())
                .add(BetterToolsItems.AMETHYST_MACHETE.get());

        this.tag(ItemTags.MINING_ENCHANTABLE)
                .addTag(BetterToolsTags.Items.HAMMERS)
                .addTag(BetterToolsTags.Items.PAXELS)
                .addTag(BetterToolsTags.Items.SCYTHES)
                .addTag(BetterToolsTags.Items.LUMBER_AXES)
                .addTag(BetterToolsTags.Items.GLASS_CHIPPERS)
                .addTag(BetterToolsTags.Items.MACHETES);

        this.tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(BetterToolsTags.Items.HAMMERS)
                .addTag(BetterToolsTags.Items.PAXELS)
                .addTag(BetterToolsTags.Items.SCYTHES)
                .addTag(BetterToolsTags.Items.LUMBER_AXES)
                .addTag(BetterToolsTags.Items.MACHETES);

        this.tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(BetterToolsTags.Items.HAMMERS)
                .addTag(BetterToolsTags.Items.PAXELS)
                .addTag(BetterToolsTags.Items.SCYTHES)
                .addTag(BetterToolsTags.Items.LUMBER_AXES)
                .addTag(BetterToolsTags.Items.GLASS_CHIPPERS)
                .add(BetterToolsItems.BEDROCK_SMASHER.get())
                .addTag(BetterToolsTags.Items.MACHETES);

        this.tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(BetterToolsTags.Items.LUMBER_AXES);

        this.tag(BetterToolsTags.Items.AMETHYST_TOOL_MATERIALS)
                .add(Items.AMETHYST_SHARD);

        this.tag(BetterToolsTags.Items.COPPER_TOOL_MATERIALS)
                .add(Items.COPPER_INGOT);

        this.tag(ItemTags.SWORDS)
                .add(BetterToolsItems.COPPER_SWORD.get())
                .add(BetterToolsItems.AMETHYST_SWORD.get());

        this.tag(ItemTags.SHOVELS)
                .add(BetterToolsItems.COPPER_SHOVEL.get())
                .add(BetterToolsItems.AMETHYST_SHOVEL.get());

        this.tag(ItemTags.PICKAXES)
                .add(BetterToolsItems.COPPER_PICKAXE.get())
                .add(BetterToolsItems.AMETHYST_PICKAXE.get());

        this.tag(ItemTags.AXES)
                .add(BetterToolsItems.COPPER_AXE.get())
                .add(BetterToolsItems.AMETHYST_AXE.get());

        this.tag(ItemTags.HOES)
                .add(BetterToolsItems.COPPER_HOE.get())
                .add(BetterToolsItems.AMETHYST_HOE.get());

        this.tag(BetterToolsTags.Items.COPPER_TOOLS)
                .add(BetterToolsItems.COPPER_SWORD.get())
                .add(BetterToolsItems.COPPER_SHOVEL.get())
                .add(BetterToolsItems.COPPER_PICKAXE.get())
                .add(BetterToolsItems.COPPER_AXE.get())
                .add(BetterToolsItems.COPPER_HOE.get())
                .add(BetterToolsItems.COPPER_HAMMER.get())
                .add(BetterToolsItems.COPPER_PAXEL.get())
                .add(BetterToolsItems.COPPER_SCYTHE.get())
                .add(BetterToolsItems.COPPER_LUMBER_AXE.get())
                .add(BetterToolsItems.COPPER_MACHETE.get());

        this.tag(BetterToolsTags.Items.AMETHYST_TOOLS)
                .add(BetterToolsItems.AMETHYST_SWORD.get())
                .add(BetterToolsItems.AMETHYST_SHOVEL.get())
                .add(BetterToolsItems.AMETHYST_PICKAXE.get())
                .add(BetterToolsItems.AMETHYST_AXE.get())
                .add(BetterToolsItems.AMETHYST_HOE.get())
                .add(BetterToolsItems.AMETHYST_HAMMER.get())
                .add(BetterToolsItems.AMETHYST_PAXEL.get())
                .add(BetterToolsItems.AMETHYST_SCYTHE.get())
                .add(BetterToolsItems.AMETHYST_LUMBER_AXE.get())
                .add(BetterToolsItems.AMETHYST_MACHETE.get());
    }
}
