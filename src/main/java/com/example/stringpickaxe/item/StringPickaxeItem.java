package com.example.stringpickaxe.item;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class StringPickaxeItem extends Item {
    public StringPickaxeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        // Keep the stack at full durability by skipping the default mining damage.
        return true;
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Tools should not lose durability from combat either.
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Intentionally empty. No code path should damage this item.
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, EquipmentSlot slot) {
        stringpickaxe$ensureSilkTouch(stack, world.getRegistryManager());
    }

    @Override
    public void onCraft(ItemStack stack, World world) {
        stringpickaxe$ensureSilkTouch(stack, world.getRegistryManager());
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, PlayerEntity player) {
        stringpickaxe$ensureSilkTouch(stack, player.getRegistryManager());
    }

    private static void stringpickaxe$ensureSilkTouch(ItemStack stack, DynamicRegistryManager registryManager) {
        RegistryEntry<Enchantment> silkTouch = registryManager.getOrThrow(RegistryKeys.ENCHANTMENT)
            .getOrThrow(Enchantments.SILK_TOUCH);
        if (stack.getEnchantments().getLevel(silkTouch) == 0) {
            stack.addEnchantment(silkTouch, 1);
        }
    }
}
