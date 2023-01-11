package org.abstruck.mc.cybermc.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;

/**
 * @author astrack
 */
public class TileEntityTypeInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Utils.MOD_ID);

    public static final RegistryObject<TileEntityType<OperatingTableTileEntity>> OPERATING_TABLE_TILEENTITY = TILE_ENTITIES.register("operating_table_tileentity",() -> TileEntityType.Builder.of(OperatingTableTileEntity::new,BlockInit.OPERATING_TABLE.get()).build(null));
}
