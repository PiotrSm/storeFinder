package com.disupport.storeFinder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StoreFinderController.class)
class StoreFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getStoreWithoutTokenTest() throws Exception {
        RequestBuilder request = get("/findStore");
        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void getStoreWithCorrectTokenTest() throws Exception {
        RequestBuilder request = get("/findStore").header("token","G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void getStoreWithWrongTokenTest() throws Exception {
        RequestBuilder request = get("/findStore").header("token","123");
        mvc.perform(request).andExpect(status().isUnauthorized());
    }


}