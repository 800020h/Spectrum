package de.dafuqs.spectrum.mixin.client;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.client.*;
import net.minecraft.client.render.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(LightmapTextureManager.class)

public class LightmapTextureManagerMixin {

	@ModifyReturnValue(method = "getDarkness(Lnet/minecraft/entity/LivingEntity;FF)F", at = @At("RETURN"))
	private static float spectrum$getDarkness(float original) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (SpectrumDimensions.DIMENSION_KEY.equals(client.player.getWorld().getRegistryKey())) {
			return Math.max(0.12F, original);
		}
		return original;
	}

}
