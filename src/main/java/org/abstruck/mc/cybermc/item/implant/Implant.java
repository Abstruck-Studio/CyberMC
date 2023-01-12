package org.abstruck.mc.cybermc.item.implant;

import net.minecraft.item.Item;
import org.abstruck.mc.cybermc.init.ItemInit;
import org.abstruck.mc.cybermc.item.IHasBasicInformation;
import org.abstruck.mc.cybermc.item.itemgroup.ModItemGroups;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Astrack
 */
public abstract class Implant extends Item implements IHasBasicInformation,ITypable,IStorable{
     public Implant(){
         super(new Properties().tab(ModItemGroups.IMPLANT_ITEM_GROUP).stacksTo(1));
     }

     public static @Nullable Implant implantFactory(String str){
         ItemInit.initImplants();
         List<Implant> list = ItemInit.implants.stream().filter(i->i.getName().equals(str)).collect(Collectors.toList());
         if (list.isEmpty()){
             return null;
         }
         return list.get(0);
     }
}

