package org.abstruck.mc.cybermc.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.item.implant.ImplantType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Goulixiaoji,Astrack
 */
public class CyberMcCapability implements IModCapability {
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
            if (value!=null&&!value.isEmpty()){
                //将value这个list转换为string储存，每条数据之间用","隔开了
                value.forEach(implant -> {
                    if (implant!=null){
                        v.append(implant.getName()).append(",");
                    }
                });
                //删除算法原因导致的最后末尾的","
                if (v.length()>1){
                    v.deleteCharAt(v.length() - 1);
                }
            }


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

    /**
     * Serialize the capability instance to a NBTTag.
     * This allows for a central implementation of saving the data.
     * <p>
     * It is important to note that it is up to the API defining
     * the capability what requirements the 'instance' value must have.
     * <p>
     * Due to the possibility of manipulating internal data, some
     * implementations MAY require that the 'instance' be an instance
     * of the 'default' implementation.
     * <p>
     * Review the API docs for more info.
     *
     * @param capability The Capability being stored.
     * @param instance   An instance of that capabilities interface.
     * @param side       The side of the object the instance is associated with.
     * @return a NBT holding the data. Null if no data needs to be stored.
     */
    @Nullable
    @Override
    public INBT writeNBT(Capability<IModCapability> capability, IModCapability instance, Direction side) {
        System.out.println("储存cap");
        return instance.serializeNBT();
    }

    /**
     * Read the capability instance from a NBT tag.
     * <p>
     * This allows for a central implementation of saving the data.
     * <p>
     * It is important to note that it is up to the API defining
     * the capability what requirements the 'instance' value must have.
     * <p>
     * Due to the possibility of manipulating internal data, some
     * implementations MAY require that the 'instance' be an instance
     * of the 'default' implementation.
     * <p>
     * Review the API docs for more info.         *
     *
     * @param capability The Capability being stored.
     * @param instance   An instance of that capabilities interface.
     * @param side       The side of the object the instance is associated with.
     * @param nbt        A NBT holding the data. Must not be null, as doesn't make sense to call this function with nothing to read...
     */
    @Override
    public void readNBT(Capability<IModCapability> capability, IModCapability instance, Direction side, INBT nbt) {
        if (nbt==null||instance==null){
            return;
        }
        if (!(nbt instanceof CompoundNBT)){
            return;
        }
        instance.deserializeNBT((CompoundNBT) nbt);
        System.out.println("读取cap");
    }
}
