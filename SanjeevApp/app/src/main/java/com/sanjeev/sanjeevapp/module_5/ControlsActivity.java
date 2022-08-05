package com.sanjeev.sanjeevapp.module_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sanjeev.sanjeevapp.R;

public class ControlsActivity extends AppCompatActivity {

    SwitchCompat switchSubscribe;

    ToggleButton btnToggle;
    RadioGroup radioGroupGender;
    RadioButton rdMale, rdFemale, rdOther;

    CheckBox checkTerms;
    SeekBar seekBar;
    TextView txtSeekValue, txtResult;

    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        initVariables();
        setUpClicks();
    }

    int progressValue= 0;
    private void setUpClicks() {
        //switch
        switchSubscribe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked) {
                    switchSubscribe.setText("Yes");
                } else {
                    switchSubscribe.setText("No");
                }
            }
        });

        //seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                txtSeekValue.setText("" + progress);

                progressValue = progress;
                txtSeekValue.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                txtSeekValue.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtSeekValue.setVisibility(View.GONE);
            }
        });

        //submit button for result of radio button,checkbox,toggle
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stResult = "";
                boolean checked = btnToggle.isChecked();
                if (checked) {
                    stResult = "Toggle Value is : On";
                } else {
                    stResult = "Toggle Value is : Off";
                }

                int checkedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
                RadioButton viewById = (RadioButton) findViewById(checkedRadioButtonId);

                stResult = stResult + "\n Gender Value: " + viewById.getText();

                if (checkTerms.isChecked()) {
                    stResult = stResult + "\n terms and conditions : accepted";
                } else {
                    stResult = stResult + "\n terms and conditions : not accepted";
                }
                stResult = stResult +"\n Final SeekBar Value : "+ progressValue;
                txtResult.setText(stResult);
            }
        });
    }

    private void initVariables() {
        switchSubscribe = findViewById(R.id.swtitchSubscribe);
        btnToggle = findViewById(R.id.toggleButton);
        radioGroupGender = findViewById(R.id.rgGender);
        rdMale = findViewById(R.id.rdMale);
        rdFemale = findViewById(R.id.rdFemale);
        rdOther = findViewById(R.id.rdOther);
        checkTerms = findViewById(R.id.checkTerms);
        seekBar = findViewById(R.id.seekBar);
        txtSeekValue = findViewById(R.id.txtSeekBarValue);
        txtResult = findViewById(R.id.txtResult);
        btnSubmit = findViewById(R.id.btnSubmit);
    }
}