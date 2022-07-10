package net.toshimichi.thymine.options;

import net.toshimichi.thymine.ThymineMod;

public enum FullBrightMode {

    OFF(false, false, "options.off"),
    NIGHT_VISION(true, false, "thymine.options.fullBright.nightVision"),
    GAMMA(false, true, "thymine.options.fullBright.gamma");

    private final boolean nightVision;
    private final boolean gamma;
    private final String translationKey;

    FullBrightMode(boolean nightVision, boolean gamma, String translationKey) {
        this.nightVision = nightVision;
        this.gamma = gamma;
        this.translationKey = translationKey;
    }

    public boolean isNightVision() {
        return nightVision && ThymineMod.getOptions().fullBrightToggle;
    }

    public boolean isGamma() {
        return gamma && ThymineMod.getOptions().fullBrightToggle;
    }

    public String getTranslationKey() {
        return translationKey;
    }
}
