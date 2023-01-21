package com.example.game4pictures.core.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.example.game4pictures.R;

public class WinDialog extends AlertDialog {

    private OnDialogButtonClickListener onDialogButtonClickListener;

    private final long deltaTime;
    private final int deltaCoin;

    private final View view;

    private final Button finishBtn;
    private final Button nextBtn;
    private final TextView deltaCoinView;

    public WinDialog(@NonNull Context context, long deltaTime, int deltaCoin) {
        super(context);

        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));

        this.deltaTime = deltaTime;
        this.deltaCoin = deltaCoin;
        view = LayoutInflater.from(context).inflate(R.layout.win_dialog_view, null, false);

        finishBtn = view.findViewById(R.id.finish_btn);
        nextBtn = view.findViewById(R.id.next_btn);
        deltaCoinView = view.findViewById(R.id.delta_coin_view);


        setView(view);


        /*int second = (int) (deltaTime / 1000) % 60;
        int minute = (int) (deltaTime / 1000) / 60 % 60;
        int hour = (int) (deltaTime / 1000) / 60 / 60 % 24;


        String timeFormat = String.format("%02d:%02d:%02d", hour, minute, second);
*/
        deltaCoinView.setText("+"+deltaCoin);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDialogButtonClickListener != null) {
                    onDialogButtonClickListener.onNextButtonClick();
                }
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDialogButtonClickListener != null) {
                    onDialogButtonClickListener.onFinishButtonClick();
                }
            }
        });
    }

    public void setOnDialogButtonClickListener(OnDialogButtonClickListener onDialogButtonClickListener) {
        this.onDialogButtonClickListener = onDialogButtonClickListener;
    }

    public interface OnDialogButtonClickListener{
        void onNextButtonClick();
        void onFinishButtonClick();
    }

}
