package org.abstruck.mc.cybermc.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author Astrack
 */
@Mod.EventBusSubscriber
public class PlayerLogInEvent {
    @SubscribeEvent
    public void playerLogIn(@NotNull EntityJoinWorldEvent event){
        if (!(event.getEntity() instanceof PlayerEntity)){
            return;
        }
        PlayerProfileManager.getInstance().addPlayer((PlayerEntity) event.getEntity());
    }
}
