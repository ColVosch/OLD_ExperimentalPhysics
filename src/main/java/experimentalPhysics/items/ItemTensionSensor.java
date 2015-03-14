package experimentalPhysics.items;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.spaceField.SpaceFieldManager;
import experimentalPhysics.spaceField.events.EventEnderOutpost;
import experimentalPhysics.util.Position;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemTensionSensor extends Item
{
	public static final String name = "itemTensionSensor";
	
	public ItemTensionSensor()
	{
		setUnlocalizedName(name);
		setTextureName(ExperimentalPhysics.MODID + ":enderPearlCore");
		setMaxStackSize(1);
		setMaxDamage(100);
		GameRegistry.registerItem(this, name);
	}

	 public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!(player.capabilities.isCreativeMode))
        {
            itemStack.attemptDamageItem(1, itemRand);
            if (itemStack.getItemDamage() == getMaxDamage())
            {
            	itemStack = null;
            }
        }

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText(Float.toString(SpaceFieldManager.spaceFields.get(world.provider.dimensionId).getTensionStrengthAt(new Position(x, y, z)))));
		}
		return true;
    }
	
}
