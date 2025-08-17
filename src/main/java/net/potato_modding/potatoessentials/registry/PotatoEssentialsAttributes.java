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

    public static final DeferredHolder<Attribute, Attribute> SHINY = registerRegularAttributes("shiny_attribute",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> ATTACK_IV = registerRegularAttributes("attack_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> ARMOR_IV = registerRegularAttributes("armor_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> POWER_IV = registerRegularAttributes("power_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> RESIST_IV = registerRegularAttributes("resist_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> CAST_IV = registerRegularAttributes("cast_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> ARMOR_PEN_IV = registerRegularAttributes("armor_pen_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> PROT_PEN_IV = registerRegularAttributes("prot_pen_iv",0,0,1);
    public static final DeferredHolder<Attribute, Attribute> CRIT_IV = registerRegularAttributes("crit_iv",0,0,1);

    //Spell Shred
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

    public static final DeferredHolder<Attribute, Attribute> POWER_LEVEL =
            registerRegularAttributes("power_level",0,0,999999999);

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    private static DeferredHolder<Attribute, Attribute> registerSpecialAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new PercentageAttribute("attribute.potatoessentials." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerMagicAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new MagicRangedAttribute("attribute.potatoessentials." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerRegularAttributes(String id, double defaultVal, double minVal, double maxVal) {
        return ATTRIBUTES.register(id, () ->
                (new RangedAttribute("attribute.potatoessentials." + id,
                        defaultVal, minVal, maxVal).setSyncable(true)));
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().forEach(entityType ->
                ATTRIBUTES.getEntries().forEach(
                        attributeDeferredHolder -> event.add(entityType, attributeDeferredHolder
                        )));
    }
}
