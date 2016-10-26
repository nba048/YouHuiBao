package com.yhb.mvp.view.fragment;

import android.graphics.Color;
import android.view.View;

import com.yhb.R;
import com.yhb.androidcharts.axis.Axis;
import com.yhb.androidcharts.entity.DateValueEntity;
import com.yhb.androidcharts.entity.IStickEntity;
import com.yhb.androidcharts.entity.LineEntity;
import com.yhb.androidcharts.entity.OHLCEntity;
import com.yhb.androidcharts.view.LineChart;
import com.yhb.base.BaseFragment;
import com.yhb.bean.entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class OrderDetailFragment_3 extends BaseFragment {
    protected List<IStickEntity> ohlc;
    LineChart linechart;
    private Order orderItem;

    public OrderDetailFragment_3(Order orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_detail_3;
    }

    @Override
    protected void setInitView(View mRootView) {
        this.linechart = (LineChart) mRootView.findViewById(R.id.linechart);

    }

    @Override
    protected void setInitData() {
        initOHLC();
        initLineChart();
    }

    private void initLineChart() {
        List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("MA5");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(initMA(5));
        lines.add(MA5);

        // 计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("MA10");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(initMA(10));
        lines.add(MA10);

        // 计算25日均线
        LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
        MA25.setTitle("MA25");
        MA25.setLineColor(Color.GREEN);
        MA25.setLineData(initMA(25));
        lines.add(MA25);

        linechart.setAxisXColor(Color.LTGRAY);
        linechart.setAxisYColor(Color.LTGRAY);
        linechart.setBorderColor(Color.LTGRAY);
        linechart.setLongitudeFontSize(14);
        linechart.setLongitudeFontColor(Color.WHITE);
        linechart.setLatitudeColor(Color.GRAY);
        linechart.setLatitudeFontColor(Color.WHITE);
        linechart.setLongitudeColor(Color.GRAY);
        linechart.setMaxValue(280);
        linechart.setMinValue(240);
        linechart.setMaxPointNum(36);
        linechart.setDisplayLongitudeTitle(true);
        linechart.setDisplayLatitudeTitle(true);
        linechart.setDisplayLatitude(true);
        linechart.setDisplayLongitude(true);
        linechart.setLatitudeNum(5);
        linechart.setLongitudeNum(6);
        linechart.setDataQuadrantPaddingTop(5);
        linechart.setDataQuadrantPaddingBottom(5);
        linechart.setDataQuadrantPaddingLeft(5);
        linechart.setDataQuadrantPaddingRight(5);
        // linechart.setAxisYTitleQuadrantWidth(50);
        // linechart.setAxisXTitleQuadrantHeight(20);
        linechart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        linechart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart1增加均线
        linechart.setLinesData(lines);
    }

    protected List<DateValueEntity> initMA(int days) {

        if (days < 2) {
            return null;
        }

        List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

        float sum = 0;
        float avg = 0;
        for (int i = 0; i < this.ohlc.size(); i++) {
            float close = (float) ((OHLCEntity) ohlc.get(i)).getClose();
            if (i < days) {
                sum = sum + close;
                avg = sum / (i + 1f);
            } else {
                sum = sum + close
                        - (float) ((OHLCEntity) ohlc.get(i - days)).getClose();
                avg = sum / days;
            }
            MA5Values.add(new DateValueEntity(avg, ohlc.get(i).getDate()));
        }

        return MA5Values;
    }
    protected void initOHLC() {
        List<IStickEntity> ohlc = new ArrayList<IStickEntity>();

        this.ohlc = new ArrayList<IStickEntity>();
        ohlc.add(new OHLCEntity(986, 1015, 977, 1003, 20130424));
        ohlc.add(new OHLCEntity(1000, 1007, 982, 991, 20130425));
        ohlc.add(new OHLCEntity(996, 1001, 985, 988, 20130426));
        ohlc.add(new OHLCEntity(977, 986, 966, 982, 20130502));
        ohlc.add(new OHLCEntity(987, 1017, 983, 1001, 20130503));
        ohlc.add(new OHLCEntity(1003, 1021, 997, 1013, 20130506));
        ohlc.add(new OHLCEntity(1009, 1010, 998, 1006, 20130507));
        ohlc.add(new OHLCEntity(1012, 1020, 1001, 1005, 20130508));
        ohlc.add(new OHLCEntity(1006, 1008, 989, 997, 20130509));
        ohlc.add(new OHLCEntity(993, 1006, 989, 1003, 20130510));
        ohlc.add(new OHLCEntity(1002, 1011, 993, 1002, 20130513));
        ohlc.add(new OHLCEntity(1003, 1005, 993, 997, 20130514));
        ohlc.add(new OHLCEntity(998, 1002, 993, 999, 20130515));
        ohlc.add(new OHLCEntity(999, 1016, 984, 1015, 20130516));
        ohlc.add(new OHLCEntity(1015, 1028, 1005, 1024, 20130517));
        ohlc.add(new OHLCEntity(1026, 1054, 1020, 1041, 20130520));
        ohlc.add(new OHLCEntity(1038, 1042, 1024, 1034, 20130521));
        ohlc.add(new OHLCEntity(1033, 1038, 1028, 1036, 20130522));
        ohlc.add(new OHLCEntity(1029, 1033, 1015, 1015, 20130523));
        ohlc.add(new OHLCEntity(1020, 1028, 1010, 1020, 20130524));
        ohlc.add(new OHLCEntity(1021, 1033, 1018, 1029, 20130527));
        ohlc.add(new OHLCEntity(1030, 1056, 1025, 1055, 20130528));
        ohlc.add(new OHLCEntity(1058, 1062, 1051, 1052, 20130529));
        ohlc.add(new OHLCEntity(1048, 1062, 1047, 1054, 20130530));
        ohlc.add(new OHLCEntity(1056, 1062, 1046, 1047, 20130531));
        ohlc.add(new OHLCEntity(997, 1001, 981, 984, 20130603));
        ohlc.add(new OHLCEntity(989, 989, 970, 974, 20130604));
        ohlc.add(new OHLCEntity(974, 977, 960, 965, 20130605));
        ohlc.add(new OHLCEntity(961, 967, 942, 945, 20130606));
        ohlc.add(new OHLCEntity(951, 957, 932, 935, 20130607));
        ohlc.add(new OHLCEntity(925, 925, 891, 902, 20130613));
        ohlc.add(new OHLCEntity(907, 907, 898, 902, 20130614));
        ohlc.add(new OHLCEntity(905, 910, 896, 901, 20130617));
        ohlc.add(new OHLCEntity(905, 912, 901, 907, 20130618));
        ohlc.add(new OHLCEntity(905, 905, 882, 889, 20130619));
        ohlc.add(new OHLCEntity(886, 886, 840, 842, 20130620));
        ohlc.add(new OHLCEntity(831, 847, 822, 828, 20130621));
        ohlc.add(new OHLCEntity(829, 829, 750, 752, 20130624));
        ohlc.add(new OHLCEntity(745, 784, 718, 780, 20130625));
        ohlc.add(new OHLCEntity(790, 795, 763, 777, 20130626));
        ohlc.add(new OHLCEntity(785, 792, 770, 788, 20130627));
        ohlc.add(new OHLCEntity(782, 830, 776, 828, 20130628));
        ohlc.add(new OHLCEntity(822, 827, 807, 817, 20130701));
        ohlc.add(new OHLCEntity(818, 822, 795, 815, 20130702));
        ohlc.add(new OHLCEntity(810, 811, 797, 804, 20130703));
        ohlc.add(new OHLCEntity(806, 828, 802, 812, 20130704));
        ohlc.add(new OHLCEntity(811, 822, 808, 811, 20130705));
        ohlc.add(new OHLCEntity(800, 805, 790, 791, 20130708));
        ohlc.add(new OHLCEntity(792, 796, 788, 792, 20130709));
        ohlc.add(new OHLCEntity(795, 813, 790, 811, 20130710));
        ohlc.add(new OHLCEntity(817, 892, 817, 886, 20130711));
        ohlc.add(new OHLCEntity(876, 885, 843, 849, 20130712));
        ohlc.add(new OHLCEntity(855, 871, 841, 856, 20130715));
        ohlc.add(new OHLCEntity(852, 855, 841, 854, 20130716));
        ohlc.add(new OHLCEntity(852, 855, 838, 845, 20130717));
        ohlc.add(new OHLCEntity(841, 845, 816, 820, 20130718));
        ohlc.add(new OHLCEntity(822, 824, 802, 803, 20130719));
        ohlc.add(new OHLCEntity(790, 799, 782, 795, 20130722));
        ohlc.add(new OHLCEntity(799, 823, 794, 814, 20130723));
        ohlc.add(new OHLCEntity(804, 809, 790, 800, 20130724));
        ohlc.add(new OHLCEntity(802, 811, 796, 802, 20130725));
        ohlc.add(new OHLCEntity(798, 801, 794, 797, 20130726));
        ohlc.add(new OHLCEntity(790, 790, 771, 774, 20130729));
        ohlc.add(new OHLCEntity(778, 796, 774, 784, 20130730));
        ohlc.add(new OHLCEntity(791, 802, 782, 786, 20130731));
        ohlc.add(new OHLCEntity(792, 802, 787, 799, 20130801));
        ohlc.add(new OHLCEntity(806, 812, 797, 798, 20130802));
        ohlc.add(new OHLCEntity(798, 807, 795, 806, 20130805));
        ohlc.add(new OHLCEntity(803, 808, 798, 803, 20130806));
        ohlc.add(new OHLCEntity(803, 814, 800, 801, 20130807));
        ohlc.add(new OHLCEntity(801, 807, 795, 799, 20130808));
        ohlc.add(new OHLCEntity(805, 808, 796, 801, 20130809));
        ohlc.add(new OHLCEntity(804, 832, 801, 831, 20130812));
        ohlc.add(new OHLCEntity(830, 843, 827, 842, 20130813));
        ohlc.add(new OHLCEntity(844, 853, 830, 831, 20130814));
        ohlc.add(new OHLCEntity(831, 837, 820, 822, 20130815));
        ohlc.add(new OHLCEntity(817, 904, 815, 831, 20130816));
        ohlc.add(new OHLCEntity(824, 850, 823, 845, 20130819));
        ohlc.add(new OHLCEntity(842, 878, 839, 851, 20130820));
        ohlc.add(new OHLCEntity(853, 858, 837, 845, 20130821));
        ohlc.add(new OHLCEntity(841, 862, 840, 844, 20130822));
        ohlc.add(new OHLCEntity(854, 863, 825, 842, 20130823));
        ohlc.add(new OHLCEntity(845, 878, 840, 874, 20130826));
        ohlc.add(new OHLCEntity(875, 905, 870, 895, 20130827));
        ohlc.add(new OHLCEntity(888, 915, 879, 900, 20130828));
        ohlc.add(new OHLCEntity(911, 921, 886, 892, 20130829));
        ohlc.add(new OHLCEntity(886, 905, 876, 899, 20130830));
        ohlc.add(new OHLCEntity(911, 929, 895, 897, 20130902));
        ohlc.add(new OHLCEntity(896, 912, 889, 909, 20130903));
        ohlc.add(new OHLCEntity(904, 924, 903, 914, 20130904));
        ohlc.add(new OHLCEntity(919, 919, 906, 913, 20130905));
        ohlc.add(new OHLCEntity(915, 987, 912, 957, 20130906));
        ohlc.add(new OHLCEntity(1028, 1053, 1018, 1053, 20130909));
        ohlc.add(new OHLCEntity(1100, 1149, 1077, 1140, 20130910));
        ohlc.add(new OHLCEntity(1121, 1147, 1120, 1127, 20130911));
        ohlc.add(new OHLCEntity(1130, 1240, 1116, 1225, 20130912));
        ohlc.add(new OHLCEntity(1208, 1227, 1173, 1191, 20130913));
        ohlc.add(new OHLCEntity(1200, 1202, 1123, 1149, 20130916));
        ohlc.add(new OHLCEntity(1141, 1148, 1077, 1083, 20130917));
        ohlc.add(new OHLCEntity(1095, 1119, 1083, 1100, 20130918));
        ohlc.add(new OHLCEntity(1105, 1120, 1080, 1118, 20130923));
        ohlc.add(new OHLCEntity(1119, 1120, 1057, 1081, 20130924));
        ohlc.add(new OHLCEntity(1074, 1118, 1069, 1078, 20130925));
        ohlc.add(new OHLCEntity(1075, 1076, 1007, 1017, 20130926));
        ohlc.add(new OHLCEntity(1011, 1033, 1005, 1024, 20130927));
        ohlc.add(new OHLCEntity(1034, 1037, 1002, 1009, 20130930));
        ohlc.add(new OHLCEntity(1003, 1033, 988, 1023, 20131008));
        ohlc.add(new OHLCEntity(1010, 1046, 1007, 1027, 20131009));
        ohlc.add(new OHLCEntity(1030, 1035, 993, 998, 20131010));
        ohlc.add(new OHLCEntity(1010, 1065, 1000, 1055, 20131011));
        ohlc.add(new OHLCEntity(1045, 1050, 1025, 1029, 20131014));
        ohlc.add(new OHLCEntity(1030, 1035, 1002, 1011, 20131015));
        ohlc.add(new OHLCEntity(1009, 1009, 982, 991, 20131016));
        ohlc.add(new OHLCEntity(1001, 1007, 981, 982, 20131017));
        ohlc.add(new OHLCEntity(982, 1006, 980, 988, 20131018));
        ohlc.add(new OHLCEntity(995, 1016, 980, 1012, 20131021));
        ohlc.add(new OHLCEntity(1011, 1011, 986, 993, 20131022));
        ohlc.add(new OHLCEntity(995, 1035, 991, 1002, 20131023));
        ohlc.add(new OHLCEntity(996, 1016, 982, 998, 20131024));
        ohlc.add(new OHLCEntity(1001, 1026, 999, 1007, 20131025));
        ohlc.add(new OHLCEntity(1008, 1022, 992, 1015, 20131028));
        ohlc.add(new OHLCEntity(1022, 1069, 1018, 1048, 20131029));
        ohlc.add(new OHLCEntity(1048, 1062, 1031, 1059, 20131030));
        ohlc.add(new OHLCEntity(1058, 1060, 1031, 1033, 20131031));
        ohlc.add(new OHLCEntity(1032, 1053, 1023, 1042, 20131101));
        ohlc.add(new OHLCEntity(1048, 1054, 1026, 1030, 20131104));

        // for (int i = ohlc.size(); i > 0; i--) {
        // this.ohlc.add(ohlc.get(i - 1));
        // }

        this.ohlc.addAll(ohlc);
    }


}
