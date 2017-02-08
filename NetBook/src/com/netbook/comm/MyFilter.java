package com.netbook.comm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *  过滤器
 *
 */
public class MyFilter implements Filter {
	private String encoding;
	@Override
	public void destroy() {}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//第一步 :将request向下转型 httpservletRequest
		HttpServletRequest req=(HttpServletRequest)request;
		//第二步:获取请求参数类型   
		String requestMethod=req.getMethod();
		//第三步 :设置编码   
		//该方法是post
		if(requestMethod.equalsIgnoreCase("post")){
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			chain.doFilter(request, response);
		}else{
			//该方法是get
			response.setCharacterEncoding(encoding);
			chain.doFilter(new MethodGetPro(req), response);
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 encoding=filterConfig.getInitParameter("encoding"); 
		 if(encoding==null||encoding==""){
			   encoding="UTF-8";
		 }
		
	}
}
//解决的是get请求乱码
class  MethodGetPro extends  HttpServletRequestWrapper{
	ServletRequest request;
	public MethodGetPro(HttpServletRequest request) {
		super(request);
		this.request=request;
	}


	@Override
	public String getParameter(String name) {
		//得到参数的类型
	
		
		String value=super.getParameter(name);
		/* new String(value."UTF-8");*/
		//解决get请求乱码
		/*if(value!=null){
			try {
	            //先将字符串转换为字节编码 ,然后将其设置为UTF-8
				value=new String(value.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
			}
		}*/
		
		return value;
	}
}