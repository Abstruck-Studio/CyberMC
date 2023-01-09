package org.abstruck.mc.cybermc.item.implant.bone;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class BoneImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.BONE;
    }
}
