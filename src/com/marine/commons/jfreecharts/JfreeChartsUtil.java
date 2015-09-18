package com.marine.commons.jfreecharts;

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
	 * ������״ͼ��
	 * @param hashMap
	 * @return JFreeChart
	 */
	public JFreeChart createBarChart(){
		
		//--------- :��֯���ݼ���ʼ---------------
		//		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//		dataset.setValue(5000, "����","Corejava");
		//		dataset.setValue(3000, "�Ϻ�","Corejava");
		//		dataset.setValue(2000, "����","Corejava");
		//		
		//		dataset.setValue(10000, "����","JavaWeb");
		//		dataset.setValue(6000, "�Ϻ�","JavaWeb");
		//		dataset.setValue(4000, "����","JavaWeb");
		//		
		//		dataset.setValue(15000, "����","����struts");
		//		dataset.setValue(5000, "�Ϻ�","����struts");
		//		dataset.setValue(10000, "����","����struts");
		//		
		//		dataset.setValue(20000, "����","��ͨJSF");
		//		dataset.setValue(10000, "�Ϻ�","��ͨJSF");
		//		dataset.setValue(10000, "����","��ͨJSF");
		//List<HashMap<Object, Object>> list = (List<HashMap<Object, Object>>)hashMap.get("chartList");
		//����list���б���
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	//���ݸ�ʽ
    	//dataset.setValue(5000, "����","Corejava");
    	//dataset.setValue(value, rowKey, columnKey);
    	//��rowKeyΪ��׼
    	for (int i = 0; i < this.list.size(); i++) {
    		
			dataset.setValue(Integer.valueOf(list.get(i).get("value").toString()), list.get(i).get("rowKey").toString(), list.get(i).get("columnKey").toString());
		}

    	JFreeChart 	chart = ChartFactory.createBarChart(	//��״ͼ
					this.title, //ͼ��ı���
					this.XLableName,  //Ŀ¼�����ʾ��ǩ 
					this.YLableName,   //��ֵ�����ʾ��ǩ
					dataset, //���ݼ�
					PlotOrientation.VERTICAL,  //ͼ��ʽ��V��ֱ;Hˮƽ 
					true, // �Ƿ���ʾͼ��
					false, // �Ƿ���ʾ������ʾ
					false // �Ƿ�����URL
    	);
    	
		//===============Ϊ�˷�ֹ�������룺������������
		chart.setTitle(new TextTitle(title, new Font("����", Font.ITALIC, 22)));
	
		LegendTitle legend = chart.getLegend(); // ��ȡͼ��
		legend.setItemFont(new Font("����", Font.BOLD, 12)); //����ͼ�������壬��ֹ��������
	
		CategoryPlot plot = (CategoryPlot) chart.getPlot(); // ��ȡ��ͼ��Plot����(ʵ��ͼ��)
		// ������ͼ����ɫ��ע�⣬ϵͳȡɫ��ʱ��Ҫʹ��16λ��ģʽ���鿴��ɫ���룬�����Ƚ�׼ȷ��
		plot.setBackgroundPaint(new Color(255, 255, 204));
		plot.setForegroundAlpha(0.65F); //����ǰ��ɫ͸����
		
		// ���ú����߿ɼ�
		plot.setRangeGridlinesVisible(true);
		// ����ɫ��
		plot.setRangeGridlinePaint(Color.gray);
		
		CategoryAxis h = plot.getDomainAxis(); //��ȡx��
		h.setMaximumCategoryLabelWidthRatio(1.0f);// �����ϵ� Lable �Ƿ�������ʾ
		h.setLabelFont(new Font("����", Font.BOLD, 12));//�������壬��ֹ��������
		h.setTickLabelFont(new Font("����", Font.BOLD, 12));// ����ֵ 
		//h.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//45����б
		
		plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 12)); //Y���������壬��ֹ��������
		
		//��ͼ�ĳ�����
		BarRenderer renderer = new BarRenderer(); 
		// �������ӿ�� 
		renderer.setMaximumBarWidth(0.05); 
		// �������Ӹ߶� 
		//renderer.setMinimumBarLength(0.2); 
		// �������ӱ߿���ɫ 
		renderer.setBaseOutlinePaint(Color.BLACK); 
		// �������ӱ߿�ɼ� 
		renderer.setDrawBarOutline(true); 
		//����ÿ��������ɫ 
		renderer.setSeriesPaint(0, Color.BLUE); 
		renderer.setSeriesPaint(1, Color.GREEN); 
		renderer.setSeriesPaint(2, Color.RED); 
		//����ÿ��������������ƽ������֮����� 
		renderer.setItemMargin(0.05); 
		// ��ʾÿ��������ֵ�����޸ĸ���ֵ���������� 
		renderer.setIncludeBaseInRange(true); 
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
		renderer.setBaseItemLabelsVisible(true); 
		// ��������͸���� 
		plot.setForegroundAlpha(1.0f); 
		//����ͼ��ӳ�����
		plot.setRenderer(renderer); 
			
		// û�����ݵ�ʱ����ʾ������
		plot.setNoDataMessage("�Ҳ�����������...");
		
		return chart;
	}
	
	/**
	 * ��������ͼ��
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
				title, //ͼ��ı���
				XLableName,  //Ŀ¼�����ʾ��ǩ 
				YLableName,   //��ֵ�����ʾ��ǩ
				dataset, //���ݼ�
				PlotOrientation.VERTICAL,  //ͼ��ʽ��V��ֱ;Hˮƽ 
				true, // �Ƿ���ʾͼ��
				false, // �Ƿ���ʾ������ʾ
				false // �Ƿ�����URL
			);
    	//===============Ϊ�˷�ֹ�������룺������������
		chart.setTitle(new TextTitle(title, new Font("����", Font.ITALIC, 22)));
	
		LegendTitle legend = chart.getLegend(); // ��ȡͼ��
		legend.setItemFont(new Font("����", Font.BOLD, 12)); //����ͼ�������壬��ֹ��������
	
		CategoryPlot plot = (CategoryPlot) chart.getPlot(); // ��ȡ����ͼ��Plot����(ʵ��ͼ��)
		// ��������ͼ����ɫ��ע�⣬ϵͳȡɫ��ʱ��Ҫʹ��16λ��ģʽ���鿴��ɫ���룬�����Ƚ�׼ȷ��
		plot.setBackgroundPaint(new Color(255, 255, 204));
		plot.setForegroundAlpha(0.65F); //����ǰ��ɫ͸����
		
		// ���ú����߿ɼ�
		plot.setRangeGridlinesVisible(true);
		// ����ɫ��
		plot.setRangeGridlinePaint(Color.gray);
		
		CategoryAxis h = plot.getDomainAxis(); //��ȡx��
		h.setMaximumCategoryLabelWidthRatio(1.0f);// �����ϵ� Lable �Ƿ�������ʾ
		h.setLabelFont(new Font("����", Font.BOLD, 12));//�������壬��ֹ��������
		h.setTickLabelFont(new Font("����", Font.BOLD, 12));// ����ֵ 
		//h.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//45����б
		
		plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 12)); //Y���������壬��ֹ��������
			
		// û�����ݵ�ʱ����ʾ������
		plot.setNoDataMessage("�Ҳ�����������...");
		
		return chart;
	}
		
	/**
	 * ������ͼ
	 * @param dataset
	 * @return
	 */
	public  JFreeChart createPieChart(){
		
		DefaultPieDataset dataset = new DefaultPieDataset();
    	//���ݸ�ʽ
    	//dataset.setValue("java�����������", 10000);
    	//dataset.setValue(key, value)
    	//��rowKeyΪ��׼
		System.out.println(list);
    	for (int i = 0; i < list.size(); i++) {
    		
  			dataset.setValue(list.get(i).get("pieKey").toString(), Integer.valueOf(list.get(i).get("pieValue").toString()));
		}
    	  
    	JFreeChart 	chart = ChartFactory.createPieChart(
    			title, // ͼ�����
				dataset, // ���ݼ�
				true, // �Ƿ���ʾͼ��
				true, // �Ƿ���ʾ������ʾ
				true // �Ƿ�����URL
				);
			
		//���ñ�������==Ϊ�˷�ֹ�������룺������������
		chart.setTitle(new TextTitle(title, new Font("����", Font.ITALIC, 22)));
		//����ͼ��������==Ϊ�˷�ֹ�������룺������������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 12)); 
		// ��ȡ��ͼ��Plot����(ʵ��ͼ��)
		PiePlot plot = (PiePlot) chart.getPlot(); 
		//ͼ�α߿���ɫ   
		plot.setBaseSectionOutlinePaint(Color.GRAY);   
		//ͼ�α߿��ϸ   
		plot.setBaseSectionOutlineStroke(new BasicStroke(0.0f)); 
		//���ñ�״ͼ�Ļ��Ʒ��򣬿��԰�˳ʱ�뷽����ƣ�Ҳ���԰���ʱ�뷽�����   
		plot.setDirection(Rotation.ANTICLOCKWISE);   
		//���û��ƽǶ�(ͼ����ת�Ƕ�)   
		plot.setStartAngle(70);   
		//����ͻ����ʾ�����ݿ�   
		plot.setExplodePercent("One", 0.1D);  
		//���ñ���ɫ͸����
		plot.setBackgroundAlpha(0.7F); 
		// ����ǰ��ɫ͸����
		plot.setForegroundAlpha(0.65F); 
		//���������ǩ������==Ϊ�˷�ֹ�������룺������������
		plot.setLabelFont(new Font("����", Font.PLAIN, 12)); 
		// ����������ʾ,��3Dͼ����Ч
		//plot.setExplodePercent(dataset.getKey(3), 0.1D);
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}/r/n({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// ͼ����ʾ�ٷֱ�
		// plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		// ָ����ʾ�ı�ͼΪ��Բ��(true) ������Բ��(false)
		plot.setCircular(false);
		// û�����ݵ�ʱ����ʾ������
		plot.setNoDataMessage("�Ҳ�����������...");    
		
		//���������ͣ��ʾ
		plot.setToolTipGenerator(new StandardPieToolTipGenerator());
		//�����ȵ�����
		plot.setURLGenerator(new StandardPieURLGenerator("detail.jsp"));
		
		return chart;
	}
	
	/**
	 *  ���ͼ��Swing Frame
	 * @param chart
	 */
	public  void drawToFrame(JFreeChart chart){
		//���ͼ��Swing Frame
		ChartFrame frame = new ChartFrame("ԭ��ͼ������ͳ��", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 *  ���ͼ��ָ���Ĵ���
	 * @param destPath
	 * @param chart
	 */
	public  void drawToOutputStream(String destPath, JFreeChart chart) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(destPath);
			// ChartUtilities.writeChartAsJPEG(
			ChartUtilities.writeChartAsPNG(fos, // ָ��Ŀ�������
					chart, // ͼ�����
					600, // ��
					500, // ��
					null); // ChartRenderingInfo��Ϣ
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
	 * ������״ͼͼ��ͼƬ	
	 * @param hashMap���У�
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
	 * ������ͼͼ��ͼƬ	
	 * @param hashMap���У�
	 * @param chartList
	 * @param String title 
	 * @param String isBarChart
	 * @param String destPath
	 */
	 public  void createPiePic ()
	    {
		 //������״ͼ
		JFreeChart chart = createPieChart();
		drawToFrame(chart);
		//drawToOutputStream("D://testcharts//mybook-bar.png", chart);
		//drawToOutputStream(hashMap.get("destPath").toString(), chart);
	    			
	    }
	 
	 /**
	 * ��������ͼͼ��ͼƬ	
	 * @param hashMap���У�
	 * @param chartList
	 * @param String title 
	 * @param String isBarChart
	 * @param String destPath
	 */
	 public  void createLinePic ()
	    {
		 //������״ͼ
		JFreeChart chart = createLineChart();
		drawToFrame(chart);
		//drawToOutputStream("D://testcharts//mybook-bar.png", chart);
		//drawToOutputStream(hashMap.get("destPath").toString(), chart);
     }
	 
//	 public List findBySqls(){
//		 
//		 List tempList = new ArrayList();
//		 
//		 //tempList =  this.findSqlList("select goods_code as aaa from goods");
//		 tempList = this.executeSqlList("select goods_code as aaa from goods");
//		 return tempList;
//	 }
	 
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
		  chartMap.put("rowKey", "����");
		  chartMap.put("columnKey", "coreJava");
		  
		  chartMap1.put("value", 1500);
		  chartMap1.put("rowKey", "�Ϻ�");
		  chartMap1.put("columnKey", "coreJava");
		  
		  chartMap2.put("value", 2000);
		  chartMap2.put("rowKey", "����");
		  chartMap2.put("columnKey", "coreJava");
		  
		  chartMap3.put("value", 2500);
		  chartMap3.put("rowKey", "����");
		  chartMap3.put("columnKey", "coreJava");
		  
		  chartMap4.put("value", 3000);
		  chartMap4.put("rowKey", "����");
		  chartMap4.put("columnKey", "Android");
		  
		  chartMap5.put("value", 1533);
		  chartMap5.put("rowKey", "�Ϻ�");
		  chartMap5.put("columnKey", "Android");
		  
		  chartMap6.put("value", 2300);
		  chartMap6.put("rowKey", "����");
		  chartMap6.put("columnKey", "Android");
		  
		  chartMap7.put("value", 2220);
		  chartMap7.put("rowKey", "����");
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
		  jcu.setTitle("���Ա���");
		  jcu.setXLableName("x��");
		  jcu.setYLableName("y��");
		  //jcu.setDestPath("D://testcharts//mybook-bar.png");
		//  jcu.createBarPic();
		  //jcu.createLinePic();
		  List<HashMap<Object, Object>> listPie = new ArrayList<HashMap<Object,Object>>();
		  HashMap<Object, Object> pieMap = new HashMap<Object, Object>();
		  pieMap.put("pieKey", "�й�");
		  pieMap.put("pieValue", 299);
		  HashMap<Object, Object> pieMap1 = new HashMap<Object, Object>();
		  pieMap1.put("pieKey", "�й�1");
		  pieMap1.put("pieValue", 299);
		  HashMap<Object, Object> pieMap2 = new HashMap<Object, Object>();
		  pieMap2.put("pieKey", "�й�2");
		  pieMap2.put("pieValue", 299);
		  HashMap<Object, Object> pieMap3 = new HashMap<Object, Object>();
		  pieMap3.put("pieKey", "�й�3");
		  pieMap3.put("pieValue", 299);
		  listPie.add(pieMap);
		  listPie.add(pieMap1);
		  listPie.add(pieMap2);
		  //listPie.add(pieMap3);
	
		  jcu.setList(listPie);
		  jcu.createPiePic();
		  
		  
	  }

}
