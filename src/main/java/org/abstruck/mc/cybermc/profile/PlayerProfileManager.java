package org.abstruck.mc.cybermc.profile;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Astrack
 */
public class PlayerProfileManager {
    public static PlayerProfileManager INSTANCE = new PlayerProfileManager();
    private Map<PlayerEntity,PlayerProfile> playerProfiles;
    private PlayerProfileManager(){
        playerProfiles = new HashMap<>();
    }

    public void addPlayer(PlayerEntity player){
        if (isPlayerProfileExist(player)){
            return;
        }
        playerProfiles.put(player,new PlayerProfile(player));
    }

    public boolean isPlayerProfileExist(PlayerEntity player){
        return playerProfiles.containsKey(player);
    }
    public boolean isPlayerProfileExist(@NotNull PlayerProfile playerProfile){
        return isPlayerProfileExist(playerProfile.getPlayer());
    }

    public PlayerProfile getProfile(PlayerEntity player){
        if (!playerProfiles.containsKey(player)){
            addPlayer(player);
        }
        return playerProfiles.get(player);
    }
    public void updateProfile(PlayerEntity player){
        getProfile(player).update();
    }

    public static PlayerProfileManager getInstance() {
        return INSTANCE;
    }
}
