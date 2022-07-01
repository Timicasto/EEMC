package org.quantumstudio.eemod.multiblocks;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.quantumstudio.eemod.api.MultiBlockRegisterEntry;

public class CokeOvenMultiBlock implements MultiBlockRegisterEntry.IMultiBlock {
	public static CokeOvenMultiBlock instance = new CokeOvenMultiBlock();

	static BlockState[][][] structure = new BlockState[5][4][5];
	static {
		BlockState BRICKS = Blocks.BRICKS.defaultBlockState();
		BlockState GLASS = Blocks.GLASS.defaultBlockState();
		BlockState BRICKS_STAIRS = Blocks.BRICK_STAIRS.defaultBlockState();
		BlockState OAK_TRAPDOOR = Blocks.OAK_TRAPDOOR.defaultBlockState();
		for (int x = 0 ; x < 5 ; ++x) {
			for (int y = 0 ; y < 5 ; ++y) {
				for (int z = 0 ; z < 5 ; ++z) {
					if (y == 0) {
						structure[x][y][z] = BRICKS;
					} else if (y == 1) {
						if (z == 0 || z == 4) {
							structure[x][y][z] = BRICKS;
						} else if ((x == 0 && z != 2) || x == 4) {
							structure[x][y][z] = BRICKS;
						} else if (x == 0) {
							structure[x][y][z] = GLASS;
						}
					} else if (y == 2) {
						if (x == 0 || x == 4 || z == 0 || z == 4) {
							structure[x][y][z] = BRICKS;
						} else if (x == 1 || x == 3 || z == 1 || z == 3) {
							structure[x][y][z] = BRICKS_STAIRS;
						}
					} else if (y == 3) {
						if (x == 0 || x == 4 || z == 0 || z == 4) {
							structure[x][y][z] = BRICKS;
						} else if ((x == 1 && z == 1) || (x == 3 && z == 1) || (x == 1 && z == 3) || (x == 3 && z == 3)) {
							structure[x][y][z] = BRICKS;
						} else if (x == 1 || z == 1 || x == 3 || z == 3) {
							structure[x][y][z] = OAK_TRAPDOOR;
						} else {
							structure[x][y][z] = GLASS;
						}
					}

					if (structure[x][y][z] == null) {
						structure[x][y][z] = Blocks.AIR.defaultBlockState();
					}
				}
			}
		}
	}

	@Override
	public ItemStack[][][] getStructureManual() {
		return new ItemStack[0][][];
	}

	@Override
	public ItemStack[] getTotalMaterials() {
		return new ItemStack[0];
	}

	@Override
	public boolean overwriteBlockRenderer(ItemStack stack, int iterator) {
		return false;
	}

	@Override
	public boolean canRenderStructure() {
		return true;
	}

	@Override
	public void renderStructure() {
		ItemStack renderStack = new ItemStack(Items.STONE);
		// Minecraft.getInstance().getItemRenderer().render();
	}

	@Override
	public float getManualScale() {
		return 12;
	}

	@Override
	public String getName() {
		return "eemod:coke_oven";
	}

	@Override
	public boolean isTrigger(BlockState state) {
		return state.getBlock() == Blocks.GLASS;
	}

	@Override
	public boolean createStructure(Level level, BlockPos pos, Direction direction, Player player) {
		if (direction == Direction.UP || direction == Direction.DOWN) {
			direction = direction.getClockWise();
		}
		BlockPos start = pos;
		boolean mirrored = false;
		boolean isValid = check(level, start, direction, mirrored);

		if (!isValid) {
			mirrored = true;
			isValid = check(level, start, direction, mirrored);
		}

		if (isValid) {
			for (int x = 0 ; x < 5 ; ++x) {
				for (int y  = 0 ; y < 4 ; ++y) {
					for (int z = -2 ; z < 2 ; ++z) {
						if (structure[x][y][z + 2].getBlock() == Blocks.AIR) {
							int zz = mirrored ? -z : z;
							BlockPos startx = start.relative(direction, x).relative(direction.getClockWise(), zz).offset(0, y, 0);
//							level.setBlock()
						}
					}
				}
			}
		}
		return true;
	}

	boolean check(Level level, BlockPos pos, Direction direction, boolean mirrored) {
		for (int x = 0 ; x < 5 ; ++x) {
			for (int y = 0 ; y < 4 ; ++y) {
				for (int z = -2 ; z < 2 ; ++z) {
					if (structure[x][y][z + 2].getBlock() != Blocks.AIR) {
						int zz = mirrored ? -z : z;
						BlockPos startx = pos.relative(direction, x).relative(direction.getClockWise(), zz).offset(0, y, 0);
						if (level.getBlockState(startx).getBlock() == Blocks.AIR) {
							return false;
						}
						if (structure[x][y][z + 2].getBlock() != level.getBlockState(startx).getBlock()) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
