package main.java.com.marine.frame.commons.jfreecharts;

import java.util.HashMap;
import java.util.List;

public class JfreeChartModel  {

        public List<HashMap<Object, Object>> list;
        public String title;
        public String XLableName;
        public String YLableName;
        public String destPath; //D://testcharts//mybook-bar.png
        
        /**
         * 设置数据集
         * @param list
         */
        public void setList(List<HashMap<Object, Object>> list) {
            this.list = list;
        }

        /**
         * 设置图表的标题
         * @param title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * 设置X轴名称
         * @param xLableName
         */
        public void setXLableName(String xLableName) {
            XLableName = xLableName;
        }

        /**
         * 设置Y轴名称
         * @param yLableName
         */
        public void setYLableName(String yLableName) {
            YLableName = yLableName;
        }

        /**
         * 设置图片保存目的路径
         * @param destPath
         */
        public void setDestPath(String destPath) {
            this.destPath = destPath;
        }
    
}
