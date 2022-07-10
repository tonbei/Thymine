package net.toshimichi.thymine.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Final
    @Shadow
    public GameOptions options;

    @Inject(at = @At("HEAD"), method = "close()V")
    private void close(CallbackInfo info) {
        if (ThymineMod.getOptions().fullBrightMode.isGamma())
            options.gamma = ThymineMod.getOptions().defaultGamma;
        options.write();
        ThymineMod.saveOptions();
    }
}
