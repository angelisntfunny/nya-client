package net.nyaclient.module.visual;

import net.nyaclient.module.HUDMod;
import net.nyaclient.module.ModCategory;
import net.nyaclient.neko.NekoFontRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TwitchChat extends HUDMod {
    public record Message(String message, String user, Color userColor, Long timestamp, int opacity) {}

    private final List<String> MESSAGE_STRINGS = List.of(
            "Who else watching in 2094?",
            "Because brread taste better than key",
            "Who else watching this at 3am",
            "No limit to the larp",
            "Is this roblox",
            "Is this real",
            "You stole my idea",
            "I'm your tightest fan :heart:",
            "Love from tel aviv",
            "Big yahu impressed",
            "Hi (sorry for bad english)",
            "Game name",
            "First",
            "Disrespect to the guy who said first",
            "Subscribe to me",
            "Can i have a shoutout",
            "This is ai",
            "Is this ai?",
            "Holy refresh pull",
            "Who else fell for the fake divorce notification",
            "What server is this",
            "I dont remember buying youtube premium",
            "Feels illegal to be this early",
            "I edited my comment you'll never know what I said",
            "Just play roblox bro",
            "Real ones remember this",
            "This is me fr",
            "Can i join",
            "Bro look behind you",
            "Who came here from mr beast",
            "Investing in this video",
            "Here from google giggles",
            "Bro they frying yo shi on google giggles",
            "I'm so gassy",
            "Im giggling",
            "This video is supported by {{user}}"
    );
    private final List<Message> MESSAGES = new ArrayList<>();

    private final List<Color> COLORS = List.of(
            new Color(255, 0, 0),
            new Color(0, 0, 255),
            new Color(0, 255, 0),
            new Color(178, 34, 34),
            new Color(255, 127, 80),
            new Color(154, 205, 50),
            new Color(255, 69, 0),
            new Color(46, 139, 87),
            new Color(218, 165, 32),
            new Color(0, 255, 120)
    );

    private int waitTime = ThreadLocalRandom.current().nextInt(3000, 11000);

    public TwitchChat() {
        super("mods.twitchchat", "icons/twitchchat.png", ModCategory.Visual, 150, 200);
    }

    @Override
    public float getWidth() {
        return 245;
    }

    @Override
    public float getHeight() {
        return 25;
    }

    private static final long FADE_START_MS = 7000;
    private static final long FADE_DURATION_MS = 4000;

    // for some reason the bad code always works
    @Override
    public void render() {
        long now = System.currentTimeMillis();
        float currentY = getY() + getHeight();

        for (int i = MESSAGES.size() - 1; i >= 0; i--) {
            Message message = MESSAGES.get(i);
            long age = now - message.timestamp();

            if (age >= FADE_START_MS + FADE_DURATION_MS) {
                MESSAGES.remove(i);
                continue;
            }

            int alpha = age <= FADE_START_MS
                    ? 255
                    : (int) (255 * (1.0 - (double) (age - FADE_START_MS) / FADE_DURATION_MS));

            float textX = getX();
            Color userColor = new Color(
                    message.userColor().getRed(),
                    message.userColor().getGreen(),
                    message.userColor().getBlue(),
                    alpha
            );

            NekoFontRenderer.renderText(8, textX, currentY, message.user + ": ", userColor);
            textX += NekoFontRenderer.getTextWidth(8, message.user + ": ");
            NekoFontRenderer.renderText(8, textX, currentY, message.message.replace("{{user}}", message.user), new Color(255, 255, 255, alpha));

            currentY -= NekoFontRenderer.getTextHeight(8) + 2;
        }

        if (MESSAGES.isEmpty()) {
            MESSAGES.add(new Message(MESSAGE_STRINGS.get(ThreadLocalRandom.current().nextInt(MESSAGE_STRINGS.size() - 1)), "User" + ThreadLocalRandom.current().nextInt(10000000, 100000000), COLORS.get(ThreadLocalRandom.current().nextInt(COLORS.size())), System.currentTimeMillis(), 255));
            return;
        }

        Message m = MESSAGES.getLast();

        if (m.timestamp < System.currentTimeMillis() - waitTime) {
            MESSAGES.add(new Message(MESSAGE_STRINGS.get(ThreadLocalRandom.current().nextInt(MESSAGE_STRINGS.size() - 1)), "User" + ThreadLocalRandom.current().nextInt(10000000, 100000000), COLORS.get(ThreadLocalRandom.current().nextInt(COLORS.size())), System.currentTimeMillis(), 255));
            waitTime = ThreadLocalRandom.current().nextInt(1550, 5650);
        }
    }
}
