package com.cedricverlinden.autoreplant.utils;

import org.bukkit.Material;

public enum Crops {

	WHEAT(Material.WHEAT_SEEDS, "autoreplant.crop.wheat"),
	BEETROOTS(Material.BEETROOT_SEEDS, "autoreplant.crop.beetroots"),
	CARROTS(Material.CARROT, "autoreplant.crop.carrots"),
	POTATOES(Material.POTATO, "autoreplant.crop.potatoes");

	private final Material seed;
	private final String permission;

	Crops(Material seed, String permission) {
		this.seed = seed;
		this.permission = permission;
	}

	public Material getSeed() {
		return seed;
	}

	public String getPermission() {
		return permission;
	}
}