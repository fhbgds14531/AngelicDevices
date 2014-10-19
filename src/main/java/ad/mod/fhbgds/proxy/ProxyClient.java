package ad.mod.fhbgds.proxy;

import ad.mod.fhbgds.ADMain;
import ad.mod.fhbgds.render.RenderInfernoTablet;
import net.minecraftforge.client.MinecraftForgeClient;

public class ProxyClient extends ProxyCommon {

	@Override
	public void registerRenderers(){
		MinecraftForgeClient.registerItemRenderer(ADMain.infernoTablet, new RenderInfernoTablet());
	}
	
}
