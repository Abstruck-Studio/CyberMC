package org.abstruck.mc.cybermc;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.abstruck.mc.cybermc.init.BlockInit;
import org.abstruck.mc.cybermc.init.ItemInit;
import org.abstruck.mc.cybermc.init.TileEntityTypeInit;

// The value here should match an entry in the META-INF/mods.toml file
/**
 * @author Astrack
 */
@Mod(Utils.MOD_ID)
public class CyberMC {

    public CyberMC() {
        Utils.LOGGER.info("Welcome to use CybetMC");
        init(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /**
     * 初始化
     * @param bus 自动注册事件
     */
    private void init(IEventBus bus){
        ItemInit.REGISTER.register(bus);
        BlockInit.REGISTER.register(bus);
        TileEntityTypeInit.TILE_ENTITIES.register(bus);
    }
}
