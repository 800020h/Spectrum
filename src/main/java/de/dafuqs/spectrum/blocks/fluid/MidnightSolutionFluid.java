package de.dafuqs.spectrum.blocks.fluid;

import de.dafuqs.spectrum.blocks.decay.*;
import de.dafuqs.spectrum.particle.*;
import de.dafuqs.spectrum.registries.*;
import net.fabricmc.api.*;
import net.minecraft.block.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public abstract class MidnightSolutionFluid extends SpectrumFluid {
	
	@Override
	public Fluid getStill() {
		return SpectrumFluids.MIDNIGHT_SOLUTION;
	}
	
	@Override
	public Fluid getFlowing() {
		return SpectrumFluids.FLOWING_MIDNIGHT_SOLUTION;
	}
	
	@Override
	public Item getBucketItem() {
		return SpectrumItems.MIDNIGHT_SOLUTION_BUCKET;
	}
	
	@Override
	protected BlockState toBlockState(FluidState fluidState) {
		return SpectrumBlocks.MIDNIGHT_SOLUTION.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
	}
	
	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == SpectrumFluids.MIDNIGHT_SOLUTION || fluid == SpectrumFluids.FLOWING_MIDNIGHT_SOLUTION;
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, FluidState state, Random random) {
		BlockPos topPos = pos.up();
		BlockState topState = world.getBlockState(topPos);
		if (topState.isAir() && !topState.isOpaqueFullCube(world, topPos) && random.nextInt(2000) == 0) {
			world.playSound(pos.getX(), pos.getY(), pos.getZ(), SpectrumSoundEvents.MIDNIGHT_SOLUTION_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
		}
	}
	
	@Override
	protected int getFlowSpeed(WorldView worldView) {
		return 5;
	}
	
	@Override
	protected int getLevelDecreasePerBlock(WorldView worldView) {
		return 1;
	}
	
	@Override
	public void onScheduledTick(World world, BlockPos pos, FluidState state) {
		super.onScheduledTick(world, pos, state);
		
		if (state.getHeight() < 1.0) {
			for (Direction direction : Direction.values()) {
				if (MidnightSolutionFluidBlock.tryConvertNeighbor(world, pos, pos.offset(direction))) {
					break;
				}
			}
		}
		
		boolean converted = BlackMateriaBlock.spreadBlackMateria(world, pos, world.random, MidnightSolutionFluidBlock.SPREAD_BLOCKSTATE);
		if (converted) {
			world.scheduleFluidTick(pos, state.getFluid(), 400 + world.random.nextInt(800));
		}
	}
	
	@Override
	public int getTickRate(WorldView worldView) {
		return 12;
	}
	
	@Override
	public ParticleEffect getParticle() {
		return SpectrumParticleTypes.DRIPPING_MIDNIGHT_SOLUTION;
	}
	
	@Override
	public ParticleEffect getSplashParticle() {
		return SpectrumParticleTypes.MIDNIGHT_SOLUTION_SPLASH;
	}
	
	public static class Flowing extends MidnightSolutionFluid {
		
		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}
		
		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
		
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}
		
	}
	
	public static class Still extends MidnightSolutionFluid {
		
		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
		
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}
		
	}
}