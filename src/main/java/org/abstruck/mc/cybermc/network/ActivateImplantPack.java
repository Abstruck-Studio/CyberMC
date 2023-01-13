package org.abstruck.mc.cybermc.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.abstruck.mc.cybermc.item.implant.IActive;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author Astrack
 */
public class ActivateImplantPack {
    ServerPlayerEntity player;
    Implant implant;
    public ActivateImplantPack(ServerPlayerEntity player, Implant implant){
        this.player = player;
        this.implant = implant;
    }

    public void encode(@NotNull PacketBuffer packetBuffer){
        packetBuffer.writeUUID(player.getUUID());
        packetBuffer.writeUtf(implant.getName());
    }

    public static ActivateImplantPack decode(@NotNull PacketBuffer packetBuffer){
        return new ActivateImplantPack(ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(packetBuffer.readUUID()),Implant.implantFactory(packetBuffer.readUtf()));
    }
    public void handler(@NotNull Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            if (!(implant instanceof IActive)){
                return;
            }
            ((IActive) implant).sendActivatePack(player,implant);
        });
        ctx.get().setPacketHandled(true);
    }
}
