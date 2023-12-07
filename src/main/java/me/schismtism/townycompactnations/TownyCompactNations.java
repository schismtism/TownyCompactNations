package me.schismtism.townycompactnations;

import com.palmergames.bukkit.towny.event.NationPreAddTownEvent;
import com.palmergames.bukkit.towny.object.TownBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;


public final class TownyCompactNations extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Plugin Started!");
        getServer().getPluginManager().registerEvents(this, this );
    }

    //Fires when a towny town is invited to a nation

    @EventHandler
    public void onJoinNation (NationPreAddTownEvent event) {
        boolean neighboringNation = false;

        // Sends TownBlocks of the invited town to NeighborUtil, which gets all the neighboring TownBlocks

        Collection<TownBlock> originalPositions = event.getTown().getTownBlocks();
        Collection<TownBlock> positionsAndNeighbors = NeighborUtil.getNeighbors(originalPositions);

        // Checks if the invited town has any chunks bordering it owned by a town in the inviting nation

        for (TownBlock position : positionsAndNeighbors) {

            // for some reason it can only detect the invited town if position.getTownOrNull()
            // though, position.getWorldCoord().getTownOrNull() works fine

            if (position.getWorldCoord().getTownOrNull() != null) {

                if (position.getWorldCoord().getTownOrNull().getNationOrNull() != null && event.getNation() == position.getWorldCoord().getTownOrNull().getNationOrNull()) {

                    neighboringNation = true;
                }
            }
        }

        if (!neighboringNation) {
            event.setCancelled(true);
            System.out.println("Cancelled nation invite");
            event.setCancelMessage("The invited town must border another nation member to join your nation");
        }
        else
        {
            System.out.println("Nation invite allowed");
        }
    }
}