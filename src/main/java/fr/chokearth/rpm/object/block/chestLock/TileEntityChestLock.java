package fr.chokearth.rpm.object.block.chestLock;

import fr.chokearth.rpm.util.handler.NetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityChestLock extends TileEntityChest {

    private String owner = "null";

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    //NBT
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.setOwner(compound.getString("owner"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("owner", this.owner);
        return compound;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        NetworkHandler.networkChestLock.sendTo(new PacketChestLockGetInfo(getPos().getX(), getPos().getY(), getPos().getZ(), this.owner), (EntityPlayerMP) player);
        return super.isUsableByPlayer(player);
    }
}
