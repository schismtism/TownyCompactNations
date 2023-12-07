package me.schismtism.townycompactnations;

import com.palmergames.bukkit.towny.object.TownBlock;

import java.util.ArrayList;
import java.util.Collection;

public class NeighborUtil {

    //Optimize this shit later, gathers every single chunk not just border chunks. It's ok, I still love you ChatGPT.
    public static Collection<TownBlock> getNeighbors(Collection<TownBlock> originalPositions) {
        Collection<TownBlock> result = new ArrayList<>();

        for (TownBlock position : originalPositions) {
            int x = position.getX();
            int z = position.getZ();
            result.add(position);

            // Add neighbors
            result.add(new TownBlock(x - 1, z, position.getWorld()));
            result.add(new TownBlock(x + 1, z, position.getWorld()));
            result.add(new TownBlock(x, z - 1, position.getWorld()));
            result.add(new TownBlock(x, z + 1, position.getWorld()));

        }


        return result;
    }
}