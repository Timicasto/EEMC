package org.quantumstudio.eemod.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.quantumstudio.eemod.ModConstants;
import org.quantumstudio.eemod.blocks.DiodeBlock;
import org.quantumstudio.eemod.blocks.OreBlocks;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = ModConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registrations {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> e) {
		final IForgeRegistry<Block> register = e.getRegistry();
		register.registerAll(
				new OreBlocks(3F).setRegistryName("chromite"),
				new OreBlocks(1.5F).setRegistryName("pyrite"),
				new OreBlocks(2.8F).setRegistryName("sphalerite"),
				new OreBlocks(1.8F).setRegistryName("chlorargyrite"),
				new OreBlocks(3F).setRegistryName("cassiterite"),
				new OreBlocks(1.2F).setRegistryName("bauxite"),
				new DiodeBlock().setRegistryName("diode")
		);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e) {
		final IForgeRegistry<Item> register = e.getRegistry();
		Item.Properties prop = new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS);
		register.registerAll(
				new BlockItem(RegistryBlocks.BAUXITE, prop).setRegistryName("bauxite"),
				new BlockItem(RegistryBlocks.CASSITERITE, prop).setRegistryName("cassiterite"),
				new BlockItem(RegistryBlocks.CHLORARGYRITE, prop).setRegistryName("chlorargyrite"),
				new BlockItem(RegistryBlocks.PYRITE, prop).setRegistryName("pyrite"),
				new BlockItem(RegistryBlocks.CHROMITE, prop).setRegistryName("chromite"),
				new BlockItem(RegistryBlocks.SPHALERITE, prop).setRegistryName("sphalerite"),
				new BlockItem(RegistryBlocks.DIODE, prop).setRegistryName("diode")
		);
	}
}
