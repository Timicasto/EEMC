package org.quantumstudio.eemod.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockRegisterEntry {

	public static List<IMultiBlock> multiblocks = new ArrayList<>();
	public static void registerStructure(IMultiBlock mb) {
		multiblocks.add(mb);
	}

	public interface IMultiBlock {
		String getName();
		boolean isTrigger(BlockState state);
		boolean createStructure(Level level, BlockPos pos, Direction direction, Player player);
		ItemStack[][][] getStructureManual();
		default BlockState getBlockStateFromItemStack(int index, ItemStack stack) {
			if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
				return (((BlockItem) stack.getItem()).getBlock().getStateDefinition().getPossibleStates().get(index));
			}
			return null;
		}
		ItemStack[] getTotalMaterials();
		@OnlyIn(Dist.CLIENT)
		boolean overwriteBlockRenderer(ItemStack stack, int iterator);
		float getManualScale();
		@OnlyIn(Dist.CLIENT)
		boolean canRenderStructure();
		@OnlyIn(Dist.CLIENT)
		void renderStructure();
	}

	public static MultiBlockFormingEvent postEvent(Player player, IMultiBlock mb, BlockPos trigger, ItemStack toolkit) {
		MultiBlockFormingEvent e = new MultiBlockFormingEvent(player, mb, trigger, toolkit);
		MinecraftForge.EVENT_BUS.post(e);
		return e;
	}

	@Cancelable
	public static class MultiBlockFormingEvent extends PlayerEvent {
		private final IMultiBlock mb;
		private final BlockPos trigger;
		private final ItemStack tool;

		public MultiBlockFormingEvent(Player player, IMultiBlock mb, BlockPos trigger, ItemStack tool) {
			super(player);
			this.mb = mb;
			this.trigger = trigger;
			this.tool = tool;
		}

		public IMultiBlock getMb() {
			return mb;
		}

		public BlockPos getTrigger() {
			return trigger;
		}

		public ItemStack getTool() {
			return tool;
		}
	}
}
