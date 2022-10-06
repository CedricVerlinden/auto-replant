package com.cedricverlinden.autoreplant;

import com.cedricverlinden.autoreplant.listeners.BlockBreakListener;
import com.cedricverlinden.autoreplant.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoReplant extends JavaPlugin {

	static AutoReplant instance;

	@Override
	public void onEnable() {
		Utils.consoleMessage("&6Enabling " + this.getName() + " v" + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors().get(0));
		
		instance = this;
		loadListeners();

		Utils.consoleMessage("&aSuccessfully enabled " + this.getName() + "!");
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe auto replant plugin is being shut down!"));
	}

	private void loadListeners() {
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
	}

	public static AutoReplant getInstance() {
		return instance;
	}
}
