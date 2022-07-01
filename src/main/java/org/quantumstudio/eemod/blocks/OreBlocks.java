package org.quantumstudio.eemod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class OreBlocks extends Block {


	public OreBlocks(float strength) {
		super(Properties.of(Material.STONE).strength(strength));
	}

}
