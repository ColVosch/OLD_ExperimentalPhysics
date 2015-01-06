package experimentalPhysics.guis;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerRefiner;
import experimentalPhysics.tileEntitys.TileEntityRefiner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

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
		this.drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal("container.inventory"), 7, 73, Color.WHITE.getRGB()); 
		this.drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal("container.refiner"), 7, 7, Color.WHITE.getRGB());
	}
	
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		//TODO extract
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(ExperimentalPhysics.MODID + ":textures/guis/GuiRefiner.png"));
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
