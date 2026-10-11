package com.howtodoinjava.junit.expenses;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(ExpenseController.class)
@Import(SecurityConfig.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private ExpenseService expenseService;

    @Test
    void publicStatusNeedsNoLogin() {
        assertThat(mvc.get().uri("/api/public/status")).hasStatusOk().hasBodyTextEqualTo("UP");
    }

    @Test
    void anonymousCallerGets401() {
        assertThat(mvc.get().uri("/api/expenses")).hasStatus(401);
    }

    @Test
    @WithMockUser(username = "lokesh")
    void mockUserSeesOwnExpenses() {
        given(expenseService.findByOwner("lokesh"))
                .willReturn(List.of(new Expense(1, "lokesh", "Train ticket", 45)));

        assertThat(mvc.get().uri("/api/expenses"))
                .hasStatusOk()
                .bodyJson().extractingPath("$[0].description").isEqualTo("Train ticket");
    }

    @Test
    @WithMockUser(roles = "USER")
    void userCannotApproveExpenses() {
        assertThat(mvc.post().uri("/api/admin/expenses/2/approve").with(csrf())).hasStatus(403);
    }

    @Test
    @WithUserDetails("admin")
    void adminFromUserDetailsServiceCanApprove() {
        given(expenseService.approve(2)).willReturn("Expense 2 approved");

        assertThat(mvc.post().uri("/api/admin/expenses/2/approve").with(csrf()))
                .hasStatusOk()
                .hasBodyTextEqualTo("Expense 2 approved");
    }

    @Test
    void httpBasicWithRealPasswordIsAccepted() {
        assertThat(mvc.get().uri("/api/expenses").with(httpBasic("lokesh", "password1"))).hasStatusOk();
    }

    @Test
    void httpBasicWithWrongPasswordIs401() {
        assertThat(mvc.get().uri("/api/expenses").with(httpBasic("lokesh", "wrong"))).hasStatus(401);
    }

    @Test
    void userPostProcessorSetsTheCaller() {
        assertThat(mvc.post().uri("/api/admin/expenses/2/approve")
                .with(user("alex").roles("USER"))
                .with(csrf()))
                .hasStatus(403);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void postWithoutCsrfTokenIs403() {
        assertThat(mvc.post().uri("/api/admin/expenses/2/approve")).hasStatus(403);
    }
}
