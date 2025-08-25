package net.potato_modding.potatoessentials.utils;

import net.potato_modding.potatoessentials.config.ServerConfigs;

import java.util.ArrayList;
import java.util.List;

public class ConfigFormulas {

    public static double modifier;

    // Rebalance Formula
    static {
        // List of possible valid configs
        List<Integer> safetyCheck = new ArrayList<>();
        safetyCheck.add(1);
        safetyCheck.add(2);
        safetyCheck.add(3);
        safetyCheck.add(4);
        safetyCheck.add(5);
        safetyCheck.add(6);

        // Defining variables that hod the values that were input in the configs
        int config_check = net.potato_modding.potatoessentials.config.ServerConfigs.FORMULA_REBALANCE.get();
        // Making sure we aren't trying to math out nonsense for spawn_armor.json
        if (!safetyCheck.contains(net.potato_modding.potatoessentials.config.ServerConfigs.FORMULA_REBALANCE.get())) config_check = 4;
        if (config_check == 3) modifier = 1.225;
        else if (config_check == 4 || config_check == 6) modifier = 2.5;
        else modifier = 1;
    }

    // Modifiers
    public static double mobType = 0;
    public static double ArmorMod = 0;
    public static double ToughMod = 0;
    public static double AttackMod = 0;

    // These modifiers ensure that things don't get out of hand, but also aren't too easy
    public static double boss_mod = ServerConfigs.BOSS_RESIST.get() ? 1.15 * modifier : ServerConfigs.BOSS_RESIST_VALUE.get();
    public static double mini_mod = ServerConfigs.MINIBOSS_RESIST.get() ? modifier : ServerConfigs.MINIBOSS_RESIST_VALUE.get();
    public static double mob_mod = ServerConfigs.MOB_RESIST.get() ? 0.85 * modifier : ServerConfigs.MOB_RESIST_VALUE.get();
    public static double summon_mod = ServerConfigs.SUMMON_RESIST.get() ? 0.9 * modifier : ServerConfigs.SUMMON_RESIST_VALUE.get();

    // Familiars random attr value
    public static double randMax = ServerConfigs.IV_SYSTEM.get() ?
            (double) Math.clamp(ServerConfigs.IV_RANGE.get(), 1, 10000) / 100 : 1;

    public static int shinyChanceModifier = Math.clamp(ServerConfigs.SHINY_CHANCE.get(), 1, 8192);
}