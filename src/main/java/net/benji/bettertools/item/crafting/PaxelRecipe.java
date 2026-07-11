package net.benji.bettertools.item.crafting;

import com.mojang.serialization.Codec;
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
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class PaxelRecipe extends ShapedRecipe {
    final ShapedRecipePattern pattern;
    final ItemStack result;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;

    public PaxelRecipe(String string, CraftingBookCategory craftingBookCategory, ShapedRecipePattern shapedRecipePattern, ItemStack itemStack, boolean bl) {
        super(string, craftingBookCategory, shapedRecipePattern, itemStack, bl);
        this.group = string;
        this.category = craftingBookCategory;
        this.pattern = shapedRecipePattern;
        this.result = itemStack;
        this.showNotification = bl;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, @NotNull HolderLookup.Provider provider) {
        super.assemble(craftingInput, provider);
        ItemStack toReturn = result.copy();
        EnchantmentHelper.setEnchantments(toReturn, combineEnchantments(craftingInput));
        return toReturn;
    }

    private ItemEnchantments combineEnchantments(CraftingInput craftingInput) {
        ItemEnchantments.Mutable combined = this.pattern.ingredients().get(0).test(craftingInput.getItem(0))
                ? new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(0)))
                : new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(2)));

        List<ItemEnchantments> enchantmentsToCombine = List.of(
                EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(1)),
                this.pattern.ingredients().get(0).test(craftingInput.getItem(0))
                        ? EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(2))
                        : EnchantmentHelper.getEnchantmentsForCrafting(craftingInput.getItem(0))
        );

        for (ItemEnchantments itemEnchantment : enchantmentsToCombine) {
            for (Holder<Enchantment> enchantment : itemEnchantment.keySet()) {
                Collection<Holder<Enchantment>> enchantmentCollection = combined.keySet();
                if (EnchantmentHelper.isEnchantmentCompatible(enchantmentCollection, enchantment)) {
                    combined.upgrade(enchantment, itemEnchantment.getLevel(enchantment));
                }
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

    public static class PaxelRecipeSerializer implements RecipeSerializer<PaxelRecipe> {
        public static final MapCodec<PaxelRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(shapedRecipe -> shapedRecipe.group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(shapedRecipe -> shapedRecipe.category),
                                ShapedRecipePattern.MAP_CODEC.forGetter(shapedRecipe -> shapedRecipe.pattern),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(shapedRecipe -> shapedRecipe.result),
                                Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(shapedRecipe -> shapedRecipe.showNotification)
                        )
                        .apply(instance, PaxelRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, PaxelRecipe> STREAM_CODEC = StreamCodec.of(
                PaxelRecipeSerializer::toNetwork, PaxelRecipeSerializer::fromNetwork
        );

        @Override
        public @NotNull MapCodec<PaxelRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, PaxelRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static PaxelRecipe fromNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
            String string = registryFriendlyByteBuf.readUtf();
            CraftingBookCategory craftingBookCategory = registryFriendlyByteBuf.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern shapedRecipePattern = ShapedRecipePattern.STREAM_CODEC.decode(registryFriendlyByteBuf);
            ItemStack itemStack = ItemStack.STREAM_CODEC.decode(registryFriendlyByteBuf);
            boolean bl = registryFriendlyByteBuf.readBoolean();
            return new PaxelRecipe(string, craftingBookCategory, shapedRecipePattern, itemStack, bl);
        }

        private static void toNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf, PaxelRecipe shapedRecipe) {
            registryFriendlyByteBuf.writeUtf(shapedRecipe.group);
            registryFriendlyByteBuf.writeEnum(shapedRecipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(registryFriendlyByteBuf, shapedRecipe.pattern);
            ItemStack.STREAM_CODEC.encode(registryFriendlyByteBuf, shapedRecipe.result);
            registryFriendlyByteBuf.writeBoolean(shapedRecipe.showNotification);
        }
    }
}