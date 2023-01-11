package org.abstruck.mc.cybermc.container.containerscreen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import org.abstruck.mc.cybermc.container.OperatingTableContainer;

/**
 * @author Astrack
 */
public class OperatingTableContainerScreen extends ContainerScreen<OperatingTableContainer> {
    private final int textureWidth = 176;
    private final int textureHeight = 166;
    public OperatingTableContainerScreen(OperatingTableContainer p_i51105_1_, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(p_i51105_1_, p_i51105_2_, p_i51105_3_);
        this.imageWidth = textureWidth;
        this.imageHeight = textureHeight;
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {

    }
}
