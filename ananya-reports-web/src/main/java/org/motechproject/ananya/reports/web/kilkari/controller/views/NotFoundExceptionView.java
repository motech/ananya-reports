package org.motechproject.ananya.reports.web.kilkari.controller.views;

import org.motechproject.ananya.reports.web.BaseResponse;
import org.motechproject.ananya.reports.web.kilkari.exceptions.NotFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class NotFoundExceptionView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        NotFoundException exceptionObject =
                (NotFoundException) model.get(SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);

        response.getOutputStream().print(BaseResponse.failure(exceptionObject.getMessage()).toJson());

        response.setStatus(HttpConstants.HTTP_STATUS_NOT_FOUND);
        response.setContentType(HttpConstants.JSON_CONTENT_TYPE);
    }
}
