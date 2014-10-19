package ad.mod.fhbgds.items;

import java.util.List;

import ad.mod.fhbgds.ADMain;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOreDust extends Item{

	private int subItems = 2;
	private IIcon[] icons = new IIcon[subItems];
	private String[] names = new String[] {"ironDust", "goldDust"}; 
	
	public ItemOreDust() {
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon("ad:ironDust");
		this.icons[0] = r.registerIcon("ad:ironDust");
		this.icons[1] = r.registerIcon("ad:goldDust");
	}
	
	public IIcon getIconFromDamage(int damage){
		int i = MathHelper.clamp_int(damage, 0, 1);
		return icons[i];
	}
	
	public String getUnlocalizedName(ItemStack stack){
		int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 1);
        return "item."+ ADMain.UNLOC_PREFIX + names[i];
	}
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 @Override
	 @SideOnly(Side.CLIENT)
     public void getSubItems(Item item, CreativeTabs tabs, List list){
         for(int i = 0; i < subItems; ++i){
         ItemStack stack = new ItemStack(item, 1, i);
         	list.add(stack);
         }
      }
}