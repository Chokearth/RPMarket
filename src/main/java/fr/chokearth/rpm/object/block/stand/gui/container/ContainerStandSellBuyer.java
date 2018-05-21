package fr.chokearth.rpm.object.block.stand.gui.container;

import fr.chokearth.rpm.init.ItemInit;
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

import java.util.ArrayList;

public class ContainerStandSellBuyer extends Container {

    private int[] field = new int[27];
    private final TileEntityStand tileEntity;

    public ContainerStandSellBuyer(InventoryPlayer player, TileEntityStand tileEntity) {
        this.tileEntity = tileEntity;

        this.addSlotToContainer(new SlotStand(tileEntity, 0, 8, 9));
        this.addSlotToContainer(new SlotStand(tileEntity, 1, 89, 9));
        this.addSlotToContainer(new SlotStand(tileEntity, 2, 8, 35));
        this.addSlotToContainer(new SlotStand(tileEntity, 3, 89, 35));
        this.addSlotToContainer(new SlotStand(tileEntity, 4, 8, 61));
        this.addSlotToContainer(new SlotStand(tileEntity, 5, 89, 61));

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

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        if(slotId > -1 && slotId < 6){
            if (player.inventory.getItemStack().getItem().getUnlocalizedName().equals(ItemStack.EMPTY.getUnlocalizedName())){
                if (this.tileEntity.getStackInSlot(slotId).getCount() <= this.tileEntity.getItemNb(slotId)){
                    if (getTheMoney(slotId, player)){
                        this.tileEntity.setItemNb(slotId, this.tileEntity.getItemNb(slotId) - this.tileEntity.getStackInSlot(slotId).getCount());
                        player.inventory.setItemStack(this.tileEntity.getStackInSlot(slotId).copy());
                    }
                }
            }
        }
        this.tileEntity.markDirty();
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    public boolean getTheMoney(int index, EntityPlayer player){
        ArrayList<Integer> b = new ArrayList<Integer>();
        int bTot = 0;
        ArrayList<Integer> s = new ArrayList<Integer>();
        int sTot = 0;
        ArrayList<Integer> g = new ArrayList<Integer>();
        int gTot = 0;

        //search
        for (int i = 0; i < player.inventory.mainInventory.size(); ++i)
            if (player.inventory.mainInventory.get(i).getItem().getUnlocalizedName().equals(ItemInit.MONEY_BRONZE.getUnlocalizedName()))
                b.add(i);
        for (int i = 0; i < player.inventory.mainInventory.size(); ++i)
            if (player.inventory.mainInventory.get(i).getItem().getUnlocalizedName().equals(ItemInit.MONEY_SILVER.getUnlocalizedName()))
                s.add(i);
        for (int i = 0; i < player.inventory.mainInventory.size(); ++i)
            if (player.inventory.mainInventory.get(i).getItem().getUnlocalizedName().equals(ItemInit.MONEY_GOLD.getUnlocalizedName()))
                g.add(i);

        //count
        for(int slotB : b)
            bTot += player.inventory.mainInventory.get(slotB).getCount();
        for(int slotS : s)
            sTot += player.inventory.mainInventory.get(slotS).getCount();
        for(int slotG : g)
            gTot += player.inventory.mainInventory.get(slotG).getCount();

        //get
        if(bTot >= this.tileEntity.getPrixBronze(index) && sTot >= this.tileEntity.getPrixSilver(index) && gTot >= this.tileEntity.getPrixGold(index)){
            int bPick = 0;
            int sPick = 0;
            int gPick = 0;

            int i = 0;
            while(bPick != this.tileEntity.getPrixBronze(index) && i < b.size()){
                if(player.inventory.mainInventory.get(b.get(i)).getCount() <= this.tileEntity.getPrixBronze(index) - bPick){
                    bPick += player.inventory.mainInventory.get(b.get(i)).getCount();
                    player.inventory.mainInventory.set(b.get(i), new ItemStack(Items.AIR));
                }else if(player.inventory.mainInventory.get(b.get(i)).getCount() > this.tileEntity.getPrixBronze(index) - bPick){
                    ItemStack stack = player.inventory.mainInventory.get(b.get(i)).copy();
                    stack.setCount(player.inventory.mainInventory.get(b.get(i)).getCount() - (this.tileEntity.getPrixBronze(index) - bPick));
                    player.inventory.mainInventory.set(b.get(i), stack);
                    bPick = this.tileEntity.getPrixBronze(index);
                }

                i++;
            }

            i = 0;
            while(sPick != this.tileEntity.getPrixSilver(index) && i < s.size()){
                if(player.inventory.mainInventory.get(s.get(i)).getCount() <= this.tileEntity.getPrixSilver(index) - sPick){
                    sPick += player.inventory.mainInventory.get(s.get(i)).getCount();
                    player.inventory.mainInventory.set(s.get(i), new ItemStack(Items.AIR));
                }else if(player.inventory.mainInventory.get(s.get(i)).getCount() > this.tileEntity.getPrixSilver(index) - sPick){
                    ItemStack stack = player.inventory.mainInventory.get(s.get(i)).copy();
                    stack.setCount(player.inventory.mainInventory.get(s.get(i)).getCount() - (this.tileEntity.getPrixSilver(index) - sPick));
                    player.inventory.mainInventory.set(s.get(i), stack);
                    sPick = this.tileEntity.getPrixSilver(index);
                }

                i++;
            }

            i = 0;
            while(gPick != this.tileEntity.getPrixGold(index) && i < b.size()){
                if(player.inventory.mainInventory.get(g.get(i)).getCount() <= this.tileEntity.getPrixGold(index) - gPick){
                    gPick += player.inventory.mainInventory.get(g.get(i)).getCount();
                    player.inventory.mainInventory.set(g.get(i), new ItemStack(Items.AIR));
                }else if(player.inventory.mainInventory.get(g.get(i)).getCount() > this.tileEntity.getPrixGold(index) - gPick){
                    ItemStack stack = player.inventory.mainInventory.get(g.get(i)).copy();
                    stack.setCount(player.inventory.mainInventory.get(g.get(i)).getCount() - (this.tileEntity.getPrixGold(index) - gPick));
                    player.inventory.mainInventory.set(g.get(i), stack);
                    gPick = this.tileEntity.getPrixGold(index);
                }

                i++;
            }

            this.tileEntity.setMoneyNb(0, this.tileEntity.getMoneyNb(0) + this.tileEntity.getPrixBronze(index));
            this.tileEntity.setMoneyNb(1, this.tileEntity.getMoneyNb(1) + this.tileEntity.getPrixSilver(index));
            this.tileEntity.setMoneyNb(2, this.tileEntity.getMoneyNb(2) + this.tileEntity.getPrixGold(index));

            return true;
        }

        return false;
    }
}
