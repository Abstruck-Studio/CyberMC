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

    /**
     * 在inventory大概长这样吧
     * [brain区] [vs] [heart]
     * [bone] [backbone] [es]
     * [hand] [arm] [leg]
     *
     * @param width 宽度
     * @param xSize 某个东西的x size（我也不知道是什么
     * @return slot 的 x坐标
     */
    public int getSlotX(int width,int xSize){
        int aPart = (width-xSize-32)/6;
        switch (this){
            case BRAIN:
            case BONE:
            case HAND:
                return 8+(-3*aPart);
            case VISION_SYSTEM:
            case BACKBONE:
            case ARM:
                return 16+(-1*aPart);
            case HEART:
            case EPIDERMAL_SYSTEM:
            case LEG:
                return 24+aPart;
        }
        return 0;
    }
    public int getSlotY(int height,int ySize){
        int aPart = (height-ySize)/6;
        switch (this){
            case BRAIN:
            case VISION_SYSTEM:
            case HEART:
                return -3*aPart;
            case BONE:
            case BACKBONE:
            case EPIDERMAL_SYSTEM:
                return -1*aPart;
            case HAND:
            case LEG:
            case ARM:
                return aPart;
        }
        return 0;
    }
}
