/*
package net.potato_modding.potatoessentials.utils;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatoessentials.tags.PotatoTags;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EventBusSubscriber
public class PowerLevel {

    private static double health = 20;
    private static double attack = 1;
    private static double armor = 0;

    private static double mana = 100;
    private static double maxMana = 100;
    private static double resist = 1;
    private static double power = 1;
    private static double cast = 1;
    private static double cooldown = 1;

    private static double critDmg = 0;
    private static double critChance = 0;
    private static double armorPierce = 0;
    private static double armorShred = 0;
    private static double protPierce = 0;
    private static double protShred = 0;

    private static double atkIV = 0;
    private static double armorIV = 0;
    private static double powIV = 0;
    private static double resIV = 0;
    private static double castIV = 0;
    private static double penIV = 0;
    private static double shredIV = 0;
    private static double critIV = 0;

    private static double atkSpeed = 0;

    private static double firePow = 0;
    private static double icePow = 0;
    private static double lightningPow = 0;
    private static double naturePow = 0;
    private static double enderPow = 0;
    private static double holyPow = 0;
    private static double eldritchPow = 0;
    private static double evocationPow = 0;
    private static double abyssalPow = 0;
    private static double bladePow = 0;
    private static double soundPow = 0;
    private static double windPow = 0;
    private static double symmetryPow = 0;
    private static double dunePow = 0;
    private static double spiritPow = 0;

    private static double fireRes = 0;
    private static double iceRes = 0;
    private static double lightningRes = 0;
    private static double natureRes = 0;
    private static double enderRes = 0;
    private static double holyRes = 0;
    private static double eldritchRes = 0;
    private static double evocationRes = 0;
    private static double abyssalRes = 0;
    private static double bladeRes = 0;
    private static double soundRes = 0;
    private static double windRes = 0;
    private static double symmetryRes = 0;
    private static double duneRes = 0;
    private static double spiritRes = 0;

    private static double manaRend = 0;
    private static double manaSteal = 0;
    private static double resPierce = 0;
    private static double resShred = 0;

    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player entity) {
            updatePowerValue(entity);
        }
    }

    @SubscribeEvent
    public static void onEffectEvent(MobEffectEvent.Added event) {
        LivingEntity entity = event.getEntity();
        updatePowerValue(entity);
    }


    @SubscribeEvent
    public static void onEffectEvent(MobEffectEvent.Expired event) {
        LivingEntity entity = event.getEntity();
        updatePowerValue(entity);
    }

    @SubscribeEvent
    public static void onEffectEvent(MobEffectEvent.Remove event) {
        LivingEntity entity = event.getEntity();
        updatePowerValue(entity);
    }

    @SubscribeEvent
    public static void onJoinEvent(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity entity)
            updatePowerValue(entity);
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        updatePowerValue(entity);
    }

    @SubscribeEvent
    public static void onCast(SpellOnCastEvent event) {
        LivingEntity entity = event.getEntity();
        updatePowerValue(entity);
    }

    private static void updatePowerValue(LivingEntity entity) {
        health = entity.getHealth();
        if (entity.getAttribute(Attributes.ARMOR) != null) {
            armor = entity.getAttributeValue(Attributes.ARMOR);
        }
        if (entity.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            attack = entity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            if (entity instanceof Player) {
                mana = MagicData.getPlayerMagicData(entity).getMana();
            }
            else {
                mana = 100;
            }
            maxMana = entity.getAttributeValue(AttributeRegistry.MAX_MANA);
            resist = entity.getAttributeValue(AttributeRegistry.SPELL_RESIST);
            power = entity.getAttributeValue(AttributeRegistry.SPELL_POWER);
            cast = entity.getAttributeValue(AttributeRegistry.CAST_TIME_REDUCTION);
            cooldown = entity.getAttributeValue(AttributeRegistry.COOLDOWN_REDUCTION);
        }

        critDmg = entity.getAttributeValue(ALObjects.Attributes.CRIT_DAMAGE);
        critChance = entity.getAttributeValue(ALObjects.Attributes.CRIT_CHANCE);
        armorPierce = entity.getAttributeValue(ALObjects.Attributes.ARMOR_PIERCE);
        armorShred = entity.getAttributeValue(ALObjects.Attributes.ARMOR_SHRED);
        protPierce = entity.getAttributeValue(ALObjects.Attributes.PROT_PIERCE);
        protShred = entity.getAttributeValue(ALObjects.Attributes.PROT_SHRED);

        atkIV = entity.getAttributeValue(PotatoEssentialsAttributes.ATTACK_IV);
        armorIV = entity.getAttributeValue(PotatoEssentialsAttributes.ARMOR_IV);
        powIV = entity.getAttributeValue(PotatoEssentialsAttributes.POWER_IV);
        resIV = entity.getAttributeValue(PotatoEssentialsAttributes.RESIST_IV);
        castIV = entity.getAttributeValue(PotatoEssentialsAttributes.CAST_IV);
        penIV = entity.getAttributeValue(PotatoEssentialsAttributes.ARMOR_PEN_IV);
        shredIV = entity.getAttributeValue(PotatoEssentialsAttributes.PROT_PEN_IV);
        critIV = entity.getAttributeValue(PotatoEssentialsAttributes.CRIT_IV);

        if (entity.getAttribute(Attributes.ATTACK_SPEED) == null) {
            atkSpeed = 2;
        }
        else {
            entity.getAttributeValue(Attributes.ATTACK_SPEED);
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            fireRes = entity.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER);
            iceRes = entity.getAttributeValue(AttributeRegistry.ICE_SPELL_POWER);
            lightningRes = entity.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER);
            natureRes = entity.getAttributeValue(AttributeRegistry.NATURE_SPELL_POWER);
            enderRes = entity.getAttributeValue(AttributeRegistry.ENDER_SPELL_POWER);
            holyRes = entity.getAttributeValue(AttributeRegistry.HOLY_SPELL_POWER);
            eldritchRes = entity.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER);
            evocationRes = entity.getAttributeValue(AttributeRegistry.EVOCATION_SPELL_POWER);
        }
        if (ModList.get().isLoaded("cataclysm_spellbooks")) {
            abyssalRes = entity.getAttributeValue(CSAttributeRegistry.ABYSSAL_MAGIC_POWER);
        }
        if (ModList.get().isLoaded("endersequipment")) {
            bladeRes = entity.getAttributeValue(EEAttributeRegistry.BLADE_SPELL_POWER);
        }
        if (ModList.get().isLoaded("alshanex_familiars")) {
            soundRes = entity.getAttributeValue(net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_SPELL_POWER);
        }
        if (ModList.get().isLoaded("aero_additions")) {
            windRes = entity.getAttributeValue(AASpells.Attributes.WIND_SPELL_POWER);
        }
        if (ModList.get().isLoaded("iss_magicfromtheeast")) {
            symmetryRes = entity.getAttributeValue(MFTEAttributeRegistries.SYMMETRY_SPELL_POWER);
            duneRes = entity.getAttributeValue(MFTEAttributeRegistries.DUNE_SPELL_POWER);
            spiritRes = entity.getAttributeValue(MFTEAttributeRegistries.SPIRIT_SPELL_POWER);
        }

        if (ModList.get().isLoaded("irons_spellbooks")) {
            fireRes = entity.getAttributeValue(AttributeRegistry.FIRE_MAGIC_RESIST);
            iceRes = entity.getAttributeValue(AttributeRegistry.ICE_MAGIC_RESIST);
            lightningRes = entity.getAttributeValue(AttributeRegistry.LIGHTNING_MAGIC_RESIST);
            natureRes = entity.getAttributeValue(AttributeRegistry.NATURE_MAGIC_RESIST);
            enderRes = entity.getAttributeValue(AttributeRegistry.ENDER_MAGIC_RESIST);
            holyRes = entity.getAttributeValue(AttributeRegistry.HOLY_MAGIC_RESIST);
            eldritchRes = entity.getAttributeValue(AttributeRegistry.ELDRITCH_MAGIC_RESIST);
            evocationRes = entity.getAttributeValue(AttributeRegistry.EVOCATION_MAGIC_RESIST);
        }
        if (ModList.get().isLoaded("cataclysm_spellbooks")) {
            abyssalRes = entity.getAttributeValue(CSAttributeRegistry.ABYSSAL_MAGIC_RESIST);
        }
        if (ModList.get().isLoaded("endersequipment")) {
            bladeRes = entity.getAttributeValue(EEAttributeRegistry.BLADE_MAGIC_RESIST);
        }
        if (ModList.get().isLoaded("alshanex_familiars")) {
            soundRes = entity.getAttributeValue(net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_MAGIC_RESIST);
        }
        if (ModList.get().isLoaded("aero_additions")) {
            windRes = entity.getAttributeValue(AASpells.Attributes.WIND_MAGIC_RESIST);
        }
        if (ModList.get().isLoaded("iss_magicfromtheeast")) {
            symmetryRes = entity.getAttributeValue(MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST);
            duneRes = entity.getAttributeValue(MFTEAttributeRegistries.DUNE_MAGIC_RESIST);
            spiritRes = entity.getAttributeValue(MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST);
        }

        manaRend = entity.getAttributeValue(PotatoEssentialsAttributes.MANA_REND);
        manaSteal = entity.getAttributeValue(PotatoEssentialsAttributes.MANA_STEAL);
        resPierce = entity.getAttributeValue(PotatoEssentialsAttributes.SPELL_RESIST_PIERCE);
        resShred = entity.getAttributeValue(PotatoEssentialsAttributes.SPELL_RESIST_SHRED);

        double castParse = RebalanceHandler.rebalanceCastFormula(cast);
        double cooldownParse = RebalanceHandler.rebalanceCooldownFormula(cooldown);
        double resistParse = RebalanceHandler.rebalanceResistFormula(resist);

        double armorLevel;
        double manaLevel;
        double resistLevel;
        double spellLevel;
        double castLevel;
        double cooldownLevel;
        double criticalLevel;
        double critLevel;
        double critMagicLevel;
        double pierceLevel;
        double shredLevel;
        double bypassLevel;
        double ivLevel;
        double defensePower = 0;
        double magicalPower;
        double attackPower;
        double attackLevel;
        double powerLevel;
        double elementalResist;
        double spellShred;
        double typeMod = 1;
        double raceMod = 1;

        // Defensive attributes
        armorLevel = armor;
        elementalResist = RebalanceHandler.rebalanceResistFormula(fireRes) * RebalanceHandler.rebalanceResistFormula(iceRes)
                * RebalanceHandler.rebalanceResistFormula(lightningRes) * RebalanceHandler.rebalanceResistFormula(natureRes)
                * RebalanceHandler.rebalanceResistFormula(enderRes) * RebalanceHandler.rebalanceResistFormula(holyRes)
                * RebalanceHandler.rebalanceResistFormula(eldritchRes) * RebalanceHandler.rebalanceResistFormula(evocationRes)
                * RebalanceHandler.rebalanceResistFormula(abyssalRes) * RebalanceHandler.rebalanceResistFormula(bladeRes)
                * RebalanceHandler.rebalanceResistFormula(soundRes) * RebalanceHandler.rebalanceResistFormula(windRes)
                * RebalanceHandler.rebalanceResistFormula(symmetryRes) * RebalanceHandler.rebalanceResistFormula(duneRes)
                * RebalanceHandler.rebalanceResistFormula(spiritRes);
        resistLevel = (resistParse * elementalResist);
        double defensePowerPre = ((health * 0.05) * resistLevel) + (armorLevel * resistLevel); // Using vanilla health as base (1/20)
        if (entity.getType().is(PotatoTags.PLAYER))
            defensePower = defensePowerPre * ((mana / (maxMana + 1000)) + 1);

        // Universal combat attributes
        criticalLevel = Math.clamp(critChance, 0, 1);
        critLevel = Math.max(Math.pow(critDmg, criticalLevel + 0.25), 1);
        pierceLevel = (armorPierce + (1.5 * protPierce)) * 0.05;
        shredLevel = (0.5 * Math.clamp(armorShred, 0, 1)) + Math.clamp(protShred, 0, 1);
        bypassLevel = 1 + (pierceLevel + shredLevel);

        double[] values = {firePow, icePow, lightningPow,
                naturePow, enderPow, holyPow, eldritchPow,
                evocationPow, abyssalPow, bladePow, soundPow,
                windPow, symmetryPow, dunePow, spiritPow};
        double elementalPower = Double.NEGATIVE_INFINITY;

        for (double val : values) {
            if (val > elementalPower) elementalPower = val;
        }

        // Magic attributes
        manaLevel = 0.5 * ((mana <= 100 ? mana : 100 + Math.sqrt(mana - 100)) + (maxMana <= 100 ? maxMana : 100 + Math.sqrt(maxMana - 100)));
        spellLevel = power * elementalPower * (1 + (manaRend / 100));
        castLevel = (1 + castParse) == 0 ? -0.01 : (1 + castParse);
        cooldownLevel = (1 + cooldownParse) == 0 ? -0.01 : (1 + cooldownParse);
        critMagicLevel = Math.max(Math.pow(critDmg, criticalLevel), 1);
        spellShred = RebalanceHandler.rebalanceResistFormula(resPierce) * (1 + resShred);
        magicalPower = manaLevel * spellLevel * castLevel * cooldownLevel * bypassLevel * critMagicLevel * spellShred;

        // Attack
        attackLevel = 5 * attack * atkSpeed * (1 + (manaRend / 100));
        attackPower = attackLevel * bypassLevel * critLevel; // Critical for attack has more value because of jump crit

        // IV bonus
        ivLevel = (atkIV + powIV + armorIV + resIV + castIV + critIV + penIV + shredIV) * 0.125;

        if (entity.getType().is(PotatoTags.BOSS)) typeMod = ConfigFormulas.boss_mod * 0.75;
        if (entity.getType().is(PotatoTags.MINIBOSS)) typeMod = ConfigFormulas.mini_mod * 0.5;
        if (entity.getType().is(PotatoTags.NORMAL)) typeMod = ConfigFormulas.mob_mod * 0.3;
        if (entity.getType().is(PotatoTags.SUMMON)) typeMod = ConfigFormulas.summon_mod * 0.25;

        if (entity.getType().is(PotatoTags.RACE_AMORPH)) raceMod = 1.15;
        if (entity.getType().is(PotatoTags.RACE_BRUTE)) raceMod = 1.05;
        if (entity.getType().is(PotatoTags.RACE_CONSTRUCT)) raceMod = 1.2;
        if (entity.getType().is(PotatoTags.RACE_DRAGON)) raceMod = 1.2;
        if (entity.getType().is(PotatoTags.RACE_DRAGONBORN)) raceMod = 1.15;
        if (entity.getType().is(PotatoTags.RACE_FISH)) raceMod = 1.05;
        if (entity.getType().is(PotatoTags.RACE_FLYING)) raceMod = 1;
        if (entity.getType().is(PotatoTags.RACE_GOLEM)) raceMod = 1.25;
        if (entity.getType().is(PotatoTags.RACE_HUMAN)) raceMod = 1.05;
        if (entity.getType().is(PotatoTags.RACE_HUMANOID)) raceMod = 1.025;
        if (entity.getType().is(PotatoTags.RACE_INSECT)) raceMod = 1.15;

        // Power Level
        double powerLevelPre = (defensePower + attackPower + magicalPower) * (1 + ivLevel) * typeMod * raceMod * (1 + (manaSteal / 10));
        powerLevel = BigDecimal.valueOf(powerLevelPre).setScale(0, RoundingMode.FLOOR).intValue();

        var instance = entity.getAttributes().getInstance(PotatoEssentialsAttributes.POWER_LEVEL);
        if (instance == null) return;

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("potatoessentials", "power_level");
        instance.removeModifier(id);
        instance.addPermanentModifier(new AttributeModifier(id, powerLevel, AttributeModifier.Operation.ADD_VALUE));
    }
}
 */
