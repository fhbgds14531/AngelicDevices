package ad.mod.fhbgds.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHelpTool extends Item {

	public ItemHelpTool() {
		
	}
	
	@Override
	@SideOnly(Side.SERVER)
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz){
		System.out.println("[AngelicDevices] Block: " + world.getBlock((int)px, (int)py, (int)pz));
		return false;
	}

}
