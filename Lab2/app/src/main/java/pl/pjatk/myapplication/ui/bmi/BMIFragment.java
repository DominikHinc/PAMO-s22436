package pl.pjatk.myapplication.ui.bmi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import java.util.Locale;

import pl.pjatk.myapplication.R;
import pl.pjatk.myapplication.databinding.FragmentBmiBinding;

public class BMIFragment extends Fragment {
    private FragmentBmiBinding binding;
    private String weight = "";
    private String height = "";

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentBmiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = binding.inputWeight.getText().toString().trim();
                height = binding.inputHeight.getText().toString().trim();

                if (!isValidNumber(weight) || !isValidNumber(height)) {
                    String resultText = getString(R.string.invalidInput);
                    binding.textResult.setText(resultText);
                    return;
                }

                int weightValue = Integer.parseInt(weight);
                int heightValue = Integer.parseInt(height);

                double bmi = calculateBMI(weightValue, heightValue);
                String resultText = getString(R.string.result) + " " + String.format(Locale.getDefault(), "%.2f", bmi) + getString(R.string.itMeans) + " " + getResultInterpretation(bmi);
                binding.textResult.setText(resultText);

                hideKeyboard(view);
            }
        });
    }

    private String getResultInterpretation(double bmi){
        if (bmi < 18.5) {
            return getString(R.string.underweight);
        } else if (bmi >= 18.5 && bmi < 25) {
            return getString(R.string.optimum);
        } else if (bmi >= 25 && bmi < 30) {
            return getString(R.string.overweight);
        } else {
            return getString(R.string.obese);
        }
    }

    private boolean isValidNumber(String value) {
        try {
            int number = Integer.parseInt(value);
            return number >= 1 && number <= 300;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private double calculateBMI(int weight, int height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}