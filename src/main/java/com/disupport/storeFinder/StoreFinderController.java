package com.disupport.storeFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StoreFinderController {

    @Autowired
    RossmannRestClient rossmannRestClient;

    @GetMapping(path = "/findStore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStore(
            @RequestParam(name = "latitude", defaultValue = "52.46034568750561") String latitude,
            @RequestParam(name = "longitude", defaultValue = "13.383527588934609") String longitude,
            @RequestHeader("token") String token
    ) {
        ResponseEntity<Object> objectResponseEntity;
        if (!token.equals("G4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKx")) {
            return objectResponseEntity = new ResponseEntity<>
                    (HttpStatus.UNAUTHORIZED);
        }
        StoreRoot response = rossmannRestClient.retrieveStore(latitude, longitude);
        if (response.store.size() > 0) {
            objectResponseEntity = new ResponseEntity<>
                    (response.store.get(0), HttpStatus.OK);
        } else {
            objectResponseEntity = new ResponseEntity<>
                    (HttpStatus.NOT_FOUND);
        }

        return objectResponseEntity;
    }
}
