package de.dafuqs.spectrum.blocks.jade_vines;

import de.dafuqs.spectrum.worldgen.*;
import net.minecraft.block.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public class NephriteBlossomBulbBlock extends PlantBlock implements Fertilizable {

    public NephriteBlossomBulbBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.025) {
            SpectrumConfiguredFeatures.NEPHRITE_BLOSSOM_BULB.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.075;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        SpectrumConfiguredFeatures.NEPHRITE_BLOSSOM_BULB.value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
    }
}