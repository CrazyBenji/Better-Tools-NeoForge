package net.benji.bettertools.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaxelRecipe extends CustomRecipe {

    private final Ingredient pickaxe;
    private final Ingredient axe;
    private final Ingredient shovel;
    private final Ingredient stick1;
    private final Ingredient stick2;
    private final ItemStack result;

    public PaxelRecipe(Ingredient pickaxe, Ingredient axe, Ingredient shovel,
                       Ingredient stick1, Ingredient stick2, ItemStack result) {
        super(CraftingBookCategory.EQUIPMENT);
        this.pickaxe = pickaxe;
        this.axe = axe;
        this.shovel = shovel;
        this.stick1 = stick1;
        this.stick2 = stick2;
        this.result = result;
    }

    @Override
    public boolean matches(@NotNull CraftingInput craftingInput, Level level) {
        if (level.isClientSide() || craftingInput.size() < 7) {
            return false;
        }

        return (this.pickaxe.test(craftingInput.getItem(0)) && this.axe.test(craftingInput.getItem(1)) && this.shovel.test(craftingInput.getItem(2))
                && this.stick1.test(craftingInput.getItem(4)) && this.stick2.test(craftingInput.getItem(7)) && testEmpty(craftingInput))
                || (this.shovel.test(craftingInput.getItem(0)) && this.axe.test(craftingInput.getItem(1)) && this.pickaxe.test(craftingInput.getItem(2))
                && this.stick1.test(craftingInput.getItem(4)) && this.stick2.test(craftingInput.getItem(7)) && testEmpty(craftingInput));
    }

    public boolean testEmpty(@NotNull CraftingInput craftingInput) {
        return (Ingredient.EMPTY.test(craftingInput.getItem(3)) && Ingredient.EMPTY.test(craftingInput.getItem(5))
                && Ingredient.EMPTY.test(craftingInput.getItem(6)) && Ingredient.EMPTY.test(craftingInput.getItem(8)));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, @NotNull HolderLookup.Provider provider) {
        ItemStack toReturn = new ItemStack(this.result.getItem());
        EnchantmentHelper.setEnchantments(toReturn, combineEnchantments(craftingInput));
        return toReturn;
    }

    private ItemEnchantments combineEnchantments(CraftingInput craftingInput) {
        ItemEnchantments.Mutable combined = new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(0)));
        List<ItemEnchantments> enchantmentsToCombine = List.of(
                EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(1)),
                EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(2))
        );

        for (ItemEnchantments itemEnchantment : enchantmentsToCombine) {
            for (Holder<Enchantment> enchantment : itemEnchantment.keySet()) {
                combined.upgrade(enchantment, itemEnchantment.getLevel(enchantment));
            }
        }

        return combined.toImmutable();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i >= 3 && j >= 3;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return BetterToolsRecipeSerializers.PAXEL_RECIPE_SERIALIZER.get();
    }

    public Ingredient pickaxe() {
        return this.pickaxe;
    }
    public Ingredient axe() {
        return this.axe;
    }
    public Ingredient shovel() {
        return this.shovel;
    }
    public Ingredient stick1() {
        return this.stick1;
    }
    public Ingredient stick2() {
        return this.stick2;
    }
    public ItemStack result() {
        return this.result;
    }

    public static class PaxelRecipeSerializer implements RecipeSerializer<PaxelRecipe> {
        public static final MapCodec<PaxelRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("pickaxe").forGetter(PaxelRecipe::pickaxe),
                Ingredient.CODEC_NONEMPTY.fieldOf("axe").forGetter(PaxelRecipe::axe),
                Ingredient.CODEC_NONEMPTY.fieldOf("shovel").forGetter(PaxelRecipe::shovel),
                Ingredient.CODEC_NONEMPTY.fieldOf("stick1").forGetter(PaxelRecipe::stick1),
                Ingredient.CODEC_NONEMPTY.fieldOf("stick2").forGetter(PaxelRecipe::stick2),
                ItemStack.CODEC.fieldOf("result").forGetter(PaxelRecipe::result)
        ).apply(inst, PaxelRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, PaxelRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, PaxelRecipe::pickaxe,
                        Ingredient.CONTENTS_STREAM_CODEC, PaxelRecipe::axe,
                        Ingredient.CONTENTS_STREAM_CODEC, PaxelRecipe::shovel,
                        Ingredient.CONTENTS_STREAM_CODEC, PaxelRecipe::stick1,
                        Ingredient.CONTENTS_STREAM_CODEC, PaxelRecipe::stick2,
                        ItemStack.STREAM_CODEC, PaxelRecipe::result,
                        PaxelRecipe::new);

        @Override
        public @NotNull MapCodec<PaxelRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, PaxelRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}