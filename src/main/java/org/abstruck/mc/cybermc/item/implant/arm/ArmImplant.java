package org.abstruck.mc.cybermc.item.implant.arm;


import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class ArmImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.ARM;
    }
}
