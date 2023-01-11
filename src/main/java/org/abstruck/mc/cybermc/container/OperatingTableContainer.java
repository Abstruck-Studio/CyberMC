package org.abstruck.mc.cybermc.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;
import org.abstruck.mc.cybermc.capability.CyberMcCapability;
import org.abstruck.mc.cybermc.capability.IModCapability;
import org.abstruck.mc.cybermc.capability.ModCapability;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Goulixiaoj,Astrack
 */
public class OperatingTableContainer extends Container {
    ImplantItemNumber intArray;
    OperatingTableTileEntity operatingTableTileEntity;
    IModCapability implants = new CyberMcCapability();
    Map<ImplantType, List<Slot>> slots = new HashMap<>();

    public OperatingTableContainer(int id, PlayerInventory playerInventory, @NotNull PacketBuffer buffer){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(), id);
    }
    public OperatingTableContainer(int id, PlayerInventory playerInventory, @NotNull PlayerEntity player,OperatingTableTileEntity operatingTableTileEntity){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(),id);
        player.getCapability(ModCapability.CAP).ifPresent(cap->implants=cap);
        this.operatingTableTileEntity = operatingTableTileEntity;
        layoutPlayerInventorySlots(playerInventory);
        layoutImplantInventorySlots(getImplantInventory());
    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return stillValid(IWorldPosCallable.create(Objects.requireNonNull(operatingTableTileEntity.getLevel()),operatingTableTileEntity.getBlockPos()),player,operatingTableTileEntity.getBlockState().getBlock());
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
                if (implants.getImplant(type).size()>=i+1){
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

    private void layoutPlayerInventorySlots(IInventory inventory) {
        // 创建玩家背包物品栏

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++)//物品栏一横行有九个
            {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // 3、工具栏
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }

}
