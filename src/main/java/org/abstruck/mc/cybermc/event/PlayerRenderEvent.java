package org.abstruck.mc.cybermc.event;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

/**
 * @author Goulixiaoji
 */
@Mod.EventBusSubscriber
public class PlayerRenderEvent {
    @SubscribeEvent
    public static void playerRender(@NotNull RenderPlayerEvent event){
//        PlayerEntity player = event.getPlayer();
//        player.getCapability(ModCapability.CAP).ifPresent((cap) -> {
//
//        });
    }
}
