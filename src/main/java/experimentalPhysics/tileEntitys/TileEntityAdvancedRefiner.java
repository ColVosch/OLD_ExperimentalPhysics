package experimentalPhysics.tileEntitys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import experimentalPhysics.ExperimentalPhysics;
import experimentalPhysics.items.ModItems;
import experimentalPhysics.network.PacketLoadInteractor;
import experimentalPhysics.network.Packets;
import experimentalPhysics.util.Position;

public class TileEntityAdvancedRefiner extends TileEntityStoring
{
	public static final int REQUIRED_PROGRESS = 10000;
	
	private boolean formed;
	private int progress;
	private int refiningSpeed;
	private float heat;
	private float minHeat;
	private float maxHeat;
	private float dustChance;
	private List<IMultiblockInput> inputs;
	private List<Position> inputPositions;
	private List<IMultiblockOutput> outputs;
	private List<Position> outputPositions;
	private List<IAdvancedRefinerModifier> modifiers;
	private List<Position> modifierPositions;
	private Random rndGen = new Random();

	
	public TileEntityAdvancedRefiner()
	{
		super();
		progress = -1;
		inputs = new ArrayList<IMultiblockInput>();
		outputs = new ArrayList<IMultiblockOutput>();
		inputPositions = new ArrayList<Position>();
		outputPositions = new ArrayList<Position>();
		modifierPositions = new ArrayList<Position>();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("progress", progress);
		
		NBTTagCompound inputCoords;
		if (inputs != null)
		{
			inputCoords = new NBTTagCompound();
			for (int i = 0; i < inputPositions.size(); i++)
			{
				inputCoords.setIntArray(Integer.toString(i), inputPositions.get(i).toIntArray());
			}
			tagCompound.setTag("inputCoords", inputCoords);
		}
		if (outputs != null)
		{
			NBTTagCompound outputCoords = new NBTTagCompound();
			for (int i = 0; i < outputPositions.size(); i++)
			{
				outputCoords.setIntArray(Integer.toString(i), outputPositions.get(i).toIntArray());
			}
			tagCompound.setTag("outputCoords", outputCoords);
		}
		if (modifiers != null)
		{
			NBTTagCompound modifierCoords = new NBTTagCompound();
			for (int i = 0; i < modifierPositions.size(); i++)
			{
				modifierCoords.setIntArray(Integer.toString(i), modifierPositions.get(i).toIntArray());
			}
			tagCompound.setTag("modifierCoords", modifierCoords);
		}
		System.out.println(tagCompound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		inputPositions = new ArrayList<Position>();
		outputPositions = new ArrayList<Position>();
		modifierPositions = new ArrayList<Position>();
		progress = tagCompound.getInteger("progress");
		initPositionList(tagCompound.getCompoundTag("inputCoords"), inputPositions);
		initPositionList(tagCompound.getCompoundTag("outputCoords"), outputPositions);
		initPositionList(tagCompound.getCompoundTag("modifierCoords"), modifierPositions);
	}
	
	private void initPositionList(NBTTagCompound coordCompound, List<Position> posList)
	{
		boolean shouldContinue = true;
		for (int i = 0; shouldContinue && i < 27; i++)
		{
			int[] coords = coordCompound.getIntArray(Integer.toString(i));
			if (coords.length == 3)
			{
				posList.add(new Position(coords));
			}
			else
			{
				shouldContinue = false;
			}
		}
	}
	
	private void initInteractorsFromCoords()
	{
		if (!worldObj.isRemote)
		{
			System.out.println("Interactors from coords. Side: server");
		}
		else
		{
			System.out.println("Interactors from coords. Side: client");
		}
		for (Position p : inputPositions)
		{
			inputs.add((IMultiblockInput) worldObj.getTileEntity(p.x, p.y, p.z));
			Packets.network.sendToAll(new PacketLoadInteractor(xCoord, yCoord, zCoord, p.x, p.y, p.z));
		}
		for (Position p : outputPositions)
		{
			outputs.add((IMultiblockOutput) worldObj.getTileEntity(p.x, p.y, p.z));
			Packets.network.sendToAll(new PacketLoadInteractor(xCoord, yCoord, zCoord, p.x, p.y, p.z));
		}			
		for (Position p : modifierPositions)
		{
			modifiers.add((IAdvancedRefinerModifier) worldObj.getTileEntity(p.x, p.y, p.z));
			Packets.network.sendToAll(new PacketLoadInteractor(xCoord, yCoord, zCoord, p.x, p.y, p.z));
		}	
		
	}
	
	

	@Override
	public String getInventoryName()
	{
		return "container.refinerAdvanced";
	}
	

	@Override
	protected void initInvenory()
	{
		/* 
		 * [0] = input
		 * [1] = outpuMain
		 * [2] = outputSecondary
		 */
		inventory = new ItemStack[3];
	}
	

	public void form()
	{
		computeAttributes();
	}
	

	public void registerInput(IMultiblockInput input)
	{
		if (!inputs.contains(input))
		{
			inputs.add(input);
			Position position = new Position(input.getCoords());
			boolean contains = false;
			for (Position p : inputPositions)
			{
				if (contains)
				{
					continue;
				}
				if (p.equals(position))
				{
					contains = true;
				}
			}
			if (contains)
			{
				inputPositions.add(new Position(input.getCoords()));
			}
		}
	}
	
	
	public void registerOutput(IMultiblockOutput output)
	{
		outputs.add(output);
		outputPositions.add(new Position (output.getCoords()));
	}
	
	
	public void registerModifyer(IAdvancedRefinerModifier modifier)
	{
		modifiers.add(modifier);
		modifierPositions.add(new Position(modifier.getCoords()));
		computeAttributes();
	}
	
	
	private void computeAttributes()
	{
		refiningSpeed = 100;
		
		minHeat = 200;
		maxHeat = 500;
		heat = 350;
		
		dustChance = 0F;
		
		if (modifiers != null)
		{
			for (IAdvancedRefinerModifier modifier : modifiers)
			{
				refiningSpeed += modifier.getRefiningSpeedChange();
				minHeat += modifier.getMinHeatChange();
				maxHeat += modifier.getMaxHeatChange();
				dustChance *= 1 + modifier.getDustChanceChange();
			}
		}
	}
	

	@Override
	public void updateEntity()
	{
		if (progress == -1)
		{
			if (canStartRefining())
			{
				progress = 0;
			}
		}
		
		if (progress >= 0 && progress < REQUIRED_PROGRESS)
		{
			progress += refiningSpeed;
		}
		
		if (progress >= REQUIRED_PROGRESS)
		{
			process();
			progress = -1;
		}	
	}
	
	
	private boolean canStartRefining()
	{
		if (inventory[0] == null)
		{
			inputItem();
		}
		return inventory[0] != null && (inventory[1] == null || inventory[1].stackSize < inventory[1].getMaxStackSize()) && (inventory[2] == null || inventory[2].stackSize < inventory[2].getMaxStackSize()) && heat <= maxHeat && heat >= minHeat;
	}
	

	private void inputItem()
	{
		if (!inputs.isEmpty())
		{
			for (IMultiblockInput input : inputs)
			{
				ItemStack inputStack = input.inputItem(Items.ender_pearl);
				if (inputStack != null && inventory[0] == null)
				{
					inventory[0] = inputStack;
					return;
				}
			}
		}
		else if (!worldObj.isRemote)
		{
			initInteractorsFromCoords();
		}
	}
	

	private void process()
	{
		decrStackSize(0, 1);
		
		if (inventory[1] == null)
		{
			inventory[1] = new ItemStack(ModItems.enderPearlCore);
		}
		else
		{
			inventory[1].stackSize ++;
		}
		
		if (rndGen.nextFloat() <= dustChance)
		{
			if (inventory[2] == null)
			{
				inventory[2] = new ItemStack(Items.cookie);
			}
			else
			{
				inventory[2].stackSize ++;
			}		
		}
		outputAllItems();
	}
	

	private void outputAllItems()
	{
		if (outputItem(inventory[1]))
		{
			inventory[1] = null;
		}
		if (outputItem(inventory[2]))
		{
			inventory[2] = null;
		}
	}
	
	private boolean outputItem(ItemStack outputStack)
	{
		if (!outputs.isEmpty())
		{
			for (IMultiblockOutput output : outputs)
			{
				if (output.outputItem(outputStack))
				{
					return true;
				}
			}
		}
		else if (!worldObj.isRemote)
		{
			initInteractorsFromCoords();
		}
		return false;
	}
}
