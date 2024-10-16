
package com.triforcehero.mod.datagen;

import com.triforcehero.mod.Mod;
import com.triforcehero.mod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Mod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.MAGCHUNK_BLOCK.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.LEAD_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MAGCHUNK_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_ORE.get());





        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LEAD_DEEPSLATE_ORE.get())
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.LEAD_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL);
    }
}
