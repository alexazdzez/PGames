package fr.na.tcharlex.PGames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.entity.Player;

import fr.na.tcharlex.PGames.Listeners.GPlayerListeners;

public class GMain extends JavaPlugin {

    private List<Player> players = new ArrayList<>();
    private GState state;

    @Override
    public void onEnable() {
        System.out.print("hello");
        setState(GState.WAITING);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new GPlayerListeners(this), this);

    }
    public void setState(GState state) {
        this.state = state;
    }

    public boolean isState(GState state) {
        return this.state == state;
    }

    public List<Player> getPlayers(){
        return players;
    }
}
