package net.benji.bettertools.item.custom;

import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class LumberAxeItem extends AxeItem implements VeinMiningTool {
    protected final int maxBlocks;

    public static final Component DESC = Component.translatable("desc.bettertools.lumber_axe").withStyle(ChatFormatting.BLUE);

    public LumberAxeItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties, int maxBlocks) {
        super(tier,  properties.attributes(createAttributes(tier, attackDamageModifier, attackSpeedModifier)));
        this.maxBlocks = maxBlocks;
    }

    public LumberAxeItem(Tier tier, Properties properties, int maxBlocks) {
        this(tier, 6.0F, -3.1F, properties, maxBlocks);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos blockPos, @NotNull LivingEntity livingEntity) {
        if (level instanceof ServerLevel serverLevel && state.is(BetterToolsTags.Blocks.LUMBER_AXE_VEIN_MINES)) {
            Set<BlockPos> toBreak = findConnectedBlocks(serverLevel, blockPos, this.maxBlocks, BetterToolsTags.Blocks.LUMBER_AXE_VEIN_MINES);
            for (BlockPos breakPos : toBreak) {
                BlockState breakState = serverLevel.getBlockState(breakPos);

                List<ItemStack> drops = generateLootTable(itemStack, serverLevel, breakState, breakPos, livingEntity);

                serverLevel.destroyBlock(breakPos, false);

                for (ItemStack drop : drops) {
                    Block.popResource(serverLevel, blockPos, drop);
                }
                EquipmentSlot equipmentSlot = itemStack.equals(livingEntity.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                itemStack.hurtAndBreak(1, livingEntity, equipmentSlot);
            }
            toBreak.clear();
        }

        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (tooltipFlag.isAdvanced()) {
            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(DESC);
        }
    }
}