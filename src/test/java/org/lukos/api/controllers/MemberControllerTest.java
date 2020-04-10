package org.lukos.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.lukos.api.domain.Member;
import org.lukos.api.service.MemberResponse;

import javax.inject.Inject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class MemberControllerTest {

    @Inject
    @Client("/api/members")
    RxHttpClient client;

    private ObjectMapper objectMapper = new ObjectMapper();
    private String requestBodyFoo = "{\"name\": \"foo\",\"points\":69}";
    private String requestBodyBar = "{\"name\": \"bar\",\"points\":88}";

    @Test
    void index() throws JsonProcessingException {
        HttpRequest<String> request = HttpRequest.GET("/");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        //assertEquals("{\"message\":\"ok\",\"member\":[]", body);
    }

    @Test
    void add() throws JsonProcessingException {
        HttpRequest<String> request = HttpRequest.POST("/", requestBodyFoo);
        String responseBody = client.toBlocking().retrieve(request);

        MemberResponse memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
        assertEquals(1, memberResponse.getMembers().size());
        assertEquals("foo", memberResponse.getMembers().get(0).getName());
        assertEquals(69, memberResponse.getMembers().get(0).getPoints());

        requestRemove("foo");
    }

    @Test
    void addMoreThanOne() throws JsonProcessingException {
        HttpRequest<String> request = HttpRequest.POST("/", requestBodyFoo);
        String responseBody = client.toBlocking().retrieve(request);
        MemberResponse memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
        assertEquals(1, memberResponse.getMembers().size());
        assertEquals("foo", memberResponse.getMembers().get(0).getName());
        assertEquals(69, memberResponse.getMembers().get(0).getPoints());


        request = HttpRequest.POST("/", requestBodyBar);
        responseBody = client.toBlocking().retrieve(request);
        memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
        assertEquals(1, memberResponse.getMembers().size());
        assertEquals("bar", memberResponse.getMembers().get(0).getName());
        assertEquals(88, memberResponse.getMembers().get(0).getPoints());


        request = HttpRequest.GET("/");
        responseBody = client.toBlocking().retrieve(request);
        memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
        assertEquals(2, memberResponse.getMembers().size());
        assertEquals("foo", memberResponse.getMembers().get(1).getName());
        assertEquals(69, memberResponse.getMembers().get(1).getPoints());
        assertEquals("bar", memberResponse.getMembers().get(0).getName());
        assertEquals(88, memberResponse.getMembers().get(0).getPoints());

        requestRemove("foo");
        requestRemove("bar");
    }

    @Test
    void edit() throws JsonProcessingException {
        HttpRequest<String> request = HttpRequest.POST("/", requestBodyFoo);
        String responseBody = client.toBlocking().retrieve(request);
        MemberResponse memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
        assertEquals(1, memberResponse.getMembers().size());
        assertEquals("foo", memberResponse.getMembers().get(0).getName());
        assertEquals(69, memberResponse.getMembers().get(0).getPoints());


        request = HttpRequest.POST("/edit/foo", "{\"name\": \"foo\",\"points\":100}");
        responseBody = client.toBlocking().retrieve(request);
        memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
        assertEquals(1, memberResponse.getMembers().size());
        assertEquals("foo", memberResponse.getMembers().get(0).getName());
        assertEquals(100, memberResponse.getMembers().get(0).getPoints());

        requestRemove("foo");
    }

    private void requestRemove(String name) throws JsonProcessingException {
        HttpRequest<String> request = HttpRequest.POST("/delete/foo", "");
        String responseBody = client.toBlocking().retrieve(request);
        MemberResponse memberResponse = objectMapper.readValue(responseBody, MemberResponse.class);

        assertEquals("ok", memberResponse.getMessage());
    }
}