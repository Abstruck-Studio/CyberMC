package org.abstruck.mc.cybermc.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.backbone.Sandevistan;
import org.abstruck.mc.cybermc.item.itemgroup.ModItemGroups;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Astrack, Goulixiaoji
 */
public class ItemInit {
    public static final Set<Implant> implants = new HashSet<>();
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);

    public static final RegistryObject<Item> SANDEVISTAN = REGISTER.register("sandevistan", Sandevistan::new);

    //以下是方块
    public static final RegistryObject<Item> OPERATING_TABLE = REGISTER.register("operating_table",()->new BlockItem(BlockInit.OPERATING_TABLE.get(),new Item.Properties().tab(ModItemGroups.CYBER_MACHINE_ITEM_GROUP)));

    static {
        implants.add((Implant) SANDEVISTAN.get());
    }
}
