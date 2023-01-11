package org.abstruck.mc.cybermc.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;
import org.abstruck.mc.cybermc.capability.CyberMcCapability;
import org.abstruck.mc.cybermc.capability.IModCapability;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Goulixiaoj,Astrack
 */
public class OperatingTableContainer extends Container {
    ImplantItemNumber intArray;
    CompoundNBT playerNBT;
    OperatingTableTileEntity operatingTableTileEntity;
    IModCapability implants = new CyberMcCapability();
    Map<ImplantType,Slot> solts = new HashMap<>();

    public OperatingTableContainer(int id, PlayerInventory playerInventory, @NotNull PacketBuffer buffer){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);
    }
    public OperatingTableContainer(int id, PlayerInventory playerInventory, @NotNull PacketBuffer packetBuffer, @NotNull World world){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(),id);
        this.playerNBT = packetBuffer.readAnySizeNbt();
        implants.deserializeNBT(playerNBT);
        this.operatingTableTileEntity = (OperatingTableTileEntity) world.getBlockEntity(packetBuffer.readBlockPos());


        Arrays.stream(ImplantType.values()).forEach(v->{
            solts.put(v,)
        });
    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return true;
    }

    @Contract(pure = true)
    private Slot createSlot(@NotNull ImplantType type){
        type.
    }
    public IInventory getImplantInventory(){

        return null;
    }
}
