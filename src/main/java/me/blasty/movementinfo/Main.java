package me.blasty.movementinfo;

import me.blasty.movementinfo.overlay.InfoHudOverlay;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("movementinfo");

    @Override
    public void onInitialize() {
        HudRenderCallback.EVENT.register(new InfoHudOverlay());
        LOGGER.info("Initialised Movement Info Mod.");
    }

}
