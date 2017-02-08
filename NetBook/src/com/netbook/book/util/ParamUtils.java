package com.netbook.book.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
/**
 * 参数封装工具类
 * @author wenber
 *
 */
public class ParamUtils{
	
	/**
	 * 将请求中的参数值通过反射赋值给对象
	 * @param cls
	 * @param request
	 * @return
	 */
	public static <T> T  getObject(Class cls,HttpServletRequest request){
		
		Object object=null;
		try {
			//根据类创建这个类的实例
			object=cls.newInstance();
			
			//取得请求对象中的参数名
			Enumeration<String> enu=request.getParameterNames();
			while(enu.hasMoreElements()){
				//取得属性名
				String name=enu.nextElement();//setacc_name
				//取得属性值
				String value=request.getParameter(name);
				
				//取得类中所有的方法
				Method[] methods=cls.getDeclaredMethods();
				for (Method method : methods) {
					//找到了需要的方法
					if(method.getName().equalsIgnoreCase("set"+name)){
						
						//取得方法的参数类型
						Class paramClass=method.getParameterTypes()[0];
						if(paramClass==String.class){
							method.invoke(object,value);
						}else if(paramClass==double.class || paramClass==Double.class
								||paramClass==float.class||  paramClass==Float.class){
							method.invoke(object, Float.parseFloat(value));
						}else if(paramClass==int.class || paramClass==Integer.class
								){
							method.invoke(object, Integer.parseInt(value));
						}else if(paramClass==java.util.Date.class){
							java.text.SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							method.invoke(object, sdf.parse(value));
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) object;
	}

}

