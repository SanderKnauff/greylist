package ooo.sansk.imine.greylist;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class GreylistListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
           return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockMultiPlaceEvent event){
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
           return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
           return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInventoryInteract(InventoryOpenEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerItemPickup(EntityPickupItemEvent event) {
        if(!(event.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) event.getEntity();

        if(player.hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerArrowPickup(PlayerPickupArrowEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerEntityLeash(PlayerLeashEntityEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        notifyNotGreyListed(event.getPlayer());
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent event) {
        if(event.getEntity().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    private void notifyNotGreyListed(HumanEntity entity) {
        TextComponent discordLink = new TextComponent("[here]");
        discordLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/KAABqtn"));
        discordLink.setBold(true);
        discordLink.setColor(ChatColor.BLUE);

        entity.spigot().sendMessage(
                new TextComponent("[Greylist] "),
                new TextComponent("You can't do that because you are not greylisted.\n"),
                new TextComponent("Please click "),
                discordLink,
                new TextComponent(" to join our Discord server and ask nicely :)")
        );

    }


}
