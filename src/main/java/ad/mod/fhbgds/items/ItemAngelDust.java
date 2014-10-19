package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAngelDust extends Item{

	public ItemAngelDust() {}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getIconString());
	}
	
	public String getIcon(){
		return this.getIconString();
	}
	
	@Override
	public boolean hasEffect(ItemStack stack){
		return true;
	}
	
}
