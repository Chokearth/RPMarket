package fr.chokearth.rpm.util.handler;

import fr.chokearth.rpm.object.block.stand.network.PacketStandSellPushButton;
import fr.chokearth.rpm.object.block.stand.network.PacketStandGetInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static SimpleNetworkWrapper networkStand;

    public static void packetInit(){
        networkStand = NetworkRegistry.INSTANCE.newSimpleChannel("RPM_StandSell");
        networkStand.registerMessage(PacketStandSellPushButton.Handler.class, PacketStandSellPushButton.class, 0, Side.SERVER);
        networkStand.registerMessage(PacketStandGetInfo.Handler.class, PacketStandGetInfo.class, 1, Side.CLIENT);
    }
}
