package me.blasty.movementinfo.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "movementinfo")
public class ConfigOptions implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean enabled = true;

    @ConfigEntry.Gui.Tooltip
    public int yposition = -1;

    @ConfigEntry.Gui.Tooltip
    public int roundingDecimalPlaces = 2;

}
