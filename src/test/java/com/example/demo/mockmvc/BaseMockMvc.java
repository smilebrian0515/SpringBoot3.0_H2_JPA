package com.example.demo.mockmvc;

import com.example.demo.shared.Constant;
import com.example.demo.shared.EResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public abstract class BaseMockMvc {

    @Autowired
    protected MockMvc mockMvc;

    protected HttpHeaders getHttpHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String token = getTestHeaderToken();
        headers.add("Content-Type", Constant.SYSTEM_CONTENT_TYPE_JSON);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }

    protected String getTestHeaderToken() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/token.txt");
        String token = fis.toString();
        fis.close();
        return token;
    }

    protected HttpHeaders getHttpHeadersNull() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", Constant.SYSTEM_CONTENT_TYPE_JSON);
        return headers;
    }

    protected ResultActions getResponseEncoding(RequestBuilder builder) throws Exception {
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions
                .andReturn()
                .getResponse()
                .setCharacterEncoding(Constant.SYSTEM_ENCODING);
        return resultActions;
    }

    protected void shouldBeError(RequestBuilder builder, EResultStatus status, int errorCode, String errorMessage) throws Exception {
        ResultActions resultActions = getResponseEncoding(builder);

        resultActions.andDo(print())
                //todo Exception handler 要改 httpStatuss
                .andExpect(status().is(status.getCode()))
                .andExpect(jsonPath("$.status").hasJsonPath())
                .andExpect(jsonPath("$.status").value(status.name().toUpperCase(Locale.ROOT)))
                .andExpect(jsonPath("$.code").hasJsonPath())
                .andExpect(jsonPath("$.code").value(errorCode))
                .andExpect(jsonPath("$.message").hasJsonPath())
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    protected void shouldBeSuccess(RequestBuilder builder) throws Exception {
        ResultActions resultActions = getResponseEncoding(builder);
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").hasJsonPath())
                .andExpect(jsonPath("$.status").value("200"))
                .andExpect(jsonPath("$.code").hasJsonPath())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.message").hasJsonPath())
                .andExpect(jsonPath("$.message").value("OK"))
        ;
    }
}
