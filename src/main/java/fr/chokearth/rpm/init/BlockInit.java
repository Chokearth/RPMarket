package fr.chokearth.rpm.init;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.object.block.stand.StandSellBlock;
import fr.chokearth.rpm.object.block.stand.StandStorageBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //BLOCK
    public static final Block STAND_SELL_BLOCK_OAK = new StandSellBlock("stand_sell_oak", Material.WOOD, Main.RPMTAB, false);
    public static final Block STAND_SELL_BLOCK_SPRUCE = new StandSellBlock("stand_sell_spruce", Material.WOOD, Main.RPMTAB, false);
    public static final Block STAND_SELL_BLOCK_BIRCH = new StandSellBlock("stand_sell_birch", Material.WOOD, Main.RPMTAB, false);
    public static final Block STAND_SELL_BLOCK_JUNGLE = new StandSellBlock("stand_sell_jungle", Material.WOOD, Main.RPMTAB, false);
    public static final Block STAND_SELL_BLOCK_ACACIA = new StandSellBlock("stand_sell_acacia", Material.WOOD, Main.RPMTAB, false);
    public static final Block STAND_SELL_BLOCK_DARKOAK = new StandSellBlock("stand_sell_darkoak", Material.WOOD, Main.RPMTAB, false);

    public static final Block STAND_STORAGE_BLOCK_OAK = new StandStorageBlock("stand_storage_oak", Material.WOOD, Main.RPMTAB);
    public static final Block STAND_STORAGE_BLOCK_SPRUCE = new StandStorageBlock("stand_storage_spruce", Material.WOOD, Main.RPMTAB);
    public static final Block STAND_STORAGE_BLOCK_BIRCH = new StandStorageBlock("stand_storage_birch", Material.WOOD, Main.RPMTAB);
    public static final Block STAND_STORAGE_BLOCK_JUNGLE = new StandStorageBlock("stand_storage_jungle", Material.WOOD, Main.RPMTAB);
    public static final Block STAND_STORAGE_BLOCK_ACACIA = new StandStorageBlock("stand_storage_acacia", Material.WOOD, Main.RPMTAB);
    public static final Block STAND_STORAGE_BLOCK_DARKOAK = new StandStorageBlock("stand_storage_darkoak", Material.WOOD, Main.RPMTAB);
}
