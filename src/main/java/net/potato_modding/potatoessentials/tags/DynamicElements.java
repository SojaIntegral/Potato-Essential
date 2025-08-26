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

public class DynamicElements extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();

    public static final Map<String, TagKey<EntityType<?>>> ELEMENT_TAGS = new HashMap<>();

    public DynamicElements() {
        super(GSON, "tags/entity_type/mob_elements");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {

        ELEMENT_TAGS.clear();

        jsons.keySet().forEach(id -> {
            String path = id.getPath();
            int slash = path.lastIndexOf('/');
            String elementName = slash != -1 ? path.substring(slash + 1) : path;

            TagKey<EntityType<?>> tag = TagKey.create(
                    Registries.ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath(id.getNamespace(), path)
            );

            ELEMENT_TAGS.put(elementName, tag);
        });

        ELEMENT_TAGS.forEach((name, tag) ->
                System.out.println("[DynamicElements] Loaded element tag: " + name + " -> " + tag)
        );
    }

    public static boolean hasElement(EntityType<?> entityType, String elementName) {
        TagKey<EntityType<?>> tag = ELEMENT_TAGS.get(elementName);
        if (tag == null) return false;

        return entityType.builtInRegistryHolder().tags().anyMatch(t -> t.equals(tag));
    }
}