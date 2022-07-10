package net.toshimichi.thymine.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {

    @Inject(at = @At("TAIL"), method = "initGameThread(Z)V")
    private static void initGameThread(CallbackInfo info) {
        ThymineMod.loadOptions();
        ThymineMod.getOptions().defaultGamma = MinecraftClient.getInstance().options.gamma;
        if (ThymineMod.getOptions().fullBrightMode.isGamma())
            MinecraftClient.getInstance().options.gamma = ThymineMod.MAX_GAMMA;
    }
}
