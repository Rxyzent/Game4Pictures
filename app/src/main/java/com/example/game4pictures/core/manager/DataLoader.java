package com.example.game4pictures.core.manager;


import com.example.game4pictures.R;
import com.example.game4pictures.core.model.QuizData;

import java.util.ArrayList;
import java.util.Collections;

public class DataLoader {


    public static ArrayList<QuizData> getData() {
        ArrayList<QuizData> data = new ArrayList<>();

        QuizData data1 = new QuizData("citrus");

        ArrayList<Integer> image1 = new ArrayList<>();
        image1.add(R.drawable.image_1_1);
        image1.add(R.drawable.image_1_2);
        image1.add(R.drawable.image_1_3);
        image1.add(R.drawable.image_1_4);

        ArrayList<String> variants1 = new ArrayList<>();

        variants1.add("c");
        variants1.add("i");
        variants1.add("t");
        variants1.add("r");
        variants1.add("u");
        variants1.add("s");

        variantHelper(variants1);

        data1.setImages(image1);
        data1.setVariants(variants1);

        data.add(data1);


        QuizData data2 = new QuizData("mexico");

        ArrayList<Integer> image2 = new ArrayList<>();
        image2.add(R.drawable.image_2_1);
        image2.add(R.drawable.image_2_2);
        image2.add(R.drawable.image_2_3);
        image2.add(R.drawable.image_2_4);

        ArrayList<String> variant2 = new ArrayList<>();
        variant2.add("m");
        variant2.add("e");
        variant2.add("x");
        variant2.add("i");
        variant2.add("c");
        variant2.add("o");

        variantHelper(variant2);

        data2.setVariants(variant2);
        data2.setImages(image2);

        data.add(data2);

        QuizData data3 = new QuizData("jump");

        ArrayList<Integer> image3 = new ArrayList<>();
        image3.add(R.drawable.image3_1);
        image3.add(R.drawable.image3_2);
        image3.add(R.drawable.image3_3);
        image3.add(R.drawable.image3_4);

        ArrayList<String> variant3 = new ArrayList<>();
        variant3.add("j");
        variant3.add("u");
        variant3.add("m");
        variant3.add("p");

        variantHelper(variant3);

        data3.setVariants(variant3);
        data3.setImages(image3);

        data.add(data3);

        QuizData data4 = new QuizData("sky");

        ArrayList<Integer> image4 = new ArrayList<>();
        image4.add(R.drawable.image4_1);
        image4.add(R.drawable.image4_2);
        image4.add(R.drawable.image4_3);
        image4.add(R.drawable.image4_4);

        ArrayList<String> variant4 = new ArrayList<>();
        variant4.add("s");
        variant4.add("k");
        variant4.add("y");

        variantHelper(variant4);

        data4.setVariants(variant4);
        data4.setImages(image4);

        data.add(data4);

        QuizData data5 = new QuizData("animal");

        ArrayList<Integer> image5 = new ArrayList<>();
        image5.add(R.drawable.image5_1);
        image5.add(R.drawable.image5_2);
        image5.add(R.drawable.image5_3);
        image5.add(R.drawable.image5_4);

        ArrayList<String> variant5 = new ArrayList<>();
        variant5.add("a");
        variant5.add("n");
        variant5.add("i");
        variant5.add("m");
        variant5.add("a");
        variant5.add("l");

        variantHelper(variant5);

        data5.setVariants(variant5);
        data5.setImages(image5);

        data.add(data5);

        QuizData data6 = new QuizData("energy");

        ArrayList<Integer> image6 = new ArrayList<>();
        image6.add(R.drawable.image6_1);
        image6.add(R.drawable.image6_2);
        image6.add(R.drawable.image6_3);
        image6.add(R.drawable.image6_4);

        ArrayList<String> variant6 = new ArrayList<>();
        variant6.add("e");
        variant6.add("n");
        variant6.add("e");
        variant6.add("r");
        variant6.add("g");
        variant6.add("y");

        variantHelper(variant6);

        data6.setVariants(variant6);
        data6.setImages(image6);

        data.add(data6);

        return data;
    }


    private static void variantHelper(ArrayList<String> list) {
        int k = 0;
        int size = list.size();
        ArrayList<Character> letters = new ArrayList<>();

        for (char i = 'a'; i <= 'z'; i++) {
            letters.add(i);
        }
        Collections.shuffle(letters);
        for (int i = 0; i <= letters.size(); i++) {
            if (k < (14 - size)) {
                int count = 0;
                for (int j = 0; j < size; j++) {
                    if (!list.get(j).equalsIgnoreCase(String.valueOf(letters.get(i)))) {
                        count++;
                    }
                }
                if (count == size) {
                    list.add(String.valueOf(letters.get(i)));
                    k++;
                }
            } else {
                break;
            }

        }
    }
}

