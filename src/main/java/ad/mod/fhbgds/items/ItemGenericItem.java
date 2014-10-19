package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGenericItem extends Item {
	
	boolean hasEffect = false;

	public ItemGenericItem() {
		this(false);
	}
	
	public ItemGenericItem(boolean hasEffect){
		this.hasEffect = hasEffect;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getIconString());
	}
	
	@Override
	public boolean hasEffect(ItemStack i){
		return this.hasEffect;
	}
	
	public Item setUnlocalizedAndTextureNames(String name){
		this.setUnlocalizedName(name);
		this.setTextureName(name);
		return this;
	}
}
