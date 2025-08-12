package net.potato_modding.potatoessentials.registry;

import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.PercentageAttribute;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.potato_modding.potatoessentials.PotatoEssentials;

@EventBusSubscriber
public class PotatoEssentialsAttributes {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, PotatoEssentials.MOD_ID);

    //Spell shred
    public static final DeferredHolder<Attribute, Attribute> SPELL_RESIST_SHRED =
            registerSpecialAttributes("spell_res_shred", 0, 0, 1);
    public static final DeferredHolder<Attribute, Attribute> SPELL_RESIST_PIERCE =
            registerRegularAttributes("spell_res_pierce", 0, 0, 1000);

    //Mana Steal
    public static final DeferredHolder<Attribute, Attribute> MANA_STEAL =
            registerRegularAttributes("mana_steal",0,0,10);

    //Mana Rend
    public static final DeferredHolder<Attribute, Attribute> MANA_REND =
            registerRegularAttributes("mana_rend",0,0,10);

    //Mana Shield
    public static final DeferredHolder<Attribute, Attribute> MANA_SHIELD =
            registerRegularAttributes("mana_shield",0,0,10);

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType ->
                ATTRIBUTES.getEntries().forEach(
                        attributeDeferredHolder -> event.add(entityType, attributeDeferredHolder
                        )));
    }

    private static DeferredHolder<Attribute, Attribute> registerSpecialAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new PercentageAttribute("attribute.potatospellbookstweaks." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerMagicAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new MagicRangedAttribute("attribute.potatospellbookstweaks." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerRegularAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new RangedAttribute("attribute.potatospellbookstweaks." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }
}
