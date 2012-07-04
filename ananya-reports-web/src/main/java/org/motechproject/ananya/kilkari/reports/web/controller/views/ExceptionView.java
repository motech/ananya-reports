package org.motechproject.ananya.kilkari.reports.web.controller.views;

import org.motechproject.ananya.kilkari.reports.web.contracts.response.BaseResponse;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExceptionView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Exception exceptionObject = (Exception) model.get(SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);

        String responseJson = BaseResponse.failure(exceptionObject.getMessage()).toJson();
        response.getOutputStream().print(responseJson);

        response.setStatus(HttpConstants.HTTP_STATUS_ERROR);
        response.setContentType(HttpConstants.JSON_CONTENT_TYPE);
    }
}
