package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHolyFabric extends Item{

	public ItemHolyFabric() {}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getIconString());
	}
	
	public boolean hasEffect(ItemStack stack){
		return true;
	}

}
