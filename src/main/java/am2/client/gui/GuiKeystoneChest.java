package am2.client.gui;

import org.lwjgl.opengl.GL11;

import am2.common.blocks.tileentity.TileEntityKeystoneChest;
import am2.common.container.ContainerKeystoneChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiKeystoneChest extends GuiContainer{

	private static final ResourceLocation background = new ResourceLocation("arsmagica2", "textures/gui/keystonechestgui.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j){
		mc.renderEngine.bindTexture(background);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}

	public GuiKeystoneChest(InventoryPlayer inventoryplayer, TileEntityKeystoneChest chest){
		super(new ContainerKeystoneChest(inventoryplayer, chest));
		xSize = 176;
		ySize = 180;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
	}
}
