package io.youngwon.app.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.youngwon.app.domain.categories.dto.CategoriesSaveRequestDto;
import io.youngwon.app.domain.products.dto.ProductsSaveRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ProductsApiControllerTest extends SpringMockMvcTestSupport {

//    @Autowired
//    private WebApplicationContext context;


    @BeforeEach
    void setup() {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
    }


    @Test
    @Order(1)
    @WithMockUser(roles = "USER")
    @DisplayName("상품 등록 성공 테스트")
    public void productSaveSuccessTest() throws Exception {

        CategoriesSaveRequestDto categoriesSaveRequestDto = CategoriesSaveRequestDto.builder().title("가전").build();
        this.mockMvc.perform(
                post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoriesSaveRequestDto))
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .title("상품1")
                .content("상품1입니다.")
                .startPrice(1000L)
                .startDateTime("2021-01-01 10:00 AM")
                .endDateTime("2021-01-10 10:00 PM")
                .categories(1L)
                .build();

        this.mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto))
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    @Order(2)
    @WithMockUser(roles = "USER")
    @DisplayName("상품 등록 실패 테스트 (존재하지 않는 카테고리 선택)")
    public void productSaveFailureTest1() throws Exception {

        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .title("상품1")
                .content("상품1입니다.")
                .startPrice(1000L)
                .startDateTime("2021-01-01 10:00 AM")
                .endDateTime("2021-01-10 10:00 PM")
                .categories(1L)
                .build();

        this.mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto))
        ).andDo(print())
                .andExpect(status().is5xxServerError());

    }


    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("상품 등록 실패 테스트 (상품 시작 가격, 종료 날짜 누락)")
    public void productSaveFailureTest2() throws Exception {
        ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                .title("상품1")
                .content("상품1입니다.")
//                .startPrice(1000L)
                .startDateTime("2021-01-01 10:00 AM")
//                .endDateTime("2021-01-10 10:00 PM")
                .categories(1L)
                .build();

        this.mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto))
        ).andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(400)));

    }


    @Test
    @DisplayName("상품 등록 실패 테스트 (로그인이 되어 있지 않음)")
    public void productSaveFailureTest3() throws Exception {

    }


    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("상품 목록 조회 테스트 (페이징)")
    public void findAllSuccessTest() throws Exception {
//        this.mockMvc.perform(get("/api/products"));

    }


    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("단일 상품 조회 테스트")
    public void findByIdSuccessTest() throws Exception {
//        this.mockMvc.perform(get("/api/products/{id}", 10))
//                .andDo(print())
//                .andExpect(status().is(HttpStatus.OK.value()))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(document("products", // (3)
//                        pathParameters(
//                                parameterWithName("id").description("products unique id") // (4)
//                        ),
//                        responseFields(
//                                fieldWithPath("success").description("result"),
//                                fieldWithPath("response.id").description("book unique id"),
//                                fieldWithPath("response.title").description("title"),
//                                fieldWithPath("response.content").description("book unique id"),
//                                fieldWithPath("response.startPrice").description("author"),
//                                fieldWithPath("response.startDateTime").description("author"),
//                                fieldWithPath("response.endDateTime").description("author"),
//                                fieldWithPath("response.viewCount").description("author"),
//                                fieldWithPath("response.auctions").description("author"),
//                                fieldWithPath("response.numOfLike").description("author"),
//                                fieldWithPath("response.isLike").description("author"),
//                                fieldWithPath("response.numOfAuctions").description("author"),
//                                fieldWithPath("response.numOfParticipant").description("author"),
//                                fieldWithPath("response.price").description("author"),
//                                fieldWithPath("response.isFinish").description("author"),
//                                fieldWithPath("error").description("result")
//                        ).and(
//                                subsectionWithPath("response.images").description("dd"),
//                                subsectionWithPath("response.categories").description("dd"))
//                ));
    }


    @Test
    @DisplayName("단일 상품 실패 테스트 (존재하지 않는 ID)")
    public void findByIdFailureTest() throws Exception {

    }


}
