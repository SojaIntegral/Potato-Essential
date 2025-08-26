package net.potato_modding.potatoessentials.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.potato_modding.potatoessentials.PotatoEssentials;

@SuppressWarnings("unused")
public class PotatoTags {

    // For familiars' natures
    public static final TagKey<EntityType<?>> HAS_NATURE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("potatoessentials", "natures/mobs_with_natures"));
    public static final TagKey<EntityType<?>> MOB_ENABLED =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("potatoessentials", "potato_whitelist"));

    // Mobs types
    public static final TagKey<EntityType<?>> BOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "rank/boss"));
    public static final TagKey<EntityType<?>> MINIBOSS =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "rank/miniboss"));
    public static final TagKey<EntityType<?>> NORMAL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "rank/normal"));
    public static final TagKey<EntityType<?>> SUMMON =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "rank/summon"));
    public static final TagKey<EntityType<?>> PLAYER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "rank/player"));
    public static final TagKey<EntityType<?>> NERFED_MOB =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "rank/disabled_iv"));

    public static final TagKey<EntityType<?>> CRASH_FIX =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "crash_fix/culprits"));

}
