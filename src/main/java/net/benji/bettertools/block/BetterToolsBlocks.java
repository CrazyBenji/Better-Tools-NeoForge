package net.benji.bettertools.block;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BetterToolsBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BetterToolsNeoforge.MOD_ID);

    public static final DeferredBlock<Block> SMASHED_BEDROCK = BLOCKS.registerSimpleBlock(
            "smashed_bedrock",
            BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
    );
    public static final DeferredItem<BlockItem> SMASHED_BEDROCK_BLOCK_ITEM = BetterToolsItems.ITEMS.registerSimpleBlockItem(SMASHED_BEDROCK);

    public static void registerBlocks(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering blocks for " + BetterToolsNeoforge.MOD_ID);
        BLOCKS.register(modEventBus);
    }
}
