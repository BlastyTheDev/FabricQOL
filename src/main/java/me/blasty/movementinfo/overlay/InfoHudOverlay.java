package me.blasty.movementinfo.overlay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class InfoHudOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(GuiGraphics drawContext, float tickDelta) {
        assert Minecraft.getInstance().cameraEntity != null;
        assert Minecraft.getInstance().gameMode != null;
        int width = Minecraft.getInstance().getWindow().getScreenWidth();
        int height = Minecraft.getInstance().getWindow().getScreenHeight();
        int guiScale = Minecraft.getInstance().options.guiScale().get();
        if (guiScale == 0)
            guiScale = 4;
        int x = (width / 2) / guiScale;
        int y = ((height / 2) + 100) / guiScale;
    }

}
