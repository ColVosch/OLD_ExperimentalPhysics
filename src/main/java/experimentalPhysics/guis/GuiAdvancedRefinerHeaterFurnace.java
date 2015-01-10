package experimentalPhysics.guis;

import org.lwjgl.opengl.GL11;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.containers.ContainerAdvancedRefinerHeaterFurnace;
import experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerHeaterFurnace;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.AdvancedModelLoader;

public class GuiAdvancedRefinerHeaterFurnace extends GuiContainer
{

	private TileEntityAdvancedRefinerHeaterFurnace heater;

	public GuiAdvancedRefinerHeaterFurnace(EntityPlayer player, TileEntityAdvancedRefinerHeaterFurnace heater)
	{
		super(new ContainerAdvancedRefinerHeaterFurnace(player, heater));
		this.heater = heater;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(ExperimentalPhysics.MODID + ":textures/guis/GuiAdvancedRefinerHeaterFurnace.png"));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		
		drawTexturedModalRect(j + 81, k + 21 + 12 - heater.getFuelTimeRemainingScaled(13), 176, 13 - heater.getFuelTimeRemainingScaled(13), 13, heater.getFuelTimeRemainingScaled(13));
	}

}
