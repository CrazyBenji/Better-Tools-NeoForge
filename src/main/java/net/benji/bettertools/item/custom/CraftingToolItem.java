package net.benji.bettertools.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CraftingToolItem extends Item {
    protected final @Nullable Component desc;

    public CraftingToolItem(Properties properties, @Nullable String tooltipLocation) {
        super(properties);

        if (tooltipLocation != null) {
            desc = Component.translatable(tooltipLocation).withStyle(ChatFormatting.BLUE);
        }
        else {
            desc = null;
        }
    }

    @Override
    public @NotNull ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack remainder = stack.copy();

        int damage = remainder.getDamageValue() + 1;

        if (damage >= remainder.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        remainder.setDamageValue(damage);
        return remainder;
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (this.desc != null) {
            if (tooltipFlag.isAdvanced()) {
                tooltipComponents.add(CommonComponents.EMPTY);
                tooltipComponents.add(this.desc);
            }
        }
    }
}