

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
		dataset.setValue(5000, "����","Corejava");
		dataset.setValue(3000, "�Ϻ�","Corejava");
		dataset.setValue(2000, "����","Corejava");
		
		dataset.setValue(10000, "����","JavaWeb");
		dataset.setValue(6000, "�Ϻ�","JavaWeb");
		dataset.setValue(4000, "����","JavaWeb");
		
		dataset.setValue(15000, "����","����struts");
		dataset.setValue(5000, "�Ϻ�","����struts");
		dataset.setValue(10000, "����","����struts");
		
		dataset.setValue(20000, "����","��ͨJSF");
		dataset.setValue(10000, "�Ϻ�","��ͨJSF");
		dataset.setValue(10000, "����","��ͨJSF");

		return dataset;
	}
	
	/**
	 * step1:��֯���ݼ�
	 * @param list
	 * @return
	 */
	
	public DefaultCategoryDataset createDataset(HashMap<Object, Object> hashMap){
		
		List<HashMap<Object, Object>> list = null;
		//����list���б���
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	//���ݸ�ʽ
    	//dataset.setValue(5000, "����","Corejava");
    	//dataset.setValue(value, rowKey, columnKey);
    	//��rowKeyΪ��׼
    	for (int i = 0; i < list.size(); i++) {
    		
			dataset.setValue(Integer.valueOf(list.get(i).get("value").toString()), list.get(i).get("rowKey").toString(), list.get(i).get("columnKey").toString());
		}
    	    	
    	return dataset;
	}
	
	/**
	 * step2:����ͼ��
	 * @param dataset
	 * @return
	 */
	public static JFreeChart createChart(CategoryDataset dataset) {
		//JFreeChart chart = ChartFactory.createBarChart3D(	//3D��״ͼ
		//JFreeChart chart = ChartFactory.createLineChart3D(  //3D����ͼ
		JFreeChart chart = ChartFactory.createLineChart(
				"1ԭ��ͼ������ͳ��", //ͼ��ı���
				"1ͼ����",  //Ŀ¼�����ʾ��ǩ 
				"1����",   //��ֵ�����ʾ��ǩ
				dataset, //���ݼ�
				PlotOrientation.VERTICAL,  //ͼ��ʽ��V��ֱ;Hˮƽ 
				true, // �Ƿ���ʾͼ��
				false, // �Ƿ���ʾ������ʾ
				false // �Ƿ�����URL
				);
		
		//===============Ϊ�˷�ֹ�������룺������������
		chart.setTitle(new TextTitle("ԭ��ͼ������ͳ��", new Font("����", Font.ITALIC, 22)));
	
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
		//renderer.setMaximumBarWidth(0.05); 
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
	 * step3: ���ͼ��Swing Frame
	 * @param chart
	 */
	public static void drawToFrame(JFreeChart chart){
		//���ͼ��Swing Frame
		ChartFrame frame = new ChartFrame("ԭ��ͼ������ͳ��", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * step3: ���ͼ��ָ���Ĵ���
	 * @param destPath
	 * @param chart
	 */
	public static void drawToOutputStream(String destPath, JFreeChart chart) {
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

	
    public static void main1(String[] args)
    {
    		
//    	// step1:�������ݼ�����
//    			CategoryDataset dataset = createDataSet2();
//    		
//    			// step2:������ͼ
//    			JFreeChart chart = createChart(dataset);
//    		
//    			// step3: ���ͼ��Swing����
//    			//drawToFrame(chart);
//    		
//    			// step3: ���ͼ������
//    			drawToOutputStream("D://testcharts//mybook-bar.png", chart);
    	    	    	
        DefaultPieDataset dpd=new DefaultPieDataset(); //����һ��Ĭ�ϵı�ͼ
        dpd.setValue("������Ա", 25);  //��������
        dpd.setValue("�г���Ա", 25);
        dpd.setValue("������Ա", 45);
        dpd.setValue("������Ա", 10);
        
        JFreeChart chart=ChartFactory.createPieChart3D("ĳ��˾��Ա��֯����ͼ",dpd,true,true,false); 
        //���Բ�����API�ĵ�,��һ�������Ǳ��⣬�ڶ���������һ�����ݼ���������������ʾ�Ƿ���ʾLegend�����ĸ�������ʾ�Ƿ���ʾ��ʾ�������������ʾͼ���Ƿ����URL
        
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
		
		chart.getTitle().setFont(new Font("����", Font.BOLD,12));
	
		 PiePlot plot = (PiePlot) chart.getPlot();  
		
		 plot.setLabelFont(new Font("����", Font.PLAIN, 12));  
		 
        ChartFrame chartFrame=new ChartFrame("ĳ��˾��Ա��֯����ͼ",chart); 
        //chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
        chartFrame.pack(); //�Ժ��ʵĴ�Сչ��ͼ��
        chartFrame.setVisible(true);//ͼ���Ƿ�ɼ�
        
    }
    
    public static void main(String args[]){
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(5000, "����","Corejava");
		dataset.setValue(3000, "�Ϻ�","Corejava");
		dataset.setValue(2000, "����","Corejava");
		
		dataset.setValue(10000, "����","JavaWeb");
		dataset.setValue(6000, "�Ϻ�","JavaWeb");
		dataset.setValue(4000, "����","JavaWeb");
		
		dataset.setValue(15000, "����","����struts");
		dataset.setValue(5000, "�Ϻ�","����struts");
		dataset.setValue(10000, "����","����struts");
		
		dataset.setValue(20000, "����","��ͨJSF");
		dataset.setValue(10000, "�Ϻ�","��ͨJSF");
		dataset.setValue(10000, "����","��ͨJSF");
		
		JFreeChart chart = ChartFactory.createLineChart(
				"1ԭ��ͼ������ͳ��", //ͼ��ı���
				"1ͼ����",  //Ŀ¼�����ʾ��ǩ 
				"1����",   //��ֵ�����ʾ��ǩ
				dataset, //���ݼ�
				PlotOrientation.VERTICAL,  //ͼ��ʽ��V��ֱ;Hˮƽ 
				true, // �Ƿ���ʾͼ��
				false, // �Ƿ���ʾ������ʾ
				false // �Ƿ�����URL
				);
		ChartFrame frame = new ChartFrame("ԭ��ͼ������ͳ��", chart);
		frame.pack();
		frame.setVisible(true);
    }
}