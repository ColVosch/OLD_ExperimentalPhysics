package experimentalPhysics.guis;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerAdvancedRefinerInsertionLock;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerInsertionLock;

public class GuiAdvancedRefinerInsertionLock extends GuiContainer
{
	public GuiAdvancedRefinerInsertionLock(EntityPlayer player, TileEntityAdvancedRefinerInsertionLock tileInsertionLock)
	{
		super(new ContainerAdvancedRefinerInsertionLock(player, tileInsertionLock));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal("container.inventory"), 7, 73, Color.WHITE.getRGB()); 
		this.drawString(Minecraft.getMinecraft().fontRenderer, StatCollector.translateToLocal("container.advancedRefinerInsertionLock"), 7, 7, Color.WHITE.getRGB());
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(ExperimentalPhysics.MODID + ":textures/guis/GuiAdvancedRefinerInsertionLock.png"));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}

}
