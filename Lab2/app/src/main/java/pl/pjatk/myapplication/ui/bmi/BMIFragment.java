package pl.pjatk.myapplication.ui.bmi;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.pjatk.myapplication.BMICalculator;
import pl.pjatk.myapplication.R;
import pl.pjatk.myapplication.databinding.FragmentBmiBinding;

public class BMIFragment extends Fragment {
    private FragmentBmiBinding binding;
    private String weight = "";
    private String height = "";
    private final List<Entry> bmiEntries = new ArrayList<>();
    private LineChart bmiChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bmiChart = binding.bmiChart;
        setupChart();
        loadMockData();

        binding.submit.setOnClickListener(view -> {
            weight = binding.inputWeight.getText().toString().trim();
            height = binding.inputHeight.getText().toString().trim();

            if (!BMICalculator.isValidNumber(weight) || !BMICalculator.isValidNumber(height)) {
                String resultText = getString(R.string.invalidInput);
                binding.textResult.setText(resultText);
                return;
            }

            int weightValue = Integer.parseInt(weight);
            int heightValue = Integer.parseInt(height);

            double bmi = BMICalculator.calculateBMI(weightValue, heightValue);
            String resultText = getString(R.string.result) + " " + String.format(Locale.getDefault(), "%.2f", bmi) + getString(R.string.itMeans) + " " + getResultInterpretation(bmi);
            binding.textResult.setText(resultText);

            addNewBMIValue(bmi);
            hideKeyboard(view);
        });

        return root;
    }

    private void setupChart() {
        bmiChart.getDescription().setEnabled(false);
        bmiChart.setTouchEnabled(true);
        bmiChart.setDragEnabled(true);
        bmiChart.setScaleEnabled(true);
        bmiChart.setPinchZoom(true);
        bmiChart.setDrawGridBackground(false);

        XAxis xAxis = bmiChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);

        YAxis leftAxis = bmiChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(15f);
        leftAxis.setAxisMaximum(35f);

        bmiChart.getAxisRight().setEnabled(false);
    }

    private void loadMockData() {
        bmiEntries.clear();
        bmiEntries.add(new Entry(0, 24.5f));
        bmiEntries.add(new Entry(1, 25.3f));
        bmiEntries.add(new Entry(2, 26.4f));
        bmiEntries.add(new Entry(3, 27.2f));
        bmiEntries.add(new Entry(4, 20.1f));
        bmiEntries.add(new Entry(5, 21.0f));
        updateChartData();
    }

    private void addNewBMIValue(double bmi) {
        float lastX = bmiEntries.isEmpty() ? 0 : bmiEntries.get(bmiEntries.size() - 1).getX() + 1;
        bmiEntries.add(new Entry(lastX, (float) bmi));
        updateChartData();
    }

    private void updateChartData() {
        LineDataSet dataSet = new LineDataSet(bmiEntries, "BMI History");
        dataSet.setColor(Color.BLUE);
        dataSet.setLineWidth(2f);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        bmiChart.setData(lineData);
        bmiChart.invalidate();
    }

    private String getResultInterpretation(double bmi) {
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