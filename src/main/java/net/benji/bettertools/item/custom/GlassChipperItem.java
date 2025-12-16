package net.benji.bettertools.item.custom;

import net.benji.bettertools.util.BetterToolsTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class GlassChipperItem extends DiggerItem {
    public GlassChipperItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, BetterToolsTags.Blocks.GLASS_CHIPPER_MINEABLE, properties.attributes(createAttributes(tier, attackDamageModifier, attackSpeedModifier)));
    }
}