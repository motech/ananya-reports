package org.motechproject.ananya.kilkari.reports.web;

import org.motechproject.ananya.kilkari.reports.web.controller.views.ExceptionView;
import org.motechproject.ananya.kilkari.reports.web.controller.views.NotFoundExceptionView;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class ViewResolver implements org.springframework.web.servlet.ViewResolver {

    private Map<String, View> viewMap;

    public ViewResolver() {
        this.viewMap = new HashMap<>();
        this.viewMap.put("exceptionView", new ExceptionView());
        this.viewMap.put("notFoundExceptionView", new NotFoundExceptionView());
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return this.viewMap.get(viewName);
    }
}
