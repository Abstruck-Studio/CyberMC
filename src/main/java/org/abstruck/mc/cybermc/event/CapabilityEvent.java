package org.abstruck.mc.cybermc.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.capability.CyberMcCapabilityProvider;
import org.abstruck.mc.cybermc.capability.IModCapability;
import org.abstruck.mc.cybermc.capability.ModCapability;
import org.jetbrains.annotations.NotNull;

/**
 * @author Goulixiaoji
 */
@Mod.EventBusSubscriber
public class CapabilityEvent {
    @SubscribeEvent
    public static void attachCapability(@NotNull AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof PlayerEntity){
            event.addCapability(new ResourceLocation(Utils.MOD_ID, "modCap"), new CyberMcCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void playerCloned(PlayerEvent.Clone event){
        PlayerEntity player = event.getPlayer();
        World world = player.getCommandSenderWorld();
        if (world.isClientSide){
            return;
        }

        MinecraftServer server = world.getServer();
        boolean keepInventory = server.getGameRules().getBoolean(new GameRules.RuleKey<>("keepInventory", GameRules.Category.PLAYER));
        if (!keepInventory){
            return;
        }
        LazyOptional<IModCapability> oldSpeedCap = event.getOriginal().getCapability(ModCapability.CAP);
        LazyOptional<IModCapability> newSpeedCap = event.getPlayer().getCapability(ModCapability.CAP);
        if (oldSpeedCap.isPresent() && newSpeedCap.isPresent()) {
            newSpeedCap.ifPresent((newCap) -> oldSpeedCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
        }



    }
}
