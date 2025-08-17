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

    // Attributes config
    public static ModConfigSpec.ConfigValue<Boolean> BOSS_SWITCH;
    public static ModConfigSpec.ConfigValue<Integer> BOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> MINIBOSS_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> MOB_RESIST;
    public static ModConfigSpec.ConfigValue<Integer> SUMMON_RESIST;
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
        }
        BUILDER.pop();
        BUILDER.push("ATTRIBUTES");
        {
            BUILDER.push("Attribute System");
            BUILDER.comment("If mobs will have random variation in their attributes (does not include players)");
            IV_SYSTEM = BUILDER.worldRestart().define("Random Attribute Variation", true);
            BUILDER.comment("Chance for perfect attributes | 1 = 100%");
            BUILDER.comment("4096 = 0.025% chance | Min: 1 | Max: 8192 | Default: 4096");
            SHINY_CHANCE = BUILDER.worldRestart().define("Chance for Perfect Attributes", 4096);
            BUILDER.comment("Bonus attributes for mobs will be increased by whatever number you put here");
            BUILDER.comment("Maximum: 10000% | Minimum: 1% | Default: 15%");
            IV_RANGE = BUILDER.worldRestart().define("Attributes variance", 15);
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
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                BOSS_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Bosses", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                MINIBOSS_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Minibosses", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                MOB_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Mobs", 100);
                BUILDER.comment("Only works when Automatic Rebalance is [true]");
                BUILDER.comment("Multiplies the resistances (in %) | Default: 100% | Max: 10000%");
                SUMMON_RESIST = BUILDER.worldRestart().define("Bonus Spell Resistance for Summons", 100);
                BUILDER.pop();
            }
            BUILDER.pop();
        }
        BUILDER.pop();
        BUILDING = BUILDER.build();
    }
}