package net.benji.bettertools.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class BlockSmasherItem extends Item {
    public final Supplier<Block> original;
    public final Supplier<Block> replacement;

    public final Component originalName;
    public static final Component DESC_USE_ON = Component.translatable("desc.bettertools.block_smasher");

    public BlockSmasherItem(Supplier<Block> original, Supplier<Block> replacement, Properties properties) {
        super(properties);
        this.original = original;
        this.replacement = replacement;
        originalName = Component.translatable(original.get().getDescriptionId()).withStyle(ChatFormatting.BLUE);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();

        if (level.getBlockState(clickedPos).is(this.original.get()) &&
                (clickedPos.getY() != 0 && clickedPos.getY() != -64)) {
            level.setBlock(clickedPos, this.replacement.get().defaultBlockState(), 3);
            if (player != null) {
                EquipmentSlot equipmentSlot = stack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                stack.hurtAndBreak(1, player, equipmentSlot);
                player.playSound(SoundEvents.GRAVEL_BREAK);
                player.swing(context.getHand());
            }
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC_USE_ON);
            tooltipComponents.add(CommonComponents.space().append(originalName));
        }
    }
}
