package org.abstruck.mc.cybermc.item.implant.brain;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

/**
 * @author Astrack
 */
public abstract class BrainImplant extends Implant {
    @Override
    public ImplantType getType() {
        return ImplantType.BRAIN;
    }
}
