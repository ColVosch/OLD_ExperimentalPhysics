package experimentalPhysics.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper
{
	public static NBTTagCompound createAndGetTagCompound(NBTTagCompound compound, String key)
	{
		return (NBTTagCompound) createAndGetNBTBase(compound, key, new NBTTagCompound());
	}

	public static NBTTagList createAndGetTagList(NBTTagCompound compound, String key)
	{
		return (NBTTagList) createAndGetNBTBase(compound, key, new NBTTagList());
	}
	
	private static NBTBase createAndGetNBTBase(NBTTagCompound compound, String key, NBTBase deflt)
	{
		NBTBase tag = compound.getTag(key);
		if (tag == null)
		{
			compound.setTag(key, deflt);
			tag = compound.getTag(key);
		}
		return tag;

	}
}
