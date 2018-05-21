package fr.chokearth.rpm.object.block.stand.network;

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

public class PacketStandGetInfo implements IMessage{

    private int field[] = new int[27];
    private int x;
    private int y;
    private int z;
    private String owner;
    private String customName;

    public PacketStandGetInfo(){}

    public PacketStandGetInfo(int[] field, int x, int y, int z, String owner, String customName) {
        this.field = field;
        this.x = x;
        this.y = y;
        this.z = z;
        this.owner = owner;
        this.customName = customName;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        for (int i =0; i < 27; i++)
            this.field[i] = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.owner = ByteBufUtils.readUTF8String(buf);
        this.customName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (int i =0; i < 27; i++)
            buf.writeInt(field[i]);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeUTF8String(buf, owner);
        ByteBufUtils.writeUTF8String(buf, customName);
    }

    public static class Handler implements IMessageHandler<PacketStandGetInfo, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketStandGetInfo message, MessageContext ctx) {
            TileEntityStand entity = (TileEntityStand) Minecraft.getMinecraft().world.getTileEntity(new BlockPos(message.x, message.y, message.z));
            entity.setField(message.field);
            entity.setOwner(message.owner);
            entity.setReste();
            entity.update();
            entity.setCustomName(message.customName);
            return null;
        }
    }
}
