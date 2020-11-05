package me.hp20.giz5;

import me.hp20.giz5.options.Giz5Options;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;

public class SprintClientTickCallback implements ClientTickCallback {

    private boolean lastPressed;

    @Override
    public void tick(MinecraftClient client) {
        Giz5Options opt = Giz5Mod.getOptions();
        if (!opt.toggleSprint) return;
        boolean pressed = Giz5Mod.getSprintKeyBinding().isPressed();
        if (pressed && !lastPressed) {
            opt.sprint = !opt.sprint;
        }
        lastPressed = pressed;
        if (opt.sprint)
            client.options.keySprint.setPressed(true);
    }
}
