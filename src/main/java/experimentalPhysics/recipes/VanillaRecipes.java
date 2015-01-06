package experimentalPhysics.recipes;

import cpw.mods.fml.common.registry.GameRegistry;

import experimentalPhysics.blocks.ModBlocks;
import experimentalPhysics.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VanillaRecipes
{
	public static void register()
	{
		//TODO create recipes for the refiner Multiblock
		GameRegistry.addRecipe(new ItemStack(ModItems.gunpowderFuelingCell, 4), "A", "A", "A", 'A', new ItemStack(Items.gunpowder));
		GameRegistry.addRecipe(new ItemStack(ModItems.enderPearlReusable), "FFF", "FCF", "FFF", 'F', new ItemStack(ModItems.gunpowderFuelingCell), 'C', new ItemStack(ModItems.enderPearlCore));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.refiner), "I I", "ILI", "I I", 'I', new ItemStack(Items.iron_ingot), 'L', new ItemStack(Items.dye, 1, 4));
	}
}
