package experimentalPhysics.guis;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerEmpty;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerDisplay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiAdvancedRefinerDisplay extends GuiContainer
{
	TileEntityAdvancedRefinerDisplay display;
	
	public GuiAdvancedRefinerDisplay(TileEntityAdvancedRefinerDisplay refiner)
	{
		super(new ContainerEmpty());
		this.display = refiner;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		if (display != null)
		{
			drawString(fontRenderer, String.format("Progress:     %4.2f %%", display.getProgressPercentage()), 5, 5, Color.WHITE.getRGB());
			drawString(fontRenderer, String.format("Heat:         %5.2f",  display.getHeat()), 5, 15, Color.WHITE.getRGB());
		}
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ExperimentalPhysics.MODID + ":textures/guis/GuiEmpty.png"));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}
}
