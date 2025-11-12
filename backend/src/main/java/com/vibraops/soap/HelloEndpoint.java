package com.vibraops.soap;

import com.vibraops.soap.types.HelloRequest;
import com.vibraops.soap.types.HelloResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * SOAP endpoint that greets a caller by name.
 */
@Endpoint
public class HelloEndpoint {
    private static final String NAMESPACE_URI = "http://vibraops.com/hello";
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HelloRequest")
    @ResponsePayload
    public HelloResponse sayHello(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        String name = request.getName() != null ? request.getName() : "world";
        response.setMessage("Hello " + name + " from VibraOps SOAP");
        return response;
    }
}