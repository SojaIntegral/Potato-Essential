package net.potato_modding.potatoessentials.attributes;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;

@EventBusSubscriber
public class ManaShield {

    @SubscribeEvent
    public static void manaShieldEvent(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        float original = event.getAmount();
        DamageContainer container = event.getContainer();

        var playerMana = MagicData.getPlayerMagicData(player);
        double currentMana = playerMana.getMana();
        double maxMana = player.getAttributeValue(AttributeRegistry.MAX_MANA);
        double manaShield = player.getAttributeValue(PotatoEssentialsAttributes.MANA_SHIELD);

        if ((player.getAttributeValue(PotatoEssentialsAttributes.MANA_SHIELD) <= 0) || (currentMana <= 0)) return;
        if (player.isBlocking() && !event.getSource().is(DamageTypeTags.BYPASSES_SHIELD)) return;

        float reduction = (float) (Math.min(manaShield * (currentMana / (maxMana + 1000)), 0.95));
        float cost = (float) (currentMana - (currentMana * reduction * 0.2));
        playerMana.setMana(cost);
        container.addModifier(DamageContainer.Reduction.MOB_EFFECTS, (ct, base) -> reduction);

        event.setAmount(original * (1 - reduction));
    }
}



