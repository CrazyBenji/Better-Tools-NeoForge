package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.block.BetterToolsBlocks;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;

public class BetterToolsModelProvider extends ModelProvider {
    public BetterToolsModelProvider(PackOutput output) {
        super(output, BetterToolsNeoforge.MOD_ID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(BetterToolsItems.COPPER_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.GOLDEN_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(BetterToolsItems.COPPER_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.IRON_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.GOLDEN_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.DIAMOND_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.NETHERITE_PAXEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(BetterToolsItems.COPPER_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.IRON_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.GOLDEN_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.DIAMOND_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.NETHERITE_SCYTHE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(BetterToolsItems.COPPER_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.IRON_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.GOLDEN_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.DIAMOND_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(BetterToolsItems.NETHERITE_LUMBER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(BetterToolsItems.GLASS_CHIPPER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(BetterToolsItems.BEDROCK_SMASHER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        blockModels.createTrivialCube(BetterToolsBlocks.SMASHED_BEDROCK.get());
    }

    @Override
    public @NotNull String getName() {
        return "BetterToolsModelProvider";
    }
}
