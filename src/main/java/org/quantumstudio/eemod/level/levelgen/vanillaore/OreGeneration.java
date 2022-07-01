package org.quantumstudio.eemod.level.levelgen.vanillaore;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.fml.ModLoader;
import org.quantumstudio.eemod.registry.RegistryBlocks;

import java.util.List;

public class OreGeneration {
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> CHROMITE_FEATURE;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> PYRITE_FEATURE;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> SPHALERITE_FEATURE;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> CHLORARGYRITE_FEATURE;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> CASSITERITE_FEATURE;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> BAUXITE_FEATURE;

	public static Holder<PlacedFeature> CHROMITE;
	public static Holder<PlacedFeature> PYRITE;
	public static Holder<PlacedFeature> SPHALERITE;
	public static Holder<PlacedFeature> CHLORARGYRITE;
	public static Holder<PlacedFeature> CASSITERITE;
	public static Holder<PlacedFeature> BAUXITE;

	public static void initGenerations() {
		if (ModLoader.isLoadingStateValid()) {
			CHROMITE_FEATURE = FeatureUtils.register("eemod:chromite", Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, RegistryBlocks.CHROMITE.defaultBlockState(), 6));
			PYRITE_FEATURE = FeatureUtils.register("eemod:pyrite", Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, RegistryBlocks.PYRITE.defaultBlockState(), 8));
			SPHALERITE_FEATURE = FeatureUtils.register("eemod:sphalerite", Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, RegistryBlocks.SPHALERITE.defaultBlockState(), 8));
			CHLORARGYRITE_FEATURE = FeatureUtils.register("eemod:chlorargyrite", Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, RegistryBlocks.CHLORARGYRITE.defaultBlockState(), 5));
			CASSITERITE_FEATURE = FeatureUtils.register("eemod:cassiterite", Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, RegistryBlocks.CASSITERITE.defaultBlockState(), 6));
			BAUXITE_FEATURE = FeatureUtils.register("eemod:bauxite", Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, RegistryBlocks.BAUXITE.defaultBlockState(), 12));

			CHROMITE = PlacementUtils.register("eemod:chromite", CHROMITE_FEATURE, List.of(CountPlacement.of(15), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(16), VerticalAnchor.belowTop(57))));
			PYRITE = PlacementUtils.register("eemod:pyrite", CHROMITE_FEATURE, List.of(CountPlacement.of(12), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(50))));
			SPHALERITE = PlacementUtils.register("eemod:sphalerite", CHROMITE_FEATURE, List.of(CountPlacement.of(24), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(22), VerticalAnchor.belowTop(36))));
			CHLORARGYRITE = PlacementUtils.register("eemod:chlorargyrite", CHROMITE_FEATURE, List.of(CountPlacement.of(20), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(19), VerticalAnchor.belowTop(47))));
			CASSITERITE = PlacementUtils.register("eemod:cassiterite", CHROMITE_FEATURE, List.of(CountPlacement.of(18), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(22), VerticalAnchor.belowTop(39))));
			BAUXITE = PlacementUtils.register("eemod:bauxite", CHROMITE_FEATURE, List.of(CountPlacement.of(18), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(8), VerticalAnchor.belowTop(32))));
		}
	}


}
