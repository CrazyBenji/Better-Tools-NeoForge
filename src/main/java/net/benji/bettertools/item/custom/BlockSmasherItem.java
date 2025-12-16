package net.benji.bettertools.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BlockSmasherItem extends Item {
    public final Supplier<Block> original;
    public final Supplier<Block> replacement;

    public BlockSmasherItem(Supplier<Block> original, Supplier<Block> replacement, Properties properties) {
        super(properties);
        this.original = original;
        this.replacement = replacement;
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
            assert player != null;
            EquipmentSlot equipmentSlot = stack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
            stack.hurtAndBreak(1, player, equipmentSlot);
            player.playSound(SoundEvents.GRAVEL_BREAK);
            if (player.getMainHandItem().getItem().equals(this)) {
                player.swing(InteractionHand.MAIN_HAND);
            }
            else {
                player.swing(InteractionHand.OFF_HAND);
            }
        }

        return super.useOn(context);
    }
}
