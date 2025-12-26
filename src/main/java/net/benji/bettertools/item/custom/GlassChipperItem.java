package net.benji.bettertools.item.custom;

import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GlassChipperItem extends DiggerItem {
    public static final Component DESC = Component.translatable("desc.bettertools.glass_chipper").withStyle(ChatFormatting.BLUE);

    public GlassChipperItem(ToolMaterial toolMaterial, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(toolMaterial, BetterToolsTags.Blocks.GLASS_CHIPPER_MINEABLE, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC);
        }
    }
}