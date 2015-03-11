package experimentalPhysics.spaceField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import experimentalPhysics.util.Position;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SpaceField
{	
	
	public static HashMap<Integer, SpaceField> spaceFields = new HashMap();
	
	public ArrayList<TensionPoint> tensionPoints;
	
	
	public SpaceField()
	{
		tensionPoints = new ArrayList();
		
	}
	
	public void readFromNBT(NBTBase tag)
	{
		NBTTagList tensionTagList = (NBTTagList) tag;
		for (int i = 0; i < tensionTagList.tagCount(); i++)
		{
			tensionPoints.add(TensionPoint.tensionPointFromNBT(tensionTagList.getCompoundTagAt(i)));
		}
	}

	public NBTTagList writeToNBT()
	{
		NBTTagList tensionTagList = new NBTTagList();
		for (TensionPoint tensionPoint : tensionPoints)
		{
			tensionTagList.appendTag(tensionPoint.writeToNBT(new NBTTagCompound()));
		}
		return tensionTagList;
	}

	
	public void addTensionPoint(Position pos, int strength)
	{
		TensionPoint tensionPoint = new TensionPoint(pos, strength);
		//TODO redundant?
//		Iterator i = tensionPoints.iterator();
//		while (i.hasNext())
//		{		
//			TensionPoint p = (TensionPoint) i.next();		
//			if (p.x == tensionPoint.x && p.y == tensionPoint.y && p.z == tensionPoint.z)
//			{
//				tensionPoint.strength += p.strength;
//				i.remove();
//			}
//		}
		tensionPoints.add(tensionPoint);
	}
	
	public void removeTensionPoint(Position pos)
	{
		Iterator i = tensionPoints.iterator();
		while (i.hasNext())
		{
			TensionPoint point = (TensionPoint) i.next();
			if (point.x == pos.x && point.y == pos.y && point.z == pos.z)
			{
				i.remove();
			}
		}
	}
}
