package com.andrewborchenko.spring.rest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/*аналогия web.xml в нем dispatcher servlet*/
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*Так как нет в приложении rootConfig классов*/
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /*аналогия дает ссылку на applicationContext.xml в нашем случае конфиг класс MyConfig*/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    /*аналогия в web.xml <url-pattern>/</url-pattern> */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
