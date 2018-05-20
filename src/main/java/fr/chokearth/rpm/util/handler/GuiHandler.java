package fr.chokearth.rpm.util.handler;

import fr.chokearth.rpm.object.block.stand.gui.*;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import fr.chokearth.rpm.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == Reference.GUI_STAND_STORAGE) return new ContainerStandStorage(player.inventory, (TileEntityStand)world.getTileEntity(new BlockPos(x, y, z)));
        if(ID == Reference.GUI_STAND_SELL_OWNER) return new ContainerStandSellOwner(player.inventory, (TileEntityStand) world.getTileEntity(new BlockPos(x, y, z)));
        if(ID == Reference.GUI_STAND_SELL_BUYER) return new ContainerStandSellBuyer(player.inventory, (TileEntityStand) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_STAND_STORAGE) return new GuiStandStorage(player.inventory, (TileEntityStand)world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_STAND_SELL_OWNER) return new GuiStandSellOwner(player.inventory, (TileEntityStand)world.getTileEntity(new BlockPos(x, y, z)));
        if (ID == Reference.GUI_STAND_SELL_BUYER) return new GuiStandSellBuyer(player.inventory, (TileEntityStand)world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }
}
