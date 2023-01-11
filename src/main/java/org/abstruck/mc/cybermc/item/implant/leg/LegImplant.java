package org.abstruck.mc.cybermc.item.implant.leg;


import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class LegImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.LEG;
    }
}
