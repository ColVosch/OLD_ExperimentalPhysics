package experimentalPhysics.spaceField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import experimentalPhysics.constants.ExpPhysConfig;
import experimentalPhysics.spaceField.events.SpaceFieldEvents;
import experimentalPhysics.util.Position;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import net.minecraftforge.common.DimensionManager;

public class SpaceField
{
	
	public ArrayList<TensionPoint> tensionPoints;
	int dimensionId;
	
	public Random rndGen;
	
	public SpaceField(int dimensionId)
	{
		tensionPoints = new ArrayList();
		rndGen = new Random();
		
		this.dimensionId = dimensionId;
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

	public void tryTriggerSpaceFieldEvent()
	{
		List players = DimensionManager.getWorld(dimensionId).playerEntities;
		if (players.size() == 0)
		{
			return;
		}
		
		int xOffset, zOffset, x, y, z;
		xOffset = rndGen.nextInt(1024) - 512;
		y = rndGen.nextInt(256);
		zOffset = rndGen.nextInt(1024) - 512;
		
		//TODO change to minecraft @ r
		EntityPlayer player = (EntityPlayer) (players.size() == 1 ? players.get(0) : players.get(rndGen.nextInt(players.size() - 1)));
		
		x = (int) player.posX + xOffset;
		z = (int) player.posZ + zOffset;
		tryTriggerSpaceFieldEventAt(new Position(x, y, z));
	}
	
	public void tryTriggerSpaceFieldEventAt(Position pos)
	{
		float strength = getTensionStrengthAt(pos);
		if (rndGen.nextFloat() <= Math.abs(strength) / 100)
		{
			SpaceFieldEvents.triggerSpaceFieldEventAt(DimensionManager.getWorld(dimensionId), pos, strength, rndGen);
		}
	}
	
	public float getTensionStrengthAt(Position pos)
	{
		float dist;
		float strength = 0;
		for (TensionPoint tensionPoint : tensionPoints)
		{
			dist = pos.getDistance(tensionPoint);
			strength += (tensionPoint.strength / (Math.pow(dist, 2d) / ExpPhysConfig.getSpaceTensionRangeAmplifier() + 1));
		}
		return strength;
	}
}












