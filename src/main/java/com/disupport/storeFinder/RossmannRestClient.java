package com.disupport.storeFinder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class RossmannRestClient {

    @Value("classpath:data.txt")
    Resource resourceFile;

    private static final String GET_STORE = "/storefinder/.rest/fotoweltapp/findStores";
    private final WebClient webClient;
    private String username = "";
    private String password = "";
    private final String baseUrl = "https://www-dev.rossmann.eu";

    public RossmannRestClient() {
        webClient = WebClient.create(baseUrl);
    }

    private void uploadCredentials() {
        try {
            File file = resourceFile.getFile();
            String[] credentials = new String(
                    Files.readAllBytes(file.toPath())).split(" ");
            username = credentials[0];
            password = credentials[1];
        } catch (FileNotFoundException ex){
            System.err.println("You need to have file data.txt with credentials in the resources");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StoreRoot retrieveStore(String latitude, String longitude) {
        uploadCredentials();
        if(username.isEmpty() || password.isEmpty())
            return new StoreRoot();

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
