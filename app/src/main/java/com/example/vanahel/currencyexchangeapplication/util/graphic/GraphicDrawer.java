package com.example.vanahel.currencyexchangeapplication.util.graphic;

import android.graphics.Color;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineDataSet.Mode;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

public class GraphicDrawer {

    private final CombinedChart chart;

    public GraphicDrawer ( CombinedChart chart ) {
        this.chart = chart;
    }

    public void drawGraphic(Map<Integer, Float> rateDynamics){

        chart.getDescription().setText("This is testing Description");
        chart.setDrawGridBackground(true);
        chart.setDrawBarShadow(true);
        chart.setDrawOrder(new DrawOrder[]{
                DrawOrder.BAR,  DrawOrder.LINE
        });

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }
        });
        CombinedData data = new CombinedData();

        data.setData(generateLineData(rateDynamics));
        data.setData(generateBarData(rateDynamics));


        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        chart.setData(data);
        chart.invalidate();

    }

    private LineData generateLineData(Map<Integer, Float> rateDynamics) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        entries = getLineEntriesData(entries, rateDynamics);

        LineDataSet set = new LineDataSet(entries, "");
        set.setColor(Color.rgb(240, 238, 70));
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData(Map<Integer, Float> rateDynamics) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries = getBarEnteries(entries, rateDynamics);

        BarDataSet set1 = new BarDataSet(entries, "");
        set1.setColors(ColorTemplate.LIBERTY_COLORS);
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);

        float barWidth = 0.45f;


        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);


        return d;
    }

    private ArrayList<Entry> getLineEntriesData(ArrayList<Entry> entries, Map<Integer, Float> rateDynamics){

        for (Map.Entry<Integer, Float> rateDynamic : rateDynamics.entrySet())
        {
            entries.add(new Entry(rateDynamic.getKey(), rateDynamic.getValue()));
        }

        return entries;
    }

    private ArrayList<BarEntry> getBarEnteries(ArrayList<BarEntry> entries, Map<Integer, Float> rateDynamics) {

        for (Map.Entry<Integer, Float> rateDynamic : rateDynamics.entrySet())
        {
            entries.add(new BarEntry(rateDynamic.getKey(), rateDynamic.getValue()));
        }

        return entries;

    }

    private final String[] mMonths = {
            "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
}
