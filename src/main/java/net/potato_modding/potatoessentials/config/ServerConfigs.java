package net.potato_modding.potatoessentials.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigs {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec BUILDING;

    // Main config
    public static ModConfigSpec.ConfigValue<Integer> FORMULA_REBALANCE;
    public static ModConfigSpec.ConfigValue<Boolean> RE_BALANCE;
    public static ModConfigSpec.ConfigValue<String> FORMULA_CAST_CUSTOM;
    public static ModConfigSpec.ConfigValue<String> FORMULA_RESIST_CUSTOM;
    public static ModConfigSpec.ConfigValue<String> FORMULA_COOLDOWN_CUSTOM;
    public static ModConfigSpec.ConfigValue<Boolean> BUFF_STACKING;
    public static ModConfigSpec.ConfigValue<Boolean> BURN_REMOVE;
    public static ModConfigSpec.ConfigValue<Boolean> JUMP_CRIT_TOGGLE;
    public static ModConfigSpec.ConfigValue<Double> JUMP_CRIT;

    // Attributes config
    public static ModConfigSpec.ConfigValue<Boolean> BOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Boolean> BOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Double> BOSS_RESIST_VALUE;
    public static ModConfigSpec.ConfigValue<Double> BOSS_LIFESTEAL;
    public static ModConfigSpec.ConfigValue<Boolean> MINIBOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MINIBOSS_RESIST_VALUE;
    public static ModConfigSpec.ConfigValue<Boolean> MOB_RESIST;
    public static ModConfigSpec.ConfigValue<Double> MOB_RESIST_VALUE;
    public static ModConfigSpec.ConfigValue<Boolean> SUMMON_RESIST;
    public static ModConfigSpec.ConfigValue<Double> SUMMON_RESIST_VALUE;
    public static ModConfigSpec.ConfigValue<Boolean> IV_SYSTEM;
    public static ModConfigSpec.ConfigValue<Integer> SHINY_CHANCE;

    // Familiars Compat
    public static ModConfigSpec.ConfigValue<Integer> IV_RANGE;
    public static ModConfigSpec.ConfigValue<Boolean> NATURE_SYSTEM;

    static {

        // MAIN CONFIG
        BUILDER.push("MAIN");
        {
            {
                BUILDER.push("Pre-sets");
                BUILDER.comment("Valid values: from 0 to 5");
                BUILDER.comment("0 = 'Classic': Old ISS formula");
                BUILDER.comment("1 = 'Default': New ISS formula");
                BUILDER.comment("2 = 'Nerfed': For very light ISS modpacks [max at 3.433]");
                BUILDER.comment("3 = 'Recommended': Made for regular modpacks and multiplayer [max at 4.152]");
                BUILDER.comment("4 = 'Apotheosis': Made modpacks with Apotheosis and other 'ridiculous' mods [max at 7.733]");
                BUILDER.comment("5 = 'Alternative': Great for balanced ISS PvP (Heavily nerfs scaling)");
                FORMULA_REBALANCE = BUILDER.worldRestart().define("Rebalance", 1);
            }
            BUILDER.pop();
            {
                BUILDER.push("Custom Formula");
                BUILDER.comment("WARNING: Do not mess with this if you don't know what you are doing!");
                RE_BALANCE = BUILDER.worldRestart().define("Enable Custom Formula", false);
                BUILDER.comment("Enabling custom formula disables all pre-sets");
                BUILDER.comment("Only use if you know exactly what you are doing!");
                FORMULA_COOLDOWN_CUSTOM = BUILDER.worldRestart().define("Cooldown Reduction formula", "x >= 0 ? (2 * x) / (x + 1) : x");
                FORMULA_CAST_CUSTOM = BUILDER.worldRestart().define("Cast Time formula", "x >= 0 ? (2 * x) / (x + 1) : x");
                FORMULA_RESIST_CUSTOM = BUILDER.worldRestart().define("Spell Resistance formula", "x >= 0 ? (2 * x) / (x + 1) : x");
            }
            BUILDER.pop();
        }
        BUILDER.pop();
        BUILDER.push("EXTRAS");
        {
            {
                BUILDER.push("Effect Stacking");
                BUILDER.comment("By turning this on, you can prevent two effects from being applied at the same time");
                BUILDER.comment("WARNING: Must be configured via datapack! See instructions in [mob_effect] folder!");
                BUFF_STACKING = BUILDER.worldRestart().define("Buff Stacking", true);
            }
            BUILDER.pop();
            {
                BUILDER.push("Remove Burning");
                BUILDER.comment("Turn this feature off if your game crashes when something catches fire");
                BURN_REMOVE = BUILDER.worldRestart().define("Fire Immune doesn't burn", true);
            }
            BUILDER.pop();
            {
                BUILDER.push("Jump-Crit Tweaker");
                BUILDER.comment("If this is enabled, jump-crits won't be affected by Apotheosis modifiers");
                JUMP_CRIT_TOGGLE = BUILDER.worldRestart().define("Activate", true);
                BUILDER.comment("Default: 0.5 (Vanilla - 1.5x damage) | Min: 0 (no bonus damage)");
                JUMP_CRIT = BUILDER.worldRestart().define("Crit Bonus Damage", 0.5);
            }
            BUILDER.pop();
        }
        BUILDER.pop();
        BUILDER.push("ATTRIBUTES");
        {
            BUILDER.push("Attribute System");
            BUILDER.comment("If mobs will have random variation in their attributes (does not include players)");
            IV_SYSTEM = BUILDER.worldRestart().define("Random Attribute Variation", true);
            BUILDER.comment("Bonus attributes for mobs will be increased by whatever number you put here");
            BUILDER.comment("Maximum: 10000% | Minimum: 1% | Default: 15%");
            IV_RANGE = BUILDER.worldRestart().define("Attributes variance", 15);
            BUILDER.comment("Chance for perfect attributes | 1 = 100%");
            BUILDER.comment("4096 = 0.025% chance | Min: 1 | Max: 8192 | Default: 4096");
            SHINY_CHANCE = BUILDER.worldRestart().define("Chance for Perfect Attributes", 4096);
            BUILDER.comment("Natures increase one attribute by 10% and reduce another by the same amount");
            BUILDER.comment("This bonus multiplies everything else, so is quite powerful");
            BUILDER.comment("WARNING: Only includes familiars by default! Add new ones via datapack");
            NATURE_SYSTEM = BUILDER.worldRestart().define("Mobs Natures", true);
            BUILDER.pop();
        }
        {
            BUILDER.push("Automatic Balance");
            BUILDER.comment("Turns ON/OFF difficulty scaling for ALL mobs | Default: true [ON]");
            BUILDER.comment("Automatic Rebalance does not affect manual bosses attributes");
            BOSS_SWITCH = BUILDER.worldRestart().define("Automatic Rebalance", true);
            {
                BUILDER.push("Modifiers");
                BUILDER.comment("WARNING: Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Whether these types of mobs will have bonus multiplier from formula type");
                BUILDER.comment("Recommended to disable if custom");
                BOSS_RESIST = BUILDER.worldRestart().define("Automatic Bosses Multiplier", false);
                BUILDER.comment("Value used when disabled | Default: 1.0");
                BOSS_RESIST_VALUE = BUILDER.worldRestart().define("Manual Boss Multiplier", 1.0);
                BUILDER.comment("Whether these types of mobs will have bonus multiplier from formula type");
                BUILDER.comment("Recommended to disable if custom");
                MINIBOSS_RESIST = BUILDER.worldRestart().define("Mini-Boss", false);
                BUILDER.comment("Value used when disabled | Default: 1.0");
                MINIBOSS_RESIST_VALUE = BUILDER.worldRestart().define("Manual Mini-Boss Multiplier", 1.0);
                BUILDER.comment("Whether these types of mobs will have bonus multiplier from formula type");
                BUILDER.comment("Recommended to disable if custom");
                MOB_RESIST = BUILDER.worldRestart().define("Regular Mobs", false);
                BUILDER.comment("Value used when disabled | Default: 1.0");
                MOB_RESIST_VALUE = BUILDER.worldRestart().define("Manual Mobs Multiplier", 1.0);
                BUILDER.comment("Whether these types of mobs will have bonus multiplier from formula type");
                BUILDER.comment("Recommended to disable if custom");
                SUMMON_RESIST = BUILDER.worldRestart().define("Summons", false);
                BUILDER.comment("Value used when disabled | Default: 1.0");
                SUMMON_RESIST_VALUE = BUILDER.worldRestart().define("Manual Summons Multiplier", 1.0);
                BUILDER.pop();
            }
            {
                BUILDER.push("Life-Steal");
                BUILDER.comment("Life-Steal attribute for bosses | Default: 0.75 | Set to 0 to disable");
                BOSS_LIFESTEAL = BUILDER.worldRestart().define("Boss Life-Steal", 0.75);
                BUILDER.pop();
            }
            BUILDER.pop();
        }
        BUILDER.pop();
        BUILDING = BUILDER.build();
    }
}