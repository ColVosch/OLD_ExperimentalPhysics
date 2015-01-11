package experimentalPhysics.recipes;

import static cpw.mods.fml.common.registry.GameRegistry.addRecipe;
import experimentalPhysics.blocks.ModBlocks;
import experimentalPhysics.items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VanillaRecipes
{
	public static void register()
	{
		addRecipe(new ItemStack(ModItems.gunpowderFuelingCell, 4), "A", "A", "A", 'A', new ItemStack(Items.gunpowder));
		addRecipe(new ItemStack(ModItems.enderPearlReusable), "FFF", "FCF", "FFF", 'F', new ItemStack(ModItems.gunpowderFuelingCell), 'C', new ItemStack(ModItems.enderPearlCore));
		addRecipe(new ItemStack(ModBlocks.refiner), "I I", "ILI", "I I", 'I', new ItemStack(Items.iron_ingot), 'L', new ItemStack(Items.dye, 1, 4));
		addRecipe(new ItemStack(ModBlocks.advancedRefinerCasing), "LIL", "III", "LIL", 'L', new ItemStack(Items.dye, 1, 4), 'I', new ItemStack(Items.iron_ingot));
		addRecipe(new ItemStack(ModBlocks.advancedRefiner), " C ", "CBC", " C ", 'C', new ItemStack(ModItems.enderPearlCore), 'B', new ItemStack(ModBlocks.advancedRefinerCasing));
		addRecipe(new ItemStack(ModBlocks.advancedRefinerHeaterFurnace), "LIL", "III", "IFI", 'L', new ItemStack(Items.dye, 1, 4), 'I', new ItemStack(Items.iron_ingot), 'F', new ItemStack(Blocks.furnace));
		addRecipe(new ItemStack(ModBlocks.advancedRefinerDisplay), "ICI", "IRI", "IPI", 'I', new ItemStack(Items.iron_ingot), 'C', new ItemStack(ModBlocks.advancedRefinerCasing), 'R', new ItemStack(Items.redstone), 'P', new ItemStack(Blocks.glass_pane));
	}
}
