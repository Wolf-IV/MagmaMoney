package com.Wolf_IV.MagmaMoney;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		player.sendMessage("§1Plugin fait par Wolf_IV le bg");
		try {
			//Le code lit mal apprendre a lire bien
			Read.read(player.getUniqueId());
			point = Read.point;
			if(point>0) {
			player.sendMessage("§dTu a "+Read.point+" Magma en reserve fais /recup pour les recupérer");
			}
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
		if(inV.getTitle().equalsIgnoreCase("§aEfficiency II Maximum")) {
			int RslotII = event.getRawSlot();
			if(RslotII==12) {
				inv.clear();
			}else if(RslotII==14) {
				if(player.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DIG_SPEED)) {
	            	if(player.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED) > 2) {
	            		Bukkit.broadcastMessage("1");
	            		//player.getInventory().getItemInHand().getItemMeta().removeEnchant(Enchantment.DIG_SPEED);
	            		player.getInventory().getItemInHand().getItemMeta().addEnchant(Enchantment.DIG_SPEED, 2, false);
	            	}
				}
			
			
			}
			event.setCancelled(true);
		}
		if(inV.getTitle().equalsIgnoreCase("§6Magmanvil")) {
			int slot = event.getRawSlot();
			int xplvl = 0;
			
			if(inv.getItem(10) != null && inv.getItem(12) != null) {
				if(inv.getItem(12).getItemMeta().getLore().contains("§8Hammer")) {
					if(inv.getItem(10).getType() == Material.WOODEN_PICKAXE || inv.getItem(10).getType() == Material.STONE_PICKAXE || inv.getItem(10).getType() == Material.IRON_PICKAXE || inv.getItem(10).getType() == Material.DIAMOND_PICKAXE || inv.getItem(10).getType() == Material.NETHERITE_PICKAXE) {
						inv.setItem(16, inv.getItem(10));
						ItemStack anvilItem = inv.getItem(16);
						ItemMeta anvilItemMeta = inv.getItem(16).getItemMeta();
						anvilItemMeta.setLore(Arrays.asList("§8Hammer"));
						anvilItem.setItemMeta(anvilItemMeta);
						inv.setItem(16, anvilItem);
						inv.setItem(4, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
						xplvl = 15;
						if(player.getLevel() >= xplvl) {
						ItemStack lvlCost =new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
						ItemMeta lvlCostMeta =lvlCost.getItemMeta();
						lvlCostMeta.setDisplayName("§a"+xplvl+" levels requis");
						lvlCost.setItemMeta(lvlCostMeta);
						inv.setItem(22,lvlCost);
						}else {
							ItemStack lvlCost =new ItemStack(Material.RED_STAINED_GLASS_PANE);
							ItemMeta lvlCostMeta =lvlCost.getItemMeta();
							lvlCostMeta.setDisplayName("§4"+xplvl+" levels requis");
							lvlCost.setItemMeta(lvlCostMeta);
							inv.setItem(22,lvlCost);
						}
					}
				}
				if(slot == 16) {
					if(player.getLevel() >= xplvl) {
						player.setLevel(player.getLevel()-xplvl);
						inv.setItem(10, null);
						inv.setItem(12, null);
						return;
					}
				}
			}
			if(slot>26 || slot == 10 || slot == 12) {
				return;
			}
			event.setCancelled(true);
		}
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
			
			/*int rawChose =event.getRawSlot();
			if(rawChose >= 27) {
				return;
			}*/
			
			
		// a faire: tester si il y a bqp de personne ou pas et faire un tableau qui va jusqua 9
			int nubP = Integer.parseInt(inV.getTitle().substring(1,2))+1;
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
			
			if(inV.getTitle().equalsIgnoreCase("§6Magmanvil")) {
	            if(inv.getItem(10) != null) {
	            	Location loc = new Location(player.getWorld(), player.getLocation().getX()-0.7F, player.getLocation().getY()+0.2F, player.getLocation().getZ()-0.7F);
	            	 event.getPlayer().getWorld().dropItemNaturally(loc, inv.getItem(10));
	            }
	            if(inv.getItem(12) != null) {
	            	Location loc = new Location(player.getWorld(), player.getLocation().getX()-0.7F, player.getLocation().getY()+0.2F, player.getLocation().getZ()-0.7F);
	            	 event.getPlayer().getWorld().dropItemNaturally(loc, inv.getItem(12));
	            }
	            
	            
			}
	 }
	
		 @EventHandler
		    public void Interact(PlayerInteractEvent event) {
			 Action action = event.getAction();
			 Player player = event.getPlayer();
			 if(action != Action.RIGHT_CLICK_BLOCK) {
				 return;
			 }
			 if(event.getClickedBlock().getType() == Material.ANVIL || event.getClickedBlock().getType() == Material.CHIPPED_ANVIL || event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {
				 Location bloc =event.getClickedBlock().getLocation();
				 Location blocy =new Location(player.getWorld(), bloc.getX(), bloc.getY()-1, bloc.getZ());
				 if(blocy.getBlock().getType() == Material.NETHERITE_BLOCK) {
					 Inventory Magmanvil = Bukkit.createInventory(null, 27, "§6Magmanvil");
						spaceProt(Magmanvil);
						Magmanvil.setItem(10, null);
						Magmanvil.setItem(12, null);
						Magmanvil.setItem(16, null);
						Magmanvil.setItem(13, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
						Magmanvil.setItem(4, new ItemStack(Material.GREEN_WOOL));

						player.openInventory(Magmanvil);
				       
					 event.setCancelled(true);
				 }
			 }
		 }
		/*@EventHandler
		 public void onBreakBlock(BlockBreakEvent event) {
			Player player = event.getPlayer();
			if(event.getBlock().getType() != Material.CHEST) {
				return;
			}
			Chest state = (Chest) event.getBlock().getState();
	        Inventory chest = state.getInventory();
	        //InventoryView chestV = state.get
	        Inventory Det = Bukkit.createInventory(null, 27, "§aDetruire?");
	        Det.setItem(12, new ItemStack(Material.RED_WOOL));
			Det.setItem(14, new ItemStack(Material.GREEN_WOOL));
			spaceProt(Det);

			player.openInventory(Det);
	       
	        player.openInventory(Det);
	        
	        event.setCancelled(true);
			
			player.getName();
		}*/
	
	
	
		    @EventHandler
	        public void Hammer(BlockBreakEvent e){
	            Player p = e.getPlayer();
	            Location bloc = e.getBlock().getLocation();
	            World world = p.getWorld();
	            Material bMat = Material.AIR;
	            if(p.getInventory().getItemInHand().getItemMeta().getLore().contains("§8Hammer")){
	            	if(p.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.DIG_SPEED)) {
	            	if(p.getInventory().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED) > 2) {
	            		Inventory Det = Bukkit.createInventory(null, 27, "§aEfficiency II Maximum");
	            		ItemStack Yes = new ItemStack(Material.GREEN_WOOL);
	        			ItemMeta customYes =Yes.getItemMeta();
	        			customYes.setDisplayName("§aMettre l'Efficiency a II");
	        			Yes.setItemMeta(customYes);
	        			ItemStack No = new ItemStack(Material.RED_WOOL);
	        			ItemMeta customNo =No.getItemMeta();
	        			customNo.setDisplayName("§4Cancel");
	        			No.setItemMeta(customNo);
	        	        Det.setItem(12, No);
	        			Det.setItem(14, Yes);
	        			spaceProt(Det);

	        			p.openInventory(Det);
	        	       
	        	        
	        	        e.setCancelled(true);
	            	}
	            	}
	            		//Material bMat = Material.AIR;
	                    double xp = p.getLocation().getX();
	                    double yp = p.getLocation().getY();
	                    double zp = p.getLocation().getZ();
	                    double xb = bloc.getX();
	                    double yb = bloc.getY();
	                    double zb = bloc.getZ();
	                    
	                    double xd = xp-xb-0.5;
	                    double yd = yp-yb;
	                    double zd = zp-zb-0.5;
	                    
	                    if(yd<=-2 || yd>=1) {
	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb, zb+1, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb, zb-1, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb, zb+1, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb, zb-1, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb, yb, zb+1, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb, yb, zb-1, p.getWorld());
	                    	//bloc.getBlock().setType();
	                    }else  if(xd<=-0.8 || xd>=0.8) {
	                    		if(zd<=-0.8 || zd>=0.8) {
	                    			if(xd>=0.8 && zd<=-0.8 || xd<=-0.8 && zd>=0.8) {
	                    				//truc du x+ et z- ou x- et z+
	                    				HammerBreak(bloc.getBlock().getType(), xb, yb-1, zb, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb+1, zb, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb-1, zb+1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb, zb+1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb+1, zb+1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb-1, zb-1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb, zb-1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb+1, zb-1, p.getWorld());
	                    			}else {
	                    				//truc du x+ et z+ ou x- et z-
	                    				HammerBreak(bloc.getBlock().getType(), xb, yb-1, zb, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb+1, zb, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb-1, zb+1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb, zb+1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb+1, zb+1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb-1, zb-1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb, zb-1, p.getWorld());
		    	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb+1, zb-1, p.getWorld());
	                    			}
	                    		}else {
		                    		//truc du x
	                    			HammerBreak(bloc.getBlock().getType(), xb, yb+1, zb, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb-1, zb, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb+1, zb+1, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb+1, zb-1, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb-1, zb+1, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb-1, zb-1, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb, zb+1, p.getWorld());
	    	                    	HammerBreak(bloc.getBlock().getType(), xb, yb, zb-1, p.getWorld());
	                    		}
	                    }else if(zd<=-0.8 || zd>=0.8) {
	                    	//truc du z
	                    	HammerBreak(bloc.getBlock().getType(), xb, yb+1, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb, yb-1, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb+1, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb+1, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb-1, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb-1, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb+1, yb, zb, p.getWorld());
	                    	HammerBreak(bloc.getBlock().getType(), xb-1, yb, zb, p.getWorld());
	                    }
	                    
	                    
	                    
	     
	                   /* for(double i = p.getLocation().getX(); i < 10; i++)
	                    {
	                    world.getBlockAt(new Location(world, i, y1, z1)).setType(Material.STONE);
	                    }*/
	                }
	        }
		    
		    public void HammerBreak(Material mat, double x, double y, double z, World world) {
		    	Location bloc =new Location(world, x, y, z);
            	if(bloc.getBlock().getType() == mat) {
            		bloc.getBlock().setType(Material.AIR);
            		world.dropItemNaturally(bloc,new ItemStack(mat));
            	}
		    }
	
	
	
	
	
	
}
