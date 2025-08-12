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

    static {

        // MAIN CONFIG
        BUILDER.push("MAIN");
        {
            {
                BUILDER.push("Pre-sets");
                BUILDER.comment("Valid values: from 0 to 5");
                BUILDER.comment("0 = 'Classic': Old ISS formula");
                BUILDER.comment("1 = 'Default': New ISS formula");
                BUILDER.comment("2 = 'Nerfed': For very light ISS modpacks");
                BUILDER.comment("3 = 'Recommended': Made for regular modpacks and multiplayer");
                BUILDER.comment("4 = 'Apotheosis': Made modpacks with Apotheosis and other 'ridiculous' mods");
                BUILDER.comment("5 = 'Alternative': Great for balanced ISS PvP (Heavily nerfs scaling)");
                FORMULA_REBALANCE = BUILDER.worldRestart().define("Rebalance", 1);
            }
            BUILDER.pop();
            {
                BUILDER.push("Custom Formula");
                BUILDER.comment("WARNING: Do not mess with this if you don't know what you are doing!");
                RE_BALANCE = BUILDER.worldRestart().define("Enable Custom Formula", false);
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
        BUILDING = BUILDER.build();
    }
}