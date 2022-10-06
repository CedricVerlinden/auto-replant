package com.cedricverlinden.autoreplant.utils;

import com.cedricverlinden.autoreplant.AutoReplant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

	public static void consoleMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
		for (E e : enumClass.getEnumConstants()) {
			if (e.name().equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasPermissionToReplant(Player player, Material crop) {
		return player.hasPermission("autoreplant.crop.all") || player.hasPermission(Crops.valueOf(crop.name()).getPermission());
	}

	public static boolean fullyGrown(Block crop) {
		Ageable ageable = (Ageable) crop.getBlockData();
		int maxAge = ageable.getMaximumAge();

		return ageable.getAge() == maxAge;
	}

	public static void removeSeed(Inventory inventory, Material crop) {
		ItemStack currentItemStack;
		int seedIndex = -1;

		Material seedMaterial = Crops.valueOf(crop.name()).getSeed();

		for (int index = 0; index < inventory.getSize(); index++) {
			currentItemStack = inventory.getItem(index);

			if (!(currentItemStack == null)) {
				if (currentItemStack.getType() == seedMaterial) {
					seedIndex = index;
					break;
				}
			}
		}

		if (!(seedIndex == -1)) {
			ItemStack seedItemStack = inventory.getItem(seedIndex);

			if (!(seedItemStack == null)) {
				int seedAmount = seedItemStack.getAmount();
				seedItemStack.setAmount(seedAmount - 1);
			}
		}
	}

	public static void replantSeed(Block block, Material seed) {
		Bukkit.getScheduler().runTaskLater(AutoReplant.getInstance(), () -> {
			block.setType(seed);
		}, 10L);
	}
}
