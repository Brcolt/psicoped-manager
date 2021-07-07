package br.com.coltextends.psicopediatria.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PropertyUtils {

    public static String[] getNullPropertyNames(Object source, String... extraProps) {

        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);

        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>(Arrays.asList(extraProps));

        for (PropertyDescriptor pd : propertyDescriptors) {
            Object srcValue = beanWrapper.getPropertyValue(pd.getName());

            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);

    }
}
