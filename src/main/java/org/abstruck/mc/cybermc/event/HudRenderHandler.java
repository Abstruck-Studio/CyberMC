package org.abstruck.mc.cybermc.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.gui.ActiveImplantGui;
import org.abstruck.mc.cybermc.item.implant.Implant;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudRenderHandler {
    private static List<Implant> activeImplantList;
    private static int currentIndexImplant = 0;
    public static final ActiveImplantGui GUI = new ActiveImplantGui(new MatrixStack());
    @SubscribeEvent
    public static void onRenderGameOverlay(@NotNull RenderGameOverlayEvent event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if (Minecraft.getInstance().player == null){
            return;
        }
        if ((!PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getIsShowedActiveImplantHud())||(PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getAllActiveImplants().isEmpty())){
            return;
        }
        activeImplantList = PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getAllActiveImplants();
        GUI.render(activeImplantList,currentIndexImplant);
    }
    @SubscribeEvent
    public static void onSwitchActiveImplant(InputEvent.@NotNull MouseInputEvent event){
        if (event.getButton() == GLFW.GLFW_MOUSE_BUTTON_RIGHT&&PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getIsShowedActiveImplantHud()){
            currentIndexImplant = (currentIndexImplant + 1) % activeImplantList.size();
        }
    }
}
