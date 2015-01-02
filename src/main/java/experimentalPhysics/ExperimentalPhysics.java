package experimentalPhysics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import experimentalPhysics.blocks.ModBlocks;
import experimentalPhysics.guis.GuiHandler;
import experimentalPhysics.items.ModItems;
import experimentalPhysics.network.PacketController;
import experimentalPhysics.recipes.VanillaRecipes;

	@Mod(modid=ExperimentalPhysics.MODID, name="Experimental Physics", version="0.0.2")
	public class ExperimentalPhysics  
	{	        
		public static final String MODID = "experimentalphysics";
		
		@Instance(MODID)
		public static ExperimentalPhysics instance;
		@SidedProxy(clientSide="experimentalPhysics.client.ClientProxy", serverSide="experimentalPhysics.CommonProxy")
        public static CommonProxy proxy;
       
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) 
        {  
        	ModBlocks.register();
        	ModItems.register();
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event)
        {
        	VanillaRecipes.register();
        	GuiHandler.register();
			PacketController.registerPackets();        	
			proxy.registerRenderers();
        }
       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {}

	}

