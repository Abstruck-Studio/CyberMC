package org.abstruck.mc.cybermc.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

import java.util.List;

/**
 * @author Goulixiaoji,Astrack
 */
public interface IModCapability extends INBTSerializable<CompoundNBT>, Capability.IStorage<IModCapability> {
    /**
     * 用于获取部件物品ID
     * @param type 该部件的类型
     * @return the implant's id
     */
    List<Implant> getImplant(ImplantType type);

    /**
     * A method to set implant
     * @param implant the item id
     */
    void addImplant(Implant implant);

    void setImplant(ImplantType type, List<Implant> implants);
    /**
     * example:
     * player.getCapability(ModCapability.CAP).ifPresent((cap)->{
     *             cap.getImplant(type);
     *             cap.setImplant(type, itemId);
     *         });
     */
}
