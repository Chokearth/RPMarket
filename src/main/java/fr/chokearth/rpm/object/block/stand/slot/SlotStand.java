package fr.chokearth.rpm.object.block.stand.slot;

import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotStand extends Slot {
    public SlotStand(TileEntityStand tileEntity, int i, int x, int y) {
        super(tileEntity,i, x, y);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return false;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
