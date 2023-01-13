package org.abstruck.mc.cybermc.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

public class ImplantSlot extends Slot {
    ImplantType acceptType;
    public ImplantSlot(IInventory inventory, int index, int x, int y, ImplantType type) {
        super(inventory, index, x, y);
        acceptType = type;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof Implant)){
            return false;
        }
        return  ((Implant) itemStack.getItem()).getType().equals(acceptType);
    }
}
