package fr.chokearth.rpm.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainConfigFile {

    private static Configuration config;

    //VAR
    public static int maxSizeStantStorage = 3456;

    //CATEGORY
    private static final String CATEGORY_STAND = "stand";

    public static void preInitConfig(FMLPreInitializationEvent e){
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "RPMarket.cfg"));
        config.load();

        syncConfig();
    }

    public static Configuration getConfig(){
        return config;
    }

    public static void syncConfig(){
        Property prop;
        List<String> propOrder = new ArrayList<String>();

        prop = config.get(CATEGORY_STAND, "maxSizeStantStorage", 3456);
        prop.setComment("Maximum storage capacity of the stand");
        maxSizeStantStorage = prop.getInt();
        propOrder.add(prop.getName());

        if (config.hasChanged()) config.save();
    }

}
