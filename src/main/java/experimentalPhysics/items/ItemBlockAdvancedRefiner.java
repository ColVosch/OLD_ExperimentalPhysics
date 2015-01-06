package experimentalPhysics.items;

import experimentalPhysics.blocks.ModBlocks;
import experimentalPhysics.util.MultiblockHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBlockAdvancedRefiner extends ItemBlock
{
	private static final String NAME = "itemBlockAdvancedRefinerCasing";
	private IIcon icon;

	public ItemBlockAdvancedRefiner(Block block)
	{
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (!MultiblockHelper.getBlocksInCube(world, x, y, z, 2).contains(ModBlocks.advancedRefiner))
		{
			return super.onItemUse(itemStack, player, world, x, y, z, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
		}
		else
		{
			if (!world.isRemote)
			{
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.refinerCoreToClose")));
			}
			return false;
		}		
	}
}
