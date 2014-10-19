package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ad.mod.fhbgds.ADMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWings extends ItemArmor {

	public ItemWings() {
		super(ADMain.ANGEL_ARMOR, 0, 1);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getIconString());
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		if(!player.onGround && player.motionY < 0.0D){
			if(!player.isSneaking() && !player.isInWater() && !player.onGround && player.motionY < -0.4) player.motionY = -0.3D;
			player.fallDistance = 1;
		}
	}

	@Override
	public boolean isDamageable(){
		return false;
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		return ADMain.UNLOC_PREFIX + "textures/models/armor/ARMOR_2.png";
	}
}
