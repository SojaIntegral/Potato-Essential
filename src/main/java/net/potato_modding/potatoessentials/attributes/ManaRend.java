package net.potato_modding.potatoessentials.attributes;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;

@EventBusSubscriber
public class ManaRend {

    @SubscribeEvent
    public static void manaRendEvent(LivingIncomingDamageEvent event) {
        var victim = event.getEntity();
        var attacker = event.getSource().getEntity();

        if (!(attacker instanceof LivingEntity livingEntity)) return;

        var hasManaRend = livingEntity.getAttribute(PotatoEssentialsAttributes.MANA_REND);
        var targetHasMana = victim.getAttribute(AttributeRegistry.MAX_MANA);

        if (hasManaRend == null) return;
        double manaRendAttr = livingEntity.getAttributeValue(PotatoEssentialsAttributes.MANA_REND);
        if (manaRendAttr <= 0) return;

        double victimMaxMana;
        double victimBaseMana;
        if(targetHasMana != null) {
            victimMaxMana = victim.getAttributeValue(AttributeRegistry.MAX_MANA);
            victimBaseMana = victim.getAttributeBaseValue(AttributeRegistry.MAX_MANA);
        }
        else {
            victimMaxMana = 100;
            victimBaseMana = 100;
        }

        double step;
        if(victim instanceof Player player) {
            var targetPlayerMagicData = MagicData.getPlayerMagicData(player);
            step = (targetPlayerMagicData.getMana() / victimBaseMana * 0.01);
        }
        else {
            step = (victimMaxMana / victimBaseMana) * 0.01;
        }

        double totalExtraDamagerPercent = 1 + (step * manaRendAttr);

        event.setAmount((float) (event.getAmount() * totalExtraDamagerPercent));
    }
}
