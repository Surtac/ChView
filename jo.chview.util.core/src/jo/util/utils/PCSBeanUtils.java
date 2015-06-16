package jo.util.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jo.util.beans.PCSBean;

public class PCSBeanUtils
{

    public static void fireAllProperties(PCSBean bean)
    {
        Class<?> beanClass = bean.getClass();
        try
        {
            BeanInfo classInfo = Introspector.getBeanInfo(beanClass);
            PropertyDescriptor[] beanProps = classInfo.getPropertyDescriptors();
            for (int i = 0; i < beanProps.length; i++)
            {
                Method read = beanProps[i].getReadMethod();
                Method write = beanProps[i].getWriteMethod();
                if ((read != null) && (write != null))
                {
                    Object val = read.invoke(bean, new Object[0]);
                    if (val instanceof PCSBean)
                        fireAllProperties((PCSBean)val);
                    bean.fireMonotonicPropertyChange(beanProps[i].getName(), val);
                }
            }
        }
        catch (IntrospectionException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e2)
        {
            e2.printStackTrace();
        }
        catch (InvocationTargetException e3)
        {
            e3.getTargetException().printStackTrace();
        }
    }

    

}
