package net.benji.bettertools.item.custom;

import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GlassChipperItem extends Item {
    public static final Component DESC = Component.translatable("desc.bettertools.glass_chipper").withStyle(ChatFormatting.BLUE);

    public GlassChipperItem(ToolMaterial toolMaterial, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(properties.tool(toolMaterial, BetterToolsTags.Blocks.GLASS_CHIPPER_MINEABLE, attackDamageModifier, attackSpeedModifier, 0));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull TooltipDisplay tooltipDisplay, @NotNull Consumer<Component> tooltipAdder, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        if (flag.isAdvanced()) {
            tooltipAdder.accept(CommonComponents.EMPTY);
            tooltipAdder.accept(DESC);
        }
    }
}