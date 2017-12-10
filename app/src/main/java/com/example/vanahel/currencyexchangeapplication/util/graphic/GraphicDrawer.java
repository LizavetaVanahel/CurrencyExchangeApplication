package com.example.vanahel.currencyexchangeapplication.util.graphic;

import android.graphics.Color;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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

        this.chart.getDescription().setText("This is testing Description");
        this.chart.setDrawGridBackground(true);
        this.chart.setDrawBarShadow(true);
        this.chart.setDrawOrder(new DrawOrder[]{
                DrawOrder.BAR,  DrawOrder.LINE
        });

        YAxis rightAxis = this.chart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = this.chart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setAxisMinimum(0f);
        XAxis xAxis = this.chart.getXAxis();
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return GraphicDrawer.this.mMonths[(int) value % GraphicDrawer.this.mMonths.length];
            }
        });
        CombinedData data = new CombinedData();

        data.setData(this.generateLineData(rateDynamics));

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        this.chart.setData(data);
        this.chart.invalidate();

    }

    private LineData generateLineData(Map<Integer, Float> rateDynamics) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        entries = this.getLineEntriesData(entries, rateDynamics);

        LineDataSet set = new LineDataSet(entries, "");
        set.setColor(Color.rgb(240, 238, 70));
        set.setColors(ColorTemplate.COLORFUL_COLORS);
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

    private ArrayList<Entry> getLineEntriesData(ArrayList<Entry> entries, Map<Integer, Float> rateDynamics){

        for (Map.Entry<Integer, Float> rateDynamic : rateDynamics.entrySet())
        {
            entries.add(new Entry(rateDynamic.getKey(), rateDynamic.getValue()));
        }

        return entries;
    }

    private final String[] mMonths = {
            "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
}
