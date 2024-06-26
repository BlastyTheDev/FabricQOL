package me.blasty.movementinfo;

import me.blasty.movementinfo.config.ConfigOptions;
import me.blasty.movementinfo.overlay.InfoHudOverlay;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("movementinfo");
    public static final ConfigHolder<ConfigOptions> CONFIG = AutoConfig.register(ConfigOptions.class, JanksonConfigSerializer::new);

    @Override
    public void onInitialize() {
        HudRenderCallback.EVENT.register(new InfoHudOverlay());
        LOGGER.info("Initialised Movement Info Mod.");
    }

    public static double getRoundingDecimalPlaces() {
        return Double.parseDouble("1" + "0".repeat(Math.max(0, CONFIG.get().roundingDecimalPlaces)));
    }

}
