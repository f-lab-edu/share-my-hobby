package flab.project.sharemyhobby.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import flab.project.sharemyhobby.model.api.request.user.JoinRequest;
import flab.project.sharemyhobby.model.user.Email;
import flab.project.sharemyhobby.model.user.Status;
import flab.project.sharemyhobby.model.user.User;
import flab.project.sharemyhobby.service.user.LoginService;
import flab.project.sharemyhobby.service.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.time.LocalDateTime.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserRestController.class)
@AutoConfigureRestDocs
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginService service;

    JoinRequest joinRequest;
    User user;

    @BeforeEach
    void setUp() {
        joinRequest = new JoinRequest("test@gmail.com", "cold-pumpkin", "12345678");
        user = User.builder()
                .id(1L)
                .email(new Email(joinRequest.getEmail()))
                .nickname(joinRequest.getNickname())
                .password(joinRequest.getPassword())
                .status(Status.DEFAULT)
                .lastLoginAt(now())
                .createAt(now())
                .updateAt(now())
                .build();
    }

    @Test
    @DisplayName("회원가입 요청 성공 시 DB에 저장된 유저 정보를 리턴한다")
    public void shouldReturnUserInfoWhenUJoinSuccess() throws Exception {
        given(userService.join(any(Email.class), anyString(), anyString()))
                .willReturn(user);

        mockMvc.perform(post("/users/join")
            .content(objectMapper.writeValueAsString(joinRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("join",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                attributes(key("title").value("Fields for user join")),
                                fieldWithPath("email").description("User's email")
                                    .attributes(key("constraints").value("Must not be empty")),
                                fieldWithPath("nickname").description("User's nickname")
                                    .attributes(key("constraints").value("Must not be empty")),
                                fieldWithPath("password").description("User's password")
                                        .attributes(key("constraints").value("Must be between 8 and 15 characters"))
                                ),
                        responseFields(
                                beneathPath("user").withSubsectionId("user"),
                                fieldWithPath("id").description("User's unique id"),
                                fieldWithPath("email.address").description("User's unique email address"),
                                fieldWithPath("nickname").description("User's nickname"),
                                fieldWithPath("status").description("User's status"),
                                fieldWithPath("profile").description("User's default profile"),
                                fieldWithPath("location").description("User's location info"),
                                fieldWithPath("address").description("User's address info"),
                                fieldWithPath("lastLoginAt").description("User last login time"),
                                fieldWithPath("createAt").description("User's first join time"),
                                fieldWithPath("updateAt").description("User info last updated time")

                        )))
            .andExpect(jsonPath("$.user.id").value(1L))
            .andExpect(jsonPath("$.user.email.address").value("test@gmail.com"))
            .andExpect(jsonPath("$.user.nickname").value("cold-pumpkin"));
    }

}