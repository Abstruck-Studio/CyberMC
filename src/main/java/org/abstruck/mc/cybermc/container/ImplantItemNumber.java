package org.abstruck.mc.cybermc.container;

import net.minecraft.util.IIntArray;

/**
 * @author Astrack,Goulixiaoji
 */
public class ImplantItemNumber implements IIntArray {
    int[] array;

    public ImplantItemNumber(int size){
        this.array = new int[size];
    }

    public ImplantItemNumber(int[] array){
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public void set(int index, int value) {
        array[index] = value;
    }

    @Override
    public int get(int index) {
        return array[index];
    }
}
