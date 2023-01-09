package org.abstruck.mc.cybermc.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.jetbrains.annotations.NotNull;

/**
 * @author Goulixiaoji
 */
public class OperatingTableContainer extends Container {

    public OperatingTableContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);
    }
    public OperatingTableContainer(int id, PlayerInventory playerInventory, PlayerEntity player){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);
    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return false;
    }
}
