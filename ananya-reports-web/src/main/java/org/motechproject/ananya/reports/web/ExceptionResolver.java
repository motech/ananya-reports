package org.motechproject.ananya.reports.web;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.motechproject.ananya.reports.web.kilkari.exceptions.NotFoundException;
import org.motechproject.ananya.reports.web.kilkari.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Component
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    private final static Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    public ExceptionResolver() {
        Properties properties = new Properties();
        properties.put(".Exception", "exceptionView");
        properties.put(NotFoundException.class.getCanonicalName(), "notFoundExceptionView");
        properties.put(ValidationException.class.getCanonicalName(), "validationExceptionView");
        setExceptionMappings(properties);
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception ex) {
        if (!(ex instanceof NotFoundException))
            log.error(getExceptionString(ex), ex);

        return super.doResolveException(request, response, handler, ex);
    }

    private String getExceptionString(Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append(ExceptionUtils.getMessage(ex));
        sb.append(ExceptionUtils.getStackTrace(ex));
        sb.append(ExceptionUtils.getRootCauseMessage(ex));
        sb.append(ExceptionUtils.getRootCauseStackTrace(ex));
        return sb.toString();
    }
}
