package fr.chokearth.rpm.object.block.stand.gui.container;

import fr.chokearth.rpm.object.block.stand.slot.SlotStand;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStandSellOwner extends Container {

    private int[] field = new int[27];
    private final TileEntityStand tileEntity;

    public ContainerStandSellOwner(InventoryPlayer player, TileEntityStand tileEntity) {
        this.tileEntity = tileEntity;

        this.addSlotToContainer(new SlotStand(tileEntity, 0, 8, 9));
        this.addSlotToContainer(new SlotStand(tileEntity, 1, 89, 9));
        this.addSlotToContainer(new SlotStand(tileEntity, 2, 8, 35));
        this.addSlotToContainer(new SlotStand(tileEntity, 3, 89, 35));
        this.addSlotToContainer(new SlotStand(tileEntity, 4, 8, 61));
        this.addSlotToContainer(new SlotStand(tileEntity, 5, 89, 61));

        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 9; x++)
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));

        for (int x = 0; x < 9; x++)
            this.addSlotToContainer(new Slot(player, x, 8+x * 18, 142));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }

    public TileEntityStand getTileEntity() {
        return tileEntity;
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileEntity);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); i++){
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            for (int v = 0; v < this.tileEntity.getFieldCount(); v++){
                if (field[v] != this.tileEntity.getField(v)) listener.sendWindowProperty(this, v, this.tileEntity.getField(v));
            }
        }

        for (int i = 0; i < this.tileEntity.getFieldCount(); i++)
            this.field[i] = this.tileEntity.getField(i);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        if(slotId > -1 && slotId < 6){
            if (player.inventory.getItemStack().getItem().getUnlocalizedName().equals(Items.AIR.getUnlocalizedName()) && this.tileEntity.getItemNb(slotId) == 0)
                tileEntity.setInventorySlotContents(slotId, new ItemStack(Items.AIR));
            else if (this.tileEntity.getItemNb(slotId) == 0 && haveItemStack(player.inventory.getItemStack(), slotId)){
                this.tileEntity.setInventorySlotContents(slotId, player.inventory.getItemStack().copy());
            }
        }
        this.tileEntity.markDirty();
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    private boolean haveItemStack(ItemStack stack, int slot){

        for (int v = 0; v < 6; v++)
            if (this.tileEntity.getStackInSlot(v).getUnlocalizedName().equals(stack.getUnlocalizedName()) && slot != v)
                return false;

        return true;
    }
}
