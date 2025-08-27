package net.potato_modding.potatoessentials.utils;

import net.potato_modding.potatoessentials.config.ServerConfigs;

import java.util.ArrayList;
import java.util.List;

import static net.potato_modding.potatoessentials.utils.RebalanceHandler.rebalanceResistFormula;

public class ConfigFormulas {

    public static double modifier;

    public static double boss_mod;
    public static double mini_mod;
    public static double mob_mod;
    public static double summon_mod;

    // Modifiers
    public static double mobType = 0;
    public static double ArmorMod = 0;
    public static double ToughMod = 0;
    public static double AttackMod = 0;

    static {
        modifier = 1.75 / rebalanceResistFormula(2);

        if(!ServerConfigs.BOSS_RESIST.get()) boss_mod = ServerConfigs.BOSS_RESIST_VALUE.get();
        else boss_mod = 1.15 * modifier;

        if(!ServerConfigs.MINIBOSS_RESIST.get()) mini_mod = ServerConfigs.MINIBOSS_RESIST_VALUE.get();
        else mini_mod = modifier;

        if(!ServerConfigs.MOB_RESIST.get()) mob_mod = ServerConfigs.MOB_RESIST_VALUE.get();
        else mob_mod = 0.85 * modifier;

        if(!ServerConfigs.SUMMON_RESIST.get()) summon_mod = ServerConfigs.SUMMON_RESIST_VALUE.get();
        else summon_mod = 0.9 * modifier;
    }

    // Familiars random attr value
    public static double randMax = ServerConfigs.IV_SYSTEM.get() ?
            (double) Math.clamp(ServerConfigs.IV_RANGE.get(), 1, 10000) / 100 : 1;

    public static int shinyChanceModifier = Math.clamp(ServerConfigs.SHINY_CHANCE.get(), 1, 8192);
}