package pl.pjatk.myapplication.ui.recommender;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pl.pjatk.myapplication.R;
import pl.pjatk.myapplication.databinding.FragmentRecommenderBinding;

public class RecommenderFragment extends Fragment {

    private FragmentRecommenderBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecommenderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText inputCaloricNeeds = binding.inputCaloricNeeds;
        Button calculateButton = binding.buttonCalculateRecommendations;
        TextView recommendationsText = binding.textDashboard;

        calculateButton.setOnClickListener(v -> {
            String caloricNeedsStr = inputCaloricNeeds.getText().toString().trim();
            if (caloricNeedsStr.isEmpty()) {
                recommendationsText.setText(getString(R.string.enter_caloric_needs));
                return;
            }

            double caloricNeeds = Double.parseDouble(caloricNeedsStr);
            String recommendations = getRecipeRecommendations(caloricNeeds);
            recommendationsText.setText(recommendations);
            inputCaloricNeeds.clearFocus();
            // Hide the keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        return root;
    }

    private String getRecipeRecommendations(double caloricNeeds) {
        if (caloricNeeds <= 1800) {
            return getString(R.string.low_calorie_recommendations);
        } else if (caloricNeeds > 1800 && caloricNeeds <= 2500) {
            return getString(R.string.balanced_diet_recommendations);
        } else {
            return getString(R.string.high_calorie_recommendations);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
