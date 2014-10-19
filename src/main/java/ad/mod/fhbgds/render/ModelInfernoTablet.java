package ad.mod.fhbgds.render;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelInfernoTablet
{
    private IModelCustom modelInfernoTablet;

    public ModelInfernoTablet(){
        modelInfernoTablet = AdvancedModelLoader.loadModel(new ResourceLocation("ad:models/tablet.obj"));
    }

    public void render(){
        modelInfernoTablet.renderAll();
    }
}