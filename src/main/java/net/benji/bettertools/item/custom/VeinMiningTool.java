package net.benji.bettertools.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public interface VeinMiningTool {
    default Set<BlockPos> findConnectedBlocks(ServerLevel serverLevel, BlockPos startingPos, int maxBlocks, TagKey<Block> blockType) {
        Set<BlockPos> toBreak = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = new HashSet<>();

        queue.add(startingPos.immutable());
        visited.add(startingPos.immutable());

        while (!queue.isEmpty() && toBreak.size() < maxBlocks) {
            BlockPos current = queue.poll();
            toBreak.add(current);

            for (BlockPos neighbor : getNeighbors(current)) {
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor.immutable());

                BlockState neighborState = serverLevel.getBlockState(neighbor);

                if (neighborState.is(blockType)) {
                    queue.add(neighbor.immutable());
                }
            }
        }

        return toBreak;
    }

    private List<BlockPos> getNeighbors(BlockPos center) {
        List<BlockPos> neighbors = new ArrayList<>();
        int radius = 1;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    neighbors.add(center.offset(x, y, z));
                }
            }
        }

        return neighbors;
    }

    default List<ItemStack> generateLootTable(ItemStack itemStack, ServerLevel serverLevel, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        LootParams.Builder lootBuilder = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockPos))
                .withParameter(LootContextParams.TOOL, itemStack)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, livingEntity)
                .withParameter(LootContextParams.BLOCK_STATE, blockState);

        return blockState.getDrops(lootBuilder);
    }

}
