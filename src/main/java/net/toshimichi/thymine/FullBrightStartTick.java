package net.toshimichi.thymine;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.toshimichi.thymine.options.FullBrightMode;
import net.toshimichi.thymine.options.ThymineOptions;

public class FullBrightStartTick implements ClientTickEvents.StartTick {

    private boolean lastPressed;

    @Override
    public void onStartTick(MinecraftClient client) {
        ThymineOptions opt = ThymineMod.getOptions();
        if (opt.fullBrightMode == FullBrightMode.OFF) return;
        boolean pressed = ThymineMod.getFullBrightKeyBinding().isPressed();
        if (pressed && !lastPressed) {
            opt.fullBrightToggle = !opt.fullBrightToggle;
            if (opt.fullBrightMode.isGamma())
                client.options.gamma = ThymineMod.MAX_GAMMA;
            else
                client.options.gamma = opt.defaultGamma;
        }
        lastPressed = pressed;
    }
}
