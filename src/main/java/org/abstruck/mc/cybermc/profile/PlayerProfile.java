package org.abstruck.mc.cybermc.profile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.capability.ModCapability;
import org.abstruck.mc.cybermc.item.implant.IActive;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Astrack
 */
// @OnlyIn(Dist.DEDICATED_SERVER)
public class PlayerProfile {
    private boolean isShowedActiveImplantHud;
    private PlayerEntity player;

    private Map<ImplantType, List<Implant>> implants;
    private DataTransfer dataTransfer;
    private IInventory implantInventory;

    public PlayerProfile(@NotNull PlayerEntity player){
        this.player = player;
        implants = new HashMap<>();
        isShowedActiveImplantHud = false;
        update();
        updateImplantInventory();
    }

    public void update(){
        updateImplants();
    }

    private void updateImplants(){
        player.getCapability(ModCapability.CAP).ifPresent(cap->{
        for (ImplantType type:ImplantType.values()){
            implants.put(type,cap.getImplant(type));
        }
    });}

    public void updateImplantInventory(){
        implantInventory = new Inventory(27);
        int index =0;
        for (ImplantType type:ImplantType.values()){
            for (int i = 0;i<3;i++){
                if (getImplants(type).size()>=i+1){
                    implantInventory.setItem(index,new ItemStack(getImplants(type).get(i)));
                }
                index++;
            }
        }
    }

    public Map<ImplantType,List<Implant>> getTypeImplantsMap(){
        return implants;
    }
    @NotNull
    public List<Implant> getImplants(ImplantType type){
        if(getTypeImplantsMap().get(type)==null){
            return new ArrayList<>();
        }
        return getTypeImplantsMap().get(type);
    }

    public List<Implant> getAllImplants(){
        List<Implant> result = new ArrayList<>();
        for (ImplantType type:getTypeImplantsMap().keySet()){
            result.addAll(implants.get(type));
        }
        return result;
    }

    public List<Implant> getAllActiveImplants(){
        List<Implant> result = new ArrayList<>();
        for (ImplantType type:getTypeImplantsMap().keySet()){
            result.addAll(implants.get(type).stream().filter(implant -> implant instanceof IActive).collect(Collectors.toList()));
        }
        return result;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public IInventory getImplantInventory() {
        return implantInventory;
    }

    public boolean getIsShowedActiveImplantHud(){
        return  isShowedActiveImplantHud;
    }

    public void setShowedActiveImplantHud(boolean showedActiveImplantHud) {
        isShowedActiveImplantHud = showedActiveImplantHud;
    }
}
