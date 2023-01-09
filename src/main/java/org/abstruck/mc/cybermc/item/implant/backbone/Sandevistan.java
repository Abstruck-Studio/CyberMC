package org.abstruck.mc.cybermc.item.implant.backbone;


import org.abstruck.mc.cybermc.item.ProductionCompany;
import org.abstruck.mc.cybermc.item.Quality;
import org.abstruck.mc.cybermc.item.implant.IActive;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.jetbrains.annotations.NotNull;

/**
 * @author Astrack
 */
public class Sandevistan extends BackboneImplant implements IActive {
    private final String NAME = "Sandevistan";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public @NotNull String toString() {
        return getName();
    }

    /**
     * 获取生产这个物品的公司
     *
     * @return 生产这个物品的公司
     */
    @Override
    public ProductionCompany getProductionCompany() {
        return ProductionCompany.MilitaryTechnology;
    }

    /**
     * 获取物品的售价
     *
     * @return 物品的价格
     */
    @Override
    public float getPrice() {
        return 500000F;
    }

    /**
     * 获取这个物品的重量
     *
     * @return 这个物品的重量
     */
    @Override
    public float getWeighty() {
        return 10F;
    }

    /**
     * 获取这个物品的品质
     *
     * @return 物品的品质
     */
    @Override
    public Quality getQuality() {
        return Quality.LEGEND;
    }
}
