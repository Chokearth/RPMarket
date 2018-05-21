package fr.chokearth.rpm.init;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.object.item.ItemPlaceStand;
import fr.chokearth.rpm.object.item.base.ItemBase;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //MATERIAL

    //ITEM
    public static final Item MONEY_BRONZE = new ItemBase("money_bronze", Main.RPMTAB);
    public static final Item MONEY_SILVER = new ItemBase("money_silver", Main.RPMTAB);
    public static final Item MONEY_GOLD = new ItemBase("money_gold", Main.RPMTAB);

    public static final Item STAND_PLACE_OAK = new ItemPlaceStand("place_stand_oak", Main.RPMTAB);
    public static final Item STAND_PLACE_SPRUCE = new ItemPlaceStand("place_stand_spruce", Main.RPMTAB);
    public static final Item STAND_PLACE_BIRCH = new ItemPlaceStand("place_stand_birch", Main.RPMTAB);
    public static final Item STAND_PLACE_JUNGLE = new ItemPlaceStand("place_stand_jungle", Main.RPMTAB);
    public static final Item STAND_PLACE_ACACIA = new ItemPlaceStand("place_stand_acacia", Main.RPMTAB);
    public static final Item STAND_PLACE_DARKOAK = new ItemPlaceStand("place_stand_darkoak", Main.RPMTAB);
}

