package org.abstruck.mc.cybermc.init;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.abstruck.mc.cybermc.container.containerscreen.OperatingTableContainerScreen;
import org.abstruck.mc.cybermc.event.KeyboardInputHandler;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {
    @SubscribeEvent
    public static void onClientSetupEvent(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ScreenManager.register(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), OperatingTableContainerScreen::new);
        });
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(KeyboardInputHandler.CAPS_LOCK));
    }

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event){
        event.enqueueWork(CapabilityInit::register);
    }
}
