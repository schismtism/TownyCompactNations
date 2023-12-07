package me.schismtism.townycompactnations;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.WorldCoord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NeighborUtil {

    public static Collection<TownBlock> getNeighboringTownBlocksWithOtherTown(Collection<TownBlock> townBlocks) {
        Collection<TownBlock> result = new ArrayList<>();

        for (TownBlock townBlock : townBlocks) {
            WorldCoord centralWorldCoord = townBlock.getWorldCoord();
            List<WorldCoord> neighboringWorldCoords = getNeighboringWorldCoords(centralWorldCoord);

            for (WorldCoord wc : neighboringWorldCoords) {
                TownBlock neighboringTownBlock = TownyAPI.getInstance().getTownBlock(wc);
                if (neighboringTownBlock == null)
                    continue;

                Town neighboringTown = neighboringTownBlock.getTownOrNull();
                if (neighboringTown == null)
                    continue;

                if (neighboringTown != townBlock.getTownOrNull())
                    result.add(neighboringTownBlock);
            }
        }

        return result;
    }

    private static List<WorldCoord> getNeighboringWorldCoords(WorldCoord wc) {
        List<WorldCoord> worldCoordList = new ArrayList<>();
        worldCoordList.add(wc.add(1, 0));
        worldCoordList.add(wc.add(-1, 0));
        worldCoordList.add(wc.add(0, 1));
        worldCoordList.add(wc.add(0, -1));

        return worldCoordList;
    }
}