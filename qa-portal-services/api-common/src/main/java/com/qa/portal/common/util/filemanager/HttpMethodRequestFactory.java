package com.qa.portal.common.util.filemanager;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

import static com.qa.portal.common.util.filemanager.OneDriveConstants.*;

@Component
public class HttpMethodRequestFactory {

    public enum HttpRequestTypeEnum {
        AUTH((f) -> createAuthRequest(f)),
        POST((f) -> createHttpPost(f)),
        GET((f) -> createHttpGet(f)),
        DELETE((f) -> createHttpDelete(f)),
        PUT((f) -> createHttpPut(f));

        private Function<FactoryParameter, HttpRequestBase> createFunction;

        HttpRequestTypeEnum(Function<FactoryParameter, HttpRequestBase> createFunction) {
            this.createFunction = createFunction;
        }

        private Function<FactoryParameter, HttpRequestBase> getHttpRequestFunction() {
            return createFunction;
        }
    }

    public HttpRequestBase getHttpMethod(String url, String authToken, HttpRequestTypeEnum requestTypeEnum) {
        FactoryParameter factoryParameter = new FactoryParameter(url, authToken);
        return requestTypeEnum.getHttpRequestFunction().apply(factoryParameter);
    }

    public HttpRequestBase getHttpMethod(String url, String authToken, String requestBody, HttpRequestTypeEnum requestTypeEnum) {
        FactoryParameter factoryParameter = new FactoryParameter(url, authToken, requestBody);
        return requestTypeEnum.getHttpRequestFunction().apply(factoryParameter);
    }

    public HttpRequestBase getHttpMethod(String url, String authToken, byte[] requestBody, HttpRequestTypeEnum requestTypeEnum) {
        FactoryParameter factoryParameter = new FactoryParameter(url, authToken, requestBody);
        return requestTypeEnum.getHttpRequestFunction().apply(factoryParameter);
    }

    private static HttpPost createAuthRequest(FactoryParameter factoryParameter) {
        HttpPost httpPost = new HttpPost(factoryParameter.url);
        httpPost.setHeaders(getAuthHeaders());
        factoryParameter.getRequestBody().ifPresent((r) -> httpPost.setEntity(getRequestBodyContent(r)));
        factoryParameter.getByteArrayRequestBody().ifPresent((r) -> httpPost.setEntity(new ByteArrayEntity(r)));
        return httpPost;
    }

    private static HttpPost createHttpPost(FactoryParameter factoryParameter) {
        HttpPost httpPost = new HttpPost(factoryParameter.url);
        httpPost.setHeaders(getCommonHttpHeaders(factoryParameter.authToken));
        factoryParameter.getRequestBody().ifPresent((r) -> httpPost.setEntity(getRequestBodyContent(r)));
        factoryParameter.getByteArrayRequestBody().ifPresent((r) -> httpPost.setEntity(new ByteArrayEntity(r)));
        return httpPost;
    }

    private static HttpPut createHttpPut(FactoryParameter factoryParameter) {
        HttpPut httpPut = new HttpPut(factoryParameter.url);
        httpPut.setHeaders(getCommonHttpHeaders(factoryParameter.authToken));
        factoryParameter.getRequestBody().ifPresent((r) -> httpPut.setEntity(getRequestBodyContent(r)));
        factoryParameter.getByteArrayRequestBody().ifPresent((r) -> httpPut.setEntity(new ByteArrayEntity(r)));
        return httpPut;
    }

    private static HttpGet createHttpGet(FactoryParameter factoryParameter) {
        HttpGet httpGet = new HttpGet(factoryParameter.url);
        httpGet.setHeaders(getCommonHttpHeaders(factoryParameter.authToken));
        return httpGet;
    }

    private static HttpDelete createHttpDelete(FactoryParameter factoryParameter) {
        HttpDelete httpDelete = new HttpDelete(factoryParameter.url);
        httpDelete.setHeaders(getCommonHttpHeaders(factoryParameter.authToken));
        return httpDelete;
    }

    private static ByteArrayEntity getRequestBodyContent(String jsonBody) {
        try {
            byte[] jsonBodyAsArray = jsonBody.getBytes(Charsets.UTF_8.name());
            return new ByteArrayEntity(jsonBodyAsArray);
        } catch (Exception e) {
            throw new QaPortalBusinessException("Error updating one drive");
        }
    }

    private static Header[] getCommonHttpHeaders(String authToken) {
        Header[] headers = new Header[3];
        headers[0] = createHeader(ACCEPT_HTTP_REQUEST_HEADER, APPLICATION_JSON_MEDIA_TYPE_STRING);
        headers[1] = createHeader(CONTENT_TYPE_HTTP_HEADER, APPLICATION_JSON_MEDIA_TYPE_STRING);
        headers[2] = createHeader(AUTHORIZATION_HTTP_REQUEST_HEADER, BEARER_TOKEN_PREFIX + authToken);
        return headers;
    }

    private static Header[] getAuthHeaders() {
        Header[] headers = new Header[1];
        headers[0] = createHeader(CONTENT_TYPE_HTTP_HEADER, APPLICATION_X_WWW_FORM_URLENCODED);
        return headers;
    }

    private static Header createHeader(String key, String value) {
        return new BasicHeader(key, value);
    }

    private static class FactoryParameter {
        private String authToken;

        private String url;

        private String requestBody;

        private byte[] byteArrayRequestBody;

        FactoryParameter(String url, String authToken) {
            this.authToken = authToken;
            this.url = url;
        }

        FactoryParameter(String url, String authToken, String requestBody) {
            this(url, authToken);
            this.requestBody = requestBody;
        }

        FactoryParameter(String url, String authToken, byte[] requestBody) {
            this(url, authToken);
            this.byteArrayRequestBody = requestBody;
        }

        private Optional<String> getRequestBody() {
            return Optional.ofNullable(requestBody);
        }

        private Optional<byte[]> getByteArrayRequestBody() {
            return Optional.ofNullable(byteArrayRequestBody);
        }
    }
}
