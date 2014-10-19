package ad.mod.fhbgds.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ad.mod.fhbgds.ADMain;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRegistryHelper {

	private CraftingRegistryHelper() {}

	public static void addMAPRecipe(ItemStack result, Object... components){
		Object[] finalComponents = new Object[components.length + 1];
		int i;
		for(i = 0; i < finalComponents.length - 1; i++){
			finalComponents[i] = components[i];
		}
		finalComponents[i] = new ItemStack(ADMain.mortarAndPestle, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addRecipe(new ShapelessOreRecipe(result, finalComponents));
	}
	
	public static void addITRecipe(ItemStack result, Object... components){
		Object[] finalComponents = new Object[components.length + 1];
		int i;
		for(i = 0; i < finalComponents.length - 1; i++){
			finalComponents[i] = components[i];
		}
		finalComponents[i] = new ItemStack(ADMain.infernoTablet, 1, OreDictionary.WILDCARD_VALUE);
		GameRegistry.addShapelessRecipe(result, finalComponents);
	}
	
	public static void addCraftingSmelting(Block component){
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(component));
		GameRegistry.addShapelessRecipe(result, component, new ItemStack(ADMain.infernoTablet, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	public static void addCraftingSmelting(Item component){
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(component));
		GameRegistry.addShapelessRecipe(result, component, new ItemStack(ADMain.infernoTablet, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	public static void addCraftingSmelting(ItemStack component){
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(component);
		GameRegistry.addShapelessRecipe(result, component, new ItemStack(ADMain.infernoTablet, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	public static void addRemainingRecipes(){
		GameRegistry.addShapelessRecipe(new ItemStack(Items.quartz, 4), Blocks.quartz_block);
		GameRegistry.addShapedRecipe(new ItemStack(Items.string), 
			"# #", 
			" # ", 
			"# #", 
			'#'	 , Blocks.tallgrass
		);
		addMAPRecipe(new ItemStack(ADMain.oreDust, 1, 1), "ingotGold");
		addMAPRecipe(new ItemStack(ADMain.oreDust, 1, 0), "ingotIron");
		addMAPRecipe(new ItemStack(Items.blaze_powder, 4), "dustGold", Items.redstone, Items.lava_bucket, Items.glowstone_dust);
		addMAPRecipe(new ItemStack(Items.gunpowder, 4), Items.blaze_powder, new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE), ADMain.silica);
		addMAPRecipe(new ItemStack(ADMain.angelDust, 4),
			"dustIron", 
			"dustGold", 
			Items.glowstone_dust, 
			"dustFeather", 
			"dustQuartz",
			Items.diamond
		);
		GameRegistry.addShapelessRecipe(new ItemStack(ADMain.infernoTablet), 
			Items.blaze_powder, 
			Blocks.coal_block, 
			Items.lava_bucket, 
			Items.fire_charge
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.mortarAndPestle), 
			" S ", 
			"FDF", 
			" B ", 
			'S', Items.stick, 
			'D', Items.quartz, 
			'F', Items.iron_ingot, 
			'B', ADMain.stoneBowl
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.wingComponentBack), 
			"# #", 
			"###", 
			"###", 
			'#', ADMain.pureIngot
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.boots), 
			"C C", 
			"C C", 
			"W W", 
			'C', ADMain.nanoMesh, 
			'W', ADMain.angelDust
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.stoneBowl, 4), 
			"# #", 
			" # ", 
			'#', Blocks.stone
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.wingComponent), 
			"#HH", 
			" #H", 
			"  #", 
			'#', Items.feather, 
			'H', ADMain.nanoMesh
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.nanoMesh), 
			"#S#", 
			"SDS", 
			"#S#", 
			'#', ADMain.silica, 
			'S', Items.string, 
			'D', ADMain.angelDust
		);
		GameRegistry.addShapedRecipe(new ItemStack(ADMain.wings), 
			"#D#", 
			'#', ADMain.wingComponent, 
			'D', ADMain.wingComponentBack
		);

	}
}
