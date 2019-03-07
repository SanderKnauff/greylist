package ooo.sansk.imine.greylist;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
    public void onPlayerFarmlandTrample(PlayerInteractEvent event) {
        if(event.getPlayer().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        if(!event.getAction().equals(Action.PHYSICAL) || !event.getClickedBlock().getType().equals(Material.FARMLAND)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent event) {
        if(event.getEntity().hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onHangingBreakByPlayerEvent(HangingBreakByEntityEvent event) {
        if(!(event.getRemover() instanceof Player)){
            return;
        }
        Player player = (Player) event.getRemover();

        if(player.hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onHangingBreakByMobEvent(HangingBreakByEntityEvent event) {
        if(!(event.getRemover() instanceof Monster)){
            return;
        }
        Monster monster = (Monster) event.getRemover();

        if(!(monster.getTarget() instanceof Player)){
            return;
        }

        Player player = (Player) monster.getTarget();

        if(player.hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemFrameDamageByPlayer(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof ItemFrame)) {
            return;
        }

        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();

        if(player.hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemFrameDamageByPlayerProjectile(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof ItemFrame)) {
            return;
        }

        if (!(event.getDamager() instanceof Projectile)) {
            return;
        }
        Projectile projectile = (Projectile) event.getDamager();

        if (!(projectile.getShooter() instanceof Player)) {
            return;
        }
        Player player = (Player) projectile.getShooter();

        if(player.hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemFrameDamageByMonsterProjectile(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof ItemFrame)) {
            return;
        }

        if (!(event.getDamager() instanceof Projectile)) {
            return;
        }
        Projectile projectile = (Projectile) event.getDamager();

        if (!(projectile.getShooter() instanceof Monster)) {
            return;
        }
        Monster monster = (Monster) projectile.getShooter();

        if (!(monster.getTarget() instanceof Player)) {
            return;
        }
        Player player = (Player) monster.getTarget();

        if(player.hasPermission(GreylistPlugin.PERMISSION_GREYLIST_BLOCKS_BYPASS)){
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
