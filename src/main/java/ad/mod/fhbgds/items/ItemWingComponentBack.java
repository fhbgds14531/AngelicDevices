package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import ad.mod.fhbgds.ADMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWingComponentBack extends ItemArmor{

	public ItemWingComponentBack() {
		super(ADMain.ANGEL_ARMOR, 0, 1);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getUnlocalizedName().substring(5));
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		return ADMain.UNLOC_PREFIX + "textures/models/armor/ARMOR_1.png";
	}
}
