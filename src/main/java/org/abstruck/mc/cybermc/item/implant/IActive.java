package org.abstruck.mc.cybermc.item.implant;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;
import org.abstruck.mc.cybermc.network.NetWorking;

/**
 * @author Astrack
 * 代表这个植入物是主动触发的
 */
public interface IActive {
    ActivateImplantInformation sendActivatePack(PlayerEntity player);

    /**
     *在客户端调用
     */
    default ActivateImplantInformation sendActivatePack(PlayerEntity player, Implant implant){
        NetWorking.INSTANCE.sendToServer(PacketDistributor.PLAYER.with());
    }
}
