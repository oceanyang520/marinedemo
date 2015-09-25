package main.java.com.marine.commons.jfreecharts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.junit.Test;

public class JfreeChartsUtil extends JfreeChartModel {

    /**
     * 生成柱状图表
     * @param hashMap
     * @return JFreeChart
     */
    public JFreeChart createBarChart(){
        
        //--------- :组织数据集开始---------------
        //      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //      dataset.setValue(5000, "北京","Corejava");
        //      dataset.setValue(3000, "上海","Corejava");
        //      dataset.setValue(2000, "广州","Corejava");
        //      
        //      dataset.setValue(10000, "北京","JavaWeb");
        //      dataset.setValue(6000, "上海","JavaWeb");
        //      dataset.setValue(4000, "广州","JavaWeb");
        //      
        //      dataset.setValue(15000, "北京","易用struts");
        //      dataset.setValue(5000, "上海","易用struts");
        //      dataset.setValue(10000, "广州","易用struts");
        //      
        //      dataset.setValue(20000, "北京","精通JSF");
        //      dataset.setValue(10000, "上海","精通JSF");
        //      dataset.setValue(10000, "广州","精通JSF");
        //List<HashMap<Object, Object>> list = (List<HashMap<Object, Object>>)hashMap.get("chartList");
        //传入list进行遍历
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //数据格式
        //dataset.setValue(5000, "北京","Corejava");
        //dataset.setValue(value, rowKey, columnKey);
        //以rowKey为标准
        for (int i = 0; i < this.list.size(); i++) {
            
            dataset.setValue(Integer.valueOf(list.get(i).get("value").toString()), list.get(i).get("rowKey").toString(), list.get(i).get("columnKey").toString());
        }

        JFreeChart  chart = ChartFactory.createBarChart(    //柱状图
                    this.title, //图表的标题
                    this.XLableName,  //目录轴的显示标签 
                    this.YLableName,   //数值轴的显示标签
                    dataset, //数据集
                    PlotOrientation.VERTICAL,  //图表方式：V垂直;H水平 
                    true, // 是否显示图例
                    false, // 是否显示工具提示
                    false // 是否生成URL
        );
        
        //===============为了防止中文乱码：必须设置字体
        chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC, 22)));
    
        LegendTitle legend = chart.getLegend(); // 获取图例
        legend.setItemFont(new Font("宋体", Font.BOLD, 12)); //设置图例的字体，防止中文乱码
    
        CategoryPlot plot = (CategoryPlot) chart.getPlot(); // 获取柱图的Plot对象(实际图表)
        // 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
        plot.setBackgroundPaint(new Color(255, 255, 204));
        plot.setForegroundAlpha(0.65F); //设置前景色透明度
        
        // 设置横虚线可见
        plot.setRangeGridlinesVisible(true);
        // 虚线色彩
        plot.setRangeGridlinePaint(Color.gray);
        
        CategoryAxis h = plot.getDomainAxis(); //获取x轴
        h.setMaximumCategoryLabelWidthRatio(1.0f);// 横轴上的 Lable 是否完整显示
        h.setLabelFont(new Font("宋体", Font.BOLD, 12));//设置字体，防止中文乱码
        h.setTickLabelFont(new Font("宋体", Font.BOLD, 12));// 轴数值 
        //h.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//45度倾斜
        
        plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 12)); //Y轴设置字体，防止中文乱码
        
        //柱图的呈现器
        BarRenderer renderer = new BarRenderer(); 
        // 设置柱子宽度 
        renderer.setMaximumBarWidth(0.05); 
        // 设置柱子高度 
        //renderer.setMinimumBarLength(0.2); 
        // 设置柱子边框颜色 
        renderer.setBaseOutlinePaint(Color.BLACK); 
        // 设置柱子边框可见 
        renderer.setDrawBarOutline(true); 
        //设置每个柱的颜色 
        renderer.setSeriesPaint(0, Color.BLUE); 
        renderer.setSeriesPaint(1, Color.GREEN); 
        renderer.setSeriesPaint(2, Color.RED); 
        //设置每个地区所包含的平行柱的之间距离 
        renderer.setItemMargin(0.05); 
        // 显示每个柱的数值，并修改该数值的字体属性 
        renderer.setIncludeBaseInRange(true); 
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        renderer.setBaseItemLabelsVisible(true); 
        // 设置柱的透明度 
        plot.setForegroundAlpha(1.0f); 
        //给柱图添加呈现器
        plot.setRenderer(renderer); 
            
        // 没有数据的时候显示的内容
        plot.setNoDataMessage("找不到可用数据...");
        
        return chart;
    }
    
    /**
     * 生成折线图表
     * @param hashMap
     * @return JFreeChart
     */
    public  JFreeChart createLineChart(){
 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println(list);
        for (int i = 0; i < this.list.size(); i++) {
            
            dataset.setValue(Integer.valueOf(list.get(i).get("value").toString()), list.get(i).get("rowKey").toString(), list.get(i).get("columnKey").toString());
        }
                  
        JFreeChart chart  = ChartFactory.createLineChart(
                title, //图表的标题
                XLableName,  //目录轴的显示标签 
                YLableName,   //数值轴的显示标签
                dataset, //数据集
                PlotOrientation.VERTICAL,  //图表方式：V垂直;H水平 
                true, // 是否显示图例
                false, // 是否显示工具提示
                false // 是否生成URL
            );
        //===============为了防止中文乱码：必须设置字体
        chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC, 22)));
    
        LegendTitle legend = chart.getLegend(); // 获取图例
        legend.setItemFont(new Font("宋体", Font.BOLD, 12)); //设置图例的字体，防止中文乱码
    
        CategoryPlot plot = (CategoryPlot) chart.getPlot(); // 获取折线图的Plot对象(实际图表)
        // 设置折线图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
        plot.setBackgroundPaint(new Color(255, 255, 204));
        plot.setForegroundAlpha(0.65F); //设置前景色透明度
        
        // 设置横虚线可见
        plot.setRangeGridlinesVisible(true);
        // 虚线色彩
        plot.setRangeGridlinePaint(Color.gray);
        
        CategoryAxis h = plot.getDomainAxis(); //获取x轴
        h.setMaximumCategoryLabelWidthRatio(1.0f);// 横轴上的 Lable 是否完整显示
        h.setLabelFont(new Font("宋体", Font.BOLD, 12));//设置字体，防止中文乱码
        h.setTickLabelFont(new Font("宋体", Font.BOLD, 12));// 轴数值 
        //h.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//45度倾斜
        
        plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 12)); //Y轴设置字体，防止中文乱码
            
        // 没有数据的时候显示的内容
        plot.setNoDataMessage("找不到可用数据...");
        
        return chart;
    }
        
    /**
     * 创建饼图
     * @param dataset
     * @return
     */
    public  JFreeChart createPieChart(){
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        //数据格式
        //dataset.setValue("java程序设计语言", 10000);
        //dataset.setValue(key, value)
        //以rowKey为标准
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            
            dataset.setValue(list.get(i).get("pieKey").toString(), Integer.valueOf(list.get(i).get("pieValue").toString()));
        }
          
        JFreeChart  chart = ChartFactory.createPieChart(
                title, // 图表标题
                dataset, // 数据集
                true, // 是否显示图例
                true, // 是否显示工具提示
                true // 是否生成URL
                );
            
        //设置标题字体==为了防止中文乱码：必须设置字体
        chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC, 22)));
        //设置图例的字体==为了防止中文乱码：必须设置字体
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 12)); 
        // 获取饼图的Plot对象(实际图表)
        PiePlot plot = (PiePlot) chart.getPlot(); 
        //图形边框颜色   
        plot.setBaseSectionOutlinePaint(Color.GRAY);   
        //图形边框粗细   
        plot.setBaseSectionOutlineStroke(new BasicStroke(0.0f)); 
        //设置饼状图的绘制方向，可以按顺时针方向绘制，也可以按逆时针方向绘制   
        plot.setDirection(Rotation.ANTICLOCKWISE);   
        //设置绘制角度(图形旋转角度)   
        plot.setStartAngle(70);   
        //设置突出显示的数据块   
        plot.setExplodePercent("One", 0.1D);  
        //设置背景色透明度
        plot.setBackgroundAlpha(0.7F); 
        // 设置前景色透明度
        plot.setForegroundAlpha(0.65F); 
        //设置区块标签的字体==为了防止中文乱码：必须设置字体
        plot.setLabelFont(new Font("隶书", Font.PLAIN, 12)); 
        // 扇区分离显示,对3D图不起效
        //plot.setExplodePercent(dataset.getKey(3), 0.1D);
        // 图例显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}:{1}/r/n({2})", NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        // 图例显示百分比
        // plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
        // 指定显示的饼图为：圆形(true) 还是椭圆形(false)
        plot.setCircular(false);
        // 没有数据的时候显示的内容
        plot.setNoDataMessage("找不到可用数据...");    
        
        //设置鼠标悬停提示
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        //设置热点链接
        plot.setURLGenerator(new StandardPieURLGenerator("detail.jsp"));
        
        return chart;
    }
    
    /**
     *  输出图表到Swing Frame
     * @param chart
     */
    public  void drawToFrame(JFreeChart chart){
        //输出图表到Swing Frame
        ChartFrame frame = new ChartFrame("原创图书销量统计", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     *  输出图表到指定的磁盘
     * @param destPath
     * @param chart
     */
    public  void drawToOutputStream(String destPath, JFreeChart chart) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destPath);
            // ChartUtilities.writeChartAsJPEG(
            ChartUtilities.writeChartAsPNG(fos, // 指定目标输出流
                    chart, // 图表对象
                    600, // 宽
                    500, // 高
                    null); // ChartRenderingInfo信息
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 创建柱状图图表图片    
     * @param hashMap中有：
     * @param chartList
     * @param String title 
     * @param String XLableName
     * @param String YLableName
     * @param String isPieChart3D
     * @param String destPath
     */
     public  void createBarPic ()
        {
        JFreeChart chart = createBarChart();
        
        //drawToOutputStream("D://testcharts//mybook-bar.png", chart);
        //drawToOutputStream(hashMap.get("destPath").toString(), chart);
        drawToFrame(chart);
        }       
     
     
     /**
     * 创建饼图图表图片 
     * @param hashMap中有：
     * @param chartList
     * @param String title 
     * @param String isBarChart
     * @param String destPath
     */
     public  void createPiePic ()
        {
         //创建柱状图
        JFreeChart chart = createPieChart();
        drawToFrame(chart);
        //drawToOutputStream("D://testcharts//mybook-bar.png", chart);
        //drawToOutputStream(hashMap.get("destPath").toString(), chart);
                    
        }
     
     /**
     * 创建折线图图表图片    
     * @param hashMap中有：
     * @param chartList
     * @param String title 
     * @param String isBarChart
     * @param String destPath
     */
     public  void createLinePic ()
        {
         //创建柱状图
        JFreeChart chart = createLineChart();
        drawToFrame(chart);
        //drawToOutputStream("D://testcharts//mybook-bar.png", chart);
        //drawToOutputStream(hashMap.get("destPath").toString(), chart);
     }
     
//   public List findBySqls(){
//       
//       List tempList = new ArrayList();
//       
//       //tempList =  this.findSqlList("select goods_code as aaa from goods");
//       tempList = this.executeSqlList("select goods_code as aaa from goods");
//       return tempList;
//   }
     
      public static  void main(String args[]){
          
          JfreeChartsUtil jc = new JfreeChartsUtil();

          HashMap<Object,Object> hashMap = new HashMap<Object, Object>();
          List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object,Object>>();
          HashMap<Object, Object> chartMap = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap1 = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap2 = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap3 = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap4 = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap5 = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap6 = new HashMap<Object, Object>();
          HashMap<Object, Object> chartMap7 = new HashMap<Object, Object>();
          chartMap.put("value", 1000);
          chartMap.put("rowKey", "北京");
          chartMap.put("columnKey", "coreJava");
          
          chartMap1.put("value", 1500);
          chartMap1.put("rowKey", "上海");
          chartMap1.put("columnKey", "coreJava");
          
          chartMap2.put("value", 2000);
          chartMap2.put("rowKey", "杭州");
          chartMap2.put("columnKey", "coreJava");
          
          chartMap3.put("value", 2500);
          chartMap3.put("rowKey", "襄阳");
          chartMap3.put("columnKey", "coreJava");
          
          chartMap4.put("value", 3000);
          chartMap4.put("rowKey", "北京");
          chartMap4.put("columnKey", "Android");
          
          chartMap5.put("value", 1533);
          chartMap5.put("rowKey", "上海");
          chartMap5.put("columnKey", "Android");
          
          chartMap6.put("value", 2300);
          chartMap6.put("rowKey", "杭州");
          chartMap6.put("columnKey", "Android");
          
          chartMap7.put("value", 2220);
          chartMap7.put("rowKey", "襄阳");
          chartMap7.put("columnKey", "Android");

          list.add(chartMap);
          list.add(chartMap1);
          list.add(chartMap2);
          list.add(chartMap3);
          list.add(chartMap4);
          list.add(chartMap5);
          list.add(chartMap6);
          list.add(chartMap7);

          JfreeChartsUtil jcu = new JfreeChartsUtil();
         // jcu.setList(list);
          jcu.setTitle("测试标题");
          jcu.setXLableName("x轴");
          jcu.setYLableName("y轴");
          //jcu.setDestPath("D://testcharts//mybook-bar.png");
        //  jcu.createBarPic();
          //jcu.createLinePic();
          List<HashMap<Object, Object>> listPie = new ArrayList<HashMap<Object,Object>>();
          HashMap<Object, Object> pieMap = new HashMap<Object, Object>();
          pieMap.put("pieKey", "中国");
          pieMap.put("pieValue", 299);
          HashMap<Object, Object> pieMap1 = new HashMap<Object, Object>();
          pieMap1.put("pieKey", "中国1");
          pieMap1.put("pieValue", 299);
          HashMap<Object, Object> pieMap2 = new HashMap<Object, Object>();
          pieMap2.put("pieKey", "中国2");
          pieMap2.put("pieValue", 299);
          HashMap<Object, Object> pieMap3 = new HashMap<Object, Object>();
          pieMap3.put("pieKey", "中国3");
          pieMap3.put("pieValue", 299);
          listPie.add(pieMap);
          listPie.add(pieMap1);
          listPie.add(pieMap2);
          jcu.setList(listPie);
          jcu.createPiePic();
          
      }

}