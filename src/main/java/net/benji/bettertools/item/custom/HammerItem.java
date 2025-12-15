package net.benji.bettertools.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends PickaxeItem {

    public HammerItem(Tier material, int attackDamage, float attackSpeed, Properties settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity player) {
        if (!level.isClientSide && level instanceof ServerLevel) {
            BlockHitResult hitResult = player.level().clip(new ClipContext(player.getEyePosition(1F),
                    (player.getEyePosition(1f).add(player.getViewVector(1F).scale(6F))),
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
            Direction hitSide = hitResult.getDirection();

            List<BlockPos> positionsToBreak = get3x3Positions(pos, hitSide);

            for (BlockPos targetPos : positionsToBreak) {
                if (!targetPos.equals(pos)) {
                    BlockState targetState = level.getBlockState(targetPos);

                    if (canBreakBlock(targetState, stack, level, pos)) {
                        LootParams.Builder lootBuilder = new LootParams.Builder((ServerLevel)level)
                                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(targetPos))
                                .withParameter(LootContextParams.TOOL, stack)
                                .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                                .withParameter(LootContextParams.BLOCK_STATE, targetState);
                        List<ItemStack> drops = targetState.getDrops(lootBuilder);

                        level.destroyBlock(targetPos, false);

                        for (ItemStack drop : drops) {
                            Block.popResource(level, targetPos, drop);
                        }
                    }
                }
            }
        }

        return super.mineBlock(stack, level, state, pos, player);
    }

    protected List<BlockPos> get3x3Positions(BlockPos center, Direction hitSide) {
        List<BlockPos> positions = new ArrayList<>();

        switch (hitSide) {
            case UP, DOWN -> {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        positions.add(center.offset(x, 0, z));
                    }
                }
            }
            case NORTH, SOUTH -> {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        positions.add(center.offset(x, y, 0));
                    }
                }
            }
            case EAST, WEST -> {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        positions.add(center.offset(0, y, z));
                    }
                }
            }
        }

        return positions;
    }

    protected boolean canBreakBlock(BlockState state, ItemStack stack, Level level, BlockPos pos) {
        if (state.isAir() || state.getDestroySpeed(level, pos) < 0) {
            return false;
        }

        return stack.isCorrectToolForDrops(state);
    }
}
