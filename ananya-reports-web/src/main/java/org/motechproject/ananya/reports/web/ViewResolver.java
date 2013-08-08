package org.motechproject.ananya.reports.web;

import org.motechproject.ananya.reports.web.kilkari.controller.views.ExceptionView;
import org.motechproject.ananya.reports.web.kilkari.controller.views.NotFoundExceptionView;
import org.motechproject.ananya.reports.web.kilkari.controller.views.ValidationExceptionView;
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
        this.viewMap.put("validationExceptionView", new ValidationExceptionView());
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return this.viewMap.get(viewName);
    }
}
