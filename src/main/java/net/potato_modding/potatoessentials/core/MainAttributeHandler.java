package net.potato_modding.potatoessentials.core;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.potato_modding.potatoessentials.config.ServerConfigs;
import net.potato_modding.potatoessentials.datagen.MobElementLoader;
import net.potato_modding.potatoessentials.datagen.MobRaceLoader;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatoessentials.tags.PotatoTags;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static net.potato_modding.potatoessentials.PotatoEssentials.MOD_ID;
import static net.potato_modding.potatoessentials.utils.ConfigFormulas.*;

@SuppressWarnings({"unused", "deprecation"})
@EventBusSubscriber
public class MainAttributeHandler {

    public static boolean isShiny = false;
    private static double SpellPower;
    private static double CastReduction;
    private static double Resist;
    private static double NeutralRes;
    private static double WaterRes;
    private static double EarthRes;
    private static double FireRes;
    private static double WindRes;
    private static double EldritchRes;
    private static double HolyRes;
    private static double BloodRes;
    private static double SoulRes;
    private static double EnderRes;
    private static double Armor;
    private static double Tough;
    private static double Attack;
    private static double ArmorPierce;
    private static double ArmorShred;
    private static double ProtPierce;
    private static double ProtShred;
    private static double CritDmg;
    private static double Crit;

    private static boolean safeguard;

    public static void checkModifier(LivingEntity entity) {
        var instance = entity.getAttributes().getInstance(AttributeRegistry.SPELL_POWER);
        if (instance == null || entity.level().isClientSide) return;

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatoessentials", "spell_power");

        safeguard = instance.getModifiers().stream().noneMatch(mod -> mod.id().equals(id));
    }

    private static void setIfNonNull(LivingEntity entity, Holder<Attribute> attribute, double value) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }

    // Add modifier (base)
    private static void addModifierIfValid(LivingEntity entity, Holder<Attribute> attribute, double value, String idName) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance == null) return;

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatoessentials", idName);

        // Avoid duplicates
        if (instance.getModifiers().stream().noneMatch(mod -> mod.id().equals(id))) {
            instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_VALUE));
        }
    }

    // Add modifier (multiplied base)
    private static void multiplyModifierIfValid(LivingEntity entity, Holder<Attribute> attribute, double value, String idName) {
        var instance = entity.getAttributes().getInstance(attribute);
        if (instance == null) return;

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatoessentials", idName);

        // Avoid duplicates
        if (instance.getModifiers().stream().noneMatch(mod -> mod.id().equals(id))) {
            instance.addPermanentModifier(new AttributeModifier(id, value, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
    }

    private static boolean shinyAttribute() {
        return ThreadLocalRandom.current().nextInt(shinyChanceModifier) == 0;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void handleResistanceAttribute(EntityJoinLevelEvent event) {

        var entity = event.getEntity();
        if (!(entity instanceof LivingEntity mob)) return;

        CompoundTag nbtdata = mob.getPersistentData();
        CompoundTag potatoData = nbtdata.getCompound("PotatoData");

        if(entity.level().isClientSide()) return;
        if (potatoData.getBoolean("shiny")) return;

        checkModifier(mob);
        if (safeguard) {

            // IVs variation setup
            double[] attrVar = new double[10];
            // Chance for shiny & prevents shinies from losing perfect IVs
            if (mob.getType().is(PotatoTags.NERFED_MOB) || !ServerConfigs.IV_SYSTEM.get()) {
                Arrays.fill(attrVar, 0);
            }
            else if (shinyAttribute() || potatoData.getBoolean("shiny")) {
                Arrays.fill(attrVar, 1 * randMax);
                isShiny = true;
            }
            else {
                for (int i = 0; i < attrVar.length; i++) {
                    attrVar[i] = Math.random() * randMax;
                }
            }

            // Checks if the mob has a valid modifier from here
            // If not, it gives the mob modifiers
            if (mob.getType().is(PotatoTags.MOB_ENABLED)) {

                if (ServerConfigs.NATURE_SYSTEM.get() && mob.getType().is(PotatoTags.HAS_NATURE)) {
                    PotatoNaturesHandler.applySpawnModifiers(mob);
                }

                // Class modifier
                if (mob.getType().is(PotatoTags.BOSS)) {
                    mobType = boss_mod;
                    ArmorMod = boss_mod * (3 * (1 + attrVar[0]));
                    ToughMod = boss_mod * (2 * (1 + attrVar[0]));
                    AttackMod = boss_mod * (2 * (1 + attrVar[1]));
                } else if (mob.getType().is(PotatoTags.MINIBOSS)) {
                    mobType = mini_mod;
                    ArmorMod = mini_mod * (1.5 * (1 + attrVar[0]));
                    ToughMod = mini_mod * (1.5 * (1 + attrVar[0]));
                    AttackMod = mini_mod * (1 * (1 + attrVar[1]));
                } else if (mob.getType().is(PotatoTags.NORMAL)) {
                    mobType = mob_mod;
                    ArmorMod = mob_mod * (0.75 * (1 + attrVar[0]));
                    ToughMod = mob_mod * (0.5 * (1 + attrVar[0]));
                    AttackMod = mob_mod * (0.65 * (1 + attrVar[1]));
                } else if (mob.getType().is(PotatoTags.SUMMON)) {
                    mobType = summon_mod;
                    ArmorMod = summon_mod * (1 * (1 + attrVar[0]));
                    ToughMod = summon_mod * (0.5 * (1 + attrVar[0]));
                    AttackMod = summon_mod * (0.5 * (1 + attrVar[1]));
                } else {
                    mobType = 1;
                    ArmorMod = 1;
                    ToughMod = 1;
                    AttackMod = 1;
                }

                mob.getType().builtInRegistryHolder().tags().forEach(tagKey -> {
                    if (tagKey.registry().equals(Registries.ENTITY_TYPE) &&
                            tagKey.location().getNamespace().equals(MOD_ID) &&
                            tagKey.location().getPath().startsWith("mob_races/")) {

                        String raceName = tagKey.location().getPath().substring("mob_races/".length());
                        ResourceLocation dataId = ResourceLocation.fromNamespaceAndPath(MOD_ID, raceName);
                        var data = MobRaceLoader.get(dataId);

                        Attack = data.attack() * AttackMod;
                        Armor = data.armor() * ArmorMod;
                        Tough = data.tough() * ToughMod;
                        SpellPower = data.spellPower() + attrVar[2];
                        CastReduction = data.castReduction() + attrVar[3];
                        Resist = data.resist() + attrVar[4];
                        NeutralRes = data.neutralRes() + attrVar[4];
                        WaterRes = data.waterRes() + attrVar[4];
                        EarthRes = data.earthRes() + attrVar[4];
                        FireRes = data.fireRes() + attrVar[4];
                        WindRes = data.windRes() + attrVar[4];
                        BloodRes = data.bloodRes() + attrVar[4];
                        HolyRes = data.holyRes() + attrVar[4];
                        EldritchRes = data.eldritchRes() + attrVar[4];
                        SoulRes = data.soulRes() + attrVar[4];
                        EnderRes = data.enderRes() + attrVar[4];
                        ArmorPierce = data.armorPierce() * (1 + attrVar[5]);
                        ArmorShred = data.armorShred() * (1 + attrVar[5]);
                        ProtPierce = data.protPierce() * (1 + attrVar[6]);
                        ProtShred = data.protShred() * (1 + attrVar[6]);
                        CritDmg = data.critDmg() + attrVar[7];
                        Crit = data.crit() + attrVar[7];
                    }
                });

                mob.getType().builtInRegistryHolder().tags().forEach(tagKey -> {
                    if (tagKey.registry().equals(Registries.ENTITY_TYPE)
                            && tagKey.location().getNamespace().equals(MOD_ID)
                            && tagKey.location().getPath().startsWith("mob_elements/")) {

                        String elementName = tagKey.location().getPath().substring("mob_elements/".length());
                        ResourceLocation dataId = ResourceLocation.fromNamespaceAndPath(MOD_ID, elementName);
                        var data = MobElementLoader.get(dataId);

                        Resist *= data.resist() * mobType;
                        NeutralRes *= data.neutralRes() * mobType;
                        WaterRes *= data.waterRes() * mobType;
                        EarthRes *= data.earthRes() * mobType;
                        FireRes *= data.fireRes() * mobType;
                        WindRes *= data.windRes() * mobType;
                        BloodRes *= data.bloodRes() * mobType;
                        HolyRes *= data.holyRes() * mobType;
                        EldritchRes *= data.eldritchRes() * mobType;
                        SoulRes *= data.soulRes() * mobType;
                        EnderRes *= data.enderRes() * mobType;
                    }
                });

                {
                    if ((Armor == 0 || attrVar[0] == 1) && (Attack == 0 || attrVar[1] == 1) &&
                            (attrVar[2] == 1) && (attrVar[3] == 1) && (attrVar[4] == 1) &&
                            (ArmorPierce == 0 || attrVar[5] == 1) && (ProtPierce == 0 || attrVar[6] == 1) && (attrVar[7] == 1)) {
                        isShiny = true;
                    }
                    else {
                        addModifierIfValid(mob, Attributes.ATTACK_DAMAGE, BigDecimal.valueOf(Attack).setScale(2, RoundingMode.HALF_UP).doubleValue(), "attack");
                        addModifierIfValid(mob, Attributes.ARMOR, BigDecimal.valueOf(Armor).setScale(2, RoundingMode.HALF_UP).doubleValue(), "armor");
                        addModifierIfValid(mob, Attributes.ARMOR_TOUGHNESS, BigDecimal.valueOf(Tough).setScale(2, RoundingMode.HALF_UP).doubleValue(), "toughness");


                        // Magic Attributes
                        if (ModList.get().isLoaded("irons_spellbooks")) {
                            multiplyModifierIfValid(mob, AttributeRegistry.SPELL_POWER, (BigDecimal.valueOf(SpellPower).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spell_power");
                            multiplyModifierIfValid(mob, AttributeRegistry.CAST_TIME_REDUCTION, (BigDecimal.valueOf(CastReduction).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "cast");
                            multiplyModifierIfValid(mob, AttributeRegistry.SPELL_RESIST, (BigDecimal.valueOf(Resist).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.FIRE_MAGIC_RESIST, (BigDecimal.valueOf(FireRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "fire_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.NATURE_MAGIC_RESIST, (BigDecimal.valueOf(EarthRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "nature_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.ENDER_MAGIC_RESIST, (BigDecimal.valueOf(EnderRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "end_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.EVOCATION_MAGIC_RESIST, (BigDecimal.valueOf(NeutralRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "evoke_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.BLOOD_MAGIC_RESIST, (BigDecimal.valueOf(BloodRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blood_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.ICE_MAGIC_RESIST, (BigDecimal.valueOf(WaterRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "ice_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.LIGHTNING_MAGIC_RESIST, (BigDecimal.valueOf(WindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "lightning_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.ELDRITCH_MAGIC_RESIST, (BigDecimal.valueOf(EldritchRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "eldritch_resist");
                            multiplyModifierIfValid(mob, AttributeRegistry.HOLY_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "holy_resist");
                        }
                        // This needs to be conditional or the game shits itself if the mod is not present
                        if (ModList.get().isLoaded("endersequipment")) {
                            multiplyModifierIfValid(mob, EEAttributeRegistry.BLADE_MAGIC_RESIST, (BigDecimal.valueOf(NeutralRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "blade_resist");
                            //var MIND_GOBLIN_RESIST = net.ender.endersequipment.registries.EEAttributeRegistry.MIND_SPELL_RESIST;
                            //multiplyModifierIfValid(mob, MIND_GOBLIN_RESIST, (BigDecimal.valueOf(SoulRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "mind_resist");
                        }
                        if (ModList.get().isLoaded("cataclysm_spellbooks")) {
                            multiplyModifierIfValid(mob, CSAttributeRegistry.ABYSSAL_MAGIC_RESIST, (BigDecimal.valueOf(WaterRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "abyssal_resist");
                            //multiplyModifierIfValid(mob, net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry.TECHNOMANCY_MAGIC_RESIST, (BigDecimal.valueOf(NeutralRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "technomancy_resist");
                        }
                        if (ModList.get().isLoaded("alshanex_familiars")) {
                            multiplyModifierIfValid(mob, net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "sound_resist");
                        }
                        if (ModList.get().isLoaded("aero_additions")) {
                            multiplyModifierIfValid(mob, AASpells.Attributes.WIND_MAGIC_RESIST, (BigDecimal.valueOf(WindRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "wind_resist");
                        }
                        if (ModList.get().isLoaded("iss_magicfromtheeast")) {
                            multiplyModifierIfValid(mob, MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST, (BigDecimal.valueOf(HolyRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "symmetry_resist");
                            multiplyModifierIfValid(mob, MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST, (BigDecimal.valueOf(SoulRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "spirit_resist");
                            multiplyModifierIfValid(mob, MFTEAttributeRegistries.DUNE_MAGIC_RESIST, (BigDecimal.valueOf(EarthRes).setScale(2, RoundingMode.HALF_UP).doubleValue() - 1), "dune_resist");
                        }
                        //if (ModList.get().isLoaded("traveloptics")) {}

                        // Apothic Attributes
                        {
                            addModifierIfValid(mob, ALObjects.Attributes.ARMOR_PIERCE, BigDecimal.valueOf(ArmorPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_pierce");
                            addModifierIfValid(mob, ALObjects.Attributes.ARMOR_SHRED, BigDecimal.valueOf(ArmorShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "armor_shred");
                            addModifierIfValid(mob, ALObjects.Attributes.PROT_PIERCE, BigDecimal.valueOf(ProtPierce).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_pierce");
                            addModifierIfValid(mob, ALObjects.Attributes.PROT_SHRED, BigDecimal.valueOf(ProtShred).setScale(4, RoundingMode.HALF_UP).doubleValue(), "protection_shred");
                            addModifierIfValid(mob, ALObjects.Attributes.CRIT_CHANCE, BigDecimal.valueOf(Crit).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_chance");
                            addModifierIfValid(mob, ALObjects.Attributes.CRIT_DAMAGE, BigDecimal.valueOf(CritDmg).setScale(4, RoundingMode.HALF_UP).doubleValue(), "critical_damage");
                        }

                        if (ModList.get().isLoaded("cataclysm") && mob.getType().is(PotatoTags.BOSS)) {
                            setIfNonNull(mob, ALObjects.Attributes.LIFE_STEAL, ServerConfigs.BOSS_LIFESTEAL.get());
                        }
                    }

                    if (isShiny) {
                        if (ServerConfigs.IV_SYSTEM.get()) {
                            setIfNonNull(mob, PotatoEssentialsAttributes.SHINY, 1);
                        }
                    }
                }
            }
        }
    }
}
