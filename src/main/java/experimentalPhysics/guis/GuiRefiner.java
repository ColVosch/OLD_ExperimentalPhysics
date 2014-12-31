package experimentalPhysics.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerRefiner;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

public class GuiRefiner extends GuiContainer
{
	private TileEntityRefiner tileRefiner;
	
	public GuiRefiner(EntityPlayer player, TileEntityRefiner tileRefiner)
	{
		super(new ContainerRefiner(player, tileRefiner));
		this.tileRefiner = tileRefiner;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal("container.inventory"), 7, 73, 16777215); 
		this.drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal("container.refiner"), 7, 7, 16777215);
	}
	
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(ExperimentalPhysics.getMainTextureFolder()+":textures/guis/GuiRefiner.png"));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		
		if (tileRefiner.isLit())
		{
			drawTexturedModalRect(j + 9, k + 32, 176, 16, 16, 16);
		}
		drawTexturedModalRect(j + 84, k + 32, 176, 0, 8, tileRefiner.getProgressScaled(9));
	}
}
