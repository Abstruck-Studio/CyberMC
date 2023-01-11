package org.abstruck.mc.cybermc.item.implant.heart;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class HeartImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.HEART;
    }
}
