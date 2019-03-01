package ooo.sansk.imine.greylist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

public class ChatLimiter implements Listener {

    private static final int COOLDOWN_SECONDS = 5;

    private final Map<Player, LocalDateTime> lastChatMessageSendTimes;

    public ChatLimiter() {
        lastChatMessageSendTimes = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_CHAT_BYPASS)){
            return;
        }

        if(!isOnCooldown(event.getPlayer())){
            cooldown(event.getPlayer());
            return;
        }

        notifyCooldown(event.getPlayer());
        event.setCancelled(true);
    }

    private boolean isOnCooldown(Player player) {
        LocalDateTime lastChatMessageSendTime = this.lastChatMessageSendTimes.get(player);
        if(lastChatMessageSendTime == null) {
            return false;
        }
        return lastChatMessageSendTime.plusSeconds(COOLDOWN_SECONDS).isAfter(LocalDateTime.now());
    }

    private void cooldown(Player player) {
        lastChatMessageSendTimes.put(player, LocalDateTime.now());
    }

    private void notifyCooldown(Player player) {
        player.sendMessage("[Greylist] Your message has been rate-limited due to not being greylisted. Please wait " + calculateRemainingCooldownSeconds(player) + " seconds before sending your next message");
    }

    private long calculateRemainingCooldownSeconds(Player player) {
        long epochNowUTC = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long epochLastMessageTimeUTC = lastChatMessageSendTimes.getOrDefault(player, LocalDateTime.now()).toEpochSecond(ZoneOffset.UTC);
        return Math.max(0, COOLDOWN_SECONDS - (epochNowUTC - epochLastMessageTimeUTC));
    }


}
