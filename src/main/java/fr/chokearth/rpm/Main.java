package fr.chokearth.rpm;

import fr.chokearth.rpm.proxy.CommonProxy;
import fr.chokearth.rpm.tab.RpmTab;
import fr.chokearth.rpm.util.Reference;
import fr.chokearth.rpm.util.handler.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID)
public class Main {
    @Mod.Instance
    public static Main instance;

    public static final CreativeTabs RPMTAB = new RpmTab();

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e){
        RegistryHandler.preInitRegistries();
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent e){
        RegistryHandler.initRegistries();
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent e){
        RegistryHandler.postInitRegistries();
    }
}
