package org.qd4j.qdframework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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

import org.qd4j.qdframework.bean.JsonResult;
import org.qd4j.qdframework.bean.Handle;
import org.qd4j.qdframework.bean.Param;
import org.qd4j.qdframework.bean.Request;
import org.qd4j.qdframework.bean.ViewResult;
import org.qd4j.qdframework.helper.BeanHelper;
import org.qd4j.qdframework.helper.ConfigHelper;
import org.qd4j.qdframework.helper.ControllerHelper;
import org.qd4j.qdframework.helper.RequestHelper;
import org.qd4j.qdframework.util.JsonUtil;
import org.qd4j.qdframework.util.ReflectionUtil;
import org.qd4j.qdframework.util.StringUtil;

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
			Param param=null;
			param = RequestHelper.createParam(req);
			Method method=handle.getMethod();
		    Object result=ReflectionUtil.InvokeMethod(controllerBeanObject, method,param);
		    
		    if(result instanceof ViewResult)
		    {
		    	handleViewResult((ViewResult)result,req,resp);
		    }
			else if(result instanceof JsonResult) {
				handleDataResult((JsonResult)result,resp);
			}
		}
	}
	
	private void handleViewResult(ViewResult view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJSPPath() + path).forward(request, response);
            }
        }
    }

    private void handleDataResult(JsonResult data, HttpServletResponse response) throws IOException {
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
