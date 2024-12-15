package demo.hateoas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import demo.hateoas.domain.User;
import demo.hateoas.service.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserService userService;

    @Test
    void getUserShouldReturnLinksTest() throws Exception {
        // モックの戻り値を設定
        var userId = 42L;
        var mockUser = new User(userId, "test user", "test@example.com");
        when(userService.findById(userId)).thenReturn(mockUser);

        mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                // ユーザー情報の検証
                .andExpect(jsonPath("$.id").value(mockUser.id()))
                .andExpect(jsonPath("$.name").value(mockUser.name()))
                .andExpect(jsonPath("$.email").value(mockUser.email()))
                // リンクの検証
                .andExpect(jsonPath("$._links.self.href")
                        .value("http://localhost/api/users/" + userId))
                .andExpect(jsonPath("$._links.articles.href")
                        .value("http://localhost/api/users/" + userId + "/articles"));

        // モックメソッドが呼ばれたことを検証
        verify(userService).findById(userId);
    }

}
