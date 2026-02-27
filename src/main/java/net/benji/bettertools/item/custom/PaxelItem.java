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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class PaxelItem extends DiggerItem {
    public static final Component DESC = Component.translatable("desc.bettertools.paxel").withStyle(ChatFormatting.BLUE);

    public PaxelItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties settings) {
        super(tier, BetterToolsTags.Blocks.PAXEL_MINEABLE, settings.attributes(createAttributes(tier, attackDamageModifier, attackSpeedModifier)));
    }

    public PaxelItem(Tier tier, Properties properties) {
        this(tier, 2.0F, -2.8F, properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);

        // Axe Logic
        if (!playerHasShieldUseIntent(context)) {
            Optional<BlockState> optional = this.evaluateNewBlockState(level, blockPos, player, level.getBlockState(blockPos), context);
            if (optional.isPresent()) {
                ItemStack itemstack = context.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemstack);
                }

                level.setBlock(blockPos, optional.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, optional.get()));
                if (player != null) {
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        // Shovel Logic
        if (context.getClickedFace() != Direction.DOWN) {
            BlockState blockstate1 = blockState.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
            BlockState blockstate2;
            if (blockstate1 != null && level.getBlockState(blockPos.above()).isAir()) {
                level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate1;
            } else if ((blockstate2 = blockState.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false)) != null && !level.isClientSide()) {
                level.levelEvent(null, 1009, blockPos, 0);
            }

            if (blockstate2 != null) {
                if (!level.isClientSide) {
                    level.setBlock(blockPos, blockstate2, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockstate2));
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    private static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
        }
        return false;
    }

    private Optional<BlockState> evaluateNewBlockState(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext p_40529_) {
        Optional<BlockState> optional = Optional.ofNullable(state.getToolModifiedState(p_40529_, ItemAbilities.AXE_STRIP, false));
        if (optional.isPresent()) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return optional;
        } else {
            Optional<BlockState> optional1 = Optional.ofNullable(state.getToolModifiedState(p_40529_, ItemAbilities.AXE_SCRAPE, false));
            if (optional1.isPresent()) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);
                return optional1;
            } else {
                Optional<BlockState> optional2 = Optional.ofNullable(state.getToolModifiedState(p_40529_, ItemAbilities.AXE_WAX_OFF, false));
                if (optional2.isPresent()) {
                    level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.levelEvent(player, 3004, pos, 0);
                    return optional2;
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC);
        }
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility)
                || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility)
                || ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility);
    }
}
