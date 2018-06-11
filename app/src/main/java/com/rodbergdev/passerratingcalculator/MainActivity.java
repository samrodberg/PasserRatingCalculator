package com.rodbergdev.passerratingcalculator;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.att_input)
    EditText attInput;
    @BindView(R.id.comp_input)
    EditText compInput;
    @BindView(R.id.yds_input)
    EditText ydsInput;
    @BindView(R.id.td_input)
    EditText tdInput;
    @BindView(R.id.int_input)
    EditText intInput;

    @BindView(R.id.clear_att_button)
    Button clearAttButton;
    @BindView(R.id.clear_comp_button)
    Button clearCompButton;
    @BindView(R.id.clear_yds_button)
    Button clearYdsButton;
    @BindView(R.id.clear_td_button)
    Button clearTdButton;
    @BindView(R.id.clear_int_button)
    Button clearIntButton;

    @BindView(R.id.clear_all_button)
    Button clearAllButton;
    @BindView(R.id.calculate_button)
    Button calculateButton;

    @BindView(R.id.rating_text)
    TextView ratingText;

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.app_name_long));
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Clear text inputs for each respective stat

        clearAttButton.setOnClickListener(v -> attInput.setText(""));

        clearCompButton.setOnClickListener(v -> compInput.setText(""));

        clearYdsButton.setOnClickListener(v -> ydsInput.setText(""));

        clearTdButton.setOnClickListener(v -> tdInput.setText(""));

        clearIntButton.setOnClickListener(v -> intInput.setText(""));

        clearAllButton.setOnClickListener((View v) -> {
            attInput.setText("");
            compInput.setText("");
            ydsInput.setText("");
            tdInput.setText("");
            intInput.setText("");
            ratingText.setText(R.string.rating_placeholder_label);
        });

        // Calculate passer rating
        calculateButton.setOnClickListener((View v) -> {
            EditText[] editTexts = {attInput, compInput, ydsInput, tdInput, intInput};

            if (checkEmptyInput(editTexts)) {
                int attempts = Integer.parseInt(attInput.getText().toString());
                int completions = Integer.parseInt(compInput.getText().toString());
                int yards = Integer.parseInt(ydsInput.getText().toString());
                int touchdowns = Integer.parseInt(tdInput.getText().toString());
                int interceptions = Integer.parseInt(intInput.getText().toString());

                PasserRating pr = new PasserRating(attempts, completions, yards, touchdowns, interceptions);

                // Format output to be align with NFL standard pattern of displaying rating
                DecimalFormat df = new DecimalFormat("###.#");
                Double rating = pr.calculatePasserRating();
                String ratingFormatted = df.format(rating);
                ratingText.setText(ratingFormatted);
            }
        });
    }

    // Helper method for ensuring text inputs are not empty before calculating rating
    private boolean checkEmptyInput(EditText[] inputs) {
        for (EditText input : inputs) {
            if (TextUtils.isEmpty(input.getText().toString())) {
                Snackbar snackbar = Snackbar.make(mainLayout, getString(R.string.empty_text_snackbar), Snackbar.LENGTH_LONG);
                snackbar.setAction(getString(R.string.snackbar_close), (v -> snackbar.dismiss()));
                snackbar.setActionTextColor(getResources().getColor(R.color.green900));
                snackbar.show();
                return false;
            }
        }
        return true;
    }
}
