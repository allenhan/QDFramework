package org.qd4j.qdframework;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qd4j.qdframework.bean.Handle;
import org.qd4j.qdframework.bean.Request;
import org.qd4j.qdframework.helper.BeanHelper;
import org.qd4j.qdframework.helper.ConfigHelper;
import org.qd4j.qdframework.helper.ControllerHelper;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		HelperLoader.init();
        ServletContext servletContext=config.getServletContext();
        ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJSPPath()+"*");
        ServletRegistration defaultServletRegistration=servletContext.getServletRegistration("default");
        defaultServletRegistration.addMapping(ConfigHelper.getASSetPath()+"*");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		String requestMethod=req.getMethod().toLowerCase();
		String requestPathString=req.getPathInfo();
		
		Handle handle=ControllerHelper.getHandle(requestMethod, requestPathString);
		if(handle!=null){
			Class<?> controllerClass=handle.getControllerClass();
			Object controllerBeanObject=BeanHelper.getBean(controllerClass);
			Map<String, Object> paramMap=new HashMap<String, Object>();
			Enumeration<String> paramNames=req.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValue=req.getParameter(paramName);
				paramMap.put(paramName, paramValue);
				
				
			}
		}
	}
	
}
