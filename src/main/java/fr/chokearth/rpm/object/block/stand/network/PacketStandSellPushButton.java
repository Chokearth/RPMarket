package fr.chokearth.rpm.object.block.stand.network;

import fr.chokearth.rpm.object.block.stand.gui.container.ContainerStandSellOwner;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketStandSellPushButton implements IMessage {

    private int id;

    public PacketStandSellPushButton(){}

    public PacketStandSellPushButton(int id) {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
    }

    public static class Handler implements IMessageHandler<PacketStandSellPushButton, IMessage> {


        @Override
        public IMessage onMessage(PacketStandSellPushButton message, MessageContext ctx) {
            ContainerStandSellOwner container = (ContainerStandSellOwner) ctx.getServerHandler().player.openContainer;
            container.getTileEntity().buttonPush(message.id);
            return null;
        }
    }
}
