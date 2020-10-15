package com.Wolf_IV.MagmaMoney;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.Wolf_IV.MagmaMoney.Commands.CMgive;

public class MListeners implements Listener {
	int invSaveMaxUser =100;//a augementer si on est plus que indiquer
	InventoryClickEvent[] invSave =new InventoryClickEvent[invSaveMaxUser];
	int[] itemBSlot =new int[invSaveMaxUser];
	int point= 0;
	
	private MainM main;
	public MListeners(MainM mainM) {
		this.main =mainM;
		}
	
		/* Chest state = (Chest) e.getClickedBlock().getState();
        Inventory chest = state.getInventory();*/
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage("§aBienvenue "+player.getName()+" sur MoulaBeo");
		try {
			//Le code lit mal apprendre a lire bien
			Read.read(player.getUniqueId());
			point = Read.point;
			player.sendMessage("§dTu a "+Read.point+" Magma en reserve fais /recup pour les recupérer");
		} catch (Exception e) {
			
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		//vrai shop §1§6Shop de: Wolf_IV  ou  §2§6Shop de: Wolf_IV Donteg_  ou  §3§6Shop de: Wolf_IV Donteg Tomshaz
		Inventory inv = event.getClickedInventory();
		int price = 0;
		InventoryView inV =event.getView();
		Player player =(Player) event.getWhoClicked();
		ItemStack current= event.getCurrentItem();
		String nomInvCh =inV.getTitle().substring(0, 9);
		if(nomInvCh.equalsIgnoreCase("§8Vente: ")) {
            int invNub =Integer.parseInt(inV.getTitle().substring(9));
           /* Bukkit.broadcastMessage(current.getItemMeta().getDisplayName());
            if(invSave[invNub].getClickedInventory().getItem(itemBSlot[invNub]).getAmount() != current.getAmount()) {
            	Bukkit.broadcastMessage("Amount = "+invSave[invNub].getClickedInventory().getItem(itemBSlot[invNub]).getAmount());
            	Bukkit.broadcastMessage("Material = "+invSave[invNub].getClickedInventory().getItem(itemBSlot[invNub]).getType());
            	Bukkit.broadcastMessage("Name = "+invSave[invNub].getClickedInventory().getItem(itemBSlot[invNub]).getItemMeta().getDisplayName());
            	player.sendMessage("§4ERROR l'item que vous voulez acheter a été retirer");
            	event.setCancelled(true);
				return;
            }*/
            
			if(event.getSlot() != 13) {
				if(inv.getItem(13) == null && event.getRawSlot()<27) {
					event.setCancelled(true);
					return;
				}
				
				if(event.getSlot() == 10 && inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase(CMgive.MagmaName) && inv.getItem(13).getAmount() >= inv.getItem(16).getAmount()) {
	                invSave[invNub].getClickedInventory().setItem(itemBSlot[invNub], inv.getItem(16));
					ItemStack Magma2 = new ItemStack(Material.LEGACY_SKULL_ITEM, inv.getItem(13).getAmount()-inv.getItem(16).getAmount());
	                Magma2.setDurability((short)3);
	                SkullMeta sm = (SkullMeta) Magma2.getItemMeta();
	                sm.setOwner("PrestonPlayz");
	                sm.setDisplayName(CMgive.MagmaName);
	                Magma2.setItemMeta(sm);
	                inv.setItem(13, Magma2);
	                //inv.setItem(10, new ItemStack(Material.BARRIER));
	                invSave[invNub]=null;
	                itemBSlot[invNub]=0;
				}else if(event.getRawSlot()<27){
					event.setCancelled(true);
				}
			}
			
		}
		nomInvCh =inV.getTitle().substring(3, 14);
		String playerNCh = "§6Shop de: "+player.getName();
		if(nomInvCh.equalsIgnoreCase("§6Shop de: ")) {
			
			
		// a faire: tester si il y a bqp de personne ou pas et faire un tableau qui va jusqua 9
			int nubP = Integer.parseInt(inV.getTitle().substring(1,2));
			int lastEnd = 14;
			for(int i = 1;i<nubP;i++) {
				String playerConName = "";
				boolean liCon =false;
				for(int li = 0; liCon==false; li++) {
					if(inV.getTitle().substring(lastEnd+li,lastEnd+li+1) != null) {// a sup si pos
				if(inV.getTitle().substring(lastEnd+li,lastEnd+li+1).equalsIgnoreCase(" ")) {
					liCon=true;
					lastEnd =lastEnd+li+1;
					if(playerConName != null ) {// a sup si pos
						if(Bukkit.getPlayer(playerConName) != null) {// a sup si pos
					if(Bukkit.getPlayer(playerConName) == player) {
						Bukkit.broadcastMessage(playerConName);
						return;
						
					}
						}
					
					}
				}else {
					if(inV.getTitle().substring(lastEnd+li,lastEnd+li+1) != null) {// a sup si pos
					playerConName = playerConName +inV.getTitle().substring(lastEnd+li,lastEnd+li+1);
					}
					
				}
				
					}
				}
			}
			
			Bukkit.broadcastMessage("AAAA");
			
		/*if(inV.getTitle().substring(0, 1) == "§1" || playerNCh.equalsIgnoreCase(inV.getTitle().substring(3))) {
			return;
		}*/
		int slotChose =event.getRawSlot();
		if(inv.getItem(slotChose) == null) {
			event.setCancelled(true);
			return;
		}
		
		if(event.getSlot() == 13) {
			event.setCancelled(true);
			return;
		}
		
		String infoName = inv.getItem(13).getItemMeta().getDisplayName();

			try {
				
			
				price = Integer.parseInt(infoName.substring(0, 2));
			}catch(Exception e){
				try {
					price = Integer.parseInt(infoName.substring(0, 1));
				}catch(Exception e2){
				player.sendMessage("§4Une erreur s'est produite "+inV.getTitle().substring(14)+ " c'est trompé dans la configuration du shop");
				event.setCancelled(true);
				return;
				}
			}
			
			
			for(int i = 0; i<invSaveMaxUser-3; i++) {
				player.sendMessage("i = "+i);
				player.sendMessage("InvSave = "+invSave[i]);
				
				
				if(invSave[i] == null) {
					invSave[i]=event;
					//a la vente tester si l'item et tjr la
					Inventory invBuy = Bukkit.createInventory(null, 27, "§8Vente: "+i);
					itemBSlot[i] = event.getRawSlot();
					 ItemStack Magma = new ItemStack(Material.LEGACY_SKULL_ITEM, price);
		                Magma.setDurability((short)3);
		                SkullMeta sm = (SkullMeta) Magma.getItemMeta();
		                sm.setOwner("PrestonPlayz");
		                sm.setDisplayName(CMgive.MagmaName);
		                Magma.setItemMeta(sm);
					invBuy.setItem(10, current);
					invBuy.setItem(16, Magma);
					invBuy.setItem(4, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
					invBuy.setItem(12, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
					invBuy.setItem(14, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
					invBuy.setItem(22, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
					spaceProt(invBuy);

					player.openInventory(invBuy);
					i=invSaveMaxUser+1;
				}
				
				
				if(i==invSaveMaxUser-3) {
					player.sendMessage("§4ERREUR trop de joueur se serve des shop prevenir un admin");
				}
				
			}
			
		event.setCancelled(true);
		return;
		
		
		}
		
		/*if(inV.getTitle().equalsIgnoreCase("Shop")) {
			String createName = current.getItemMeta().getDisplayName();
			if(createName.substring(0, 6).equalsIgnoreCase("create")) {
				
			}
		}*/
		
		
		
	}
	
	public void spaceProt(Inventory inv) {
		for(int i = 0;i<27;i++) {
			if(inv.getItem(i) == null && i != 13) {
		inv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
			}
		}
	}
	
	
	
	
	
	
	
	
		@EventHandler
	 public void onInvClose(InventoryCloseEvent event) {
		 Player player = (Player) event.getPlayer();
		 Inventory inv =event.getInventory();
		 InventoryView inV = event.getView();
		 String nomInvCh =inV.getTitle().substring(0, 9);
		 
			if(nomInvCh.equalsIgnoreCase("§8Vente: ")) {
	            int invNub =Integer.parseInt(inV.getTitle().substring(9));
	            invSave[invNub]=null;
	            if(inv.getItem(13) != null) {
	            	Location loc = new Location(player.getWorld(), player.getLocation().getX()-0.7F, player.getLocation().getY()+0.2F, player.getLocation().getZ()-0.7F);
	            	 event.getPlayer().getWorld().dropItemNaturally(loc, inv.getItem(13));
	            }
	            
			}
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
