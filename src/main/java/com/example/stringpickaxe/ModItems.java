package com.example.stringpickaxe;

import com.example.stringpickaxe.item.StringPickaxeItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItems {
    private static final Identifier STRING_PICKAXE_ID = Identifier.of(StringPickaxeMod.MOD_ID, "string_pickaxe");
    private static final TagKey<Block> INCORRECT_FOR_DIAMOND_TOOL = TagKey.of(
        RegistryKeys.BLOCK,
        Identifier.ofVanilla("incorrect_for_diamond_tool")
    );

    private static final TagKey<Item> IRON_INGOT_REPAIR_ITEMS = TagKey.of(
        RegistryKeys.ITEM,
        Identifier.ofVanilla("iron_ingots")
    );

    public static final ToolMaterial STRING_PICKAXE_MATERIAL = new ToolMaterial(
        INCORRECT_FOR_DIAMOND_TOOL,
        1561,
        6.0F,
        1.0F,
        10,
        IRON_INGOT_REPAIR_ITEMS
    );

    public static Item STRING_PICKAXE;

    private ModItems() {
    }

    public static void register() {
        Item.Settings settings = new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, STRING_PICKAXE_ID))
            .pickaxe(STRING_PICKAXE_MATERIAL, 1.0F, -2.8F);

        STRING_PICKAXE = Registry.register(
            Registries.ITEM,
            STRING_PICKAXE_ID,
            new StringPickaxeItem(settings)
        );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(STRING_PICKAXE));
    }
}
