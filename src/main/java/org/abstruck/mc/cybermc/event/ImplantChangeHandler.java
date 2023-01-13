package org.abstruck.mc.cybermc.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.capability.ModCapability;
import org.abstruck.mc.cybermc.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Astrack
 */
@Mod.EventBusSubscriber
public class ImplantChangeHandler {
    @SubscribeEvent
    public static void onCloseOperatingTableContainer(PlayerContainerEvent.@NotNull Close event){
        if (!(event.getContainer() instanceof OperatingTableContainer)){
            return;
        }
        //获取容器内的物品堆
        NonNullList<ItemStack> itemStacks = event.getContainer().getItems();
        //获取正在使用的玩家
        PlayerEntity player = ((OperatingTableContainer) event.getContainer()).getPlayer();
        List<Implant> implants = new ArrayList<>();
        //向待储存的implant列表里添加容器内的指定位置的implant
        for (ItemStack aItemStack:itemStacks.subList(36, itemStacks.size()-1)) {
            if (!(aItemStack.getItem() instanceof Implant)){
                continue;
            }
            implants.add((Implant) aItemStack.getItem());
        }
        //获取到玩家的capability储存implants
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
        //更新玩家档案
        PlayerProfileManager.getInstance().getProfile(player).update();
    }
}
