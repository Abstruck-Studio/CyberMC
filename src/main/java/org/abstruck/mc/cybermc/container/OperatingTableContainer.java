package org.abstruck.mc.cybermc.container;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;
import org.abstruck.mc.cybermc.capability.ModCapability;
import org.abstruck.mc.cybermc.init.ContainerTypeInit;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
        if (stillValid(IWorldPosCallable.create(Objects.requireNonNull(operatingTableTileEntity.getLevel()),operatingTableTileEntity.getBlockPos()),player,operatingTableTileEntity.getBlockState().getBlock())){
            return true;
        }
        onContainerClosed();
        return false;
    }

    @Override
    protected boolean moveItemStackTo(@NotNull ItemStack itemStack, int fromIndex, int toIndex, boolean flag) {
        Item item = itemStack.getItem();
        int startIndex = 36;
        int range = 3;
        if (!(item instanceof Implant)){
            if (toIndex<startIndex||toIndex>startIndex+ImplantType.values().length*range){
                return super.moveItemStackTo(itemStack,fromIndex,toIndex,flag);
            }
            return false;
        }
        Implant implant = (Implant) item;
        for (ImplantType type:ImplantType.values()){
            if (implant.getType().equals(type)){
//                return startIndex <= toIndex && toIndex < startIndex + 3;
                if (startIndex <= toIndex && toIndex < startIndex + range) {
                    Slot toSlot = this.slots.get(toIndex);
                    if (toSlot != null) {
                        ItemStack currentStack = toSlot.getItem();
                        if (currentStack.isEmpty()) {
                            toSlot.set(itemStack.copy());
                            itemStack.setCount(0);
                            toSlot.setChanged();
                            slotsChanged(getImplantInventory());
                            return true;
                        }
                    }
                }
            }
            startIndex+=range;
        }
        return false;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public IInventory getImplantInventory(){
//        IInventory inventory = new Inventory(27);
//        for (int i = 0;i<ImplantType.values().length;i++){
//            for (int j=0;j<3;j++){
//                if (PlayerProfileManager.getInstance().getProfile(player).getImplants(ImplantType.values()[i]).size()>=j+1){
//                    inventory.setItem(i*3+j,new ItemStack(PlayerProfileManager.getInstance().getProfile(player).getImplants(ImplantType.values()[i]).get(j)));
//                }
//            }
//        }
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
                Slot slot = new Slot(inventory,index,x,y);

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

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull PlayerEntity p_82846_1_, int p_82846_2_) {
        return ItemStack.EMPTY;
    }

    public void onContainerClosed(){
        NonNullList<ItemStack> itemStacks = this.getItems();
        PlayerEntity player = ((OperatingTableContainer) this).getPlayer();
        List<Implant> implants = new ArrayList<>();
        for (ItemStack aItemStack:itemStacks.subList(36, itemStacks.size()-1)) {
            if (!(aItemStack.getItem() instanceof Implant)){
                continue;
            }
            implants.add((Implant) aItemStack.getItem());
        }
        player.getCapability(ModCapability.CAP).ifPresent(cap->{
            for (ImplantType implantType:ImplantType.values()){
                List<Implant> implantsOfThisType = new ArrayList<>();
                for (Implant implant:implants){
                    if (implant.getType().equals(implantType)){
                        implantsOfThisType.add(implant);
                    }
                }
                cap.setImplant(implantType,implantsOfThisType);
            }
        });
        PlayerProfileManager.getInstance().getProfile(player).update();
    }
}
