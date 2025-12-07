package com.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LifeSteal extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("LifeSteal Plugin Enabled!");
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer == null) return;

        // ✅ Add 1 heart to killer
        double killerMax = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(killerMax + 2);

        // ✅ Remove 1 heart from victim
        double victimMax = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(victimMax - 2);

        killer.sendMessage("§aYou gained 1 heart!");
        victim.sendMessage("§cYou lost 1 heart!");
    }
}
