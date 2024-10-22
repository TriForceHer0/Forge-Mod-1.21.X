package com.triforcehero.mod.worldgen;

import com.triforcehero.mod.Mod;
import com.triforcehero.mod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_ORE_KEY = registerKey("lead_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MAGNESIUM_ORE_KEY = registerKey("magnesium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_QUARTZITE_ORE_KEY = registerKey("quartzite_block");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldLeadOres = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.LEAD_ORE.get().defaultBlockState())







        );
        List<OreConfiguration.TargetBlockState> overworldMagnesiumOres = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.MAGNESIUM_ORE.get().defaultBlockState())






        );
        List<OreConfiguration.TargetBlockState> overworldQuartziteOres = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.QUARTZITE_ORE.get().defaultBlockState())






        );






        register(context, OVERWORLD_LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLeadOres, 5));
        register(context, OVERWORLD_MAGNESIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMagnesiumOres, 8));
        register(context, OVERWORLD_QUARTZITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldQuartziteOres, 18));

    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,  ResourceLocation.fromNamespaceAndPath(Mod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}