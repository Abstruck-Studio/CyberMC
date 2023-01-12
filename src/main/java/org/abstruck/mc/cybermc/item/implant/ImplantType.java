package org.abstruck.mc.cybermc.item.implant;

/**
 * @author Goulixiaoji
 */
public enum ImplantType {
    ARM("arm"),
    BACKBONE("backbone"),
    BONE("bone"),
    BRAIN("brain"),
    EPIDERMAL_SYSTEM("epidermal_system"),
    HAND("hand"),
    HEART("heart"),
    LEG("leg"),
    VISION_SYSTEM("vision_system");

    ImplantType(String value) {
    }

    /*
     * 在inventory大概长这样吧
     * [brain区] [vs] [heart]
     * [bone] [backbone] [es]
     * [hand] [arm] [leg]
     */
}
