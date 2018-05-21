package fr.chokearth.rpm.object.block.stand.gui.gui;

import fr.chokearth.rpm.object.block.stand.gui.container.ContainerStandStorage;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import fr.chokearth.rpm.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiStandStorage extends GuiContainer {

    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/container/stand_storage.png");
    private final InventoryPlayer player;
    private final TileEntityStand tileEntity;

    public GuiStandStorage(InventoryPlayer player, TileEntityStand tileEntity){
        super(new ContainerStandStorage(player, tileEntity));
        this.player = player;
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        this.fontRenderer.drawString(this.tileEntity.getReste() + "", i+56, j+65, 4210752);
    }
}
