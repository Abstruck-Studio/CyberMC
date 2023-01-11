package org.abstruck.mc.cybermc.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;
import org.abstruck.mc.cybermc.capability.CyberMcCapability;
import org.abstruck.mc.cybermc.capability.IModCapability;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Goulixiaoj,Astrack
 */
public class OperatingTableContainer extends Container {
    ImplantItemNumber intArray;
    CompoundNBT playerNBT;
    OperatingTableTileEntity operatingTableTileEntity;
    IModCapability implants = new CyberMcCapability();
    Map<ImplantType, List<Slot>> slots = new HashMap<>();

    public OperatingTableContainer(int id, PlayerInventory playerInventory, @NotNull PacketBuffer buffer){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);
    }
    public OperatingTableContainer(int id, PlayerInventory playerInventory, @NotNull PacketBuffer packetBuffer, @NotNull World world){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(),id);
        this.playerNBT = packetBuffer.readAnySizeNbt();
        implants.deserializeNBT(playerNBT);
        this.operatingTableTileEntity = (OperatingTableTileEntity) world.getBlockEntity(packetBuffer.readBlockPos());
        layoutPlayerInventorySlots(playerInventory);
        layoutImplantInventorySlots(getImplantInventory());
    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return true;
    }

    public IInventory getImplantInventory(){
        return new Inventory(27);
    }

    private void layoutImplantInventorySlots(IInventory inventory){
        int index = 0;
        int x = 8;
        final int DX = 18;
        int y = 10;
        final int DY = 18;
        for (ImplantType type:ImplantType.values()){
            List<Slot> s = new ArrayList<>();
            //一个type只有3个槽可以用
            for (int i = 0;i<3;i++){
                Slot slot = new Slot(inventory,index,x,y);
                //把implant塞到slot里（如果有的话
                if (implants.getImplant(type).size()>=i){
                    slot.set(new ItemStack(implants.getImplant(type).get(i)));
                }
                s.add(addSlot(slot));
                //把x和y调整成下一个slot的位置
                x+=DX;
                if (index%9==0){
                    //如果index是9的倍数，那下一个slot就要换行
                    x=0;
                    y+=DY;
                }
                index++;
            }
            slots.put(type,s);
        }
    }

    //展示玩家inventory的一坨烂代码（不想维护
    private void layoutPlayerInventorySlots(IInventory inventory) {
        // Player inventory
        int index = 9;
        int y = 84;
        for (int j = 0; j < 3; j++) {
            int index1 = index;
            int x = 8;
            for (int i = 0; i < 9; i++) {
                addSlot(new Slot(inventory, index1, x, y));
                x += 18;
                index1++;
            }
            index = index1;
            y += 18;
        }

        // Hotbar
        y += 58;
        int index1 = 0;
        int x = 8;
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inventory, index1, x, 84));
            x += 18;
            index1++;
        }
    }
}
