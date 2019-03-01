package ooo.sansk.imine.greylist;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class GreylistPlugin extends JavaPlugin {

    public static final Permission PERMISSION_GREYLIST_BLOCKS_BYPASS = new Permission("greylist.blocks.bypass");
    public static final Permission PERMISSION_GREYLIST_CHAT_BYPASS = new Permission("greylist.chat.bypass");

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new GreylistListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatLimiter(), this);

        Bukkit.getPluginManager().addPermission(PERMISSION_GREYLIST_BLOCKS_BYPASS);
        Bukkit.getPluginManager().addPermission(PERMISSION_GREYLIST_CHAT_BYPASS);
    }

    @Override
    public void onDisable() {
        Bukkit.getPluginManager().removePermission(PERMISSION_GREYLIST_BLOCKS_BYPASS);
        Bukkit.getPluginManager().removePermission(PERMISSION_GREYLIST_CHAT_BYPASS);
    }
}
