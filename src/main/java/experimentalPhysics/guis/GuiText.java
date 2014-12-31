package experimentalPhysics.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiText extends GuiScreen
{
	private static final int GUI_ID = 20;
	private static final int X_POS = 10;
	private static final int Y_POS = 10;
	private static final int COLOR_WHITE = 16777215;

	public GuiText() {}
	
	public static int getId()
	{
		return GUI_ID;
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		this.drawString(Minecraft.getMinecraft().fontRenderer, "GUI!!!", X_POS, Y_POS, COLOR_WHITE);
	}
}
