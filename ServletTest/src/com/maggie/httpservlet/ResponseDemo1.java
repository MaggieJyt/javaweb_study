package com.maggie.httpservlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Buffer;

/**
 * Servlet implementation class ResponseDemo1
 */
@WebServlet("/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResponseDemo1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// outputStream(response);
		// printWriter(response);
		// digitOut(response);
		// digitPrintWriter(response);
		// downloadFileByOutputStream(response);
		// downloadChineseByOutPutStream(response);
		downloadFileByPrintWriter(response);// 下载的时候会数据丢失，下载不完全
		// 所以使用PrintWriter流处理字节数据，会导致数据丢失
		/*
		 * 在编写下载文件功能时，要使用OutputStream流，避免使用PrintWriter流，因为OutputStream流是字节流，可以处理任意类型的数据，
		 * 而PrintWriter流是字符流，只能处理字符数据，如果用字符流处理字节数据，会导致数据丢失。
		 */
	}

	private void outputStream(HttpServletResponse response) throws IOException {
		String string = "你好";
		OutputStream outputStream = response.getOutputStream();
		response.setHeader("content-type", "text/html;charset=UTF-8");// 设置响应头，浏览器编码UTF-8

		outputStream.write(string.getBytes("UTF-8"));// 将字符转换成字节数组，指定以UTF-8编码进行转换
	}

	private void printWriter(HttpServletResponse response) throws IOException {
		String string = "大家好";
		// response.setHeader("content-type", "text/html;charset=UTF-8");//
		// 设置响应头，浏览器编码UTF-8
		response.setCharacterEncoding("UTF-8");
		
		// 相对于response.setCharacterEncoding("UTF-8");，还是response.setHeader方便点
		/*http://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/ServletResponse.html#setCharacterEncoding(java.lang.String)
		 * response.setCharacterEncoding("UTF-8")的作用是指定对服务器响应进行重新编码的编码。同时，
		 * 浏览器也是根据这个参数来对其接收到的数据进行重新编码（或者称为解码）。
		 * 所以在无论你在JSP中设置response.setCharacterEncoding
		 * ("UTF-8")或者response.setCharacterEncoding("GBK")，浏览器均能正确显示中文（
		 * 前提是你发送到浏览器的数据编码是正确的，比如正确设置了pageEncoding参数等）。
		 */
		PrintWriter printWriter = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		printWriter.write(string);
	}

	private void digitOut(HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write("outputstream输出数字：".getBytes());
		// outputStream.write(1111); //输出的是“W”

		outputStream.write((1 + "").getBytes());
		// 输出1
		// 1+""--->转换成string类型

	}

	private void digitPrintWriter(HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		// out.write(1111);//输出乱码
		out.write(1 + ""); // 输出1
	}

	// 文件下载
	private void downloadFileByOutputStream(HttpServletResponse response) throws IOException {
		String filePath = this.getServletContext().getRealPath("/download/a.jpg");
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		// 3.设置content-disposition响应头控制浏览器以下载的形式打开文件
		// Content-Disposition就是当用户想把请求所得的内容存为一个文件的时候提供一个默认的文件名
		// 参照http://www.cnblogs.com/brucejia/archive/2012/12/24/2831060.html
		response.setHeader("content-disposition", "attachment;filename=" + fileName);

		InputStream inputStream = new FileInputStream(filePath);

		// 创建数据缓冲区
		int len = 0;
		byte[] buffer = new byte[1024];
		OutputStream outputStream = response.getOutputStream();

		while ((len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);

		}

	}

	/*
	 * 下载中文文件,中文名文件下载时，文件名要经过URL编码，否则会出现文件名乱码 URLEncoder.encode(fileName, "UTF-8"))
	 */
	private void downloadChineseByOutPutStream(HttpServletResponse response) throws IOException {
		String filePath = this.getServletContext().getRealPath("/download/大家好.jpg");
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);// 大家好 (1)
		// String fileName =
		// filePath.substring(filePath.lastIndexOf("\\")+1);//%2FUsers%2Fji%2FTomcat%2Fapache-tomcat-9.0.0.M17%2Fwebapps%2FServletTest%2Fdownload%2F大家好
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

		InputStream inputStream = new FileInputStream(filePath);
		// 创建数据缓冲区
		int len = 0;
		byte[] buffer = new byte[1024];
		OutputStream outputStream = response.getOutputStream();

		while ((len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);

		}
	}

	/*
	 * 下载文件，通过PrintWriter流，虽然也能够实现下载，但是会导致数据丢失，因此不推荐使用PrintWriter流下载文件
	 */
	private void downloadFileByPrintWriter(HttpServletResponse response) throws FileNotFoundException, IOException {
		String realPath = this.getServletContext().getRealPath("/download/大家好.jpg");// 获取要下载的文件的绝对路径
		String fileName = realPath.substring(realPath.lastIndexOf("/") + 1);// 获取要下载的文件名
		// 设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

		FileReader in = new FileReader(realPath);

		int len = 0;
		// 字符流用的是char
		char[] chars = new char[1024];
		PrintWriter out = response.getWriter();
		while ((len = in.read(chars)) > 0) {
			out.write(chars, 0, len);// 将缓冲区的数据输出到客户端浏览器
		}
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
