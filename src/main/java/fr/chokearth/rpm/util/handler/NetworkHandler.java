package fr.chokearth.rpm.util.handler;

import fr.chokearth.rpm.object.block.chestLock.PacketChestLockGetInfo;
import fr.chokearth.rpm.object.block.stand.network.PacketStandSellPushButton;
import fr.chokearth.rpm.object.block.stand.network.PacketStandGetInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static SimpleNetworkWrapper networkStand;
    public static SimpleNetworkWrapper networkChestLock;

    public static void packetInit(){
        networkStand = NetworkRegistry.INSTANCE.newSimpleChannel("RPM_StandSell");
        networkStand.registerMessage(PacketStandSellPushButton.Handler.class, PacketStandSellPushButton.class, 0, Side.SERVER);
        networkStand.registerMessage(PacketStandGetInfo.Handler.class, PacketStandGetInfo.class, 1, Side.CLIENT);

        networkChestLock = NetworkRegistry.INSTANCE.newSimpleChannel("TPM_ChestLock");
        networkChestLock.registerMessage(PacketChestLockGetInfo.Handler.class, PacketChestLockGetInfo.class, 0, Side.CLIENT);
    }
}
