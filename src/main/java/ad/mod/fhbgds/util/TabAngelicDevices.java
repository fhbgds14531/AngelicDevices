package ad.mod.fhbgds.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ad.mod.fhbgds.ADMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabAngelicDevices extends CreativeTabs{

	public TabAngelicDevices(String label) {
		super(label);
	}

	@Override
	public String getTabLabel(){
		return "angelicDevices";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return ADMain.wings;
	}
}
