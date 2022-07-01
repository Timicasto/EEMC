package org.quantumstudio.eemod;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.redstone.Redstone;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.quantumstudio.eemod.level.levelgen.vanillaore.OreGeneration;

@Mod(ModConstants.MODID)
public class EEMod {

	public EEMod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(EEMod::setup);
		MinecraftForge.EVENT_BUS.addListener(EEMod::registerFeatures);
		Redstone.SIGNAL_MAX = 999999999;
	}

	public static void setup(FMLCommonSetupEvent e) {
		e.enqueueWork(OreGeneration::initGenerations);
	}

	public static void registerFeatures(BiomeLoadingEvent e) {
		e.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.CHLORARGYRITE);
		e.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.BAUXITE);
		e.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.CASSITERITE);
		e.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.PYRITE);
		e.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.SPHALERITE);
		e.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.CHROMITE);
	}
}
