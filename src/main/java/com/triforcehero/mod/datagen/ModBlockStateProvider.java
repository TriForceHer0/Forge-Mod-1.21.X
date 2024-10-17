package com.triforcehero.mod.datagen;

import com.triforcehero.mod.Mod;
import com.triforcehero.mod.block.ModBlocks;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Mod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.MAGCHUNK_BLOCK);

        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.LEAD_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.MAGNESIUM_ORE);
        blockWithItem(ModBlocks.QUARTZITE_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}