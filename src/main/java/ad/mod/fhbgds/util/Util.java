package ad.mod.fhbgds.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;


public class Util {

	public static boolean hasEnchantment(ItemStack stack, Enchantment e){
		if(stack == null || e == null || stack.stackTagCompound == null) return false;
		NBTTagList enchants = (NBTTagList) stack.stackTagCompound.getTag("ench");
		if(enchants == null) return false;
		for (int i = 0; i < enchants.tagCount(); i++) {
			NBTTagCompound enchant = enchants.getCompoundTagAt(i);
			if (enchant.getShort("id") == e.effectId) return true;
		}
		return false;
	}
	
	public static ItemStack setEnchantments(ItemStack stack, Enchantment[] enchs, int [] lvls){
		if(enchs.length != lvls.length) throw new IllegalArgumentException("ad.mod.fhbgds.util.Util.setEnchantments: enchs and lvls must be the same length.");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < enchs.length; i++){
			map.put(enchs[i].effectId, lvls[i]);
		}
		Map<Integer, Integer> emptyMap = new HashMap<Integer, Integer>();
		EnchantmentHelper.setEnchantments(emptyMap, stack);
		EnchantmentHelper.setEnchantments(map, stack);
		return stack;
	}
	
	public static int getLevelOfEnchantment(ItemStack stack, Enchantment e){
		if(!Util.hasEnchantment(stack, e)) return 0;
		NBTTagList enchants = (NBTTagList) stack.stackTagCompound.getTag("ench");
		for (int i = 0; i < enchants.tagCount(); i++) {
			NBTTagCompound enchant = enchants.getCompoundTagAt(i);
			if (enchant.getShort("id") == e.effectId) return enchant.getInteger("lvl");
		}
		return 0;
	}
}
