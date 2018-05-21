package fr.chokearth.rpm.object.block.stand.tileEntity;

import fr.chokearth.rpm.config.MainConfigFile;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.object.block.stand.network.PacketStandGetInfo;
import fr.chokearth.rpm.util.handler.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityStand extends TileEntity implements IInventory{

    //VARIABLE base
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(16, ItemStack.EMPTY);
    private String customName = "null";
    private String owner = "null";

    //VARIABLE storage
    private static final int MAX_STORAGE = MainConfigFile.maxSizeStantStorage;
    private int itemNb0, itemNb1, itemNb2, itemNb3, itemNb4, itemNb5;
    private int moneyB, moneyS, moneyG;
    private int reste;

    //VARIABLE prix
    private int prixItemBronze0, prixItemBronze1, prixItemBronze2, prixItemBronze3, prixItemBronze4, prixItemBronze5;
    private int prixItemSilver0, prixItemSilver1, prixItemSilver2, prixItemSilver3, prixItemSilver4, prixItemSilver5;
    private int prixItemGold0, prixItemGold1, prixItemGold2, prixItemGold3, prixItemGold4, prixItemGold5;

    //METHODE
    @SideOnly(Side.CLIENT)
    public void setField(int[] field){
        for(int i = 0; i < this.getFieldCount(); i++)
            this.setField(i, field[i]);
    }

    public void update(){

        for (int i = 0; i < 6; i++){
            ItemStack item = this.getStackInSlot(i).copy();
            item.setCount(this.getItemNb(i));
            this.setInventorySlotContents(i+6, item);
        }


        for (int i = 0; i < 3; i++) {
            ItemStack item;
            switch (i) {
                case 0:
                    item = new ItemStack(ItemInit.MONEY_BRONZE);
                    break;
                case 1:
                    item = new ItemStack(ItemInit.MONEY_SILVER);
                    break;
                case 2:
                    item = new ItemStack(ItemInit.MONEY_GOLD);
                    break;
                default:
                    item = ItemStack.EMPTY;
            }

            item.setCount(this.getMoneyNb(i));
            this.setInventorySlotContents(i + 13, item);
        }

        setReste();

        if (this.getReste() == this.getInventoryStackLimit()){
            world.getBlockState(pos).getBlock().setHardness(2.0f);
            world.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock().setHardness(2.0f);
        }
        else{
            world.getBlockState(pos).getBlock().setBlockUnbreakable();
            world.getBlockState(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ())).getBlock().setBlockUnbreakable();
        }

        this.markDirty();
        this.updateContainingBlockInfo();
    }

    public void buttonPush(int id){
        int v = 0;

        for (int x1 = 0; x1 < 6; x1++){
            if(id-(x1*3) == 0)this.setPrixBronze(v, this.getPrixBronze(v)+1);
            if(id-(x1*3) == 1)this.setPrixSilver(v, this.getPrixSilver(v)+1);
            if(id-(x1*3) == 2)this.setPrixGold(v, this.getPrixGold(v)+1);

            if(id-(x1*3) == 0+18)this.setPrixBronze(v, this.getPrixBronze(v)-1);
            if(id-(x1*3) == 1+18)this.setPrixSilver(v, this.getPrixSilver(v)-1);
            if(id-(x1*3) == 2+18)this.setPrixGold(v, this.getPrixGold(v)-1);

            if (this.getPrixBronze(v) > 64) this.setPrixBronze(v, 0);
            if (this.getPrixBronze(v) < 0) this.setPrixBronze(v, 64);

            if (this.getPrixSilver(v) > 64) this.setPrixSilver(v, 0);
            if (this.getPrixSilver(v) < 0) this.setPrixSilver(v, 64);

            if (this.getPrixGold(v) > 64) this.setPrixGold(v, 0);
            if (this.getPrixGold(v) < 0) this.setPrixGold(v, 64);

            v++;
        }
        this.markDirty();
    }

    //FIELD
    @Override
    public int getField(int id) {
        switch (id){
            case 0:
                return this.prixItemBronze0;
            case 1:
                return this.prixItemBronze1;
            case 2:
                return this.prixItemBronze2;
            case 3:
                return this.prixItemBronze3;
            case 4:
                return this.prixItemBronze4;
            case 5:
                return this.prixItemBronze5;
            case 6:
                return this.prixItemSilver0;
            case 7:
                return this.prixItemSilver1;
            case 8:
                return this.prixItemSilver2;
            case 9:
                return this.prixItemSilver3;
            case 10:
                return this.prixItemSilver4;
            case 11:
                return this.prixItemSilver5;
            case 12:
                return this.prixItemGold0;
            case 13:
                return this.prixItemGold1;
            case 14:
                return this.prixItemGold2;
            case 15:
                return this.prixItemGold3;
            case 16:
                return this.prixItemGold4;
            case 17:
                return this.prixItemGold5;
            case 18:
                return itemNb0;
            case 19:
                return itemNb1;
            case 20:
                return itemNb2;
            case 21:
                return itemNb3;
            case 22:
                return itemNb4;
            case 23:
                return itemNb5;
            case 24:
                return moneyB;
            case 25:
                return moneyS;
            case 26:
                return moneyG;
            default:
                return 0;

        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0:
                this.prixItemBronze0 = value;
                break;
            case 1:
                this.prixItemBronze1 = value;
                break;
            case 2:
                this.prixItemBronze2 = value;
                break;
            case 3:
                this.prixItemBronze3 = value;
                break;
            case 4:
                this.prixItemBronze4 = value;
                break;
            case 5:
                this.prixItemBronze5 = value;
                break;
            case 6:
                this.prixItemSilver0 = value;
                break;
            case 7:
                this.prixItemSilver1 = value;
                break;
            case 8:
                this.prixItemSilver2 = value;
                break;
            case 9:
                this.prixItemSilver3 = value;
                break;
            case 10:
                this.prixItemSilver4 = value;
                break;
            case 11:
                this.prixItemSilver5 = value;
                break;
            case 12:
                this.prixItemGold0 = value;
                break;
            case 13:
                this.prixItemGold1 = value;
                break;
            case 14:
                this.prixItemGold2 = value;
                break;
            case 15:
                this.prixItemGold3 = value;
                break;
            case 16:
                this.prixItemGold4 = value;
                break;
            case 17:
                this.prixItemGold5 = value;
                break;
            case 18:
                itemNb0 = value;
                break;
            case 19:
                itemNb1 = value;
                break;
            case 20:
                itemNb2 = value;
                break;
            case 21:
                itemNb3 = value;
                break;
            case 22:
                itemNb4 = value;
                break;
            case 23:
                itemNb5 = value;
                break;
            case 24:
                moneyB = value;
                break;
            case 25:
                moneyS = value;
                break;
            case 26:
                moneyG = value;
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 27;
    }

    public int getItemNb(int i){
        return getField(i+18);
    }
    public void setItemNb(int i, int value){
        setField(i+18, value);
    }

    public int getPrixBronze(int i){
        return getField(i);
    }
    public void setPrixBronze(int i, int value){
        setField(i, value);
    }

    public int getPrixSilver(int i){
        return getField(i+6);
    }
    public void setPrixSilver(int i, int value){
        setField(i+6, value);
    }

    public int getPrixGold(int i){
        return getField(i+12);
    }
    public void setPrixGold(int i, int value){
        setField(i+12, value);
    }

    public int getMoneyNb(int i){
        return getField(i+24);
    }
    public void setMoneyNb(int i, int value){
        setField(i+24, value);
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);

        for (int v = 0; v < this.getFieldCount(); v++)
            this.setField(v, compound.getInteger("field"+v));

        if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
        this.setOwner(compound.getString("owner"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        for (int v = 0; v < this.getFieldCount(); v++)
            compound.setInteger("field"+v, this.getField(v));

        ItemStackHelper.saveAllItems(compound, this.inventory);

        if(this.hasCustomName()) compound.setString("CustomName", this.customName);
        compound.setString("owner", this.owner);
        return compound;
    }

    //INVENTORY
    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.inventory)
            if(!stack.isEmpty()) return false;
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return (ItemStack)this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return MAX_STORAGE;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        int[] field = new int[this.getFieldCount()];
        for (int i = 0; i < this.getFieldCount(); i++)
            field[i] = getField(i);
        NetworkHandler.networkStand.sendTo(new PacketStandGetInfo(field, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.owner, this.customName), (EntityPlayerMP) player);
        this.update();
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    //GETTER SETTER
    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.stand";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getReste() {
        return reste;
    }

    public void setReste() {
        int reste;
        reste = this.getInventoryStackLimit();
        for (int i = 0; i < 6; i++)
            reste -= this.getItemNb(i);

        this.reste = reste;
    }
}
