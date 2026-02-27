package net.benji.bettertools.item.custom;

import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MacheteItem extends DiggerItem {
    protected final int maxFoliage;
    protected final Set<BlockPos> toBreak;

    public static Component DESC = Component.translatable("desc.bettertools.machete").withStyle(ChatFormatting.BLUE);

    public MacheteItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties, int maxFoliage) {
        super(tier, BetterToolsTags.Blocks.MACHETE_MINEABLE, properties.attributes(createAttributes(tier, attackDamageModifier, attackSpeedModifier))); // Make tag
        this.maxFoliage = maxFoliage;
        this.toBreak = new HashSet<>();
    }

    public MacheteItem(Tier tier, Properties properties, int maxFoliage) {
        this(tier, 2.0F, -2.8F, properties, maxFoliage);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull BlockState blockState, @NotNull BlockPos blockPos, @NotNull LivingEntity livingEntity) {
        if (level instanceof ServerLevel serverLevel && blockState.is(BetterToolsTags.Blocks.MACHETE_MINEABLE)) {
            findConnectedFoliage(serverLevel, blockPos);
            for (BlockPos breakPos : toBreak) {
                BlockState breakState = level.getBlockState(breakPos);

                List<ItemStack> drops = generateLootTable(itemStack, serverLevel, breakState, breakPos, livingEntity);

                level.destroyBlock(breakPos, false);

                for (ItemStack drop : drops) {
                    Block.popResource(level, blockPos, drop);
                }
                EquipmentSlot equipmentSlot = itemStack.equals(livingEntity.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                itemStack.hurtAndBreak(1, livingEntity, equipmentSlot);
            }
            this.toBreak.clear();
        }

        return true;
    }

    private List<ItemStack> generateLootTable(ItemStack itemStack, ServerLevel serverLevel, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        LootParams.Builder lootBuilder = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockPos))
                .withParameter(LootContextParams.TOOL, itemStack)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, livingEntity)
                .withParameter(LootContextParams.BLOCK_STATE, blockState);

        return blockState.getDrops(lootBuilder);
    }

    public void findConnectedFoliage(ServerLevel serverLevel, BlockPos startingPos) {
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> visited = new HashSet<>();

        queue.add(startingPos.immutable());
        visited.add(startingPos.immutable());

        while (!queue.isEmpty() && this.toBreak.size() < this.maxFoliage) {
            BlockPos current = queue.poll();
            this.toBreak.add(current);

            for (BlockPos neighbor : getNeighbors(current)) {
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor.immutable());

                BlockState neighborState = serverLevel.getBlockState(neighbor);

                if (neighborState.is(BetterToolsTags.Blocks.MACHETE_MINEABLE)) {
                    queue.add(neighbor.immutable());
                }
            }
        }
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

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext tooltipContext, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, tooltipContext, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC);
        }
    }
}
