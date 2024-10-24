package com.triforcehero.mod.item;

import com.triforcehero.mod.Mod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//Items
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Mod.MOD_ID );

    public static final RegistryObject<Item> LEAD = ITEMS.register( "lead",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGNESIUM = ITEMS.register("magnesium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAWLEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUARTZITECHUNK = ITEMS.register("quartzite_chunk",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
