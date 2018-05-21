package fr.chokearth.rpm.object.item.base;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.util.inter.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    protected String name;

    public ItemBase(String name, CreativeTabs creativeTabs) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(creativeTabs);

        this.name= name;

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
