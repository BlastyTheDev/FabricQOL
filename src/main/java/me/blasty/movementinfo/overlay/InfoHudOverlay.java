package me.blasty.movementinfo.overlay;

import me.blasty.movementinfo.Main;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class InfoHudOverlay implements HudRenderCallback {

    private final List<Double> previousAccel = new ArrayList<>();
    private double previousTickSpeed = 0;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (!Main.CONFIG.get().enabled)
            return;

        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null)
            return;
        if (client.world == null)
            return;

        int width = 10;

        int height;
        if (Main.CONFIG.get().yposition != -1) {
            height = client.getWindow().getHeight();
            height /= (int) client.getWindow().getScaleFactor();
            height *= (int) (client.getWindow().getScaleFactor() - 1);
        } else height = Main.CONFIG.get().yposition;

        Vec3d playerPosVec = player.getPos();

        // x/y/z change in the last tick (20 ticks per second)
        double xc = playerPosVec.x - client.player.prevX;
        double yc = playerPosVec.y - client.player.prevY;
        double zc = playerPosVec.z - client.player.prevZ;

        double tickSpeed = Math.sqrt(xc * xc + yc * yc + zc * zc);
        double speed = tickSpeed * 20;

        double kinetic = 0.5 * (speed * speed);

        // bedrock level
        double b = playerPosVec.y + 64;

        double potential = 32 * b;
        double total = kinetic + potential;

        // m/s^2, highest in last 20 ticks
        if (previousAccel.size() >= 21)
            previousAccel.removeFirst();
        previousAccel.add(Math.abs((tickSpeed - previousTickSpeed) / 0.05));

        double accel = 0;
        for (Double d : previousAccel) {
            if (d > accel)
                accel = d;
        }

        previousTickSpeed = tickSpeed;

        drawTextWithShadow(drawContext, "Speed: " + round(speed) + "m/s", width / 3, height / 4, 0xFFFFFF);
        drawTextWithShadow(drawContext, "Kinetic Energy: " + round(kinetic), width / 3, height / 4 + 10, 0xFFFFFF);
        drawTextWithShadow(drawContext, "Potential Energy: " + round(potential), width / 3, height / 4 + 20, 0xFFFFFF);
        drawTextWithShadow(drawContext, "Total Energy: " + round(total), width / 3, height / 4 + 30, 0xFFFFFF);
        drawTextWithShadow(drawContext, "Max Accel (20t): " + round(accel) + "m/s^2", width / 3, height / 4 + 40, 0xFFFFFF);
    }

    private void drawTextWithShadow(DrawContext drawContext, String text, int x, int y, int color) {
        drawContext.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, Text.literal(text), x, y, color);
    }

    private double round(double x) {
        return Math.round(x * Main.getRoundingDecimalPlaces()) / Main.getRoundingDecimalPlaces();
    }

}
