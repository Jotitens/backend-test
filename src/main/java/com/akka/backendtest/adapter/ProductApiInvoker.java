package com.akka.backendtest.adapter;

import com.akka.backendtest.utils.UtilsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.Callable;

import static com.akka.backendtest.utils.Constants.LEVEL_WARN;

@Component
public class ProductApiInvoker {

    public <T> ResponseEntity<T> invoke(Callable<ResponseEntity<T>> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            UtilsLog.customLog(LEVEL_WARN, e.getLocalizedMessage());
            return null;
        }

    }
}
