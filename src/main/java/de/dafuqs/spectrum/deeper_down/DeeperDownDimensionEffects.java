package de.dafuqs.spectrum.deeper_down;

import net.fabricmc.api.*;
import net.minecraft.client.render.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.*;

@Environment(EnvType.CLIENT)
public class DeeperDownDimensionEffects extends DimensionEffects {
	
	public DeeperDownDimensionEffects() {
		super(Float.NaN, false, DimensionEffects.SkyType.NONE, false, true);
	}
	
	@Override
	public @Nullable float[] getFogColorOverride(float skyAngle, float tickDelta) {
		return null;
	}
	
	@Override
	public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
		return color;
	}
	
	@Override
	public boolean useThickFog(int camX, int camY) {
		return true;
	}
	
}