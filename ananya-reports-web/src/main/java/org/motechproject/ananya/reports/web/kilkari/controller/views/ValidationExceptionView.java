package org.motechproject.ananya.reports.web.kilkari.controller.views;

import org.apache.commons.httpclient.HttpStatus;
import org.motechproject.ananya.reports.web.BaseResponse;
import org.motechproject.ananya.reports.web.kilkari.exceptions.ValidationException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ValidationExceptionView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ValidationException exceptionObject =
                (ValidationException) model.get(SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);

        response.getOutputStream().print(BaseResponse.failure(exceptionObject.getMessage()).toJson());
        response.setStatus(HttpStatus.SC_BAD_REQUEST);
    }
}
