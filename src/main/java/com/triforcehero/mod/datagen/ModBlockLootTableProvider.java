package com.triforcehero.mod.datagen;

import com.triforcehero.mod.block.ModBlocks;
import com.triforcehero.mod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.LEAD_BLOCK.get());
        dropSelf(ModBlocks.MAGCHUNK_BLOCK.get());
        dropSelf(ModBlocks.PEDESTAL.get());
        dropSelf(ModBlocks.CRUSHER.get());


        this.add(ModBlocks.LEAD_ORE.get(),
                block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAWLEAD.get()));
        this.add(ModBlocks.MAGNESIUM_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.MAGNESIUM_ORE.get(), ModItems.MAGNESIUM.get(), 4, 8));
        this.add(ModBlocks.QUARTZITE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.QUARTZITE_ORE.get(), ModItems.QUARTZITECHUNK.get(), 1, 3));
        this.add(ModBlocks.LEAD_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.LEAD_ORE.get(), ModItems.RAWLEAD.get(), 4, 8));
        this.add(ModBlocks.DEEPSLATE_PENTLANDITE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_PENTLANDITE_ORE.get(), ModItems.QUARTZITECHUNK.get(), 4, 8));




    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}