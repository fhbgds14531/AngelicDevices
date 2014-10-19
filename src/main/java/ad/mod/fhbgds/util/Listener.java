package ad.mod.fhbgds.util;

import java.util.Random;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import ad.mod.fhbgds.ADMain;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Listener {
	
	Random rand = new Random();
	
	@SubscribeEvent
	public void onDrop(LivingDropsEvent event) {
		if (event.source.getDamageType().equals("player") && rand.nextFloat() < 0.4F) {
			if (event.entityLiving instanceof EntityChicken) {
				event.entityLiving.dropItem(ADMain.featherDust, rand.nextInt(4) + 1);
			}
		}
	}
	boolean hadBootsLastTick = false;
	@SubscribeEvent
	public void stepHeight(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack stack = player.getCurrentArmor(0);
			if(stack != null){
				if(stack.getItem() == ADMain.boots){
					hadBootsLastTick = true;
					if(player.stepHeight < 1.0f) player.stepHeight = 1.0f;
				}else{
					if(hadBootsLastTick) player.stepHeight = 0.5f;
					hadBootsLastTick = false;
				}
			}else{
				if(hadBootsLastTick) player.stepHeight = 0.5f;
				hadBootsLastTick = false;
			}
		}
	}
}
