package org.abstruck.mc.cybermc.block.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.container.OperatingTableContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Astrack
 */
public class OperatingTableTileEntity extends BedTileEntity implements INamedContainerProvider {

    public OperatingTableTileEntity(){
        super();
    }

    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui."+ Utils.MOD_ID+".operating_table_container");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, @NotNull PlayerInventory inventory, @NotNull PlayerEntity player) {
        return new OperatingTableContainer(sycID,inventory,player,this);
    }
}
