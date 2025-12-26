package net.benji.bettertools.item.custom;

import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class PaxelItem extends Item {
    public static final Component DESC = Component.translatable("desc.bettertools.paxel").withStyle(ChatFormatting.BLUE);

    public PaxelItem(ToolMaterial toolMaterial, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(properties.tool(toolMaterial, BetterToolsTags.Blocks.PAXEL_MINEABLE, attackDamageModifier, attackSpeedModifier, 0));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);
        ItemStack stack = context.getItemInHand();

        // Axe Logic
        Optional<BlockState> optional = this.getStripped(blockState);
        Optional<BlockState> optional2 = Optional.ofNullable(blockState.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
        Optional<BlockState> optional3 = Optional.ofNullable(blockState.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
        ItemStack itemStack = context.getItemInHand();
        Optional<BlockState> optional4 = Optional.empty();
        if (optional.isPresent()) {
            level.playSound(player, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            optional4 = optional;
        } else if (optional2.isPresent()) {
            level.playSound(player, blockPos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3005, blockPos, 0);
            optional4 = optional2;
        } else if (optional3.isPresent()) {
            level.playSound(player, blockPos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3004, blockPos, 0);
            optional4 = optional3;
        }

        if (optional4.isPresent()) {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
            }

            level.setBlock(blockPos, optional4.get(), 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, optional4.get()));
            if (player != null) {
                EquipmentSlot equipmentSlot = stack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                itemStack.hurtAndBreak(1, player, equipmentSlot);
            }

            return InteractionResult.SUCCESS;
        }

        // Shovel Logic
        if (context.getClickedFace() != Direction.DOWN) {
            BlockState blockState2 = getFlattened(blockState);
            BlockState blockState3 = null;
            if (blockState2 != null && level.getBlockState(blockPos.above()).isAir()) {
                level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockState3 = blockState2;
            } else if (blockState.getBlock() instanceof CampfireBlock && blockState.getValue(CampfireBlock.LIT)) {
                if (!level.isClientSide()) {
                    level.levelEvent(null, 1009, blockPos, 0);
                }

                CampfireBlock.dowse(context.getPlayer(), level, blockPos, blockState);
                blockState3 = blockState.setValue(CampfireBlock.LIT, Boolean.FALSE);
            }

            if (blockState3 != null) {
                if (!level.isClientSide()) {
                    level.setBlock(blockPos, blockState3, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState3));
                    if (player != null) {
                        EquipmentSlot equipmentSlot = stack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                        context.getItemInHand().hurtAndBreak(1, player, equipmentSlot);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private Optional<BlockState> getStripped(BlockState unstrippedState) {
        return Optional.ofNullable(AxeItem.getAxeStrippingState(unstrippedState));
    }

    private BlockState getFlattened(BlockState unflattenedState) {
        return ShovelItem.getShovelPathingState(unflattenedState);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull TooltipDisplay tooltipDisplay, @NotNull Consumer<Component> tooltipAdder, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        if (flag.isAdvanced()) {
            tooltipAdder.accept(CommonComponents.EMPTY);
            tooltipAdder.accept(DESC);
        }
    }
}
