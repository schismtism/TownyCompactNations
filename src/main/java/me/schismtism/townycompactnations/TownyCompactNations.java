package me.schismtism.townycompactnations;

import com.palmergames.bukkit.towny.event.NationPreAddTownEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public final class TownyCompactNations extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("TownyCompactNations enabled");
        getServer().getPluginManager().registerEvents(this, this );
    }

    @EventHandler
    public void onNationInviteTown(NationPreAddTownEvent event) {
        Collection<TownBlock> neighborsWithTown = NeighborUtil.getNeighboringTownBlocksWithOtherTown(event.getTown().getTownBlocks());
        boolean isTownBorderingNation = false;

        for (TownBlock townBlock : neighborsWithTown) {
            Town town = townBlock.getTownOrNull();
            if (town == null)
                continue;

            Nation nation = town.getNationOrNull();
            if (nation == null)
                continue;

            if (event.getNation() == nation) {
                isTownBorderingNation = true;
                break;
            }
        }

        if (!isTownBorderingNation) {
            event.setCancelled(true);
            event.setCancelMessage("Invited town must border another town in your nation");
        }
    }
}