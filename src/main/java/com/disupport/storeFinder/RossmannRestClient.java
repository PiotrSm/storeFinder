package com.disupport.storeFinder;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RossmannRestClient {

    private static final String GET_STORE = "/storefinder/.rest/fotoweltapp/findStores";
    private WebClient webClient;
    private String username = "di-support";
    private String password = "fotoweltapp";
    private String baseUrl = "https://www-dev.rossmann.eu";

    public RossmannRestClient() {
        webClient = WebClient.create(baseUrl);
    }

    public StoreRoot retrieveStore(String latitude, String longitude) {
        return webClient.get()
                .uri(builder ->
                        builder.path(GET_STORE)
                                .queryParam("longitude", longitude)
                                .queryParam("latitude", latitude)
                                .build())
                .headers(headers -> headers.setBasicAuth(username, password))
                .retrieve()
                .bodyToMono(StoreRoot.class)
                .block();
    }

}
