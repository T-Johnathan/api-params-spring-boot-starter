package com.ittc.project.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ittc.project.annotation.Encrypt;
import com.ittc.project.common.BaseResponse;
import com.ittc.project.config.EncryptProperties;
import com.ittc.project.utils.AESUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import javax.annotation.Resource;

@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<BaseResponse> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public BaseResponse beforeBodyWrite(BaseResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            if (body.getMessage()!=null) {
                body.setMessage(AESUtil.encrypt(body.getMessage().getBytes(),keyBytes));
            }
            if (body.getData() != null) {
                body.setData(AESUtil.encrypt(objectMapper.writeValueAsBytes(body.getData()), keyBytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }


}
