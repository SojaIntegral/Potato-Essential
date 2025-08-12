package net.potato_modding.potatoessentials.utils;

import com.snackpirate.aeromancy.spells.AASpells;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.cataclysm_spellbooks.registries.CSAttributeRegistry;
import net.ender.endersequipment.registries.EEAttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
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
import net.potato_modding.potatospells.registry.PotatoAttributes;
import net.potato_modding.potatospells.tags.PotatoTags;
import net.potato_modding.potatospells.utils.ConfigFormulas;
import net.warphan.iss_magicfromtheeast.registries.MFTEAttributeRegistries;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EventBusSubscriber
public class PowerLevel {

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
        double health = entity.getHealth();
        double armor = (entity.getAttribute(Attributes.ARMOR) == null) ? 0 : entity.getAttributeValue(Attributes.ARMOR);
        double attack = (entity.getAttribute(Attributes.ATTACK_DAMAGE) == null) ? 0 : entity.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double mana = (entity instanceof Player) ? ((ModList.get().isLoaded("irons_spellbooks")) ? MagicData.getPlayerMagicData(entity).getMana() : 100) : 100;
        double maxMana = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.MAX_MANA) : 100);
        double resist = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.SPELL_RESIST) : 1);
        double power = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.SPELL_POWER) : 1);
        double cast = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.CAST_TIME_REDUCTION) : 1);
        double cooldown = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.COOLDOWN_REDUCTION) : 1);
        double critDmg = entity.getAttributeValue(ALObjects.Attributes.CRIT_DAMAGE);
        double critChance = entity.getAttributeValue(ALObjects.Attributes.CRIT_CHANCE);
        double armorPierce = entity.getAttributeValue(ALObjects.Attributes.ARMOR_PIERCE);
        double armorShred = entity.getAttributeValue(ALObjects.Attributes.ARMOR_SHRED);
        double protPierce = entity.getAttributeValue(ALObjects.Attributes.PROT_PIERCE);
        double protShred = entity.getAttributeValue(ALObjects.Attributes.PROT_SHRED);
        double atkIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.ATTACK_IV) : 0);
        double armorIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.ARMOR_IV) : 0);
        double powIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.POWER_IV) : 0);
        double resIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.RESIST_IV) : 0);
        double castIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.CAST_IV) : 0);
        double penIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.ARMOR_PEN_IV) : 0);
        double shredIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.PROT_PEN_IV) : 0);
        double critIV = ((ModList.get().isLoaded("potatospellbookstweaks")) ? entity.getAttributeValue(PotatoAttributes.CRIT_IV) : 0);
        double atkSpeed = (entity.getAttribute(Attributes.ATTACK_SPEED) == null) ? 0 :  entity.getAttributeValue(Attributes.ATTACK_SPEED);
        double firePow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.FIRE_SPELL_POWER) : 1);
        double icePow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.ICE_SPELL_POWER) : 1);
        double lightningPow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.LIGHTNING_SPELL_POWER) : 1);
        double naturePow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.NATURE_SPELL_POWER) : 1);
        double enderPow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.ENDER_SPELL_POWER) : 1);
        double holyPow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.HOLY_SPELL_POWER) : 1);
        double eldritchPow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.ELDRITCH_SPELL_POWER) : 1);
        double evocationPow = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.EVOCATION_SPELL_POWER) : 1);
        double abyssalPow = ((ModList.get().isLoaded("cataclysm_spellbooks")) ? entity.getAttributeValue(CSAttributeRegistry.ABYSSAL_MAGIC_POWER) : 1);
        double bladePow = ((ModList.get().isLoaded("endersequipment")) ? entity.getAttributeValue(EEAttributeRegistry.BLADE_SPELL_POWER) : 1);
        double soundPow = ((ModList.get().isLoaded("alshanex_familiars")) ? entity.getAttributeValue(net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_SPELL_POWER) : 1);
        double windPow = ((ModList.get().isLoaded("aero_additions")) ? entity.getAttributeValue(AASpells.Attributes.WIND_SPELL_POWER) : 1);
        double symmetryPow = ((ModList.get().isLoaded("iss_magicfromtheeast")) ? entity.getAttributeValue(MFTEAttributeRegistries.SYMMETRY_SPELL_POWER) : 1);
        double dunePow = ((ModList.get().isLoaded("iss_magicfromtheeast")) ? entity.getAttributeValue(MFTEAttributeRegistries.DUNE_SPELL_POWER) : 1);
        double spiritPow = ((ModList.get().isLoaded("iss_magicfromtheeast")) ? entity.getAttributeValue(MFTEAttributeRegistries.SPIRIT_SPELL_POWER) : 1);
        double fireRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.FIRE_MAGIC_RESIST) : 1);
        double iceRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.ICE_MAGIC_RESIST) : 1);
        double lightningRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.LIGHTNING_MAGIC_RESIST) : 1);
        double natureRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.NATURE_MAGIC_RESIST) : 1);
        double enderRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.ENDER_MAGIC_RESIST) : 1);
        double holyRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.HOLY_MAGIC_RESIST) : 1);
        double eldritchRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.ELDRITCH_MAGIC_RESIST) : 1);
        double evocationRes = ((ModList.get().isLoaded("irons_spellbooks")) ? entity.getAttributeValue(AttributeRegistry.EVOCATION_MAGIC_RESIST) : 1);
        double abyssalRes = ((ModList.get().isLoaded("cataclysm_spellbooks")) ? entity.getAttributeValue(CSAttributeRegistry.ABYSSAL_MAGIC_RESIST) : 1);
        double bladeRes = ((ModList.get().isLoaded("endersequipment")) ? entity.getAttributeValue(EEAttributeRegistry.BLADE_MAGIC_RESIST) : 1);
        double soundRes = ((ModList.get().isLoaded("alshanex_familiars")) ? entity.getAttributeValue(net.alshanex.familiarslib.registry.AttributeRegistry.SOUND_MAGIC_RESIST) : 1);
        double windRes = ((ModList.get().isLoaded("aero_additions")) ? entity.getAttributeValue(AASpells.Attributes.WIND_MAGIC_RESIST) : 1);
        double symmetryRes = ((ModList.get().isLoaded("iss_magicfromtheeast")) ? entity.getAttributeValue(MFTEAttributeRegistries.SYMMETRY_MAGIC_RESIST) : 1);
        double duneRes = ((ModList.get().isLoaded("iss_magicfromtheeast")) ? entity.getAttributeValue(MFTEAttributeRegistries.DUNE_MAGIC_RESIST) : 1);
        double spiritRes = ((ModList.get().isLoaded("iss_magicfromtheeast")) ? entity.getAttributeValue(MFTEAttributeRegistries.SPIRIT_MAGIC_RESIST) : 1);
        double manaRend = entity.getAttributeValue(PotatoEssentialsAttributes.MANA_REND);
        double manaSteal = entity.getAttributeValue(PotatoEssentialsAttributes.MANA_STEAL);
        double resPierce = entity.getAttributeValue(PotatoEssentialsAttributes.SPELL_RESIST_PIERCE);
        double resShred = entity.getAttributeValue(PotatoEssentialsAttributes.SPELL_RESIST_SHRED);

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
        if(entity.getType().is(PotatoTags.PLAYER)) defensePower = defensePowerPre * ((mana / (maxMana + 1000)) + 1);

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
        manaLevel = 0.5 * ((mana <= 100? mana : 100 + Math.sqrt(mana - 100)) + (maxMana <= 100? maxMana : 100 + Math.sqrt(maxMana - 100)));
        spellLevel = power * elementalPower * (1 + manaRend / 100);
        castLevel = (1 + castParse) == 0 ? -0.01 : (1 + castParse);
        cooldownLevel = (1 + cooldownParse) == 0 ? -0.01 : (1 + cooldownParse);
        critMagicLevel = Math.max(Math.pow(critDmg, criticalLevel), 1);
        spellShred = RebalanceHandler.rebalanceResistFormula(resPierce) * (1 + resShred);
        magicalPower = manaLevel * spellLevel * castLevel * cooldownLevel * bypassLevel * critMagicLevel * spellShred;

        // Attack
        attackLevel = 5 * attack * atkSpeed * (1 + manaRend / 100);
        attackPower = attackLevel * bypassLevel * critLevel; // Critical for attack has more value because of jump crit

        // IV bonus
        ivLevel = (atkIV + powIV + armorIV + resIV + castIV + critIV + penIV + shredIV) * 0.125;

        if(entity.getType().is(PotatoTags.BOSS)) typeMod = ConfigFormulas.boss_mod * 0.75;
        if(entity.getType().is(PotatoTags.MINIBOSS)) typeMod = ConfigFormulas.mini_mod * 0.5;
        if(entity.getType().is(PotatoTags.NORMAL)) typeMod = ConfigFormulas.mob_mod * 0.3;
        if(entity.getType().is(PotatoTags.SUMMON)) typeMod = ConfigFormulas.summon_mod * 0.25;

        if(entity.getType().is(PotatoTags.RACE_AMORPH)) raceMod = 1.15;
        if(entity.getType().is(PotatoTags.RACE_BRUTE)) raceMod = 1.05;
        if(entity.getType().is(PotatoTags.RACE_CONSTRUCT)) raceMod = 1.2;
        if(entity.getType().is(PotatoTags.RACE_DRAGON)) raceMod = 1.2;
        if(entity.getType().is(PotatoTags.RACE_DRAGONBORN)) raceMod = 1.15;
        if(entity.getType().is(PotatoTags.RACE_FISH)) raceMod = 1.05;
        if(entity.getType().is(PotatoTags.RACE_FLYING)) raceMod = 1;
        if(entity.getType().is(PotatoTags.RACE_GOLEM)) raceMod = 1.25;
        if(entity.getType().is(PotatoTags.RACE_HUMAN)) raceMod = 1.05;
        if(entity.getType().is(PotatoTags.RACE_HUMANOID)) raceMod = 1.025;
        if(entity.getType().is(PotatoTags.RACE_INSECT)) raceMod = 1.15;

        // Power Level
        double powerLevelPre = (defensePower + attackPower + magicalPower) * (1 + ivLevel) * typeMod * raceMod * (1 + manaSteal / 10);
        powerLevel = BigDecimal.valueOf(powerLevelPre).setScale(0, RoundingMode.FLOOR).intValue();

        var instance = entity.getAttributes().getInstance(PotatoEssentialsAttributes.POWER_LEVEL);
        if (instance != null)
        {
            instance.setBaseValue(powerLevel);
        }
    }
}

