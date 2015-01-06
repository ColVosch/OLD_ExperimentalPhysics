package experimentalPhysics.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import experimentalPhysics.ExperimentalPhysics;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEnderPearlReusable extends Item
{
	private String name = "itemEnderPearlReusable";
	
	private IIcon[] icons;
	
	public ItemEnderPearlReusable()
	{
		setUnlocalizedName(name);
		setTextureName(ExperimentalPhysics.MODID+":enderPearlReusable");
		setMaxStackSize(1);
		setMaxDamage(8);
		GameRegistry.registerItem(this, name);
	}	
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[8];
 
        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon(ExperimentalPhysics.MODID + ":" + name + (i + 1));
        }
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!(player.capabilities.isCreativeMode))
        {
            itemStack.attemptDamageItem(1, itemRand);
            if (itemStack.getItemDamage() == 8)
            {
            	itemStack = new ItemStack(ModItems.enderPearlCore);
            }
        }

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
		    world.spawnEntityInWorld(new EntityEnderPearl(world, player));
		}
		
		return itemStack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        
		return damage < 8 ? icons[(8 - damage - 1)] : icons[7];
    }
}
