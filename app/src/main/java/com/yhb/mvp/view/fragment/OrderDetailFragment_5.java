package com.yhb.mvp.view.fragment;

import android.graphics.Color;
import android.view.View;

import com.yhb.R;
import com.yhb.androidcharts.axis.Axis;
import com.yhb.androidcharts.entity.DateValueEntity;
import com.yhb.androidcharts.entity.LineEntity;
import com.yhb.androidcharts.event.IZoomable;
import com.yhb.androidcharts.view.SlipLineChart;
import com.yhb.base.BaseFragment;
import com.yhb.bean.entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class OrderDetailFragment_5 extends BaseFragment {
    protected List<DateValueEntity> dv1;
    protected List<DateValueEntity> dv2;
    SlipLineChart sliplinechart;
    private Order orderItem;

    public OrderDetailFragment_5(Order orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_detail_5;
    }

    @Override
    protected void setInitView(View mRootView) {
        this.sliplinechart = (SlipLineChart) mRootView.findViewById(R.id.sliplinechart);

    }

    @Override
    protected void setInitData() {
        initDV1();
        initDV2();
        initSlipLineChart();
    }

    private void initSlipLineChart() {
        List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("HIGH");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(dv1);
        lines.add(MA5);

        // 计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("LOW");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(dv2);
        lines.add(MA10);

        sliplinechart.setAxisXColor(Color.LTGRAY);
        sliplinechart.setAxisYColor(Color.LTGRAY);
        sliplinechart.setBorderColor(Color.LTGRAY);
        sliplinechart.setLongitudeFontSize(14);
        sliplinechart.setLongitudeFontColor(Color.WHITE);
        sliplinechart.setLatitudeColor(Color.GRAY);
        sliplinechart.setLatitudeFontColor(Color.WHITE);
        sliplinechart.setLongitudeColor(Color.GRAY);
        sliplinechart.setMaxValue(1300);
        sliplinechart.setMinValue(700);
        sliplinechart.setDisplayFrom(10);
        sliplinechart.setDisplayNumber(30);
        sliplinechart.setMinDisplayNumber(5);
        sliplinechart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        sliplinechart.setDisplayLongitudeTitle(true);
        sliplinechart.setDisplayLatitudeTitle(true);
        sliplinechart.setDisplayLatitude(true);
        sliplinechart.setDisplayLongitude(true);
        sliplinechart.setDataQuadrantPaddingTop(5);
        sliplinechart.setDataQuadrantPaddingBottom(5);
        sliplinechart.setDataQuadrantPaddingLeft(5);
        sliplinechart.setDataQuadrantPaddingRight(5);
        // sliplinechart.setAxisYTitleQuadrantWidth(50);
        // sliplinechart.setAxisXTitleQuadrantHeight(20);
        sliplinechart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        sliplinechart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        sliplinechart.setLinesData(lines);
    }

    protected void initDV1() {
        List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

        this.dv1 = new ArrayList<DateValueEntity>();
        dv.add(new DateValueEntity(947.3056f, 20130424));
        dv.add(new DateValueEntity(952.2242f, 20130425));
        dv.add(new DateValueEntity(963.2635f, 20130426));
        dv.add(new DateValueEntity(961.9385f, 20130502));
        dv.add(new DateValueEntity(962.3391f, 20130503));
        dv.add(new DateValueEntity(961.9631f, 20130506));
        dv.add(new DateValueEntity(961.916f, 20130507));
        dv.add(new DateValueEntity(961.9375f, 20130508));
        dv.add(new DateValueEntity(962.1758f, 20130509));
        dv.add(new DateValueEntity(962.1837f, 20130510));
        dv.add(new DateValueEntity(962.1995f, 20130513));
        dv.add(new DateValueEntity(962.1158f, 20130514));
        dv.add(new DateValueEntity(962.2931f, 20130515));
        dv.add(new DateValueEntity(963.1225f, 20130516));
        dv.add(new DateValueEntity(965.0629f, 20130517));
        dv.add(new DateValueEntity(969.385f, 20130520));
        dv.add(new DateValueEntity(975.5116f, 20130521));
        dv.add(new DateValueEntity(974.0666f, 20130522));
        dv.add(new DateValueEntity(974.2079f, 20130523));
        dv.add(new DateValueEntity(977.2924f, 20130524));
        dv.add(new DateValueEntity(977.4907f, 20130527));
        dv.add(new DateValueEntity(976.429f, 20130528));
        dv.add(new DateValueEntity(977.8235f, 20130529));
        dv.add(new DateValueEntity(981.4609f, 20130530));
        dv.add(new DateValueEntity(983.0612f, 20130531));
        dv.add(new DateValueEntity(978.343f, 20130603));
        dv.add(new DateValueEntity(972.4412f, 20130604));
        dv.add(new DateValueEntity(965.072f, 20130605));
        dv.add(new DateValueEntity(954.1762f, 20130606));
        dv.add(new DateValueEntity(941.5963f, 20130607));
        dv.add(new DateValueEntity(921.8664f, 20130613));
        dv.add(new DateValueEntity(905.6599f, 20130614));
        dv.add(new DateValueEntity(891.2146f, 20130617));
        dv.add(new DateValueEntity(879.2878f, 20130618));
        dv.add(new DateValueEntity(865.2361f, 20130619));
        dv.add(new DateValueEntity(843.2399f, 20130620));
        dv.add(new DateValueEntity(821.4298f, 20130621));
        dv.add(new DateValueEntity(784.0339f, 20130624));
        dv.add(new DateValueEntity(759.5865f, 20130625));
        dv.add(new DateValueEntity(738.5209f, 20130626));
        dv.add(new DateValueEntity(723.5436f, 20130627));
        dv.add(new DateValueEntity(720.2877f, 20130628));
        dv.add(new DateValueEntity(718.5511f, 20130701));
        dv.add(new DateValueEntity(720.9672f, 20130702));
        dv.add(new DateValueEntity(725.9567f, 20130703));
        dv.add(new DateValueEntity(726.3284f, 20130704));
        dv.add(new DateValueEntity(728.0508f, 20130705));
        dv.add(new DateValueEntity(728.961f, 20130708));
        dv.add(new DateValueEntity(730.1062f, 20130709));
        dv.add(new DateValueEntity(734.6287f, 20130710));
        dv.add(new DateValueEntity(736.1662f, 20130711));
        dv.add(new DateValueEntity(739.5985f, 20130712));
        dv.add(new DateValueEntity(743.5045f, 20130715));
        dv.add(new DateValueEntity(749.4669f, 20130716));
        dv.add(new DateValueEntity(753.7623f, 20130717));
        dv.add(new DateValueEntity(753.6917f, 20130718));
        dv.add(new DateValueEntity(752.4678f, 20130719));
        dv.add(new DateValueEntity(760.7568f, 20130722));
        dv.add(new DateValueEntity(765.0131f, 20130723));
        dv.add(new DateValueEntity(768.8569f, 20130724));
        dv.add(new DateValueEntity(770.9514f, 20130725));
        dv.add(new DateValueEntity(768.5318f, 20130726));
        dv.add(new DateValueEntity(762.7225f, 20130729));
        dv.add(new DateValueEntity(759.3295f, 20130730));
        dv.add(new DateValueEntity(757.1793f, 20130731));
        dv.add(new DateValueEntity(756.1526f, 20130801));
        dv.add(new DateValueEntity(755.1125f, 20130802));
        dv.add(new DateValueEntity(756.6308f, 20130805));
        dv.add(new DateValueEntity(757.8153f, 20130806));
        dv.add(new DateValueEntity(757.0371f, 20130807));
        dv.add(new DateValueEntity(763.2288f, 20130808));
        dv.add(new DateValueEntity(764.5119f, 20130809));
        dv.add(new DateValueEntity(767.9202f, 20130812));
        dv.add(new DateValueEntity(770.146f, 20130813));
        dv.add(new DateValueEntity(772.2369f, 20130814));
        dv.add(new DateValueEntity(772.1298f, 20130815));
        dv.add(new DateValueEntity(771.5269f, 20130816));
        dv.add(new DateValueEntity(770.4365f, 20130819));
        dv.add(new DateValueEntity(767.9823f, 20130820));
        dv.add(new DateValueEntity(767.901f, 20130821));
        dv.add(new DateValueEntity(768.2333f, 20130822));
        dv.add(new DateValueEntity(769.7356f, 20130823));
        dv.add(new DateValueEntity(772.7566f, 20130826));
        dv.add(new DateValueEntity(771.9353f, 20130827));
        dv.add(new DateValueEntity(772.5748f, 20130828));
        dv.add(new DateValueEntity(774.17f, 20130829));
        dv.add(new DateValueEntity(776.6239f, 20130830));
        dv.add(new DateValueEntity(779.4005f, 20130902));
        dv.add(new DateValueEntity(782.8205f, 20130903));
        dv.add(new DateValueEntity(787.7852f, 20130904));
        dv.add(new DateValueEntity(795.1398f, 20130905));
        dv.add(new DateValueEntity(798.0329f, 20130906));
        dv.add(new DateValueEntity(777.0803f, 20130909));
        dv.add(new DateValueEntity(745.4303f, 20130910));
        dv.add(new DateValueEntity(733.794f, 20130911));
        dv.add(new DateValueEntity(713.0938f, 20130912));
        dv.add(new DateValueEntity(709.4212f, 20130913));
        dv.add(new DateValueEntity(715.0446f, 20130916));
        dv.add(new DateValueEntity(727.5064f, 20130917));
        dv.add(new DateValueEntity(742.578f, 20130918));
        dv.add(new DateValueEntity(759.8558f, 20130923));
        dv.add(new DateValueEntity(781.4722f, 20130924));
        dv.add(new DateValueEntity(799.6322f, 20130925));
        dv.add(new DateValueEntity(813.7519f, 20130926));
        dv.add(new DateValueEntity(828.4345f, 20130927));
        dv.add(new DateValueEntity(844.6599f, 20130930));
        dv.add(new DateValueEntity(861.8906f, 20131008));
        dv.add(new DateValueEntity(881.4863f, 20131009));
        dv.add(new DateValueEntity(897.0036f, 20131010));
        dv.add(new DateValueEntity(918.4781f, 20131011));
        dv.add(new DateValueEntity(940.6985f, 20131014));
        dv.add(new DateValueEntity(951.0224f, 20131015));
        dv.add(new DateValueEntity(942.7723f, 20131016));
        dv.add(new DateValueEntity(932.7551f, 20131017));
        dv.add(new DateValueEntity(924.7807f, 20131018));
        dv.add(new DateValueEntity(936.6127f, 20131021));
        dv.add(new DateValueEntity(945.5508f, 20131022));
        dv.add(new DateValueEntity(952.1615f, 20131023));
        dv.add(new DateValueEntity(950.4466f, 20131024));
        dv.add(new DateValueEntity(953.2289f, 20131025));
        dv.add(new DateValueEntity(963.9264f, 20131028));
        dv.add(new DateValueEntity(968.6712f, 20131029));
        dv.add(new DateValueEntity(972.3124f, 20131030));
        dv.add(new DateValueEntity(972.3439f, 20131031));
        dv.add(new DateValueEntity(971.8104f, 20131101));
        dv.add(new DateValueEntity(972.5886f, 20131104));

        // for (int i = dv.size(); i > 0; i--) {
        // this.dv1.add(dv.get(i - 1));
        // }

        this.dv1.addAll(dv);
    }

    protected void initDV2() {
        List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

        this.dv2 = new ArrayList<DateValueEntity>();
        dv.add(new DateValueEntity(1059.5943f, 20130424));
        dv.add(new DateValueEntity(1046.7757f, 20130425));
        dv.add(new DateValueEntity(1026.9364f, 20130426));
        dv.add(new DateValueEntity(1026.2614f, 20130502));
        dv.add(new DateValueEntity(1024.6608f, 20130503));
        dv.add(new DateValueEntity(1025.8368f, 20130506));
        dv.add(new DateValueEntity(1026.1839f, 20130507));
        dv.add(new DateValueEntity(1026.0624f, 20130508));
        dv.add(new DateValueEntity(1026.3241f, 20130509));
        dv.add(new DateValueEntity(1026.2162f, 20130510));
        dv.add(new DateValueEntity(1026.4004f, 20130513));
        dv.add(new DateValueEntity(1025.9841f, 20130514));
        dv.add(new DateValueEntity(1026.3068f, 20130515));
        dv.add(new DateValueEntity(1028.6774f, 20130516));
        dv.add(new DateValueEntity(1031.737f, 20130517));
        dv.add(new DateValueEntity(1035.6149f, 20130520));
        dv.add(new DateValueEntity(1036.5883f, 20130521));
        dv.add(new DateValueEntity(1040.2333f, 20130522));
        dv.add(new DateValueEntity(1039.392f, 20130523));
        dv.add(new DateValueEntity(1039.9075f, 20130524));
        dv.add(new DateValueEntity(1042.3092f, 20130527));
        dv.add(new DateValueEntity(1049.7709f, 20130528));
        dv.add(new DateValueEntity(1054.7764f, 20130529));
        dv.add(new DateValueEntity(1058.339f, 20130530));
        dv.add(new DateValueEntity(1061.3387f, 20130531));
        dv.add(new DateValueEntity(1063.1569f, 20130603));
        dv.add(new DateValueEntity(1065.8587f, 20130604));
        dv.add(new DateValueEntity(1069.2279f, 20130605));
        dv.add(new DateValueEntity(1074.9237f, 20130606));
        dv.add(new DateValueEntity(1080.7036f, 20130607));
        dv.add(new DateValueEntity(1090.4335f, 20130613));
        dv.add(new DateValueEntity(1097.14f, 20130614));
        dv.add(new DateValueEntity(1101.7853f, 20130617));
        dv.add(new DateValueEntity(1102.9121f, 20130618));
        dv.add(new DateValueEntity(1103.4638f, 20130619));
        dv.add(new DateValueEntity(1105.56f, 20130620));
        dv.add(new DateValueEntity(1106.7701f, 20130621));
        dv.add(new DateValueEntity(1115.766f, 20130624));
        dv.add(new DateValueEntity(1116.7134f, 20130625));
        dv.add(new DateValueEntity(1113.479f, 20130626));
        dv.add(new DateValueEntity(1104.3563f, 20130627));
        dv.add(new DateValueEntity(1084.9122f, 20130628));
        dv.add(new DateValueEntity(1063.1488f, 20130701));
        dv.add(new DateValueEntity(1036.8327f, 20130702));
        dv.add(new DateValueEntity(1007.5432f, 20130703));
        dv.add(new DateValueEntity(989.9715f, 20130704));
        dv.add(new DateValueEntity(971.9491f, 20130705));
        dv.add(new DateValueEntity(953.6389f, 20130708));
        dv.add(new DateValueEntity(937.1937f, 20130709));
        dv.add(new DateValueEntity(920.2712f, 20130710));
        dv.add(new DateValueEntity(917.1337f, 20130711));
        dv.add(new DateValueEntity(908.4014f, 20130712));
        dv.add(new DateValueEntity(899.9954f, 20130715));
        dv.add(new DateValueEntity(888.733f, 20130716));
        dv.add(new DateValueEntity(880.0376f, 20130717));
        dv.add(new DateValueEntity(877.9082f, 20130718));
        dv.add(new DateValueEntity(876.6321f, 20130719));
        dv.add(new DateValueEntity(872.6431f, 20130722));
        dv.add(new DateValueEntity(871.7868f, 20130723));
        dv.add(new DateValueEntity(870.243f, 20130724));
        dv.add(new DateValueEntity(869.5485f, 20130725));
        dv.add(new DateValueEntity(868.8681f, 20130726));
        dv.add(new DateValueEntity(870.3774f, 20130729));
        dv.add(new DateValueEntity(870.6704f, 20130730));
        dv.add(new DateValueEntity(871.0206f, 20130731));
        dv.add(new DateValueEntity(870.7473f, 20130801));
        dv.add(new DateValueEntity(870.4874f, 20130802));
        dv.add(new DateValueEntity(870.4691f, 20130805));
        dv.add(new DateValueEntity(870.3846f, 20130806));
        dv.add(new DateValueEntity(870.1628f, 20130807));
        dv.add(new DateValueEntity(855.2711f, 20130808));
        dv.add(new DateValueEntity(849.188f, 20130809));
        dv.add(new DateValueEntity(843.2797f, 20130812));
        dv.add(new DateValueEntity(839.8539f, 20130813));
        dv.add(new DateValueEntity(836.363f, 20130814));
        dv.add(new DateValueEntity(836.6701f, 20130815));
        dv.add(new DateValueEntity(840.073f, 20130816));
        dv.add(new DateValueEntity(846.1634f, 20130819));
        dv.add(new DateValueEntity(852.3176f, 20130820));
        dv.add(new DateValueEntity(856.8989f, 20130821));
        dv.add(new DateValueEntity(860.7666f, 20130822));
        dv.add(new DateValueEntity(863.7643f, 20130823));
        dv.add(new DateValueEntity(870.7433f, 20130826));
        dv.add(new DateValueEntity(882.6646f, 20130827));
        dv.add(new DateValueEntity(893.4251f, 20130828));
        dv.add(new DateValueEntity(901.1299f, 20130829));
        dv.add(new DateValueEntity(908.776f, 20130830));
        dv.add(new DateValueEntity(915.0994f, 20130902));
        dv.add(new DateValueEntity(922.2794f, 20130903));
        dv.add(new DateValueEntity(928.6147f, 20130904));
        dv.add(new DateValueEntity(932.6601f, 20130905));
        dv.add(new DateValueEntity(945.367f, 20130906));
        dv.add(new DateValueEntity(988.5196f, 20130909));
        dv.add(new DateValueEntity(1049.9696f, 20130910));
        dv.add(new DateValueEntity(1091.2059f, 20130911));
        dv.add(new DateValueEntity(1152.2061f, 20130912));
        dv.add(new DateValueEntity(1191.8787f, 20130913));
        dv.add(new DateValueEntity(1216.6553f, 20130916));
        dv.add(new DateValueEntity(1227.3935f, 20130917));
        dv.add(new DateValueEntity(1237.8219f, 20130918));
        dv.add(new DateValueEntity(1247.9441f, 20130923));
        dv.add(new DateValueEntity(1250.2277f, 20130924));
        dv.add(new DateValueEntity(1252.4677f, 20130925));
        dv.add(new DateValueEntity(1250.548f, 20130926));
        dv.add(new DateValueEntity(1248.2654f, 20130927));
        dv.add(new DateValueEntity(1243.74f, 20130930));
        dv.add(new DateValueEntity(1238.9093f, 20131008));
        dv.add(new DateValueEntity(1232.3136f, 20131009));
        dv.add(new DateValueEntity(1225.6963f, 20131010));
        dv.add(new DateValueEntity(1218.3218f, 20131011));
        dv.add(new DateValueEntity(1207.7014f, 20131014));
        dv.add(new DateValueEntity(1202.7775f, 20131015));
        dv.add(new DateValueEntity(1204.8276f, 20131016));
        dv.add(new DateValueEntity(1199.0448f, 20131017));
        dv.add(new DateValueEntity(1193.1192f, 20131018));
        dv.add(new DateValueEntity(1159.9872f, 20131021));
        dv.add(new DateValueEntity(1131.2491f, 20131022));
        dv.add(new DateValueEntity(1109.9384f, 20131023));
        dv.add(new DateValueEntity(1103.1533f, 20131024));
        dv.add(new DateValueEntity(1091.071f, 20131025));
        dv.add(new DateValueEntity(1070.0735f, 20131028));
        dv.add(new DateValueEntity(1062.0287f, 20131029));
        dv.add(new DateValueEntity(1056.4875f, 20131030));
        dv.add(new DateValueEntity(1058.056f, 20131031));
        dv.add(new DateValueEntity(1060.3895f, 20131101));
        dv.add(new DateValueEntity(1061.7113f, 20131104));

        // for (int i = dv.size(); i > 0; i--) {
        // this.dv2.add(dv.get(i - 1));
        // }

        this.dv2.addAll(dv);
    }

}
