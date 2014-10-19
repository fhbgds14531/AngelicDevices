package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMortarAndPestle extends Item{

	public ItemMortarAndPestle() {
		this.setMaxDamage(255);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getUnlocalizedName().substring(5));
	}

	@Override
	public boolean isDamageable(){
		return true;
	}
	
	@Override
	public boolean hasContainerItem(){
		return true;
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack){
		return false;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack){
		int damage = itemStack.getItemDamage() + 1;
		itemStack.setItemDamage(damage);
		return itemStack;
	}
	
}
