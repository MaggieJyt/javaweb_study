package com.maggie.servlet.study1;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo3
 * 利用ServletContext对象读取资源文件
 */
@WebServlet("/ServletDemo3")
public class ServletDemo3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/html;charset=UTF-8");//可以直接设置响应头content-type的内容
		//目的是控制浏览器用UTF-8进行解码；前提是全都是设置为UTF-8
		
		response.setHeader("content-type","text/html;charset=UTF-8");//是修改一个响应头信息的
		readSrcDirPropCfgFile(response);
		readPropCfgFile2(response);
		readWebRootDirPropCfgFile(response);
		readPropCfgFile1(response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void readSrcDirPropCfgFile(HttpServletResponse response) throws IOException {
        /**
         * 通过ServletContext对象读取src目录下的db1.properties配置文件
         */
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db4.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("name");
        String password = prop.getProperty("password");
        response.getWriter().println("读取src目录下的db4.properties配置文件：");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}", 
                        driver,url, username, password));
    }

	private void readPropCfgFile2(HttpServletResponse response)
            throws IOException {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/maggie/servlet/study1/db2.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("name");
        String password = prop.getProperty("password");
        response.getWriter().println("读取src目录下的gacl.servlet.study包中的db2.properties配置文件：");
        response.getWriter().println(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}", 
                        driver,url, username, password));
    }
	
	
	private void readWebRootDirPropCfgFile(HttpServletResponse response)
            throws IOException {
        /**
         * 通过ServletContext对象读取WebRoot目录下的properties配置文件
         * “/”代表的是项目根目录
         */
        InputStream in = this.getServletContext().getResourceAsStream("/db3.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("name");
        String password = prop.getProperty("password");
        response.getWriter().println("读取WebRoot目录下的db3.properties配置文件：");
        response.getWriter().print(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}", 
                        driver,url, username, password));
    }
	
	
	private void readPropCfgFile1(HttpServletResponse response)
            throws IOException {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db/config/db1.properties");
        Properties prop = new Properties();
        prop.load(in);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String username = prop.getProperty("name");
        String password = prop.getProperty("password");
        response.getWriter().println("读取WebRoot目录下的db3.properties配置文件：");
        response.getWriter().print(
                MessageFormat.format(
                        "driver={0},url={1},username={2},password={3}", 
                        driver,url, username, password));
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
