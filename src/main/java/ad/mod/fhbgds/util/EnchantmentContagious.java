package ad.mod.fhbgds.util;

import ad.mod.fhbgds.ADMain;
import ad.mod.fhbgds.items.ItemInfernoTablet;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentContagious extends Enchantment{

	public EnchantmentContagious() {
		super(ADMain.contagiousID, 5, EnumEnchantmentType.breakable);
		this.setName("ad:contagious");
	}

	@Override
	public boolean canApply(ItemStack stack){
		return stack.getItem() instanceof ItemInfernoTablet;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment e){
		return false;
	}
}
