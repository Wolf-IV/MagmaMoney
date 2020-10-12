package com.Wolf_IV.MagmaMoney.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CMgive implements CommandExecutor {
public static String MagmaName = "§6Magma";
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("mgive")) {
				
				

                ItemStack Magma = new ItemStack(Material.LEGACY_SKULL_ITEM);
                Magma.setDurability((short)3);
                SkullMeta sm = (SkullMeta) Magma.getItemMeta();
                sm.setOwner("PrestonPlayz");
                sm.setDisplayName(MagmaName);
                Magma.setItemMeta(sm);

                ItemStack Ballon = new ItemStack(Material.BLACK_SHULKER_BOX);
    			ItemMeta customBallon =Ballon.getItemMeta();
    			customBallon.setDisplayName("§1|§6Shop de: Donteg_");//fait gaffe de le changer aussi dans la method on Interact
    			customBallon.setLore(Arrays.asList("§cAvec un simple clic droit ", "lancez un WaterBaloon"));
    			Ballon.setItemMeta(customBallon);
    			
    			
    			ItemStack Ballon1 = new ItemStack(Material.CHEST);
    			ItemMeta customBallon1 =Ballon1.getItemMeta();
    			customBallon1.setDisplayName("§3|§6Shop de: Donteg_ Wolf_IV Sharky");//fait gaffe de le changer aussi dans la method on Interact
    			customBallon1.setLore(Arrays.asList("§cAvec un simple clic droit ", "lancez un WaterBaloon"));
    			Ballon1.setItemMeta(customBallon1);
			
				
				player.getInventory().addItem(Magma);
				player.getInventory().addItem(Ballon);
				player.getInventory().addItem(Ballon1);
			return true;
			}
			
			}
		return false;
	}

}
