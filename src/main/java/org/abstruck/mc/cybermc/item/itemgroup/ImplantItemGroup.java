package org.abstruck.mc.cybermc.item.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.init.ItemInit;

import javax.annotation.Nonnull;

/**
 * @author Astrack
 */
public class ImplantItemGroup extends ItemGroup {
    public ImplantItemGroup(){
        super("implant_group");
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.SANDEVISTAN.get());
    }
}
