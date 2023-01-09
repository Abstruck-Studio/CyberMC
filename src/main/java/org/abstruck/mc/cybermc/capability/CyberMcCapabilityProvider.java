package org.abstruck.mc.cybermc.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * @author Goulixiaoji
 */
public class CyberMcCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IModCapability capability;

    @Nonnull
    public IModCapability get(){
        if(capability == null){
            capability = new CyberMcCapability();
        }
        return capability;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        get().deserializeNBT(nbt);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return get().serializeNBT();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.CAP ? LazyOptional.of(this::get).cast() : LazyOptional.empty();
    }
}
