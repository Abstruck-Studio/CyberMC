package org.abstruck.mc.cybermc.item.implant.visionsystem;


import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class VisionSystemImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.VISION_SYSTEM;
    }
}
