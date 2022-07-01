package org.quantumstudio.eemod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import org.quantumstudio.eemod.api.components.DiodeTypes;

import java.util.Random;

public class DiodeBlock extends Block {
	public static final EnumProperty TYPE;

	public static final DirectionProperty FACING;

	private int output;

	private int input;

	private int reverseOutput;

	private boolean brokeDown;

	static {
		TYPE = EnumProperty.create("type", DiodeTypes.class);
		FACING = BlockStateProperties.HORIZONTAL_FACING;
	}

	public DiodeBlock() {
		super(Properties.of(Material.DECORATION).instabreak().randomTicks());
		this.registerDefaultState(this.getStateDefinition().any().setValue(TYPE, DiodeTypes.N4001).setValue(FACING, Direction.NORTH));
	}

	void breakdown(Level level, BlockPos pos) {
		brokeDown = true;
		level.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0, 0.01, 0);
	}

	@Override
	public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return state.getSignal(level, pos, direction);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		super.tick(state, level, pos, random);
		int input = getInputVoltage(level, pos, state);
		if (!brokeDown) {
			output = (int) Math.floor(input - ((DiodeTypes) state.getValue(TYPE)).getForwardDrop());
		} else {
			output = input;
			reverseOutput = getReverseVoltage(level, pos, state);
		}
		if (getReverseVoltage(level, pos, state) >= ((DiodeTypes) state.getValue(TYPE)).getBreakdownVoltage()) {
			breakdown(level, pos);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, FACING);
	}

	protected int getInputVoltage(Level level, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING);
		BlockPos p = pos.relative(direction);
		int voltage = level.getSignal(p, direction);
		input = voltage;
		if (voltage >= 15) {
			return voltage;
		} else {
			BlockState s = level.getBlockState(p);
			return Math.max(voltage, s.is(Blocks.REDSTONE_WIRE) ? s.getValue(RedStoneWireBlock.POWER) : 0);
		}
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	protected int getReverseVoltage(Level level, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING).getOpposite();
		BlockPos p = pos.relative(direction);
		return level.getSignal(p, direction);
	}

	@Override
	public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		if (direction == state.getValue(FACING)) {
			return output;
		} else if (direction == state.getValue(FACING).getOpposite()) {
			return reverseOutput;
		} else {
			return 0;
		}
	}
}
