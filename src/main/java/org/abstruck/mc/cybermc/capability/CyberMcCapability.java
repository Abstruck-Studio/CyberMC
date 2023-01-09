package org.abstruck.mc.cybermc.capability;

import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.item.implant.ImplantType;

import java.util.*;

/**
 * @author Goulixiaoji
 */
public class CyberMcCapability implements IModCapability{
    Map<String, List<String>> implantMap;

    public CyberMcCapability(){
        implantMap = new HashMap<>();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        Arrays.stream(ImplantType.values()).forEach(type->{
            if (nbt.contains(type.name())){
                implantMap.put(type.name(), nbt.getString(type.name()));
            }
        });
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        Iterator<Map.Entry<String, List<String>>> iterator = implantMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            nbt.putString(entry.getKey(), entry.getValue());
        }
        return nbt;
    }

    @Override
    public List<String> getImplant(ImplantType type) {
        if (implantMap.containsKey(type.name())){
            return implantMap.get(type.name());
        }
        return new ArrayList<>();
    }

    @Override
    public void addImplant(ImplantType type, String implant) {
        if (implantMap.containsKey(type.name())){
            List<String> implantIdList = implantMap.get(type.name());
            implantIdList.add(implant);
            implantMap.put(type.name(), implantIdList);
        }
    }

    @Override
    public void setImplant(ImplantType type, List<String> implants) {
        implantMap.put(type.name(), implants);
    }
}
