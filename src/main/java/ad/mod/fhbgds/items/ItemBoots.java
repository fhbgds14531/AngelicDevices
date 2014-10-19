package ad.mod.fhbgds.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import ad.mod.fhbgds.ADMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBoots extends ItemArmor{

	public ItemBoots() {
		super(ADMain.ANGEL_ARMOR, 0, 3);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getIconString());
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1));
		if(!player.isSneaking()) player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 1, 1));
		if(player.isBurning()) player.extinguish();
		if(!player.onGround) player.fallDistance = 1;
    }
	
	@Override
	public boolean isDamageable(){
		return false;
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		return ADMain.UNLOC_PREFIX + "textures/models/armor/ARMOR_1.png";
	}
}
