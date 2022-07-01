package org.quantumstudio.eemod.multiblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.HitResult;
import org.quantumstudio.eemod.api.IEnumPropertyBlock;
import org.quantumstudio.eemod.api.StatePropertiesHandler;
import org.quantumstudio.eemod.entities.block.MultiBlockPartBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockTemplateBlock<E extends Enum<E> & IEnumPropertyBlock> extends TEBlock<E> {
	public MultiBlockTemplateBlock(String name, Material material, EnumProperty<E> property, Object... additional) {
		super(name, material, property, combine(additional, StatePropertiesHandler.HORIZONTAL_FACING, StatePropertiesHandler.SLAVE_MULTIBLOCK));
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack) {
		BlockEntity te = world.getBlockEntity(pos);
		if (te instanceof MultiBlockPartBlockEntity) {
			MultiBlockPartBlockEntity tile = (MultiBlockPartBlockEntity)te;
			if (!tile.formed && tile.pos == -1  && !tile.getBlock().isEmpty()) {
				world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, tile.getBlock().copy()));
			}
		}
		if (te instanceof MultiBlockPartBlockEntity) {
			((MultiBlockPartBlockEntity)te).disassemble();
		}
		super.spawnAfterBreak(state, world, pos, stack);
	}

	@Override
	public List<ItemStack> getDrops(BlockState p_220076_1_, LootContext.Builder p_220076_2_) {
		return new ArrayList<>();
	}

	/*@Override
	public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
		ItemStack stack = origin(world, pos);
		if (!stack.isEmpty()) {
			return stack;
		}
		return super.getPickBlock(state, target, world, pos, player);
	}*/

	public ItemStack origin(BlockGetter world, BlockPos pos) {
		BlockEntity te = world.getBlockEntity(pos);
		if (te instanceof MultiBlockPartBlockEntity) {
			return ((MultiBlockPartBlockEntity)te).getBlock();
		}
		return ItemStack.EMPTY;
	}
}
