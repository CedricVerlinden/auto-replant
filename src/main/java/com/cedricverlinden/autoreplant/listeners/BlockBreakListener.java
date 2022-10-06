package com.cedricverlinden.autoreplant.listeners;

import com.cedricverlinden.autoreplant.utils.Crops;
import com.cedricverlinden.autoreplant.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;

public class BlockBreakListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();

		Block block = event.getBlock();
		Material blockMaterial = block.getBlockData().getMaterial();

		if (!Utils.isInEnum(blockMaterial.name(), Crops.class)) {
			return;
		}

		if (!Utils.hasPermissionToReplant(player, blockMaterial)) {
			return;
		}

		if (!Utils.fullyGrown(block)) {
			return;
		}

		Material seed = Crops.valueOf(blockMaterial.name()).getSeed();
		if (!inventory.contains(seed)) {
			return;
		}

		Utils.removeSeed(inventory, blockMaterial);
		Utils.replantSeed(block, blockMaterial);
	}
}
