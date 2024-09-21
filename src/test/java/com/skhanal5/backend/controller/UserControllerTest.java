package com.skhanal5.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.skhanal5.backend.repository.UserRepository;
import com.skhanal5.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private UserRepository userRepository;

  private static String SAMPLE_REQUEST_BODY =
      """
    {
        "email_address": "foo@bar.com"
        "first_name": "John"
        "last_name": "Doe"
    }
""";

  @Test
  public void testInsertUser() throws Exception {
    // test should see if we get the posted data back
    var expectedResponse = new User("foo", "bar", "boe");
    when(userRepository.save(any())).thenReturn(expectedResponse);
    mockMvc
        .perform(
            post("api/user").contentType(MediaType.APPLICATION_JSON).content(SAMPLE_REQUEST_BODY))
        .andExpect(status().isCreated());
  }
}
