package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class FullBrightHud extends DrawableHelper {

    public void render(MatrixStack stack, float partial) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getProfiler().push("fullBright");
        TextRenderer renderer = client.textRenderer;

        float x = ThymineMod.getOptions().fullBrightHud.getX();
        float y = ThymineMod.getOptions().fullBrightHud.getY();
        int color = ThymineMod.getOptions().fullBrightHud.color;
        if (ThymineMod.getOptions().fullBrightToggle) {
            String toggleSprintEnabled = new TranslatableText("thymine.messages.toggleFullBright.enabled").getString();
            renderer.drawWithShadow(stack, toggleSprintEnabled, x, y, color);
        } else {
            String toggleSprintDisabled = new TranslatableText("thymine.messages.toggleFullBright.disabled").getString();
            renderer.drawWithShadow(stack, toggleSprintDisabled, x, y, color);
        }
        client.getProfiler().pop();
    }
}
