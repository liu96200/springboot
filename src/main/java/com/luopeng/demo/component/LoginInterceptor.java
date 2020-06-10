package com.luopeng.demo.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("username");
        if (user == null || user.equals(""))  {
            //未登陆，返回登陆页面

            //map.put("msg","没有权限请先登陆");
            request.setAttribute("msg","没有权限请先登陆");
            request.getRequestDispatcher("/").forward(request,response);
            //response.sendRedirect("/");//"/index.html").forward(request,response);
            return false;
        }

        return true;
    }
    /**
     * 定义排除拦截URL
     * @return
     */
    private List<String> url = new ArrayList();
    public List<String> getUrl(){
        url.add("/index");      //登录页
        url.add("/regist");   //登录action URL
        url.add("/offer");      //试看页面
        url.add("/doRegist");
        url.add("/login");
        url.add("/");
        //网站静态资源
        url.add("classpath:/static/**");
        url.add("/css/**");
        url.add("/js/**");
        url.add("/img/**");
        return url;
    }
}

