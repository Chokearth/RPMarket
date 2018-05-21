package fr.chokearth.rpm.object.block.stand.gui.container;

import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.object.block.stand.slot.SlotStand;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerStandStorage extends Container {

    private int[] field = new int[27];
    private final TileEntityStand tileEntity;

    public ContainerStandStorage(InventoryPlayer player, TileEntityStand tileEntity) {
        this.tileEntity = tileEntity;

        this.addSlotToContainer(new SlotStand(tileEntity, 6, 98, 9));
        this.addSlotToContainer(new SlotStand(tileEntity, 7, 152, 9));
        this.addSlotToContainer(new SlotStand(tileEntity, 8, 98, 35));
        this.addSlotToContainer(new SlotStand(tileEntity, 9, 152, 35));
        this.addSlotToContainer(new SlotStand(tileEntity, 10, 98, 61));
        this.addSlotToContainer(new SlotStand(tileEntity, 11, 152, 61));

        this.addSlotToContainer(new SlotStand(tileEntity, 12, 35, 61));

        this.addSlotToContainer(new SlotStand(tileEntity, 13, 17, 23));
        this.addSlotToContainer(new SlotStand(tileEntity, 14, 35, 23));
        this.addSlotToContainer(new SlotStand(tileEntity, 15, 53, 23));

        for (int y = 0; y < 3; y++){
            for (int x = 0; x < 9; x++){

                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));

            }
        }

        for (int x = 0; x < 9; x++){
            this.addSlotToContainer(new Slot(player, x, 8+x * 18, 142));
        }
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

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {

        if (slotId == 6){ //depot

            for (int i = 0; i < 6; i++){
                if (this.tileEntity.getStackInSlot(i).getUnlocalizedName().equals(player.inventory.getItemStack().getUnlocalizedName()) && !this.tileEntity.getStackInSlot(i).getUnlocalizedName().equals(ItemStack.EMPTY.getUnlocalizedName()) && this.tileEntity.getReste() >= player.inventory.getItemStack().getCount()){
                    this.tileEntity.setItemNb(i, this.tileEntity.getItemNb(i)+player.inventory.getItemStack().getCount());
                    player.inventory.setItemStack(ItemStack.EMPTY);
                }else if (this.tileEntity.getStackInSlot(i).getUnlocalizedName().equals(player.inventory.getItemStack().getUnlocalizedName()) && !this.tileEntity.getStackInSlot(i).getUnlocalizedName().equals(ItemStack.EMPTY.getUnlocalizedName()) && this.tileEntity.getReste() < player.inventory.getItemStack().getCount()){
                    player.inventory.getItemStack().setCount(player.inventory.getItemStack().getCount() - this.tileEntity.getReste());
                    this.tileEntity.setItemNb(i, this.tileEntity.getItemNb(i) + this.tileEntity.getReste());
                }
            }

        }else if (slotId >= 0 && slotId < 6){ //retrait

            if (player.inventory.getItemStack().getUnlocalizedName().equals(ItemStack.EMPTY.getUnlocalizedName())){
                ItemStack stack = this.tileEntity.getStackInSlot(slotId).copy();
                if (this.tileEntity.getItemNb(slotId) < stack.getMaxStackSize()){
                    stack.setCount(this.tileEntity.getItemNb(slotId));
                    this.tileEntity.setItemNb(slotId, 0);
                }else{
                    stack.setCount(stack.getMaxStackSize());
                    this.tileEntity.setItemNb(slotId, this.tileEntity.getItemNb(slotId) - stack.getCount());
                }
                player.inventory.setItemStack(stack);
            }

        }else if (slotId > 6 && slotId < 10 && player.inventory.getItemStack().getUnlocalizedName().equals(ItemStack.EMPTY.getUnlocalizedName())){
            ItemStack item;

            switch (slotId){
                case 7:
                    item = new ItemStack(ItemInit.MONEY_BRONZE);
                    break;
                case 8:
                    item = new ItemStack(ItemInit.MONEY_SILVER);
                    break;
                case 9:
                    item = new ItemStack(ItemInit.MONEY_GOLD);
                    break;
                default:
                    item = ItemStack.EMPTY;
            }

            if (this.tileEntity.getMoneyNb(slotId-7) <= item.getMaxStackSize())
                item.setCount(this.tileEntity.getMoneyNb(slotId-7));
            else
                item.setCount(item.getMaxStackSize());
            player.inventory.setItemStack(item);
            this.tileEntity.setMoneyNb(slotId-7, this.tileEntity.getMoneyNb(slotId-7)-item.getCount());
        }

        this.tileEntity.update();
        this.detectAndSendChanges();
        this.tileEntity.markDirty();
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
