package org.abstruck.mc.cybermc.block.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.container.OperatingTableContainer;
import org.abstruck.mc.cybermc.init.TileEntityTypeInit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Astrack
 */
public class OperatingTableTileEntity extends TileEntity implements INamedContainerProvider {
    public OperatingTableTileEntity() {
        super(TileEntityTypeInit.OPERATING_TABLE_TILEENTITY.get());
    }


    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui."+ Utils.MOD_ID+".operating_table_container");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, @NotNull PlayerInventory inventory, @NotNull PlayerEntity player) {
        PacketBuffer packetBuffer = null;
        return new OperatingTableContainer(sycID,inventory,packetBuffer,player.getCommandSenderWorld());
    }
}
