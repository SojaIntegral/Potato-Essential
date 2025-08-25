package net.potato_modding.potatoessentials.utils;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.potato_modding.potatoessentials.config.ServerConfigs;

@EventBusSubscriber
public class JumpCritTweaker {

    @SubscribeEvent
    public static void jumpCritNerf(CriticalHitEvent event) {
        if(!ServerConfigs.JUMP_CRIT_TOGGLE.get()) return;
        float jumpCrit = (float) Math.max(ServerConfigs.JUMP_CRIT.get() + 1, 1);
        if (event.isVanillaCritical()) {
            event.setDamageMultiplier(jumpCrit);
        }
    }
}
