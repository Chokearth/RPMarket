package fr.chokearth.rpm.util.handler;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.config.MainConfigFile;
import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.util.inter.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> e){
        e.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> e){
        e.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntity();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent e){
        for (Item item : ItemInit.ITEMS)
            if(item instanceof IHasModel)
                ((IHasModel)item).registerModels();

        for (Block block : BlockInit.BLOCKS)
            if (block instanceof IHasModel)
                ((IHasModel)block).registerModels();
    }

    public static void preInitRegistries(FMLPreInitializationEvent e){
        NetworkHandler.packetInit();
        MainConfigFile.preInitConfig(e);
    }

    public static void initRegistries(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
    }

    public static void postInitRegistries(){

    }
}
