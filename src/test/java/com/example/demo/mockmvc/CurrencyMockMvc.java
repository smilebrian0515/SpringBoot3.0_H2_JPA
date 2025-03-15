package com.example.demo.mockmvc;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class CurrencyMockMvc extends BaseMockMvc {

    @Test
    public void currency() throws Exception {
        HttpHeaders headers = getHttpHeadersNull();

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/currency/byDb")
                .headers(headers);

        shouldBeSuccess(builder);
    }
}
