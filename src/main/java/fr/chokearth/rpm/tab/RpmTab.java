package fr.chokearth.rpm.tab;

import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RpmTab extends CreativeTabs {

    public  RpmTab(){
        super("RP Market");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.MONEY_GOLD);
    }
}
