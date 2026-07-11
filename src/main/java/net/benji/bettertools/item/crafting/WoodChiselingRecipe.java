package net.benji.bettertools.item.crafting;

import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodChiselingRecipe extends CustomRecipe {
    public WoodChiselingRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput recipeInput, @NotNull Level level) {
        boolean chisel = false;
        boolean log = false;

        for (int i = 0; i < recipeInput.size(); i++) {
            ItemStack itemStack = recipeInput.getItem(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.is(BetterToolsItems.WOOD_CHISEL.get()) && !chisel) {
                    chisel = true;
                }
                else if (itemStack.getItem() instanceof BlockItem blockItem && !log) {
                    BlockState stripped = AxeItem.getAxeStrippingState(blockItem.getBlock().defaultBlockState());
                    if (stripped != null) {
                        log = true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }

        return chisel && log;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput recipeInput, HolderLookup.@NotNull Provider provider) {
        ItemStack result = ItemStack.EMPTY;

        for (int i = 0; i < recipeInput.size(); i++) {
            ItemStack input = recipeInput.getItem(i);
            if (!input.isEmpty()) {
                if (input.getItem() instanceof BlockItem blockItem) {
                    BlockState stripped = AxeItem.getAxeStrippingState(blockItem.getBlock().defaultBlockState());
                    if (stripped != null) {
                        result = new ItemStack(stripped.getBlock().asItem());
                    }
                }
            }
        }

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i >= 2 && j >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BetterToolsRecipeSerializers.WOOD_CHISELING_RECIPE_SERIALIZER.get();
    }
}