package fr.chokearth.rpm.util.handler;

import fr.chokearth.rpm.object.block.stand.network.PacketStandSellGetInfo;
import fr.chokearth.rpm.object.block.stand.network.PacketStandSellPushButton;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static SimpleNetworkWrapper networkStandSell;
    public static SimpleNetworkWrapper networkStandStorage;

    public static void packetInit(){
        networkStandSell = NetworkRegistry.INSTANCE.newSimpleChannel("RPM_StandSell");
        networkStandSell.registerMessage(PacketStandSellPushButton.Handler.class, PacketStandSellPushButton.class, 0, Side.SERVER);
        networkStandSell.registerMessage(PacketStandSellGetInfo.Handler.class, PacketStandSellGetInfo.class, 1, Side.CLIENT);
    }

}
