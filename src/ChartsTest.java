

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ChartsTest
{

    //test
    public static CategoryDataset createDataSet2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(5000, "北京","Corejava");
        dataset.setValue(3000, "上海","Corejava");
        dataset.setValue(2000, "广州","Corejava");
        
        dataset.setValue(10000, "北京","JavaWeb");
        dataset.setValue(6000, "上海","JavaWeb");
        dataset.setValue(4000, "广州","JavaWeb");
        
        dataset.setValue(15000, "北京","易用struts");
        dataset.setValue(5000, "上海","易用struts");
        dataset.setValue(10000, "广州","易用struts");
        
        dataset.setValue(20000, "北京","精通JSF");
        dataset.setValue(10000, "上海","精通JSF");
        dataset.setValue(10000, "广州","精通JSF");

        return dataset;
    }
    
    /**
     * step1:组织数据集
     * @param list
     * @return
     */
    
    public DefaultCategoryDataset createDataset(HashMap<Object, Object> hashMap){
        
        List<HashMap<Object, Object>> list = null;
        //传入list进行遍历
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //数据格式
        //dataset.setValue(5000, "北京","Corejava");
        //dataset.setValue(value, rowKey, columnKey);
        //以rowKey为标准
        for (int i = 0; i < list.size(); i++) {
            
            dataset.setValue(Integer.valueOf(list.get(i).get("value").toString()), list.get(i).get("rowKey").toString(), list.get(i).get("columnKey").toString());
        }
                
        return dataset;
    }
    
    /**
     * step2:创建图表
     * @param dataset
     * @return
     */
    public static JFreeChart createChart(CategoryDataset dataset) {
        //JFreeChart chart = ChartFactory.createBarChart3D( //3D柱状图
        //JFreeChart chart = ChartFactory.createLineChart3D(  //3D折线图
        JFreeChart chart = ChartFactory.createLineChart(
                "1原创图书销量统计", //图表的标题
                "1图书名",  //目录轴的显示标签 
                "1销量",   //数值轴的显示标签
                dataset, //数据集
                PlotOrientation.VERTICAL,  //图表方式：V垂直;H水平 
                true, // 是否显示图例
                false, // 是否显示工具提示
                false // 是否生成URL
                );
        
        //===============为了防止中文乱码：必须设置字体
        chart.setTitle(new TextTitle("原创图书销量统计", new Font("黑体", Font.ITALIC, 22)));
    
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
        //renderer.setMaximumBarWidth(0.05); 
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
     * step3: 输出图表到Swing Frame
     * @param chart
     */
    public static void drawToFrame(JFreeChart chart){
        //输出图表到Swing Frame
        ChartFrame frame = new ChartFrame("原创图书销量统计", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * step3: 输出图表到指定的磁盘
     * @param destPath
     * @param chart
     */
    public static void drawToOutputStream(String destPath, JFreeChart chart) {
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

    
    public static void main1(String[] args)
    {
            
//      // step1:创建数据集对象
//              CategoryDataset dataset = createDataSet2();
//          
//              // step2:创建饼图
//              JFreeChart chart = createChart(dataset);
//          
//              // step3: 输出图表到Swing窗口
//              //drawToFrame(chart);
//          
//              // step3: 输出图表到磁盘
//              drawToOutputStream("D://testcharts//mybook-bar.png", chart);
                        
        DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
        dpd.setValue("管理人员", 25);  //输入数据
        dpd.setValue("市场人员", 25);
        dpd.setValue("开发人员", 45);
        dpd.setValue("其他人员", 10);
        
        JFreeChart chart=ChartFactory.createPieChart3D("某公司人员组织数据图",dpd,true,true,false); 
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        
        chart.getTitle().setFont(new Font("宋体", Font.BOLD,12));
    
         PiePlot plot = (PiePlot) chart.getPlot();  
        
         plot.setLabelFont(new Font("隶书", Font.PLAIN, 12));  
         
        ChartFrame chartFrame=new ChartFrame("某公司人员组织数据图",chart); 
        //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
        
    }
    
    public static void main(String args[]){
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(5000, "北京","Corejava");
        dataset.setValue(3000, "上海","Corejava");
        dataset.setValue(2000, "广州","Corejava");
        
        dataset.setValue(10000, "北京","JavaWeb");
        dataset.setValue(6000, "上海","JavaWeb");
        dataset.setValue(4000, "广州","JavaWeb");
        
        dataset.setValue(15000, "北京","易用struts");
        dataset.setValue(5000, "上海","易用struts");
        dataset.setValue(10000, "广州","易用struts");
        
        dataset.setValue(20000, "北京","精通JSF");
        dataset.setValue(10000, "上海","精通JSF");
        dataset.setValue(10000, "广州","精通JSF");
        
        JFreeChart chart = ChartFactory.createLineChart(
                "1原创图书销量统计", //图表的标题
                "1图书名",  //目录轴的显示标签 
                "1销量",   //数值轴的显示标签
                dataset, //数据集
                PlotOrientation.VERTICAL,  //图表方式：V垂直;H水平 
                true, // 是否显示图例
                false, // 是否显示工具提示
                false // 是否生成URL
                );
        ChartFrame frame = new ChartFrame("原创图书销量统计", chart);
        frame.pack();
        frame.setVisible(true);
    }
}