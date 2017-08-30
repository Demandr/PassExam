package com.oleksandr.passexam;

import android.content.Context;
import android.preference.PreferenceManager;


public class QueryPreferences {
    private static final String PREF_FIRST_BLOCK = "first_block";
    private static final String PREF_SECOND_BLOCK = "second_block";
    private static final String PREF_THIRD_BLOCK = "third_block";
    private static final String PREF_BLOCK_4 = "block_4";
    private static final String PREF_BLOCK_5 = "block_5";
    private static final String PREF_BLOCK_6 = "block_6";
    private static final String PREF_BLOCK_7 = "block_7";
    private static final String PREF_BLOCK_8 = "block_8";
    private static final String PREF_BLOCK_9 = "block_9";
    private static final String PREF_BLOCK_10 = "block_10";
    private static final String PREF_BLOCK_11 = "block_11";
    private static final String PREF_BLOCK_12 = "block_12";
    private static final String PREF_QUANTITY = "quantity";
    private static final String PREF_RANDOM = "random";

    public static String getQuantity(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_QUANTITY, 20) + "";
    }

    public static void setQuantity(Context context, int quantity){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_QUANTITY, quantity)
                .apply();
    }

    public static boolean getRandom(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_RANDOM, false);
    }

    public static void setRandom(Context context, boolean rand){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_RANDOM, rand)
                .apply();
    }

    public static boolean getFirstBlock(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_FIRST_BLOCK, false);
    }

    public static boolean getSecondBlock(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_SECOND_BLOCK, false);
    }

    public static boolean getThirdBlock(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_THIRD_BLOCK, false);
    }

    public static boolean getBlock4(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_4, false);
    }
    public static boolean getBlock5(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_5, false);
    }
    public static boolean getBlock6(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_6, false);
    }
    public static boolean getBlock7(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_7, false);
    }
    public static boolean getBlock8(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_8, false);
    }
    public static boolean getBlock9(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_9, false);
    }
    public static boolean getBlock10(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_10, false);
    }
    public static boolean getBlock11(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_11, false);
    }
    public static boolean getBlock12(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_BLOCK_12, false);
    }

    public static void setCheckValue(Context context, boolean first, boolean second, boolean third, boolean b4,
                                     boolean b5, boolean b6, boolean b7, boolean b8, boolean b9, boolean b10,
                                     boolean b11, boolean b12){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_FIRST_BLOCK, first)
                .putBoolean(PREF_SECOND_BLOCK, second)
                .putBoolean(PREF_THIRD_BLOCK, third)
                .putBoolean(PREF_BLOCK_4, b4)
                .putBoolean(PREF_BLOCK_5, b5)
                .putBoolean(PREF_BLOCK_6, b6)
                .putBoolean(PREF_BLOCK_7, b7)
                .putBoolean(PREF_BLOCK_8, b8)
                .putBoolean(PREF_BLOCK_9, b9)
                .putBoolean(PREF_BLOCK_10, b10)
                .putBoolean(PREF_BLOCK_11, b11)
                .putBoolean(PREF_BLOCK_12, b12)
                .apply();
    }
}
