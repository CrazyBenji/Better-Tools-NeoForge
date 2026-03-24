package net.benji.bettertools.data;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class BetterToolsModelProvider extends ItemModelProvider {
    public BetterToolsModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BetterToolsNeoforge.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(BetterToolsItems.IRON_HAMMER);
        handheldItem(BetterToolsItems.GOLDEN_HAMMER);
        handheldItem(BetterToolsItems.DIAMOND_HAMMER);
        handheldItem(BetterToolsItems.NETHERITE_HAMMER);

        handheldItem(BetterToolsItems.IRON_PAXEL);
        handheldItem(BetterToolsItems.GOLDEN_PAXEL);
        handheldItem(BetterToolsItems.DIAMOND_PAXEL);
        handheldItem(BetterToolsItems.NETHERITE_PAXEL);

        handheldItem(BetterToolsItems.IRON_SCYTHE);
        handheldItem(BetterToolsItems.GOLDEN_SCYTHE);
        handheldItem(BetterToolsItems.DIAMOND_SCYTHE);
        handheldItem(BetterToolsItems.NETHERITE_SCYTHE);

        handheldItem(BetterToolsItems.IRON_LUMBER_AXE);
        handheldItem(BetterToolsItems.GOLDEN_LUMBER_AXE);
        handheldItem(BetterToolsItems.DIAMOND_LUMBER_AXE);
        handheldItem(BetterToolsItems.NETHERITE_LUMBER_AXE);

        handheldItem(BetterToolsItems.IRON_MACHETE);
        handheldItem(BetterToolsItems.GOLDEN_MACHETE);
        handheldItem(BetterToolsItems.DIAMOND_MACHETE);
        handheldItem(BetterToolsItems.NETHERITE_MACHETE);

        handheldItem(BetterToolsItems.GLASS_CHIPPER);

        handheldItem(BetterToolsItems.BEDROCK_SMASHER);
    }

    private void handheldItem(DeferredItem<?> item) {
        withExistingParent(item.getId().getPath(),
                mcLoc("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(BetterToolsNeoforge.MOD_ID, "item/" + item.getId().getPath()));
    }
}