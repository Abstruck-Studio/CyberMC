package org.abstruck.mc.cybermc.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * @author Goulixiaoji
 */
public class ModCapability {
    @CapabilityInject(IModCapability.class)
    public static Capability<IModCapability> CAP;
}
