package ad.mod.fhbgds.items;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ad.mod.fhbgds.ADMain;
import ad.mod.fhbgds.util.Util;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemInfernoTablet extends Item{
	
	protected Map<ItemStack, ItemStack> results;

	public ItemInfernoTablet() {
		this.setMaxDamage(511);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(this.getUnlocalizedName().substring(5));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i1, boolean b) {
		
	}
	
	@Override
	public boolean isDamageable(){
		return true;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2){
		return false;
	}
	
	@Override
	public boolean hasContainerItem(){
		return true;
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack){
		return false;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
		entity.setFire(100);
		if(!(entity instanceof EntityPlayer)){
			entity.attackEntityFrom(DamageSource.onFire, 3000);
			entity.worldObj.playSoundEffect(entity.posX + 0.5, entity.posY + 0.5, entity.posZ + 0.5, "random.fizz", 0.5F, 2.6F + (entity.worldObj.rand.nextFloat() - entity.worldObj.rand.nextFloat()) * 0.8F);
		}
        return true;
    }
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean onItemUse(ItemStack tablet, EntityPlayer player, World world, int x, int y, int z, int side, float dx, float dy, float dz){
		if(this.results == null) this.results = (Map<ItemStack, ItemStack>)FurnaceRecipes.smelting().getSmeltingList();
		Block block = world.getBlock(x, y, z);
		ItemStack smeltingResult = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(block, 1));
		if(smeltingResult == null) return false;
		if(!world.isRemote)	this.convertBlock(x, y, z, tablet, world, player);
			if(Util.hasEnchantment(tablet, ADMain.contagious)){
				int range = 1;
				for(int x1 = x - range; x1 <= x + range; x1++){
					for(int y1 = y - range; y1 <= y + range; y1++){
						for(int z1 = z - range; z1 <= z + range; z1++){
							if(world.getBlock(x1, y1, z1) == block){
								if(!world.isRemote)	this.convertBlock(x1, y1, z1, tablet, world, player);
								this.spawnParticles(x1, y1, z1, world);
							}
						}
					}
				}
			}
		this.spawnParticles(x, y, z, world);
		world.playSoundEffect((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        return false;
    }
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack){
		int damage = itemStack.getItemDamage() + 1;
		itemStack.setItemDamage(damage);
		return itemStack;
	}
	
	public void spawnParticles(int x, int y, int z, World world){
		for(double i0 = x + 1.1; i0 >= x - 0.2; i0-=0.2){
			for(double i1 = y; i1 <= y + 1; i1+=0.2){
				for(double i2 = z + 1.1; i2 >= z - 0.2; i2-=0.2){
					if(world.rand.nextFloat() > 0.025){
						if(world.rand.nextFloat() > 0.55) MinecraftServer.getServer().getEntityWorld().spawnParticle("smoke", i0, i1, i2, 0.0, 0.0, 0.0);
					}else{
						MinecraftServer.getServer().getEntityWorld().spawnParticle("flame", i0, i1, i2, 0.0, 0.075, 0.0);
					}
				}
			}
		}
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book){
		return Util.hasEnchantment(book, ADMain.contagious);
	}
	
	public boolean convertBlock(int x, int y, int z, ItemStack tablet, World world, EntityPlayer player){
		Block target = world.getBlock(x, y, z);
		ItemStack smeltingResult = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(target, 1));
		if(smeltingResult == null) return false;
		if(smeltingResult.getItem() instanceof ItemBlock){
			Block block = Block.getBlockFromItem(smeltingResult.getItem());
			MinecraftServer.getServer().getEntityWorld().setBlock(x, y, z, block);
			MinecraftServer.getServer().getEntityWorld().notifyBlocksOfNeighborChange(x, y, z, block);
			if(!player.capabilities.isCreativeMode) tablet.damageItem(1, player);
			return true;
		} else {
			int count = 1;
			count += Util.getLevelOfEnchantment(tablet, Enchantment.fortune);
			EntityItem ei = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(smeltingResult.getItem(), count, smeltingResult.getItemDamage()));
			MinecraftServer.getServer().getEntityWorld().setBlockToAir(x, y, z);
			MinecraftServer.getServer().getEntityWorld().notifyBlocksOfNeighborChange(x, y, z, Blocks.air);
			MinecraftServer.getServer().getEntityWorld().spawnEntityInWorld(ei);
			count -= Util.getLevelOfEnchantment(tablet, Enchantment.unbreaking);
			if(count  <= 0 && world.rand.nextFloat() > 0.8f) count = 1;
			if(!player.capabilities.isCreativeMode) tablet.damageItem(count, player);
			return true;
		}
	}
	
}
