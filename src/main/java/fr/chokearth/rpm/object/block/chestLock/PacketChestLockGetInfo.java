package fr.chokearth.rpm.object.block.chestLock;

import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketChestLockGetInfo implements IMessage{

    private int x;
    private int y;
    private int z;
    private String owner;

    public PacketChestLockGetInfo(){}

    public PacketChestLockGetInfo(int x, int y, int z, String owner) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.owner = owner;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.owner = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeUTF8String(buf, owner);
    }

    public static class Handler implements IMessageHandler<PacketChestLockGetInfo, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketChestLockGetInfo message, MessageContext ctx) {
            TileEntityChestLock entity = (TileEntityChestLock) Minecraft.getMinecraft().world.getTileEntity(new BlockPos(message.x, message.y, message.z));
            entity.setOwner(message.owner);
            return null;
        }
    }
}
