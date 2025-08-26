/*
 * MIT License
 * Copyright (c) 2025 Sofia Bergi
 * See the LICENSE file for more information.
 */

package net.potato_modding.potatoessentials;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.potato_modding.potatoessentials.config.ServerConfigs;
import net.potato_modding.potatoessentials.datagen.MobElementLoader;
import net.potato_modding.potatoessentials.datagen.MobRaceLoader;
import net.potato_modding.potatoessentials.registry.PotatoEssentialsAttributes;
import net.potato_modding.potatoessentials.tags.DynamicElements;
import net.potato_modding.potatoessentials.tags.DynamicRaces;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Mod(PotatoEssentials.MOD_ID)
public class PotatoEssentials {
    public static final String MOD_ID = "potatoessentials";
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public PotatoEssentials(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.addListener(this::onReloadListeners);

        PotatoEssentialsAttributes.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfigs.BUILDING, MOD_ID + "-server.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    private void onReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new MobRaceLoader());
        event.addListener(new MobElementLoader());
        event.addListener(new DynamicRaces());
        event.addListener(new DynamicElements());
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(PotatoEssentials.MOD_ID, path);
    }
}
