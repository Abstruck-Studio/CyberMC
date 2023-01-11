package org.abstruck.mc.cybermc.capability;

import net.minecraft.nbt.CompoundNBT;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Goulixiaoji,Astrack
 */
public class CyberMcCapability implements IModCapability{
    Map<String, List<Implant>> implantMap;

    public CyberMcCapability(){
        implantMap = new HashMap<>();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        Arrays.stream(ImplantType.values()).forEach(type->{
            if (nbt.contains(type.name())){
                List<Implant> implants = new ArrayList<>();
                Arrays.stream(nbt.getString(type.name()).split(",")).forEach(name-> implants.add(Implant.implantFactory(name)));
                implantMap.put(type.name(),implants);
            }
        });
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();

        implantMap.forEach((key, value) -> {
            StringBuffer v = new StringBuffer();
            //将value这个list转换为string储存，每条数据之间用","隔开了
            value.forEach(implant -> v.append(implant.getName()).append(","));
            //删除算法原因导致的最后末尾的","
            v.deleteCharAt(v.length() - 1);

            nbt.putString(key, v.toString());
        });

        return nbt;
    }

    @Override
    public List<Implant> getImplant(@NotNull ImplantType type) {
        List<Implant> result = new ArrayList<>();
        if (implantMap.containsKey(type.name())){
            result.addAll(implantMap.get(type.name()));
        }
        return result;
    }

    @Override
    public void addImplant(@NotNull Implant implant) {
        ImplantType type = implant.getType();
        if (implantMap.containsKey(type.name())){
            List<Implant> implantIdList = implantMap.get(type.name());
            implantIdList.add(implant);
            implantMap.put(type.name(), implantIdList);
        }
    }

    @Override
    public void setImplant(@NotNull ImplantType type, List<Implant> implants) {
        implantMap.put(type.name(), implants);
    }
}
