package net.potato_modding.potatoessentials.utils;

import net.potato_modding.potatoessentials.config.ServerConfigs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

public class RebalanceHandler {

    /*
    SOFTCAP FORMULAS
    */

    private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("JavaScript");
    private static final Map<Double, Double> CACHE = new HashMap<>();
    private static String lastCooldownFormula = null;
    private static String lastResistFormula = null;
    private static String lastCastFormula = null;

    public static double customCooldownFormula(double x) {
        String cooldownFormula = ServerConfigs.FORMULA_COOLDOWN_CUSTOM.get();

        if (!cooldownFormula.equals(lastCooldownFormula)) {
            CACHE.clear();
            lastCooldownFormula = cooldownFormula;
        }

        if (CACHE.containsKey(x)) {
            return CACHE.get(x);
        }

        try {
            ENGINE.put("x", x);
            Object result = ENGINE.eval(cooldownFormula);
            if (result instanceof Number number) {
                return number.doubleValue();
            }
        } catch (Exception e) {
            System.err.println("Invalid formula: " + cooldownFormula);
            e.printStackTrace();
        }
        return 1.0;
    }

    public static double customResistFormula(double x) {
        String resistFormula = ServerConfigs.FORMULA_RESIST_CUSTOM.get();

        if (!resistFormula.equals(lastResistFormula)) {
            CACHE.clear();
            lastResistFormula = resistFormula;
        }

        if (CACHE.containsKey(x)) {
            return CACHE.get(x);
        }

        try {
            ENGINE.put("x", x);
            Object result = ENGINE.eval(resistFormula);
            if (result instanceof Number number) {
                return number.doubleValue();
            }
        } catch (Exception e) {
            System.err.println("Invalid formula: " + resistFormula);
            e.printStackTrace();
        }
        return 1.0;
    }

    public static double customCastFormula(double x) {
        String castFormula = ServerConfigs.FORMULA_CAST_CUSTOM.get();

        if (!castFormula.equals(lastCastFormula)) {
            CACHE.clear();
            lastCastFormula = castFormula;
        }

        if (CACHE.containsKey(x)) {
            return CACHE.get(x);
        }

        try {
            ENGINE.put("x", x);
            Object result = ENGINE.eval(castFormula);
            if (result instanceof Number number) {
                return number.doubleValue();
            }
        } catch (Exception e) {
            System.err.println("Invalid formula: " + castFormula);
            e.printStackTrace();
        }
        return 1.0;
    }

    private static final int reFormula = ServerConfigs.FORMULA_REBALANCE.get();
    private static final boolean enableCustom = ServerConfigs.RE_BALANCE.get();

    public static double rebalanceCooldownFormula(double x) {
        if(!enableCustom) {
            return switch (reFormula) {
                case 0 -> x <= 1.75 ? x : 1 / (-16 * (x - 1.5)) + 2;
                case 1 -> x <= 1.5 ? x : -.25 * (1 / (x - 1)) + 2;
                case 2 -> x <= 3.62699 ? 2 * (Math.sin(0.4 * (x + 0.3))) + 0.00624 : 2;
                case 3 -> x <= 4.80999 ? 2 * (Math.sin(0.28 * (x + 0.8))) + 0.034136 : 2;
                case 4 -> x <= 8.01198 ? 2 * (Math.sin(0.15 * (x + 2.46))) + 0.001736 : 2;
                default -> x >= 0 ? 1.966667 - (30 / (29 + x)) : 2 - ((20 - x) / 20);
            };
        }
        else return customCooldownFormula(x);
    }
    public static double rebalanceResistFormula(double x) {
        if(!enableCustom) {
            return switch (reFormula) {
                case 0 -> x <= 1.75 ? x : 1 / (-16 * (x - 1.5)) + 2;
                case 1 -> x <= 1.5 ? x : -.25 * (1 / (x - 1)) + 2;
                case 2 -> x <= 3.62699 ? 2 * (Math.sin(0.4 * (x + 0.3))) + 0.00624 : 2;
                case 3 -> x <= 4.80999 ? 2 * (Math.sin(0.28 * (x + 0.8))) + 0.034136 : 2;
                case 4 -> x <= 8.01198 ? 2 * (Math.sin(0.15 * (x + 2.46))) + 0.001736 : 2;
                default -> x >= 0 ? 1.966667 - (30 / (29 + x)) : 2 - ((20 - x) / 20);
            };
        }
        else return customResistFormula(x);
    }
    public static double rebalanceCastFormula(double x) {
        if(!enableCustom) {
            return switch (reFormula) {
                case 0 -> x <= 1.75 ? x : 1 / (-16 * (x - 1.5)) + 2;
                case 1 -> x <= 1.5 ? x : -.25 * (1 / (x - 1)) + 2;
                case 2 -> x <= 3.62699 ? 2 * (Math.sin(0.4 * (x + 0.3))) + 0.00624 : 2;
                case 3 -> x <= 4.80999 ? 2 * (Math.sin(0.28 * (x + 0.8))) + 0.034136 : 2;
                case 4 -> x <= 8.01198 ? 2 * (Math.sin(0.15 * (x + 2.46))) + 0.001736 : 2;
                default -> x >= 0 ? 1.966667 - (30 / (29 + x)) : 2 - ((20 - x) / 20);
            };
        }
        else return customCastFormula(x);
    }
}