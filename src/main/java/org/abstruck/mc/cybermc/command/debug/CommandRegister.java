package org.abstruck.mc.cybermc.command.debug;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

/**
 * @author Astrack
 */
@Mod.EventBusSubscriber
public class CommandRegister {
    @SubscribeEvent
    public static void onServerStart(@NotNull RegisterCommandsEvent event){
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        LiteralCommandNode<CommandSource> cmd = dispatcher.register(
                Commands.literal("cybermc").then(
                        Commands.literal("debug")
                                .requires((commandSource) -> commandSource.hasPermission(0))
                                .then(Commands.literal("nbt"))
                                .executes(new NbtCommand())
                )
        );
        dispatcher.register(Commands.literal("cb").redirect(cmd));

    }
}
