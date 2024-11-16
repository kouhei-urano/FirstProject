package org.example.plugin.sample;

import static org.bukkit.Color.BLACK;
import static org.bukkit.Color.BLUE;
import static org.bukkit.Color.GREEN;
import static org.bukkit.Color.RED;
import static org.bukkit.Color.WHITE;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;



public final class Main extends JavaPlugin implements Listener {

    int count = 0;

    @Override
    public void onEnable() {
      Bukkit.getPluginManager ().registerEvents (this, this);
    }

    @EventHandler
    public void PlayerJoinEvent (PlayerJoinEvent e) throws IOException {
      Player player = e.getPlayer();

      Path path = Path.of ("join.txt");
      Files.writeString (path,"ログインしてくれ頼む", StandardOpenOption.CREATE);
      player.sendMessage(Files.readString (path));
    }


    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent e) {
      Player player = e.getPlayer ();
      ItemStack[] itemStacks = player.getInventory ().getContents ();
      Arrays.stream (itemStacks)
          .filter (item -> !Objects.isNull (item) && item.getMaxStackSize () == 64 && item.getAmount () < 64)
          .forEach (item -> item.setAmount (0));

      player.getInventory (). setContents(itemStacks);
    }

  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
    // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
    Player player = e.getPlayer ();
    World world = player.getWorld ();

    count++;
    List<Color> colorList = List.of (RED, BLUE, WHITE, BLACK);
    if(BigInteger.valueOf(count).isProbablePrime(1)){
      System.out.println(count + "は素数です");
      for(Color color : colorList) {
        Firework firework = world.spawn (player.getLocation (), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta ();

        fireworkMeta.addEffect (
            FireworkEffect.builder ()
                .withColor (RED)
                .withColor (BLUE)
                .withColor (GREEN)
                .withColor (WHITE)
                .with (Type.BALL_LARGE)
                .withFlicker ()
                .build ());
        fireworkMeta.setPower (3);

        // 追加した情報で再設定する。
        firework.setFireworkMeta (fireworkMeta);
      }
    }

    Path path = Path.of ("firework.txt");
    Files.writeString (path, "たーまやー");
    player.sendMessage(Files.readString (path));
  }
}

//ここを変更しました。
//ここも変更しました。
//プルリクエスト本番
//できたかな？


    



