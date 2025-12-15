package net.benji.bettertools.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ScytheItem extends HoeItem {

    public ScytheItem(Tier material, int attackDamage, float attackSpeed, Properties settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (!world.isClientSide && world instanceof ServerLevel) {
            // Get all positions in 3x3 area around the clicked position
            List<BlockPos> positionsToHoe = get3x3Positions(pos);

            boolean anyBlockHoed = false;

            // Attempt to hoe each block in the area
            for (BlockPos targetPos : positionsToHoe) {
                if (canHoeBlock(world, targetPos)) {
                    BlockState currentState = world.getBlockState(targetPos);
                    BlockState hoedState = getHoedState(currentState);

                    if (hoedState != null && world.getBlockState(targetPos.above()).isAir()) {
                        // Set the hoed block state
                        world.setBlock(targetPos, hoedState, 11);

                        // Play hoe sound
                        world.playSound(null, targetPos, SoundEvents.HOE_TILL,
                                SoundSource.BLOCKS, 1.0F, 1.0F);

                        anyBlockHoed = true;
                    }
                }
            }

            if (anyBlockHoed) {
                // Damage the tool once for the original block
                assert player != null;
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
                return InteractionResult.SUCCESS;
            }
        }

        // Fall back to default hoe behavior if no custom hoeing happened
        return super.useOn(context);
    }

    private List<BlockPos> get3x3Positions(BlockPos center) {
        List<BlockPos> positions = new ArrayList<>();

        // Create 3x3 area on the same Y level (horizontal plane)
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                positions.add(center.offset(x, 0, z));
            }
        }

        return positions;
    }

    private boolean canHoeBlock(Level world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        // Check if the block can be hoed (dirt, grass, coarse dirt, etc.)
        return block == Blocks.DIRT ||
                block == Blocks.GRASS_BLOCK ||
                block == Blocks.COARSE_DIRT ||
                block == Blocks.PODZOL ||
                block == Blocks.MYCELIUM ||
                block == Blocks.ROOTED_DIRT;
    }

    private BlockState getHoedState(BlockState currentState) {
        Block currentBlock = currentState.getBlock();

        // Convert blocks to their hoed equivalents
        if (currentBlock == Blocks.DIRT ||
                currentBlock == Blocks.GRASS_BLOCK ||
                currentBlock == Blocks.COARSE_DIRT ||
                currentBlock == Blocks.PODZOL ||
                currentBlock == Blocks.MYCELIUM ||
                currentBlock == Blocks.ROOTED_DIRT) {
            return Blocks.FARMLAND.defaultBlockState();
        }

        return null; // Block cannot be hoed
    }
}
