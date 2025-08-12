package net.potato_modding.potatoessentials.attributes;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;

@EventBusSubscriber
public class ManaSteal {

    @SubscribeEvent
    public static void manaStealEvent(LivingDamageEvent.Post event) {
        var sourceEntity = event.getSource().getEntity();
        var target = event.getEntity();

        if (!(sourceEntity instanceof LivingEntity livingEntity)) return;
        if (!(livingEntity instanceof ServerPlayer player)) return;

        var hasManaSteal = player.getAttribute(PotatoEssentialsAttributes.MANA_STEAL);

        if (hasManaSteal == null) return;

        double manaStealAttr = player.getAttributeValue(PotatoEssentialsAttributes.MANA_STEAL);
        double targetMana = target.getAttributeValue(AttributeRegistry.MAX_MANA);
        var attackerPlayerMagicData = MagicData.getPlayerMagicData(player);

        if (manaStealAttr <= 0) return;

        int addMana;
        if(target.getAttributes().hasAttribute(AttributeRegistry.MAX_MANA)) {
            addMana = (int) Math.min(100 + attackerPlayerMagicData.getMana(), (manaStealAttr * event.getOriginalDamage()) + attackerPlayerMagicData.getMana());
        }
        else {
            addMana = (int) Math.min(targetMana, (manaStealAttr * event.getOriginalDamage()) + attackerPlayerMagicData.getMana());
        }

        attackerPlayerMagicData.setMana(addMana);
        PacketDistributor.sendToPlayer(player, new SyncManaPacket(attackerPlayerMagicData));

        if (target instanceof ServerPlayer serverTargetPlayer) {
            var targetPlayerMagicData = MagicData.getPlayerMagicData(serverTargetPlayer);

            if(targetMana <= 0) return;

            int subMana = (int) Math.max(targetPlayerMagicData.getMana() - (manaStealAttr * event.getOriginalDamage()), 0);

            targetPlayerMagicData.setMana(subMana);
            PacketDistributor.sendToPlayer(player, new SyncManaPacket(targetPlayerMagicData));
        }
    }
}
