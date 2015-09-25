package test.java;

import java.io.UnsupportedEncodingException;

import org.junit.Test;


public class StrTest {
    public static void main(String args[]) throws UnsupportedEncodingException{
        
        //request.setCharacterEncoding("GBK");
        //response.setContentType("text/html;charset=GBK");
        //对于JSP页面，获取请求参数之前：
        //<%request.setCharacterEncoding("GB2312")%>
        //<%@page contentType="text/html;charset=GBK"%>
        byte[] str1 = "ocean".getBytes("UTF-8");
        
        String s_gbk = new String("b_gbk".getBytes("gbk"),"GBK"); 
        String str = "marinestr";
        str= java.net.URLDecoder.decode(str, "UTF-8");
        System.out.println("hello marine"+str);
        
    }
    
    
    //junit 4j test
    
    @Test
    public void backName(){
        System.out.println("my name is ocean");
        System.exit(0);
        System.out.println("valid is stop!");
    }
}
