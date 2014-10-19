package ad.mod.fhbgds;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespaced;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import ad.mod.fhbgds.items.ItemAngelDust;
import ad.mod.fhbgds.items.ItemBoots;
import ad.mod.fhbgds.items.ItemGenericItem;
import ad.mod.fhbgds.items.ItemHolyFabric;
import ad.mod.fhbgds.items.ItemInfernoTablet;
import ad.mod.fhbgds.items.ItemMortarAndPestle;
import ad.mod.fhbgds.items.ItemOreDust;
import ad.mod.fhbgds.items.ItemPureIngot;
import ad.mod.fhbgds.items.ItemStoneBowl;
import ad.mod.fhbgds.items.ItemWingComponent;
import ad.mod.fhbgds.items.ItemWingComponentBack;
import ad.mod.fhbgds.items.ItemWings;
import ad.mod.fhbgds.lib.Reference;
import ad.mod.fhbgds.proxy.ProxyCommon;
import ad.mod.fhbgds.util.CraftingRegistryHelper;
import ad.mod.fhbgds.util.EnchantmentContagious;
import ad.mod.fhbgds.util.Listener;
import ad.mod.fhbgds.util.TabAngelicDevices;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class ADMain {

	public static final String UNLOC_PREFIX = Reference.MODID + ":";
	
	@Instance(Reference.MODID)
	public static ADMain instance;
	
	@SidedProxy(modId = Reference.MODID, serverSide = Reference.MODID + ".mod.fhbgds.proxy.ProxyCommon", clientSide = Reference.MODID + ".mod.fhbgds.proxy.ProxyClient")
	public static ProxyCommon proxy = new ProxyCommon();
	
	public static final RegistryNamespaced reg = GameData.getItemRegistry();
	
	public static ArmorMaterial ANGEL_ARMOR = EnumHelper.addArmorMaterial("ANGEL", 30, new int[] {3, 10, 6, 5}, 25);
	public static Enchantment contagious;
	public static TabAngelicDevices tab = new TabAngelicDevices("angelicDevices");
	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		angelWingsID = config.get(Configuration.CATEGORY_GENERAL, "angelWingsID", 8000).getInt();
		angelBootsID = config.get(Configuration.CATEGORY_GENERAL, "angelBootsID", 8001).getInt();
		wingComponentID = config.get(Configuration.CATEGORY_GENERAL, "wingComponentID", 8002).getInt();
		wingComponentBackID = config.get(Configuration.CATEGORY_GENERAL, "wingComponentBackID", 8003).getInt();
		mortarAndPestleID = config.get(Configuration.CATEGORY_GENERAL, "mortarAndPestleID", 8004).getInt();
		oreDustID = config.get(Configuration.CATEGORY_GENERAL, "oreDustID", 8005).getInt();
		angelDustID = config.get(Configuration.CATEGORY_GENERAL, "angelDustID", 8006).getInt();
		pureIngotID = config.get(Configuration.CATEGORY_GENERAL, "pureIngotID", 8007).getInt();
		holyFabricID = config.get(Configuration.CATEGORY_GENERAL, "holyFabricID", 8008).getInt();
		stoneBowlID = config.get(Configuration.CATEGORY_GENERAL, "stoneBowlID", 8009).getInt();
		infernoTabletID = config.get(Configuration.CATEGORY_GENERAL, "infernoTabletID", 8010).getInt();
		featherDustID = config.get(Configuration.CATEGORY_GENERAL, "featherDustID", 8011).getInt();
		quartzDustID = config.get(Configuration.CATEGORY_GENERAL, "quartzDustID", 8012).getInt();
		silicaID = config.get(Configuration.CATEGORY_GENERAL, "silicaID", 8013).getInt();
		contagiousID = config.get(Configuration.CATEGORY_GENERAL, "contagiousEnchantmentID", 255).getInt();
		config.save();
		
		contagious = new EnchantmentContagious();
		
		wings = new ItemWings().setUnlocalizedName(UNLOC_PREFIX + "angelWings").setTextureName(UNLOC_PREFIX + "angelWings").setCreativeTab(tab);
		boots = new ItemBoots().setUnlocalizedName(UNLOC_PREFIX + "angelBoots").setTextureName(UNLOC_PREFIX + "angelBoots").setCreativeTab(tab);
		wingComponent = new ItemWingComponent().setUnlocalizedName(UNLOC_PREFIX + "wingComponent").setTextureName(UNLOC_PREFIX + "wingComponent").setCreativeTab(tab);
		wingComponentBack = new ItemWingComponentBack().setUnlocalizedName(UNLOC_PREFIX + "wingComponentBack").setTextureName(UNLOC_PREFIX + "wingComponentBack").setCreativeTab(tab);
		mortarAndPestle = new ItemMortarAndPestle().setUnlocalizedName(UNLOC_PREFIX + "mortarAndPestle").setTextureName(UNLOC_PREFIX + "mortarAndPestle").setCreativeTab(tab);
		oreDust = new ItemOreDust().setUnlocalizedName(UNLOC_PREFIX + "oreDust").setTextureName(UNLOC_PREFIX + "oreDust").setCreativeTab(tab);
		angelDust = new ItemAngelDust().setUnlocalizedName(UNLOC_PREFIX + "angelDust").setTextureName(UNLOC_PREFIX + "angelDust").setCreativeTab(tab);
		pureIngot = new ItemPureIngot().setUnlocalizedName(UNLOC_PREFIX + "pureIngot").setTextureName(UNLOC_PREFIX + "pureIngot").setCreativeTab(tab);
		nanoMesh = new ItemHolyFabric().setUnlocalizedName(UNLOC_PREFIX + "holyFabric").setTextureName(UNLOC_PREFIX + "holyFabric").setCreativeTab(tab);
		stoneBowl = new ItemStoneBowl().setUnlocalizedName(UNLOC_PREFIX + "stoneBowl").setTextureName(UNLOC_PREFIX + "stoneBowl").setCreativeTab(tab);
		infernoTablet = new ItemInfernoTablet().setUnlocalizedName(UNLOC_PREFIX + "infernoTablet").setTextureName(UNLOC_PREFIX + "infernoTablet").setCreativeTab(tab);
		featherDust = new ItemGenericItem().setUnlocalizedAndTextureNames(UNLOC_PREFIX + "featherDust").setCreativeTab(tab);
		quartzDust = new ItemGenericItem(true).setUnlocalizedAndTextureNames(UNLOC_PREFIX + "quartzDust").setCreativeTab(tab);
		silica = new ItemGenericItem().setUnlocalizedAndTextureNames(UNLOC_PREFIX + "silicaDust").setCreativeTab(tab);
		
		reg.addObject(angelWingsID, "angel_wings", wings);
		reg.addObject(angelBootsID, "angel_boots", boots);
		reg.addObject(wingComponentID, "single_wing", wingComponent);
		reg.addObject(wingComponentBackID, "angel_chestplate", wingComponentBack);
		reg.addObject(mortarAndPestleID, "mortar_and_pestle", mortarAndPestle);
		reg.addObject(oreDustID, "ore_dust", oreDust);
		reg.addObject(angelDustID, "angel_dust", angelDust);
		reg.addObject(pureIngotID, "pure_ingot", pureIngot);
		reg.addObject(holyFabricID, "holy_fabric", nanoMesh);
		reg.addObject(stoneBowlID, "stone_bowl", stoneBowl);
		reg.addObject(infernoTabletID, "inferno_tablet", infernoTablet);
		reg.addObject(featherDustID, "feather_dust", featherDust);
		reg.addObject(quartzDustID, "quartz_dust", quartzDust);
		reg.addObject(silicaID, "silica_dust", silica);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.registerRenderers();
		
		MinecraftForge.EVENT_BUS.register(new Listener());
		
		OreDictionary.registerOre("dustIron", new ItemStack(oreDust, 1, 0));
		OreDictionary.registerOre("dustGold", new ItemStack(oreDust, 1, 1));
		OreDictionary.registerOre("dustFeather", featherDust);
		OreDictionary.registerOre("dustQuartz", quartzDust);
		OreDictionary.registerOre("sandSilica", silica);

		GameRegistry.addSmelting(angelDust, new ItemStack(pureIngot), 1);
		GameRegistry.addSmelting(nanoMesh, new ItemStack(angelDust), 0.1f);
		GameRegistry.addSmelting(stoneBowl, new ItemStack(Blocks.stone), 0.01f);
		GameRegistry.addSmelting(new ItemStack(oreDust, 1, 0), new ItemStack(Items.iron_ingot), 0.2f);
		GameRegistry.addSmelting(new ItemStack(oreDust, 1, 1), new ItemStack(Items.gold_ingot), 0.4f);
		GameRegistry.addSmelting(new ItemStack(quartzDust, 3), new ItemStack(Items.quartz), 0.01f);
		
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(oreDust, 2, 0), Blocks.iron_ore);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(featherDust, 4), Items.feather);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(oreDust, 2, 1), Blocks.gold_ore);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(Items.redstone, 6), Blocks.redstone_ore);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(Items.diamond, 2), Blocks.diamond_ore);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(Blocks.gravel), Blocks.cobblestone);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(Blocks.sand), Blocks.gravel);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(quartzDust, 3), Items.quartz);
		CraftingRegistryHelper.addMAPRecipe(new ItemStack(silica), new Object[] {Blocks.sand, "dustQuartz"});
		
		CraftingRegistryHelper.addCraftingSmelting(new ItemStack(oreDust, 1, 0));
		CraftingRegistryHelper.addCraftingSmelting(new ItemStack(oreDust, 1, 1));
		
		CraftingRegistryHelper.addRemainingRecipes();
	}
	
	public static int angelWingsID;
	public static int angelBootsID;
	public static int wingComponentID;
	public static int wingComponentBackID;
	public static int mortarAndPestleID;
	public static int oreDustID;
	public static int angelDustID;
	public static int pureIngotID;
	public static int holyFabricID;
	public static int stoneBowlID;
	public static int infernoTabletID;
	public static int featherDustID;
	public static int quartzDustID;
	public static int silicaID;
	
	public static Item wings;
	public static Item boots;
	public static Item wingComponent;
	public static Item wingComponentBack;
	public static Item mortarAndPestle;
	public static Item oreDust;
	public static Item angelDust;
	public static Item pureIngot;
	public static Item nanoMesh;
	public static Item stoneBowl;
	public static Item infernoTablet;
	public static Item featherDust;
	public static Item quartzDust;
	public static Item silica;
	
	public static int contagiousID;
}
