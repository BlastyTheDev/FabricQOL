package me.blasty.movementinfo.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.blasty.movementinfo.Main;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setTitle(Text.literal("Movement Info Config"))
                    .setParentScreen(parent)
                    .setSavingRunnable(Main.CONFIG::save);
            ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enabled"), Main.CONFIG.get().enabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(value -> Main.CONFIG.get().enabled = value)
                    .build());
            general.addEntry(entryBuilder.startIntField(Text.literal("Y Position"), Main.CONFIG.get().yposition)
                    .setDefaultValue(-1)
                    .setSaveConsumer(value -> Main.CONFIG.get().yposition = value)
                    .build());
            general.addEntry(entryBuilder.startIntField(Text.literal("Rounding Decimal Places"), Main.CONFIG.get().roundingDecimalPlaces)
                    .setDefaultValue(2)
                    .setSaveConsumer(value -> Main.CONFIG.get().roundingDecimalPlaces = value)
                    .build());
            return builder.build();
        };
    }

}
