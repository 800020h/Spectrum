package de.dafuqs.spectrum.worldgen;

import com.google.common.collect.ImmutableList;
import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.blocks.SpectrumBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class SpectrumFeatures {

    public static ConfiguredFeature<?, ?> CITRINE_GEODE;
    public static ConfiguredFeature<?, ?> TOPAZ_GEODE;
    public static ConfiguredFeature<?, ?> MOONSTONE_GEODE;

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }
    
    public static void register() {
        BlockState AIR = Blocks.AIR.getDefaultState();
        BlockState CALCITE = Blocks.CALCITE.getDefaultState();
        BlockState TUFF = Blocks.TUFF.getDefaultState();
        
        BlockState CITRINE_BLOCK = SpectrumBlocks.CITRINE_BLOCK.getDefaultState();
        BlockState BUDDING_CITRINE = SpectrumBlocks.BUDDING_CITRINE.getDefaultState();
        BlockState SMALL_CITRINE_BUD = SpectrumBlocks.SMALL_CITRINE_BUD.getDefaultState();
        BlockState MEDIUM_CITRINE_BUD = SpectrumBlocks.MEDIUM_CITRINE_BUD.getDefaultState();
        BlockState LARGE_CITRINE_BUD = SpectrumBlocks.LARGE_CITRINE_BUD.getDefaultState();
        BlockState CITRINE_CLUSTER = SpectrumBlocks.CITRINE_CLUSTER.getDefaultState();
        
        BlockState TOPAZ_BLOCK = SpectrumBlocks.TOPAZ_BLOCK.getDefaultState();
        BlockState BUDDING_TOPAZ = SpectrumBlocks.BUDDING_TOPAZ.getDefaultState();
        BlockState SMALL_TOPAZ_BUD = SpectrumBlocks.SMALL_TOPAZ_BUD.getDefaultState();
        BlockState MEDIUM_TOPAZ_BUD = SpectrumBlocks.MEDIUM_TOPAZ_BUD.getDefaultState();
        BlockState LARGE_TOPAZ_BUD = SpectrumBlocks.LARGE_TOPAZ_BUD.getDefaultState();
        BlockState TOPAZ_CLUSTER = SpectrumBlocks.TOPAZ_CLUSTER.getDefaultState();  
        
        BlockState MOONSTONE_BLOCK = SpectrumBlocks.MOONSTONE_BLOCK.getDefaultState();
        BlockState BUDDING_MOONSTONE = SpectrumBlocks.BUDDING_MOONSTONE.getDefaultState();
        BlockState SMALL_MOONSTONE_BUD = SpectrumBlocks.SMALL_MOONSTONE_BUD.getDefaultState();
        BlockState MEDIUM_MOONSTONE_BUD = SpectrumBlocks.MEDIUM_MOONSTONE_BUD.getDefaultState();
        BlockState LARGE_MOONSTONE_BUD = SpectrumBlocks.LARGE_MOONSTONE_BUD.getDefaultState();
        BlockState MOONSTONE_CLUSTER = SpectrumBlocks.MOONSTONE_CLUSTER.getDefaultState();
        
        CITRINE_GEODE   = (Feature.GEODE.configure(new GeodeFeatureConfig(
                        new GeodeLayerConfig(
                                new SimpleBlockStateProvider(AIR),
                                new SimpleBlockStateProvider(CITRINE_BLOCK),
                                new SimpleBlockStateProvider(BUDDING_CITRINE),
                                new SimpleBlockStateProvider(CALCITE),
                                new SimpleBlockStateProvider(TUFF),
                                ImmutableList.of(SMALL_CITRINE_BUD, MEDIUM_CITRINE_BUD, LARGE_CITRINE_BUD, CITRINE_CLUSTER)),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2), 0.35D, 0.083D, true, 4, 7, 3, 5, 1, 3, -16, 16, 0.05D))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(6, 0, 47))).spreadHorizontally()).applyChance(48);
        
        TOPAZ_GEODE = (Feature.GEODE.configure(new GeodeFeatureConfig(
                        new GeodeLayerConfig(
                                new SimpleBlockStateProvider(AIR),
                                new SimpleBlockStateProvider(TOPAZ_BLOCK),
                                new SimpleBlockStateProvider(BUDDING_TOPAZ),
                                new SimpleBlockStateProvider(CALCITE),
                                new SimpleBlockStateProvider(TUFF),
                                ImmutableList.of(SMALL_TOPAZ_BUD, MEDIUM_TOPAZ_BUD, LARGE_TOPAZ_BUD, TOPAZ_CLUSTER)),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2), 0.35D, 0.083D, true, 4, 7, 3, 5, 1, 3, -16, 16, 0.05D))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(6, 0, 47))).spreadHorizontally()).applyChance(48);
        
        MOONSTONE_GEODE = (Feature.GEODE.configure(new GeodeFeatureConfig(
                        new GeodeLayerConfig(
                                new SimpleBlockStateProvider(AIR),
                                new SimpleBlockStateProvider(MOONSTONE_BLOCK),
                                new SimpleBlockStateProvider(BUDDING_MOONSTONE),
                                new SimpleBlockStateProvider(CALCITE),
                                new SimpleBlockStateProvider(TUFF),
                                ImmutableList.of(SMALL_MOONSTONE_BUD, MEDIUM_MOONSTONE_BUD, LARGE_MOONSTONE_BUD, MOONSTONE_CLUSTER)),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2), 0.35D, 0.083D, true, 4, 7, 3, 5, 1, 3, -16, 16, 0.05D))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(6, 0, 47))).spreadHorizontally()).applyChance(48);

        RegistryKey<ConfiguredFeature<?, ?>> CITRINE_GEODE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(SpectrumCommon.MOD_ID, "citrine_geode"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, CITRINE_GEODE_KEY.getValue(), CITRINE_GEODE);
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_STRUCTURES, CITRINE_GEODE_KEY);

        RegistryKey<ConfiguredFeature<?, ?>> TOPAZ_GEODE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(SpectrumCommon.MOD_ID, "topaz_geode"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, TOPAZ_GEODE_KEY.getValue(), TOPAZ_GEODE);
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_STRUCTURES, TOPAZ_GEODE_KEY);

        RegistryKey<ConfiguredFeature<?, ?>> MOONSTONE_GEODE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(SpectrumCommon.MOD_ID, "moonstone_geode"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, MOONSTONE_GEODE_KEY.getValue(), MOONSTONE_GEODE);
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_STRUCTURES, MOONSTONE_GEODE_KEY);




    }



}
