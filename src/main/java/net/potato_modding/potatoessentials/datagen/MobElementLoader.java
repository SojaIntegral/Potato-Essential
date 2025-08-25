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

public class MobElementLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    private static final Map<ResourceLocation, MobElementData> DATA = new HashMap<>();

    public record MobElementData(
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
            double enderRes
    ) {}

    public MobElementLoader() {
        super(GSON, "mob_elements");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {
        DATA.clear();
        jsons.forEach((id, json) -> DATA.put(id, GSON.fromJson(json, MobElementData.class)));
    }

    public static MobElementData get(ResourceLocation id) {
        return DATA.getOrDefault(id, defaultValues());
    }

    private static MobElementData defaultValues() {
        return new MobElementData(0,0,0,0,0,0,0,0,0,0,0);
    }
}
