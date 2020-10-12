package com.Wolf_IV.MagmaMoney.Commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CShop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("shop")) {
				if(player.getItemInHand() ==null) {
					player.sendMessage("§4Vous devez avoir un votre item dans les main");
					return false;
				}
				if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("shop")) {
					int nubArg = 0;
					Bukkit.broadcastMessage("1");
					String pE =null;
					if(args.length>=1) {
						StringBuilder bc = new StringBuilder();
						for(String part : args) {
							bc.append(part+ " ");
							nubArg++;
						}
						pE = bc.toString();
					}
					Bukkit.broadcastMessage("3");
					ItemStack shop = player.getItemInHand();
	    			ItemMeta customShop =shop.getItemMeta();
	    			if(pE != null) {
	    				Bukkit.broadcastMessage("3.5");
	    			customShop.setDisplayName("§"+(nubArg+1)+"|§6Shop de: "+player.getDisplayName()+" "+pE);//fait gaffe de le changer aussi dans la method on Interact
	    			}else {
	    				Bukkit.broadcastMessage("3.5Nnm");
	    				customShop.setDisplayName("§1|§6Shop de: "+player.getDisplayName());
	    			}
	    			Bukkit.broadcastMessage("4");
	    			shop.setItemMeta(customShop);
					player.setItemInHand(shop);
					Bukkit.broadcastMessage("5");
				}else {
					player.sendMessage("§4Votre item ne sappelle pas shop pour le transformé appleler le shop");
				}
				
				return true;
			}
		}
		return false;
	}

}
