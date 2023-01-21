package com.example.game4pictures.ui.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game4pictures.R;
import com.example.game4pictures.core.dialog.WinDialog;
import com.example.game4pictures.core.manager.DataLoader;
import com.example.game4pictures.core.manager.GameController;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private StringBuilder answerBuilder = new StringBuilder();
    private GameController controller;
    private ConstraintLayout answerGroup;
    private ConstraintLayout variantGroup;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private TextView totalCoinView;
    private TextView levelMaxCoinView;
    private Button help1, help2,levelView;
    private int totalAnswers = 0,helpCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        controller = new GameController(DataLoader.getData());

        loadViews();

        loadDatToView();

        loadActions();

    }

    private void loadActions() {
        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button button = (Button) variantGroup.getChildAt(i);
            button.setOnClickListener(this::onVariantClick);
        }
        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button button = (Button) answerGroup.getChildAt(i);
            button.setOnClickListener(this::onAnswerClick);
        }
        help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controller.help1CoinPay()){
                    boolean isAnswerFound = false;
                    for (int i = helpCount; i < answerGroup.getChildCount(); i++) {
                        if (answerGroup.getChildAt(i).getVisibility() == View.VISIBLE){
                            char a = controller.getAnswer().charAt(i);
                            Button answer = (Button) answerGroup.getChildAt(i);
                            for (int j = 0; j < variantGroup.getChildCount(); j++) {
                                if (variantGroup.getChildAt(j).getVisibility() == View.VISIBLE){
                                    Button variant = (Button)  variantGroup.getChildAt(j);
                                    if(answer.getText().toString().isEmpty()){
                                        if(variant.getText().toString().contains(String.valueOf(a))){
                                            answer.setText(variant.getText());
                                            variant.setVisibility(View.INVISIBLE);
                                            totalAnswers++;
                                            if (totalAnswers == controller.getTotalAnswer()) {
                                                checkWin();
                                            }
                                            isAnswerFound = true;
                                            helpCount++;
                                            break;
                                        }
                                    }else {
                                        if(variant.getText().toString().contains(String.valueOf(a))) {
                                            answer.performClick();
                                            answer.setText(variant.getText());
                                            variant.setVisibility(View.INVISIBLE);
                                            totalAnswers++;
                                            if (totalAnswers == controller.getTotalAnswer()) {
                                                checkWin();
                                            }
                                            isAnswerFound = true;
                                            helpCount++;
                                            break;
                                        }
                                    }
                                }
                            }
                            if(!isAnswerFound){
                                for (int j = helpCount; j < answerGroup.getChildCount(); j++) {
                                    if(answerGroup.getChildAt(j).getVisibility() == View.VISIBLE){
                                        Button answerBtn = (Button) answerGroup.getChildAt(j);
                                        if(answer.getText().toString().isEmpty()){
                                            if(answerBtn.getText().toString().contains(String.valueOf(a)) && i!=j){
                                                answer.setText(answerBtn.getText());
                                                answerBtn.setText("");
                                                isAnswerFound = true;
                                                helpCount++;
                                                break;
                                            }
                                        }else {
                                            if(answerBtn.getText().toString().contains(String.valueOf(a)) && i!=j) {
                                                answer.performClick();
                                                answer.setText(answerBtn.getText());
                                                answerBtn.setText("");
                                                isAnswerFound = true;
                                                helpCount++;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if(isAnswerFound){
                            break;
                        }
                    }
                }else {
                    Toast.makeText(GameActivity.this, "You don't have enough money", Toast.LENGTH_SHORT).show();
                }
            }
        });
        help2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controller.help2CoinPay()){
                    loadCoins();
                    boolean isFalseAnswerFound = true;
                    for (int i = 0; i < variantGroup.getChildCount(); i++) {
                        boolean isFalseVariant = true;
                        if(variantGroup.getChildAt(i).getVisibility() == View.VISIBLE){
                            Button variant = (Button) variantGroup.getChildAt(i);
                            for (int j = 0; j < answerGroup.getChildCount(); j++) {
                                if(answerGroup.getChildAt(j).getVisibility() == View.VISIBLE){
                                    char a = controller.getAnswer().charAt(j);
                                    if (variant.getText().toString().contains(String.valueOf(a))) {
                                        isFalseVariant = false ;
                                    }
                                }
                            }
                            if(isFalseVariant){
                                variant.setVisibility(View.INVISIBLE);
                                isFalseAnswerFound = false;
                                break;
                            }
                        }
                    }
                    if(isFalseAnswerFound){
                        Toast.makeText(GameActivity.this, "Xato harflar qolmadi", Toast.LENGTH_SHORT).show();
                        controller.coinAdd();
                    }
                    /*if(isFalseAnswerInAnwerGroup){
                        for (int i = 0; i < answerGroup.getChildCount(); i++) {
                            boolean isFalseVariant = true;
                            if(answerGroup.getChildAt(i).getVisibility() == View.VISIBLE){
                                Button answer = (Button) answerGroup.getChildAt(i);
                                for (int j = 0; j < answerGroup.getChildCount(); j++) {
                                    if(answerGroup.getChildAt(j).getVisibility() == View.VISIBLE){
                                        char a = controller.getAnswer().charAt(j);
                                        Log.d("log", String.valueOf(j));
                                        Log.d("log", String.valueOf(answer.getText().toString().equals("")));
                                        if(answer.getText().toString().isEmpty()){
                                            break;
                                        }else if (answer.getText().toString().contains(String.valueOf(a))) {
                                            isFalseVariant = false;
                                        }
                                    }
                                }
                                if(isFalseVariant){
                                    answer.setText("");
                                    break;
                                }
                            }
                        }
                    }*/
                }else {
                    Toast.makeText(GameActivity.this, "You don't have enough money", Toast.LENGTH_SHORT).show();
                }
                loadCoins();
            }
        });
    }

    private void checkWin() {
        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button answerBtn = (Button) answerGroup.getChildAt(i);
            if (!answerBtn.getText().toString().isEmpty() && answerBtn.getVisibility() == View.VISIBLE) {
                answerBuilder.append(answerBtn.getText().toString());
            }
        }

        boolean result = controller.checkWin(answerBuilder.toString());

        if (result) {
            if (!controller.isFinished()) {

                WinDialog winDialog = new WinDialog(GameActivity.this, 0, controller.getMAX_COIN());

                winDialog.setCancelable(false);
                winDialog.show();

                winDialog.setOnDialogButtonClickListener(new WinDialog.OnDialogButtonClickListener() {
                    @Override
                    public void onNextButtonClick() {
                        winDialog.dismiss();
                        loadDatToView();
                    }

                    @Override
                    public void onFinishButtonClick() {
                        winDialog.dismiss();
                        finish();
                    }
                });


                String text = result ? "Win" : "Lose";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            } else {
                loadCoins();
                Toast.makeText(this, "Tugadi", Toast.LENGTH_SHORT).show();
            }
        } else {
            Animation shakeAnim = AnimationUtils.loadAnimation(this,R.anim.shake_anim);
            for (int i = 0; i < answerGroup.getChildCount(); i++) {
                if(answerGroup.getChildAt(i).getVisibility() == View.VISIBLE){
                    Button button = (Button) answerGroup.getChildAt(i);
                    button.startAnimation(shakeAnim);
                }
            }
            loadCoins();
        }


    }

    private void loadCoins() {
        levelMaxCoinView.setText("+" + controller.getMAX_COIN());
        totalCoinView.setText(String.valueOf(controller.getTotalCoins()));
    }

    private void onAnswerClick(View view) {

        if (controller.isFinished()) {
            return;
        }

        Button button = (Button) view;

        String answerText = button.getText().toString();

        for (int i = 0; i < variantGroup.getChildCount(); i++) {

            Button variant = (Button) variantGroup.getChildAt(i);

            if (variant.getVisibility() == View.INVISIBLE && variant.getText().toString().equalsIgnoreCase(answerText) && button.getTag().equals(variant.getTag())) {
                button.setText("");
                variant.setVisibility(View.VISIBLE);
                totalAnswers--;
                answerBuilder.delete(0, answerBuilder.length());
                break;
            }

        }

    }


    private void onVariantClick(View view) {
        if (controller.isFinished()) {
            return;
        } else if (totalAnswers >= controller.getTotalAnswer()) {
            return;
        }

        Button button = (Button) view;
        String text = button.getText().toString();

        for (int i = 0; i < answerGroup.getChildCount(); i++) {

            Button answerBtn = (Button) answerGroup.getChildAt(i);

            if (answerBtn.getText().toString().isEmpty() && answerBtn.getVisibility() == View.VISIBLE) {
                answerBtn.setText(text);
                answerBtn.setTag(button.getTag());
                totalAnswers++;
                button.setVisibility(View.INVISIBLE);

                break;
            }
        }

        if (totalAnswers == controller.getTotalAnswer()) {
            checkWin();
        }


    }

    private void loadDatToView() {

        totalAnswers = 0;
        helpCount = 0;
        if (!answerBuilder.toString().isEmpty()) {
            answerBuilder = null;
            answerBuilder = new StringBuilder();
        }

        ArrayList<Integer> images = controller.getImages();


        imageView1.setImageResource(images.get(0));
        imageView2.setImageResource(images.get(1));
        imageView3.setImageResource(images.get(2));
        imageView4.setImageResource(images.get(3));


        for (int i = 0; i < answerGroup.getChildCount(); i++) {
            Button view = (Button) answerGroup.getChildAt(i);

            view.setText("");
            view.setTag("");

            if (i < controller.getTotalAnswer()) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }

        ArrayList<String> variants = controller.getVariants();

        for (int i = 0; i < variantGroup.getChildCount(); i++) {
            Button button = (Button) variantGroup.getChildAt(i);
            button.setText(variants.get(i));
            button.setTag(i);
            button.setVisibility(View.VISIBLE);
        }

        levelView.setText(String.valueOf(controller.getLevel()));
        loadCoins();
    }

    private void loadViews() {

        answerGroup = findViewById(R.id.answer_group);
        variantGroup = findViewById(R.id.variant_group);

        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);

        help1 = findViewById(R.id.help_1);
        help2 = findViewById(R.id.help_2);

        totalCoinView = findViewById(R.id.total_coin);
        levelView = findViewById(R.id.level_view);
        levelMaxCoinView = findViewById(R.id.level_max_coin);


    }


}