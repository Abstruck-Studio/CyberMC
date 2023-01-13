package org.abstruck.mc.cybermc.container;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.math.BlockPos;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;
import org.abstruck.mc.cybermc.container.slot.ImplantSlot;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
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
    OperatingTableTileEntity operatingTableTileEntity;
    Map<ImplantType, List<Slot>> slotsMap = new HashMap<>();
    PlayerEntity player;


    public OperatingTableContainer(int id, @NotNull PlayerInventory playerInventory, BlockPos blockPos){
        super(ContainerTypeInit.OPERATING_TABLE_CONTAINER_TYPE.get(),id);
        this.player = playerInventory.player;
        assert Minecraft.getInstance().level != null;
        this.operatingTableTileEntity = (OperatingTableTileEntity) Minecraft.getInstance().level.getBlockEntity(blockPos);
        layoutPlayerInventorySlots(playerInventory);
        layoutImplantInventorySlots(getImplantInventory());
    }


    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        return this.getImplantInventory().stillValid(player);
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public IInventory getImplantInventory(){
        return PlayerProfileManager.getInstance().getProfile(player).getImplantInventory();
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
                Slot slot = new ImplantSlot(inventory,index,x,y,type);


                s.add(this.addSlot(slot));
                //把x和y调整成下一个slot的位置
                x+=DX;
                if (index%9==0){
                    //如果index是9的倍数，那下一个slot就要换行
                    x=0;
                    y+=DY;
                }
                index++;
            }
            slotsMap.put(type,s);
        }
    }

    private void layoutPlayerInventorySlots(IInventory inventory) {
        // 创建玩家背包物品栏

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++){
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // 工具栏
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }


}
