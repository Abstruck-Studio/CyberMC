package org.abstruck.mc.cybermc.item.implant.backbone;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class BackboneImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.BACKBONE;
    }
}
