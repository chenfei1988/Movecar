package xinyiyun.chenfei.com.baselibrary.utils.MPAchart;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

import xinyiyun.chenfei.com.baselibrary.basic.BaseApplication;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

/**
 * Created by loptop on 2017/6/2.
 */
public class BarChartManager {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private Typeface mTf;
    final int[] MATERIAL_COLORS = {
            rgb("#2f83cc"), rgb("#2f83cc"), rgb("#2f83cc"), rgb("#2f83cc"), rgb("#2f83cc"), rgb("#2f83cc")
    };
    public BarChartManager(BarChart barChart) {
        this.mBarChart = barChart;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
        mTf = Typeface.createFromAsset(BaseApplication.application.getAssets(),
                "OpenSans-Regular.ttf");
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {

        //设置背景是否网格显示
        mBarChart.setDrawGridBackground(true);
        mBarChart.setTouchEnabled(false);
        mBarChart.setScaleEnabled(false);
        mBarChart.setDrawValueAboveBar(true);
        //显示边界
        mBarChart.setDrawBorders(false);
        //折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setTextSize(12f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.CIRCLE);// 样式
        legend.setFormSize(6f);// 字体
        legend.setTextColor(Color.BLACK);// 颜色
        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(true);
        xAxis.setTypeface(mTf);



        leftAxis.setDrawTopYLabelEntry(true);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawLabels(true);
        leftAxis.setTypeface(mTf);


        rightAxis.setAxisMinimum(0f);
        rightAxis.setEnabled(false);
        //设置动画效果
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
        mBarChart.animateY(700, Easing.EasingOption.EaseInCubic);
        mBarChart.setNoDataText("没有数据");
        mBarChart.getDescription(). setPosition(120f, 45f);
    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showBarChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart();
        final String[] quarters = new String[] { "1月", "2月", "3月", "4月","5月", "6月", "7月", "8月" ,"9月", "10月", "11月", "12月"};
        IAxisValueFormatter xformatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        };
;
        xAxis.setValueFormatter(xformatter);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new BarEntry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        barDataSet.setColor(color);

        barDataSet.setValueTextSize(9f);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        //设置X轴的刻度数
        xAxis.setLabelCount(xAxisValues.size()-1, false);
        mBarChart.setData(data);
    }
    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     */
    public void showBarChartString(final List<String> xAxisValues, List<Float> yAxisValues, String label) {
        initLineChart();
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisValues.get((int) value);
            }
        };
        xAxis.setValueFormatter(formatter);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new BarEntry(i, yAxisValues.get(i)));
        }
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(entries);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, label);
            set1.setDrawIcons(false);
          //  set1.setColors(MATERIAL_COLORS);
            set1.setColor(rgb("#2f83cc"));
            set1.setValueTextSize(10f);
         //   set1.setValueTextColor(R.color.c_e98341);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    return (int)entry.getY() + "";
                }
            });
            data.setBarWidth(0.4f);
            mBarChart.setData(data);
        }
    }
    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showBarChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        BarData data = new BarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {

                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }

        int amount = yAxisValues.size(); //需要显示柱状图的类别 数量
        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet

//        float groupSpace = 0.12f; //柱状图组之间的间距
//        float barSpace =  0.02f; // x4 DataSet
//        float barWidth = 0.2f; // x4 DataSet
        // (0.2 + 0.02) * 4 + 0.12 = 1.00 即100% 按照百分百布局
        xAxis.setLabelCount(amount - 1, false);
        //柱状图宽度
        data.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0, groupSpace, barSpace);

        mBarChart.setData(data);
    }


    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, false);

        mBarChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
        mBarChart.invalidate();
    }
}
