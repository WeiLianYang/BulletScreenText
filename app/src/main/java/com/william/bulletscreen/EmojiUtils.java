package com.william.bulletscreen;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * @author William
 * @date 12/4/20 10:04 PM
 * Class Comment：https://www.jianshu.com/p/42fd6f84c27a
 */

public class EmojiUtils {

    private static final String TAG = EmojiUtils.class.getSimpleName();
    private static Set<Character> emojiSignatureSet = new HashSet<>(1801);

    private EmojiUtils() {
    }

    public static boolean isContainEmoji(String s) {
        char[] chars = s.toCharArray();
        int charsLength = chars.length;
        char currentChar;
        char realChar;
        for (int i = 0; i < charsLength; i++) {
            currentChar = chars[i];
            realChar = currentChar;
            if (currentChar >= 0xD800 && currentChar <= 0xDBFF && (i + 1) < charsLength) {
                char nextChar = chars[++i];
                realChar = (char) (0x10000 + (currentChar - 0xD800) * 0x400 + (nextChar - 0xDC00));
            }
            if (emojiSignatureSet.contains(realChar)) {
                return true;
            }
        }
        return false;
    }

    public static String filterEmoji(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int charsLength = chars.length;
        char currentChar;
        char realChar;
        for (int i = 0; i < charsLength; i++) {
            currentChar = chars[i];
            realChar = currentChar;
            if (currentChar >= 0xD800 && currentChar <= 0xDBFF && (i + 1) < charsLength) {
                char nextChar = chars[++i];
                realChar = (char) (0x10000 + (currentChar - 0xD800) * 0x400 + (nextChar - 0xDC00));
            }
            if (!emojiSignatureSet.contains(realChar)) {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }

    private static void addUnicodeToSet(Set<Character> set, int code) {
        if (set == null) {
            return;
        }
        set.add((char) code);
    }

    private static void addUnicodeToSet(Set<Character> set, int codeStart, int codeEnd) {
        if (set == null) {
            return;
        }
        for (int i = codeStart; i <= codeEnd; i++) {
            addUnicodeToSet(set, i);
        }
    }

    static {
        Log.d(TAG, "init start:" + System.currentTimeMillis());
        addUnicodeToSet(emojiSignatureSet, 0x007F);
        addUnicodeToSet(emojiSignatureSet, 0x00A9);
        addUnicodeToSet(emojiSignatureSet, 0x00AE);
        addUnicodeToSet(emojiSignatureSet, 0x200D);
        addUnicodeToSet(emojiSignatureSet, 0x203C);
        addUnicodeToSet(emojiSignatureSet, 0x2049);
        addUnicodeToSet(emojiSignatureSet, 0x20E3);
        addUnicodeToSet(emojiSignatureSet, 0x2122);
        addUnicodeToSet(emojiSignatureSet, 0x2139);
        addUnicodeToSet(emojiSignatureSet, 0x2194, 0x2199);
        addUnicodeToSet(emojiSignatureSet, 0x21A9, 0x21AA);
        addUnicodeToSet(emojiSignatureSet, 0x231A, 0x231B);
        addUnicodeToSet(emojiSignatureSet, 0x2328);
        addUnicodeToSet(emojiSignatureSet, 0x2388);
        addUnicodeToSet(emojiSignatureSet, 0x23CF);
        addUnicodeToSet(emojiSignatureSet, 0x23E9, 0x23F3);
        addUnicodeToSet(emojiSignatureSet, 0x23F8, 0x23FA);
        addUnicodeToSet(emojiSignatureSet, 0x24C2);
        addUnicodeToSet(emojiSignatureSet, 0x25AA, 0x25AB);
        addUnicodeToSet(emojiSignatureSet, 0x25B6);
        addUnicodeToSet(emojiSignatureSet, 0x25C0);
        addUnicodeToSet(emojiSignatureSet, 0x25FB, 0x25FE);
        addUnicodeToSet(emojiSignatureSet, 0x2600, 0x2605);
        addUnicodeToSet(emojiSignatureSet, 0x2607, 0x2612);
        addUnicodeToSet(emojiSignatureSet, 0x2614, 0x2685);
        addUnicodeToSet(emojiSignatureSet, 0x2690, 0x2705);
        addUnicodeToSet(emojiSignatureSet, 0x2708, 0x2712);
        addUnicodeToSet(emojiSignatureSet, 0x2714);
        addUnicodeToSet(emojiSignatureSet, 0x2716);
        addUnicodeToSet(emojiSignatureSet, 0x271D);
        addUnicodeToSet(emojiSignatureSet, 0x2721);
        addUnicodeToSet(emojiSignatureSet, 0x2728);
        addUnicodeToSet(emojiSignatureSet, 0x2733, 0x2734);
        addUnicodeToSet(emojiSignatureSet, 0x2744);
        addUnicodeToSet(emojiSignatureSet, 0x2747);
        addUnicodeToSet(emojiSignatureSet, 0x274C);
        addUnicodeToSet(emojiSignatureSet, 0x274E);
        addUnicodeToSet(emojiSignatureSet, 0x2753, 0x2755);
        addUnicodeToSet(emojiSignatureSet, 0x2757);
        addUnicodeToSet(emojiSignatureSet, 0x2763, 0x2767);
        addUnicodeToSet(emojiSignatureSet, 0x2795, 0x2797);
        addUnicodeToSet(emojiSignatureSet, 0x27A1);
        addUnicodeToSet(emojiSignatureSet, 0x27B0);
        addUnicodeToSet(emojiSignatureSet, 0x27BF);
        addUnicodeToSet(emojiSignatureSet, 0x2934, 0x2935);
        addUnicodeToSet(emojiSignatureSet, 0x2B05, 0x2B07);
        addUnicodeToSet(emojiSignatureSet, 0x2B1B, 0x2B1C);
        addUnicodeToSet(emojiSignatureSet, 0x2B50);
        addUnicodeToSet(emojiSignatureSet, 0x2B55);
        addUnicodeToSet(emojiSignatureSet, 0x3030);
        addUnicodeToSet(emojiSignatureSet, 0x303D);
        addUnicodeToSet(emojiSignatureSet, 0x3297);
        addUnicodeToSet(emojiSignatureSet, 0x3299);
        addUnicodeToSet(emojiSignatureSet, 0xF000, 0xF02B);
        addUnicodeToSet(emojiSignatureSet, 0xF030, 0xF093);
        addUnicodeToSet(emojiSignatureSet, 0xF0A0, 0xF0AE);
        addUnicodeToSet(emojiSignatureSet, 0xF0B1, 0xF0BF);
        addUnicodeToSet(emojiSignatureSet, 0xF0C1, 0xF0CF);
        addUnicodeToSet(emojiSignatureSet, 0xF0D1, 0xF0F5);
        addUnicodeToSet(emojiSignatureSet, 0xF12F);
        addUnicodeToSet(emojiSignatureSet, 0xF170, 0xF171);
        addUnicodeToSet(emojiSignatureSet, 0xF17E, 0xF17F);
        addUnicodeToSet(emojiSignatureSet, 0xF18E);
        addUnicodeToSet(emojiSignatureSet, 0xF191, 0xF19A);
        addUnicodeToSet(emojiSignatureSet, 0xF1E6, 0xF1FF);
        addUnicodeToSet(emojiSignatureSet, 0xF201, 0xF202);
        addUnicodeToSet(emojiSignatureSet, 0xF21A);
        addUnicodeToSet(emojiSignatureSet, 0xF22F);
        addUnicodeToSet(emojiSignatureSet, 0xF232, 0xF23A);
        addUnicodeToSet(emojiSignatureSet, 0xF250, 0xF251);
        addUnicodeToSet(emojiSignatureSet, 0xF260, 0xF265);
        addUnicodeToSet(emojiSignatureSet, 0xF300, 0xF53D);
        addUnicodeToSet(emojiSignatureSet, 0xF546, 0xF64F);
        addUnicodeToSet(emojiSignatureSet, 0xF680, 0xF6D4);
        addUnicodeToSet(emojiSignatureSet, 0xF6E0, 0xF6EC);
        addUnicodeToSet(emojiSignatureSet, 0xF6F0, 0xF6F9);
        addUnicodeToSet(emojiSignatureSet, 0xF7D5, 0xF7D8);
        addUnicodeToSet(emojiSignatureSet, 0xF910, 0xF93A);
        addUnicodeToSet(emojiSignatureSet, 0xF93C, 0xF93E);
        addUnicodeToSet(emojiSignatureSet, 0xF940, 0xF945);
        addUnicodeToSet(emojiSignatureSet, 0xF947, 0xF970);
        addUnicodeToSet(emojiSignatureSet, 0xF973, 0xF976);
        addUnicodeToSet(emojiSignatureSet, 0xF97A);
        addUnicodeToSet(emojiSignatureSet, 0xF97C, 0xF9A2);
        addUnicodeToSet(emojiSignatureSet, 0xF9B0, 0xF9B9);
        addUnicodeToSet(emojiSignatureSet, 0xF9C0, 0xF9C2);
        addUnicodeToSet(emojiSignatureSet, 0xF9D0, 0xF9FF);
        addUnicodeToSet(emojiSignatureSet, 0xFA60, 0xFA6D);
        addUnicodeToSet(emojiSignatureSet, 0xFE0E, 0xFE0F);
        Log.d(TAG, "init end  :" + System.currentTimeMillis());
        Log.d(TAG, "set size: " + emojiSignatureSet.size());
    }
}

