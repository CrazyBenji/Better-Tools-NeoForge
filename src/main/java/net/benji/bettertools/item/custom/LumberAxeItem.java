package net.benji.bettertools.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LumberAxeItem extends AxeItem {
    private final int maxLogs;
    private Set<BlockPos> toBreak;

    public LumberAxeItem(Tier tier, Properties properties, int maxLogs) {
        super(tier, 6.0F, -3.2F, properties);
        this.maxLogs = maxLogs;
        this.toBreak = new HashSet<>();
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity player) {

        if (!level.isClientSide && level instanceof ServerLevel server && state.is(BlockTags.LOGS)) {

            breakConnectedLogs(server, pos);
            for (BlockPos breakPos : toBreak) {
                level.destroyBlock(breakPos, true);
                stack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(player.getUsedItemHand()));
            }
            this.toBreak.clear();
        }

        return super.mineBlock(stack, level, state, pos, player);
    }

    private void breakConnectedLogs(ServerLevel level, BlockPos startPos) {
        if (this.toBreak.size() >= maxLogs) {
            return;
        }
        ArrayList<BlockPos> toCheck = populateArrayList(startPos);

        for (BlockPos pos : toCheck) {
            if (!this.toBreak.contains(pos) && level.getBlockState(pos).is(BlockTags.LOGS)) {
                this.toBreak.add(pos);
                this.breakConnectedLogs(level, pos);
            }
        }
    }

    private ArrayList<BlockPos> populateArrayList(BlockPos pos) {
        ArrayList<BlockPos> list = new ArrayList<>();
        list.add(pos.east());
        list.add(pos.east().north());
        list.add(pos.west());
        list.add(pos.west().south());
        list.add(pos.north());
        list.add(pos.north().west());
        list.add(pos.south());
        list.add(pos.south().east());

        list.add(pos.above());
        list.add(pos.east().above());
        list.add(pos.east().north().above());
        list.add(pos.west().above());
        list.add(pos.west().south().above());
        list.add(pos.north().above());
        list.add(pos.north().west().above());
        list.add(pos.south().above());
        list.add(pos.south().east().above());

        return list;
    }

}
