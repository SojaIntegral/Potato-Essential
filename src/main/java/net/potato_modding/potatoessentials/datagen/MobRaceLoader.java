package net.potato_modding.potatoessentials.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.Map;

public class MobRaceLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    public static final Map<ResourceLocation, MobRaceData> DATA = new HashMap<>();

    public record MobRaceData(
            double attack,
            double armor,
            double tough,
            double spellPower,
            double castReduction,
            double resist,
            double neutralRes,
            double waterRes,
            double earthRes,
            double fireRes,
            double windRes,
            double bloodRes,
            double holyRes,
            double eldritchRes,
            double soulRes,
            double enderRes,
            double armorPierce,
            double armorShred,
            double protPierce,
            double protShred,
            double critDmg,
            double crit
    ) {}

    public MobRaceLoader() {
        super(GSON, "mob_races");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {
        DATA.clear();
        jsons.forEach((id, json) -> {
            try {
                DATA.put(id, GSON.fromJson(json, MobRaceLoader.MobRaceData.class));
            } catch (Exception e) {
                System.err.println("[MobElementLoader] Failed to parse JSON for " + id);
                e.printStackTrace();
            }
        });

        System.out.println("Available keys: " + MobRaceLoader.DATA.keySet());
    }

    public static MobRaceData get(ResourceLocation id) {
        return DATA.getOrDefault(id, defaultValues());
    }

    public static MobRaceData defaultValues() {
        return new MobRaceData(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    }
}
