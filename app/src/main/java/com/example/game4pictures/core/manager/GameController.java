package com.example.game4pictures.core.manager;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.example.game4pictures.core.model.QuizData;

import java.util.ArrayList;
import java.util.Collections;

public class GameController {

    private int level = 1;
    private int totalCoins = 1000;

    private int MAX_COIN = 10;
    private final int DELTA_COIN = 2;
    private final int MIN_COIN = 4;

    private int position = 0;


    private final ArrayList<QuizData> data;

    public GameController(ArrayList<QuizData> data) {
        this.data = data;
        Collections.shuffle(this.data);
    }

    private QuizData getQuizData() {
        return data.get(position);
    }

    public String getAnswer(){
        return data.get(position).getAnswer();
    }
    public ArrayList<Integer> getImages() {
        Collections.shuffle(getQuizData().getImages());
        return getQuizData().getImages();
    }

    public ArrayList<String> getVariants() {
        Collections.shuffle(getQuizData().getVariants());
        return getQuizData().getVariants();
    }

    public int getTotalAnswer() {
        return getQuizData().getAnswer().length();
    }

    public String getLevel() {
        return String.valueOf(level);
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public int getMAX_COIN() {
        return MAX_COIN;
    }

    public boolean checkWin(String toString) {

        Log.d(getClass().getSimpleName() + "TAG", "checkWin: " + toString);

        if (getQuizData().getAnswer().equalsIgnoreCase(toString)) {

            position++;

            level++;

            totalCoins += MAX_COIN;

            MAX_COIN = 10;

            return true;
        } else {

            if (MAX_COIN > MIN_COIN) {
                MAX_COIN -= DELTA_COIN;
            }
            return false;
        }
    }
    public boolean help1CoinPay(){
        int HELP_1_COIN = 50;
        if(totalCoins>=HELP_1_COIN){
            totalCoins -= HELP_1_COIN;
            return true;
        }else {
            return false;
        }
    }
    public boolean help2CoinPay(){
        int HELP_2_COIN = 10;
        if(totalCoins>=HELP_2_COIN){
            totalCoins -= HELP_2_COIN;
            return true;
        }else {
            return false;
        }

    }

    public void coinAdd(){
        int HELP_2_COIN = 10;
        totalCoins+=HELP_2_COIN;
    }

    public boolean isFinished() {
        return position == data.size();
    }
}

