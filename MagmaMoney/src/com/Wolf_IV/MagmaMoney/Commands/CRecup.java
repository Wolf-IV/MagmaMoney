package com.Wolf_IV.MagmaMoney.Commands;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.Wolf_IV.MagmaMoney.Read;
import com.Wolf_IV.MagmaMoney.Write;

public class CRecup implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("recup")) {
				Random rand = new Random();
				try {
					Read.read(player.getUniqueId());
					if(Read.point == 0) {
						player.sendMessage("§4Vous n'aver pas de point a recup");
						return false;
					}
					player.sendMessage("§5Un coffre contenant "+Read.point+" Magma vous attends en surface a 50 block de vous");
					int x = (int) (player.getLocation().getX()+rand.nextInt(100)-50);
					int y;
					int z = (int) (player.getLocation().getZ()+rand.nextInt(100)-50);
					for(y = 255; y<=0 ; y--) {
						Location locB =new Location(player.getWorld(), x, y, z);
						if(locB.getBlock().getType() != Material.AIR) {
							locB=new Location(player.getWorld(), x, y+1, z);
							locB.getBlock().setType(Material.CHEST);
							Chest state = (Chest) locB.getBlock();
					        Inventory chest = state.getInventory();
					        ItemStack Magma = new ItemStack(Material.LEGACY_SKULL_ITEM, Read.point);
			                Magma.setDurability((short)3);
			                SkullMeta sm = (SkullMeta) Magma.getItemMeta();
			                sm.setOwner("PrestonPlayz");
			                sm.setDisplayName(CMgive.MagmaName);
			                Magma.setItemMeta(sm);
					        chest.setItem(13, Magma);
					        Write.write(player.getUniqueId(), 0, Read.stats);
						}
					}
					player.sendMessage("§4Le coffre ne s'est pas placé veuiller reaissyer");
				} catch (Exception e) {
					player.sendMessage("§4Vous n'aver pas de point a recup");
				}
			
			
				return true;
			}
			
		}
		return false;
	}

}
