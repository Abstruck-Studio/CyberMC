package org.abstruck.mc.cybermc.item.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.init.ItemInit;
import org.jetbrains.annotations.NotNull;

/**
 * @author Astrack
 */
public class CyberMachineGroup extends ItemGroup {
    public CyberMachineGroup(){
        super("cyber_machine_group");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(ItemInit.SANDEVISTAN.get());
    }
}
