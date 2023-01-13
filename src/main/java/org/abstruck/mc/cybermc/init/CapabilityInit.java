package org.abstruck.mc.cybermc.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.capability.CyberMcCapability;
import org.abstruck.mc.cybermc.capability.IModCapability;

/**
 * @author Astrack
 */
public class CapabilityInit {
    private static final ResourceLocation MOD_CAPABILITY = new ResourceLocation(Utils.MOD_ID,"mod_cap");
    public static void register(){
        CapabilityManager.INSTANCE.register(IModCapability.class,new CyberMcCapability(),CyberMcCapability::new);
    }
}
