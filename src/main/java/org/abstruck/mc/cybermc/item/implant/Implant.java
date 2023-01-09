package org.abstruck.mc.cybermc.item.implant;

import net.minecraft.item.Item;
import org.abstruck.mc.cybermc.item.IHasBasicInformation;
import org.abstruck.mc.cybermc.item.itemgroup.ModItemGroups;


/**
 * @author Astrack
 */
public abstract class Implant extends Item implements IHasBasicInformation,ITypable,IStorable{
     public Implant(){
         super(new Properties().tab(ModItemGroups.IMPLANT_ITEM_GROUP).stacksTo(1));
     }

     public static Implant implantFactory(String str){
     }
}

