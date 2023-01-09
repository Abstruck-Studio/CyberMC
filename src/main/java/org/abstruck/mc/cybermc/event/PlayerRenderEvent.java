package org.abstruck.mc.cybermc.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.capability.ModCapability;

/**
 * @author Goulixiaoji
 */
@Mod.EventBusSubscriber
public class PlayerRenderEvent {
    @SubscribeEvent
    public static void playerRender(RenderPlayerEvent event){
        PlayerEntity player = event.getPlayer();
        player.getCapability(ModCapability.CAP).ifPresent((cap) -> {

        });
    }
}
