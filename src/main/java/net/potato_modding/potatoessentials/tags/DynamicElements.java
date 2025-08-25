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
    public static final Map<String, TagKey<EntityType<?>>> DYNAMIC_ELEMENTS = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().create();

    public DynamicElements() {
        super(GSON, "tags/entity_type/mob_elements");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons,
                         ResourceManager resourceManager,
                         ProfilerFiller profiler) {
        DYNAMIC_ELEMENTS.clear();
        for (ResourceLocation path : jsons.keySet()) {
            String fileName = path.getPath();
            String elementName = fileName.substring(fileName.lastIndexOf('/') + 1);
            TagKey<EntityType<?>> tag = TagKey.create(
                    Registries.ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath("potatoessentials", "mob_elements/" + elementName)
            );
            DYNAMIC_ELEMENTS.put(elementName, tag);
        }
    }
}