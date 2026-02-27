package net.benji.bettertools.block;

import net.benji.bettertools.BetterToolsNeoforge;
import net.benji.bettertools.item.BetterToolsItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class BetterToolsBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BetterToolsNeoforge.MOD_ID);

    public static final DeferredBlock<Block> SMASHED_BEDROCK = registerBlockWithItem(
            "smashed_bedrock",
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)
    );

    public static DeferredBlock<Block> registerBlockWithItem(String key, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> toReturn = BLOCKS.registerBlock(key, blockFactory, properties);
        BetterToolsItems.ITEMS.registerSimpleBlockItem(toReturn);
        return toReturn;
    }

    public static void registerBlocks(IEventBus modEventBus) {
        BetterToolsNeoforge.LOGGER.info("Registering blocks for " + BetterToolsNeoforge.MOD_ID);
        BLOCKS.register(modEventBus);
    }
}
