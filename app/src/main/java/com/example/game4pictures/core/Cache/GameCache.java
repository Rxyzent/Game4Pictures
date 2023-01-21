package com.example.game4pictures.core.Cache;

import android.content.Context;
import android.content.SharedPreferences;

public class GameCache {
    private String KEY_MODE = "key_mode";
    private static GameCache gameCache;

    private SharedPreferences preferences;

    private String KEY_COUNT = "level_count";

    private GameCache(Context context){
        preferences = context.getSharedPreferences("game_cache",Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if (gameCache == null) {
            gameCache = new GameCache(context);
        }
    }

    public static GameCache getGameCashe() {
        return gameCache;
    }
}
