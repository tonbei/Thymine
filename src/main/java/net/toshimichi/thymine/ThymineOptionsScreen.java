package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.toshimichi.thymine.options.FullBrightMode;
import net.toshimichi.thymine.options.Position;
import net.toshimichi.thymine.options.ThymineOptions;

import java.util.Arrays;

public class ThymineOptionsScreen extends GameOptionsScreen {

    private static final Option[] options;

    private static ThymineOptions options() {
        return ThymineMod.getOptions();
    }

    static {
        options = new Option[]{
                CyclingOption.create("thymine.options.fullBright", FullBrightMode.values(), p -> new TranslatableText(p.getTranslationKey()), g -> options().fullBrightMode,
                        (g, o, v) -> {
                            options().fullBrightMode = v;
                            if (v.isGamma()) MinecraftClient.getInstance().options.gamma = ThymineMod.MAX_GAMMA;
                            else MinecraftClient.getInstance().options.gamma = options().defaultGamma;
                        }),
                CyclingOption.create("thymine.options.fullBrightHud", p -> !options().fullBrightHud.isHidden(), (g, o, t) -> options().fullBrightHud.hidden = !t),
                CyclingOption.create("thymine.options.fastSneak", p -> options().fastSneak, (g, o, t) -> options().fastSneak = t),
                CyclingOption.create("thymine.options.toggleSprint", p -> options().toggleSprint, (g, o, t) -> options().toggleSprint = t),
                CyclingOption.create("thymine.options.noHurtBobbing", p -> options().noHurtBobbing, (g, o, t) -> options().noHurtBobbing = t),
                CyclingOption.create("thymine.options.shiftFix", p -> options().shiftFix, (g, o, t) -> options().shiftFix = t),
                CyclingOption.create("thymine.options.ignoreCooldown", p -> options().ignoreCooldown, (g, o, t) -> options().ignoreCooldown = t),
                CyclingOption.create("thymine.options.softSneak", p -> options().softSneak, (g, o, t) -> options().softSneak = t),
                CyclingOption.create("thymine.options.forceIcon", p -> options().forceIcon, (g, o, t) -> options().forceIcon = t),
                CyclingOption.create("thymine.options.antiSwim", p -> options().antiSwim, (g, o, t) -> options().antiSwim = t),
                CyclingOption.create("thymine.options.potionHud",
                        Arrays.asList(Position.LEFT_TOP, Position.LEFT_BOTTOM, Position.RIGHT_TOP, Position.RIGHT_BOTTOM, Position.CENTER),
                        p -> switch (p) {
                            case LEFT_TOP -> new TranslatableText("thymine.options.potionHud.left_top");
                            case LEFT_BOTTOM -> new TranslatableText("thymine.options.potionHud.left_bottom");
                            case RIGHT_TOP -> new TranslatableText("thymine.options.potionHud.right_top");
                            case RIGHT_BOTTOM -> new TranslatableText("thymine.options.potionHud.right_bottom");
                            default -> new TranslatableText("options.off");
                        }, g -> options().potionHudOptions.position,
                        (g, o, v) -> {
                            options().potionHudOptions.position = v;
                            options().potionHud = (v != Position.CENTER);
                        }),
                CyclingOption.create("thymine.options.armorHud", p -> options().armorHud, (g, o, t) -> options().armorHud = t),
                CyclingOption.create("thymine.options.noStatusOverlay", p -> options().noStatusOverlay, (g, o, t) -> options().noStatusOverlay = t),
                new DoubleOption("thymine.options.lowFire", 0, 100, 1, p -> options().lowFire, (s, b) -> options().lowFire = b,
                        (s, t) -> new TranslatableText("thymine.options.lowFire", String.format("%.0f", options().lowFire)))
        };
    }

    public ThymineOptionsScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, new TranslatableText("thymine.options.title"));
    }

    @Override
    protected void init() {
        int count = 0;
        int size = options.length;

        for (int i = 0; i < size; i++) {
            Option option = options[i];
            int x = this.width / 2 - 155 + i % 2 * 160;
            int y = this.height / 6 + 24 * (i / 2);
            addDrawableChild(option.createButton(this.client.options, x, y, 150));
            count++;
        }

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (count + 1) / 2, 200, 20, ScreenTexts.DONE, (buttonWidget) -> {
            onClose();
        }));
    }

    @Override
    public void onClose() {
        ThymineMod.saveOptions();
        super.onClose();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
