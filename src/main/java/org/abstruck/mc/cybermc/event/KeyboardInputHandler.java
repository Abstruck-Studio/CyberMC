package org.abstruck.mc.cybermc.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.abstruck.mc.cybermc.Utils;
import org.abstruck.mc.cybermc.item.implant.IActive;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

/**
 * @author  Astrack
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyboardInputHandler {
    public static final KeyBinding CAPS_LOCK = new KeyBinding("key.showImplantHud",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_CAPS_LOCK,
            "key.category." + Utils.MOD_ID);

    @SubscribeEvent
    public static void onShowHud(InputEvent.KeyInputEvent event){
        if (CAPS_LOCK.isDown()){
            if (PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getIsShowedActiveImplantHud()&& !HudRenderHandler.GUI.activeImplantList.isEmpty()&&HudRenderHandler.GUI.activeImplantList.get(HudRenderHandler.GUI.currentIndexImplant) instanceof IActive){
                ((IActive) HudRenderHandler.GUI.activeImplantList.get(HudRenderHandler.GUI.currentIndexImplant)).sendActivatePack(Minecraft.getInstance().player);
            }
            PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).setShowedActiveImplantHud(!PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getIsShowedActiveImplantHud());
        }
    }
    public static void onCloseHud(InputEvent.@NotNull KeyInputEvent event){
        if (event.getKey() == GLFW.GLFW_KEY_ESCAPE && PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).getIsShowedActiveImplantHud()){
            PlayerProfileManager.getInstance().getProfile(Minecraft.getInstance().player).setShowedActiveImplantHud(false);
        }
    }
}
