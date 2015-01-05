package experimentalPhysics.guis;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerEmpty;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefiner;

public class GuiAdvancedRefinerDisplay extends GuiContainer
{
	TileEntityAdvancedRefiner refiner;
	
	public GuiAdvancedRefinerDisplay(TileEntityAdvancedRefiner refiner)
	{
		super(new ContainerEmpty());
		this.refiner = refiner;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		if (refiner != null)
		{
			drawString(fontRenderer, String.format("Progress:     %4.2f", ((float) refiner.getProgress()) / ((float) TileEntityAdvancedRefiner.REQUIRED_PROGRESS)), 5, 5, Color.WHITE.getRGB());
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
