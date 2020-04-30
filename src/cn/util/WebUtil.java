package cn.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtil {

    public static <T> T requestFormBean(HttpServletRequest httpServletRequest, Class<T> aClass) {
        try { 
            //获取Bean的对象
            T bean = aClass.newInstance();
            
            //获取表单中所有的输入框名称
            Enumeration enumeration = httpServletRequest.getParameterNames();

            //遍历表单提交过来的输入框内容
            while (enumeration.hasMoreElements()) {
                //每个输入框名字
                String name = (String) enumeration.nextElement();
                //获取得到值
                String value = httpServletRequest.getParameter(name);
                
                System.out.println(name+"-"+value);
                
                //如果用户提交的数据不为空，那么将数据封装到Bean中
                if (!value.equals("") && value != null) {
                    BeanUtils.setProperty(bean, name, value);
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("封装数据到Bean中出错！");
        }  
    }

}
