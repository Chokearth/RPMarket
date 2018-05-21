package fr.chokearth.rpm.util.handler;

import fr.chokearth.rpm.object.block.chestLock.TileEntityChestLock;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

    public static void registerTileEntity(){
        GameRegistry.registerTileEntity(TileEntityStand.class, "stand_sell");
        GameRegistry.registerTileEntity(TileEntityChestLock.class, "chest_lock");
    }

}
