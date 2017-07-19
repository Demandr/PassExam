package com.oleksandr.passexam;

import android.content.Context;
import android.preference.PreferenceManager;


public class QueryPreferences {
    private static final String FIRST_BLOCK = "first_block";
    private static final String SECOND_BLOCK = "second_block";
    private static final String THIRD_BLOCK = "third_block";
    private static final String QUANTITY = "quantity";

    public static String getQUANTITY(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(QUANTITY, 20) + "";
    }

    public static void setQuantity(Context context, int quantity){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(QUANTITY, quantity)
                .apply();
    }

    public static boolean getFirstBlock(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(FIRST_BLOCK, false);
    }

    public static boolean getSecondBlock(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(SECOND_BLOCK, false);
    }

    public static boolean getThirdBlock(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(THIRD_BLOCK, false);
    }

    public static void setCheckValue(Context context, boolean first, boolean second, boolean third){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(FIRST_BLOCK, first)
                .putBoolean(SECOND_BLOCK, second)
                .putBoolean(THIRD_BLOCK, third)
                .apply();
    }
}
