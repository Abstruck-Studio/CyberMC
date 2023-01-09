package org.abstruck.mc.cybermc.item.implant.epidermalsystem;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class EpidermalSystemImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.EPIDERMALSYSTEM;
    }
}
