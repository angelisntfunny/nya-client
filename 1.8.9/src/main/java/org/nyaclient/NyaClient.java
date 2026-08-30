package org.nyaclient;

import lombok.Getter;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.nyaclient.event.EventBus;
import org.nyaclient.module.ModManager;

public class NyaClient {
    NyaClient() {}

    private static NyaClient INSTANCE;

    @Getter private EventBus eventBus;
    @Getter private ModManager modManager;
    @Getter private KeyBinding modsConfigKeybind;

//    private UnicodeFont interFontNormal;

//    private void loadFonts() {
//        try {
//            Font awtFont = Font.createFont(
//                    Font.TRUETYPE_FONT,
//                    org.newdawn.slick.util.ResourceLoader.getResourceAsStream("assets/nyaclient/Inter.ttf")
//            );
//
//            awtFont = awtFont.deriveFont(Font.PLAIN, 19f);
//
//            interFontNormal = new UnicodeFont(awtFont);
//            interFontNormal.getEffects().add(new ColorEffect(Color.WHITE));
//            interFontNormal.addAsciiGlyphs();
//            interFontNormal.loadGlyphs();
//        } catch (FontFormatException | IOException | SlickException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public UnicodeFont getInterFontNormal() {
//        if (interFontNormal == null) {
//            loadFonts();
//        }
//        return interFontNormal;
//    }

    public static NyaClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NyaClient();
        }

        return INSTANCE;
    }

    public void start() {
        eventBus = new EventBus();
        modManager = new ModManager();
        modsConfigKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nyaclient.modsconfigkeybind",
                Keyboard.KEY_RSHIFT,
                "category.nyaclient"
        ));
    }
}
