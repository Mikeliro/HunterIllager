package baguchan.hunterillager;

import baguchan.hunterillager.client.HunterRenderingRegistry;
import baguchan.hunterillager.event.EntityEventHandler;
import baguchan.hunterillager.world.IllagerWoodHutGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = HunterIllagerCore.MODID, name = HunterIllagerCore.MODNAME, version = HunterIllagerCore.VERSION, useMetadata = true, dependencies = "required:forge@[14.23.5.2772,);")
public class HunterIllagerCore
{
    public static final String MODID = "hunterillager";
    public static final String MODNAME = "HunterIllager";
    public static final String VERSION = "1.2";

    @Mod.Metadata
    public static ModMetadata metadata;

    @Mod.Instance(MODID)
    public static HunterIllagerCore instance;

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void registerEntityEntries(RegistryEvent.Register<EntityEntry> event) {
        HunterEntityRegistry.registerEntities();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new IllagerWoodHutGenerator(), 0);

        if (event.getSide().isClient()) {
            HunterRenderingRegistry.registerRenderers();
        }
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        //メタ情報の登録
        loadMeta();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    private void loadMeta() {
        metadata.modId = MODID;
        metadata.name = MODNAME;
        metadata.version = VERSION;
        metadata.description = ("add HunterIllager");
        metadata.credits = ("");
        metadata.logoFile = ("");
        metadata.url = ("https://minecraft.curseforge.com/projects/hunterillager");

        metadata.autogenerated = false;
    }
}
