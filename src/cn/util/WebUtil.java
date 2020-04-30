package cn.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtil {

    public static <T> T requestFormBean(HttpServletRequest httpServletRequest, Class<T> aClass) {
        try { 
            //��ȡBean�Ķ���
            T bean = aClass.newInstance();
            
            //��ȡ�������е����������
            Enumeration enumeration = httpServletRequest.getParameterNames();

            //�������ύ���������������
            while (enumeration.hasMoreElements()) {
                //ÿ�����������
                String name = (String) enumeration.nextElement();
                //��ȡ�õ�ֵ
                String value = httpServletRequest.getParameter(name);
                
                System.out.println(name+"-"+value);
                
                //����û��ύ�����ݲ�Ϊ�գ���ô�����ݷ�װ��Bean��
                if (!value.equals("") && value != null) {
                    BeanUtils.setProperty(bean, name, value);
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("��װ���ݵ�Bean�г���");
        }  
    }

}
