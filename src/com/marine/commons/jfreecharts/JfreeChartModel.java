package com.marine.commons.jfreecharts;

import java.util.HashMap;
import java.util.List;

public class JfreeChartModel  {

		public List<HashMap<Object, Object>> list;
		public String title;
		public String XLableName;
		public String YLableName;
		public String destPath; //D://testcharts//mybook-bar.png
		
		/**
		 * �������ݼ�
		 * @param list
		 */
		public void setList(List<HashMap<Object, Object>> list) {
			this.list = list;
		}

		/**
		 * ����ͼ��ı���
		 * @param title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * ����X������
		 * @param xLableName
		 */
		public void setXLableName(String xLableName) {
			XLableName = xLableName;
		}

		/**
		 * ����Y������
		 * @param yLableName
		 */
		public void setYLableName(String yLableName) {
			YLableName = yLableName;
		}

		/**
		 * ����ͼƬ����Ŀ��·��
		 * @param destPath
		 */
		public void setDestPath(String destPath) {
			this.destPath = destPath;
		}
	
}
