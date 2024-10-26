package com.triforcehero.mod.screen.custom;
import com.mojang.blaze3d.systems.RenderSystem;
import com.triforcehero.mod.Mod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
public class CrusherScreen extends AbstractContainerScreen<CrusherMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Mod.MOD_ID,"textures/gui/crusher/crusher_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Mod.MOD_ID,"textures/gui/crusher/arrow_progress.png");
    //private static final ResourceLocation CRYSTAL_TEXTURE =
           // ResourceLocation.parse("textures/block/amethyst_cluster.png");
    public CrusherScreen(CrusherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    @Override
    protected void init() {
        super.init();
        // Gets rid of title and inventory title
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(guiGraphics, x, y);
        //renderProgressCrystal(guiGraphics, x, y);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE,x + 73, y + 35, 0, 0, menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }
    //private void renderProgressCrystal(GuiGraphics guiGraphics, int x, int y) {
      //  if(menu.isCrafting()) {
          //  guiGraphics.blit(CRYSTAL_TEXTURE, x + 104, y + 13 + 16 - menu.getScaledCrystalProgress(), 0,
              //      16 - menu.getScaledCrystalProgress(), 16, menu.getScaledCrystalProgress(),16, 16);
        //}
   // }
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}