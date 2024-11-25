package org.example.plugin.sample;

import com.mojang.brigadier.Message;
import java.util.Objects;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class SetLevelCommand implements CommandExecutor {

  private Main main;

  public SetLevelCommand (Main main) {
    this.main = main;
  }

  @Override
  public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    if (sender instanceof Player player)
      if (args.length == 1) {
        player.setLevel (Integer.parseInt (args[0]));
      } else {
        player.sendMessage (Objects.requireNonNull (main.getConfig ().getString ("Message")));
      }
    return false;
  }
}