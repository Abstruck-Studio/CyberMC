package org.abstruck.mc.cybermc.command.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import org.abstruck.mc.cybermc.profile.PlayerProfileManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author AStrack
 */
public class NbtCommand implements Command<CommandSource> {

    @Override
    public int run(@NotNull CommandContext<CommandSource> context) throws CommandSyntaxException {
        if (!(context.getSource().getEntity() instanceof PlayerEntity)){
            return 0;
        }
        context.getSource().sendSuccess(new TranslationTextComponent(PlayerProfileManager.getInstance().getProfile((PlayerEntity) context.getSource().getEntity()).getAllImplants().toString()),false);
        return 0;
    }
}
