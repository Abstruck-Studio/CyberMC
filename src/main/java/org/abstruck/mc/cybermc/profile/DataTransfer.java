package org.abstruck.mc.cybermc.profile;

import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

import java.util.List;
import java.util.Map;

/**
 * @author Astrack
 */
// @OnlyIn(Dist.DEDICATED_SERVER)
public class DataTransfer {
    Map<ImplantType, List<Implant>> implants;

    public DataTransfer(Map<ImplantType,List<Implant>> implants){
        this.implants = implants;
    }
}
