package fr.na.tcharlex.PGames.Listeners;

import fr.na.tcharlex.PGames.GMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.na.tcharlex.PGames.GState;

public class GPlayerListeners implements Listener {

	private GMain main;
	public GPlayerListeners(GMain main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Location spawn = new Location(Bukkit.getWorld("world"), 101, 4, 373);
		Player player = event.getPlayer();
		player.teleport(spawn);
		player.getInventory().clear();
		player.setFoodLevel(20);
		player.setHealth(20);
		player.setGameMode(GameMode.ADVENTURE);
		
		if(!main.isState(GState.WAITING)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage("La partie est d�ja commencer");
			return;
		}
		
		if(!main.getPlayers().contains(player)) main.getPlayers().add(player);
		
		if(main.isState(GState.WAITING) && main.getPlayers().size() == 1) {
			main.setState(GState.STARTING);
		}
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(main.getPlayers().contains(player)) main.getPlayers().remove(player);
		
	}

}
