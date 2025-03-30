package pl.pjatk.myapplication.ui.harris;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pl.pjatk.myapplication.R;

public class HarrisFragment extends Fragment {

    private EditText inputAge, inputWeight, inputHeight;
    private RadioGroup genderGroup;
    private Spinner activityLevelSpinner;
    private TextView resultText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_harris, container, false);

        inputAge = view.findViewById(R.id.input_age);
        inputWeight = view.findViewById(R.id.input_weight);
        inputHeight = view.findViewById(R.id.input_height);
        genderGroup = view.findViewById(R.id.gender_group);
        activityLevelSpinner = view.findViewById(R.id.activity_level_spinner);
        resultText = view.findViewById(R.id.result_text);
        Button calculateButton = view.findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(v -> calculateCaloricNeeds());

        return view;
    }

    private void calculateCaloricNeeds() {
        String ageStr = inputAge.getText().toString().trim();
        String weightStr = inputWeight.getText().toString().trim();
        String heightStr = inputHeight.getText().toString().trim();

        if (ageStr.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            resultText.setText(R.string.fieldsRequired);
            return;
        }

        int age = Integer.parseInt(ageStr);
        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            resultText.setText(R.string.fieldsRequired);
            return;
        }

        RadioButton selectedGender = genderGroup.findViewById(selectedGenderId);
        boolean isMale = selectedGender.getId() == R.id.gender_male;

        double bmr;
        if (isMale) {
            bmr = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
        } else {
            bmr = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
        }

        String activityLevel = activityLevelSpinner.getSelectedItem().toString();
        double activityMultiplier = getActivityMultiplier(activityLevel);

        int caloricNeeds = (int) (bmr * activityMultiplier);
        resultText.setText(getString(R.string.harrisResult) + " " + caloricNeeds + " " + getString(R.string.harrisUnit));
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private double getActivityMultiplier(String activityLevel) {
        String[] activityLevels = getResources().getStringArray(R.array.activity_levels);
        if (activityLevel.equals(activityLevels[0])) {
            return 1.2;
        } else if (activityLevel.equals(activityLevels[1])) {
            return 1.375;
        } else if (activityLevel.equals(activityLevels[2])) {
            return 1.55;
        } else if (activityLevel.equals(activityLevels[3])) {
            return 1.725;
        } else if (activityLevel.equals(activityLevels[4])) {
            return 1.9;
        } else {
            return 1.0;
        }
    }

}