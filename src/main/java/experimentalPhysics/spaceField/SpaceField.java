package experimentalPhysics.spaceField;

import static experimentalPhysics.util.NBTHelper.*;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import experimentalPhysics.util.Position;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;

public class SpaceField
{	
	
	public static HashMap<Integer, SpaceField> spaceFields = new HashMap();
	
	public ArrayList<TensionPoint> tensionPoints;
	
	public SpaceField()
	{
		tensionPoints = new ArrayList();
		
	}
	
	protected NBTTagList writeToNBT()
	{
		NBTTagList tensionTagList = new NBTTagList();
		for (TensionPoint tensionPoint : tensionPoints)
		{
			tensionTagList.appendTag(tensionPoint.toNBT(new NBTTagCompound()));
		}
		return tensionTagList;
	}

	
	public void addTensionPoint(Position pos, int strength)
	{
		TensionPoint tensionPoint = new TensionPoint(pos, strength);
		boolean canNotAdd = false;
		for (int i = 0; !(i >= tensionPoints.size() || canNotAdd); i++)
		{			
			canNotAdd = tensionPoints.get(i).equals(tensionPoint);
		}
		if (!canNotAdd)
		{
			tensionPoints.add(tensionPoint);
		}
	}
	
	public void removeTensionPoint(Position pos)
	{
		for (TensionPoint point : tensionPoints)
		{
			if (point.x == pos.x && point.y == pos.y && point.z == pos.z)
			{
				tensionPoints.remove(point);
			}
		}
	}

	public void readFromNBT(NBTBase tag)
	{
		
		
	}
}
