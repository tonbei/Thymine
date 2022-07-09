package net.toshimichi.thymine.mixin;

import com.mamiyaotaru.voxelmap.Map;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;
import java.util.Collections;

@Mixin(Map.class)
public class VoxelMapMixin {

    @Redirect(method = "drawMinimap(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/MinecraftClient;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getStatusEffects()Ljava/util/Collection;"))
    private Collection<StatusEffectInstance> getStatusEffects(ClientPlayerEntity entity) {
        if (ThymineMod.getOptions().noStatusOverlay)
            return Collections.EMPTY_SET;
        return entity.getStatusEffects();
    }
}
