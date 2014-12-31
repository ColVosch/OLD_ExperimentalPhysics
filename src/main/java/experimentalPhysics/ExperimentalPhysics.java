package experimentalPhysics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import experimentalPhysics.blocks.ModBlocks;
import experimentalPhysics.guis.GuiHandler;
import experimentalPhysics.items.ModItems;
import experimentalPhysics.network.Packets;

	@Mod(modid=ExperimentalPhysics.MODID, name="Experimental Physics", version="0.0.2")
	public class ExperimentalPhysics  
	{
	        
		public static final String MODID = "experimentalphysics";
		public static CreativeTabs expPhysTab = new ExperimentalPhysicsCreativeTab();
		
		@Instance(value = "experimentalphysics")
        public static ExperimentalPhysics instance;
       
        @SidedProxy(clientSide="experimentalPhysics.client.ClientProxy", serverSide="experimentalPhysics.CommonProxy")
        public static CommonProxy proxy;
       
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) 
        {  
        	ModBlocks.init(expPhysTab);
        	ModItems.init(expPhysTab);
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event)
        {
        	initRecepies();
        	proxy.registerRenderers();
        	NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        	Packets.register();
        }
       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {}
        
        public static String getMainTextureFolder()
        {
        	return MODID;
        }

        
		private static void initRecepies()
        {
        	GameRegistry.addRecipe(new ItemStack(ModItems.gunpowderFuelingCell, 4), "A", "A", "A", 'A', new ItemStack(Items.gunpowder));
    		GameRegistry.addRecipe(new ItemStack(ModItems.enderPearlReusable), "FFF", "FCF", "FFF", 'F', new ItemStack(ModItems.gunpowderFuelingCell), 'C', new ItemStack(ModItems.enderPearlCore));
    		GameRegistry.addRecipe(new ItemStack(ModBlocks.refiner), "I I", "ILI", "I I", 'I', new ItemStack(Items.iron_ingot), 'L', new ItemStack(Items.dye, 1, 4));
        }
	}

