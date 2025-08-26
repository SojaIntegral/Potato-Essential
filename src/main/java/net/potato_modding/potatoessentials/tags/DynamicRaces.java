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

    private static final Gson GSON = new GsonBuilder().create();

    public static final Map<String, TagKey<EntityType<?>>> RACE_TAGS = new HashMap<>();

    public DynamicRaces() {
        super(GSON, "tags/entity_type/mob_races");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {

        RACE_TAGS.clear();

        jsons.keySet().forEach(id -> {
            String path = id.getPath();
            int slash = path.lastIndexOf('/');
            String raceName = slash != -1 ? path.substring(slash + 1) : path;

            TagKey<EntityType<?>> tag = TagKey.create(
                    Registries.ENTITY_TYPE,
                    id
            );

            RACE_TAGS.put(raceName, tag);
        });

        RACE_TAGS.forEach((name, tag) ->
                System.out.println("[DynamicRaces] Loaded race tag: " + name + " -> " + tag)
        );
    }

    public static boolean hasRace(EntityType<?> entityType, String raceName) {
        TagKey<EntityType<?>> tag = RACE_TAGS.get(raceName);
        if (tag == null) return false;

        return entityType.builtInRegistryHolder().tags().anyMatch(t -> t.equals(tag));
    }
}
