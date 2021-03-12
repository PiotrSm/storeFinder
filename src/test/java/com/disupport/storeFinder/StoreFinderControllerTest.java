package com.disupport.storeFinder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StoreFinderController.class)
class StoreFinderControllerTest {

    @Autowired
    private StoreFinderController storeFinderController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RossmannRestClient mockClient;

    @BeforeEach
    private void init(){
        StoreRoot storeRoot = new StoreRoot();
        Store store = new Store();
        store.city = "Szczecin";
        storeRoot.store.add( store);
        when(mockClient.retrieveStore(anyString(), anyString())).thenReturn(storeRoot);
    }

    @Test
    void getStoreWithoutTokenTest() throws Exception {
        RequestBuilder request = get("/findStore");
        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void getStoreWithCorrectTokenTest() throws Exception {
        RequestBuilder request = get("/findStore").header("token", "G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void getStoreWithWrongTokenTest() throws Exception {
        RequestBuilder request = get("/findStore").header("token", "123");
        mvc.perform(request).andExpect(status().isUnauthorized());
    }

//    @Test
//    void getStoreDefaultValuesTest() throws Exception {
//        RequestBuilder request = get("/findStore").header("token", "G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx");
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("city").value("Berlin"));
//    }
//
//    @Test
//    void getStoreHamburgValuesTest() throws Exception {
//        RequestBuilder request = get("/findStore")
//                .header("token", "G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx")
//                .param("latitude", "53.48721554670396")
//                .param("longitude", "9.890717494348136");
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("city").value("Hamburg"));
//    }
//
//    @Test
//    void getStorePoznanValuesTest() throws Exception {
//        RequestBuilder request = get("/findStore")
//                .header("token", "G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx")
//                .param("latitude", "52.406376")
//                .param("longitude", "16.925167");
//        mvc.perform(request)
//                .andExpect(status().isNotFound());
//    }

    @Test
    void getStoreMockValuesTest() throws Exception {
               RequestBuilder request = get("/findStore")
                .header("token", "G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("city").value("Szczecin"));
    }


}