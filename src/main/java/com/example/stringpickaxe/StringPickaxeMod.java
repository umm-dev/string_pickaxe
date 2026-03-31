package com.example.stringpickaxe;

import net.fabricmc.api.ModInitializer;

public final class StringPickaxeMod implements ModInitializer {
    public static final String MOD_ID = "stringpickaxe";

    @Override
    public void onInitialize() {
        ModItems.register();
    }
}

