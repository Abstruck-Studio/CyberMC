package org.abstruck.mc.cybermc.item.implant.hand;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;


/**
 * @author Administrator
 */
public abstract class HandImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.HAND;
    }
}
