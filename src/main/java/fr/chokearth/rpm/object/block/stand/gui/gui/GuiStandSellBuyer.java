package fr.chokearth.rpm.object.block.stand.gui.gui;

import fr.chokearth.rpm.object.block.stand.gui.container.ContainerStandSellBuyer;
import fr.chokearth.rpm.object.block.stand.tileEntity.TileEntityStand;
import fr.chokearth.rpm.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiStandSellBuyer extends GuiContainer {

    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/container/stand_sell.png");
    private final InventoryPlayer player;
    private final TileEntityStand tileEntity;

    public GuiStandSellBuyer(InventoryPlayer player, TileEntityStand tileEntity) {
        super(new ContainerStandSellBuyer(player, tileEntity));
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

        //button
        int v = 0;
        int t = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 2; x++) {
                if (this.tileEntity.getField(t) < 10)
                    this.fontRenderer.drawString(this.tileEntity.getField(t) + "", i + 26 + (81 * x) + 4, j + 12 + (26 * y), 4210752);
                else
                    this.fontRenderer.drawString(this.tileEntity.getField(t) + "", i + 26 + (81 * x), j + 12 + (26 * y), 4210752);

                if (this.tileEntity.getField(t + 6) < 10)
                    this.fontRenderer.drawString(this.tileEntity.getField(t + 6) + "", i + 26 + (81 * x) + 16 + 4, j + 12 + (26 * y), 4210752);
                else
                    this.fontRenderer.drawString(this.tileEntity.getField(t + 6) + "", i + 26 + (81 * x) + 16, j + 12 + (26 * y), 4210752);

                if (this.tileEntity.getField(t + 12) < 10)
                    this.fontRenderer.drawString(this.tileEntity.getField(t + 12) + "", i + 26 + (81 * x) + 32 + 4, j + 12 + (26 * y), 4210752);
                else
                    this.fontRenderer.drawString(this.tileEntity.getField(t + 12) + "", i + 26 + (81 * x) + 32, j + 12 + (26 * y), 4210752);

                t++;
            }
        }
    }
}
