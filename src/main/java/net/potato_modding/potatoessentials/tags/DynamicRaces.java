package net.potato_modding.potatoessentials.tags;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class DynamicRaces extends SimpleJsonResourceReloadListener {
    public static final Map<String, TagKey<EntityType<?>>> DYNAMIC_RACES = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().create();

    public DynamicRaces() {
        super(GSON, "tags/entity_type/mob_races");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {
        DYNAMIC_RACES.clear();
        for (ResourceLocation path : jsons.keySet()) {
            String fileName = path.getPath();
            String raceName = fileName.substring(fileName.lastIndexOf('/') + 1);
            TagKey<EntityType<?>> tag = TagKey.create(
                    Registries.ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath("potatoessentials", "mob_races/" + raceName)
            );
            DYNAMIC_RACES.put(raceName, tag);
        }
    }
}


