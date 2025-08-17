package net.potato_modding.potatoessentials.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.potato_modding.potatoessentials.PotatoEssentials;
import net.potato_modding.potatospells.PotatoSpells;

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

    // Mobs elements
    // Base
    public static final TagKey<EntityType<?>> TYPE_NEUTRAL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_neutral"));
    public static final TagKey<EntityType<?>> TYPE_WATER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_water"));
    public static final TagKey<EntityType<?>> TYPE_EARTH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_earth"));
    public static final TagKey<EntityType<?>> TYPE_FIRE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_fire"));
    public static final TagKey<EntityType<?>> TYPE_WIND =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_wind"));
    public static final TagKey<EntityType<?>> TYPE_ELDRITCH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_eldritch"));
    public static final TagKey<EntityType<?>> TYPE_HOLY =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_holy"));
    public static final TagKey<EntityType<?>> TYPE_BLOOD =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_blood"));
    public static final TagKey<EntityType<?>> TYPE_SOUL =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_soul"));
    public static final TagKey<EntityType<?>> TYPE_ENDER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "elemental_system/type_ender"));

    // Races
    public static final TagKey<EntityType<?>> RACE_HUMAN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/human"));
    public static final TagKey<EntityType<?>> RACE_HUMANOID =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/humanoid"));
    public static final TagKey<EntityType<?>> RACE_UNDEAD =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/undead"));
    public static final TagKey<EntityType<?>> RACE_BRUTE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/brute"));
    public static final TagKey<EntityType<?>> RACE_INSECT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/insect"));
    public static final TagKey<EntityType<?>> RACE_FLYING =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/flying"));
    public static final TagKey<EntityType<?>> RACE_GOLEM =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/golem"));
    public static final TagKey<EntityType<?>> RACE_CONSTRUCT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/construct"));
    public static final TagKey<EntityType<?>> RACE_FISH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/fish"));
    public static final TagKey<EntityType<?>> RACE_SPIRIT =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/spirit"));
    public static final TagKey<EntityType<?>> RACE_AMORPH =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/amorph"));
    public static final TagKey<EntityType<?>> RACE_DRAGON =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/dragon"));
    public static final TagKey<EntityType<?>> RACE_DRAGONBORN =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/dragonborn"));
    public static final TagKey<EntityType<?>> RACE_PLAYER =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, "race_system/player"));

    public static final TagKey<EntityType<?>> CRASH_FIX =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(PotatoSpells.MOD_ID, "crash_fix/culprits"));

}
