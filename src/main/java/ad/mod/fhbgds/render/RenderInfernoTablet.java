package ad.mod.fhbgds.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfernoTablet implements IItemRenderer{
    private final ModelInfernoTablet modelInfernoTablet;
    private final ResourceLocation TEXTURE = new ResourceLocation("ad:textures/models/infernoTablet.png");

    public RenderInfernoTablet(){
        modelInfernoTablet = new ModelInfernoTablet();
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType){
    	if(itemRenderType != ItemRenderType.INVENTORY){
    		return true;
    	}
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper){
    	if(itemRenderType != ItemRenderType.INVENTORY){
    		return true;
    	}
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data){
        switch (type){
            case ENTITY:
                renderIT(-0.5F, -0.38F, 0.5F, type);
                return;
            case EQUIPPED:
                renderIT(0.0F, 0.0F, 1.0F, type);
                return;
            case EQUIPPED_FIRST_PERSON:
                renderIT(1.0F, 1.0F, 1.0F, type);
                return;
            case INVENTORY:
                renderIT(-1.0F, -0.9F, 0.0F, type);
                return;
            default:
        }
    }

    private void renderIT(float x, float y, float z, ItemRenderType type){
        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        switch(type){
		case ENTITY:
			GL11.glScalef(.7F, .7F, .7F);
			GL11.glTranslatef(x - 0.65f, y, z + 0.51f);
			GL11.glRotatef(-15f, 0, 0, 1);
			break;
		case EQUIPPED:
			GL11.glScalef(.85F, .85F, .85F);
			GL11.glTranslatef(x - 0.8f, y - 0.1f, z + 0.6f);
			GL11.glRotatef(-10F, 0, 1, 0);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glScalef(.725f, .725F, .725F);
	        GL11.glTranslatef(x - 1.2f, y - 0.8f, z + 0.6f);
	        GL11.glRotatef(45F, 0, 1, 1);
	        GL11.glRotatef(17F, 1, 0, 0);
			break;
		case INVENTORY:
			GL11.glScalef(.9F, .9F, .9F);
			GL11.glTranslatef(x, y - 0.625f, z + 0.46f);
			GL11.glRotatef(45F, 0, 1, 0);
			GL11.glRotatef(-20F, 1, 0, 0);
			break;
		default:
			break;
        }
        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE);

        // Render
        modelInfernoTablet.render();

        GL11.glPopMatrix();
    }
}