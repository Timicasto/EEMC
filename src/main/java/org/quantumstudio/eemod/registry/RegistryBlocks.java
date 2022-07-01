package org.quantumstudio.eemod.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ObjectHolder;
import org.quantumstudio.eemod.ModConstants;
import org.quantumstudio.eemod.blocks.OreBlocks;

@ObjectHolder(ModConstants.MODID)
public class RegistryBlocks {
	// START ORES
	@ObjectHolder("chromite")
	public static final Block CHROMITE = Blocks.AIR;
	@ObjectHolder("pyrite")
	public static final Block PYRITE = Blocks.AIR;
	@ObjectHolder("sphalerite")
	public static final Block SPHALERITE = Blocks.AIR;
	@ObjectHolder("chlorargyrite")
	public static final Block CHLORARGYRITE = Blocks.AIR;
	@ObjectHolder("cassiterite")
	public static final Block CASSITERITE = Blocks.AIR;
	@ObjectHolder("bauxite")
	public static final Block BAUXITE = Blocks.AIR;
	// STOP ORES

	public static final Block DIODE = Blocks.AIR;

	// public static final Block MULTIBLOCK_MACHINE = Blocks.AIR;
}
