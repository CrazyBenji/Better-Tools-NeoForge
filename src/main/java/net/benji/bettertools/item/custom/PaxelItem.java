package net.benji.bettertools.item.custom;

import com.google.common.collect.BiMap;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class PaxelItem extends DiggerItem {
    public static final Component DESC = Component.translatable("desc.bettertools.paxel").withStyle(ChatFormatting.BLUE);

    public PaxelItem(Tier material, float attackDamage, float attackSpeed, Properties settings) {
        super(attackDamage, attackSpeed, material, BetterToolsTags.Blocks.PAXEL_MINEABLE, settings);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);

        // Axe Logic
        Optional<BlockState> optional = this.getStripped(blockState);
        Optional<BlockState> optional2 = WeatheringCopper.getPrevious(blockState);
        Optional<BlockState> optional3 = Optional.ofNullable((Block)((BiMap<?, ?>)HoneycombItem.WAX_OFF_BY_BLOCK.get()).get(blockState.getBlock()))
                .map(block -> block.withPropertiesOf(blockState));
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
                itemStack.hurtAndBreak(1, player, playerx -> playerx.broadcastBreakEvent(context.getHand()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        // Shovel Logic
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
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
                if (!level.isClientSide) {
                    level.setBlock(blockPos, blockState3, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState3));
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, playerx -> playerx.broadcastBreakEvent(context.getHand()));
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }

    }

    private Optional<BlockState> getStripped(BlockState unstrippedState) {
        return Optional.ofNullable(AxeItem.getAxeStrippingState(unstrippedState));
    }

    private BlockState getFlattened(BlockState unflattenedState) {
        return ShovelItem.getShovelPathingState(unflattenedState);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC);
        }
    }
}
