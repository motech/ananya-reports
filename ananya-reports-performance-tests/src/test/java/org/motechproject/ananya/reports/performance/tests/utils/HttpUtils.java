package org.motechproject.ananya.reports.performance.tests.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtils {

    public static String constructUrl(String url, String path, Map<String, String> parametersMap) {
        url += "/" + path;
        if (parametersMap != null) {
            url = url + "?";
            for (String key : parametersMap.keySet()) {
                url += (key + "=" + parametersMap.get(key) + "&");
            }
        }
        return url;
    }

    public static ResponseEntity<String> httpPost(Map<String, String> queryStringParametersMap, Object postParam, String urlPath) {
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(
                constructUrl(ContextUtils.getConfiguration().baseUrl(), urlPath, queryStringParametersMap), postParam, String.class);
        return responseEntity;
    }

    public static void httpPut(Object request, String urlPath, String urlVariable) {
        String urlWithBase = constructUrl(ContextUtils.getConfiguration().baseUrl(), urlPath, null);
        new RestTemplate().put(String.format("%s/%s", urlWithBase, urlVariable), request);
    }
}