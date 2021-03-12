package io.youngwon.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;



import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ProductsApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//


    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("상품 목록 조회 테스트")
    public void findAllSuccessTest() throws Exception {

    }


    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("상품 등록 테스트")
    public void saveSuccessTest() throws Exception {

        String title = "title";
        String content = "content";
        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        ResultActions result = mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                        .andExpect(status().isOk());
        //then
        result.andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ProductsApiControllerTest.class))
                .andExpect(handler().methodName("review"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(400)));


    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("상품 등록 테스트(제목 누락)")
    public void saveFailureTest() throws Exception {

        String content = "content";
        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .content(content)
                .build();

        ResultActions result = mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //then
        result.andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ProductsApiControllerTest.class))
                .andExpect(handler().methodName("review"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(400)));


    }


    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("상품 수정 테스트")
    public void updateSuccessTest() throws Exception {

        String title = "title";
        String content = "content";
        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        ResultActions result = mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //then
        result.andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ProductsApiControllerTest.class))
                .andExpect(handler().methodName("review"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(400)));


    }
}
