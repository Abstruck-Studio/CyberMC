package org.abstruck.mc.cybermc.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.abstruck.mc.cybermc.Utils;

/**
 * @author Astrack
 */
public class NetWorking {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int getNextId(){
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Utils.MOD_ID, "mod_networking"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.messageBuilder(ActivateImplantPack.class,getNextId())
                .encoder(ActivateImplantPack::encode)
                .decoder(ActivateImplantPack::decode)
                .consumer(ActivateImplantPack::handler)
                .add();
    }
}
