package net.toshimichi.thymine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.toshimichi.thymine.options.ThymineOptions;
import org.apache.commons.io.FileUtils;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ThymineMod implements ModInitializer {

    private static ThymineOptions options;
    private static KeyBinding sprintKeyBinding;
    private static KeyBinding fullBrightKeyBinding;

    private static final File configFile = new File("config/thymine.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static final double MAX_GAMMA = 15.0;

    @Override
    public void onInitialize() {
        sprintKeyBinding = new KeyBinding("thymine.options.toggleSprint", GLFW.GLFW_KEY_R, "thymine");
        fullBrightKeyBinding = new KeyBinding("thymine.options.toggleFullBright", GLFW.GLFW_KEY_B, "thymine");
        KeyBindingHelper.registerKeyBinding(sprintKeyBinding);
        KeyBindingHelper.registerKeyBinding(fullBrightKeyBinding);
        ClientTickEvents.START_CLIENT_TICK.register(new SprintStartTick());
        ClientTickEvents.START_CLIENT_TICK.register(new FullBrightStartTick());
    }

    public static ThymineOptions getOptions() {
        return options;
    }

    public static KeyBinding getSprintKeyBinding() {
        return sprintKeyBinding;
    }

    public static KeyBinding getFullBrightKeyBinding() {
        return fullBrightKeyBinding;
    }

    public static void saveOptions() {
        try {
            FileUtils.write(configFile, gson.toJson(options), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadOptions() {
        if (configFile.exists()) {
            try {
                String text = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8);
                options = gson.fromJson(text, ThymineOptions.class);
            } catch (IOException e) {
                e.printStackTrace();
                //If something goes wrong, renew config file
                options = new ThymineOptions();
            }
        } else {
            options = new ThymineOptions();
        }
    }
}
