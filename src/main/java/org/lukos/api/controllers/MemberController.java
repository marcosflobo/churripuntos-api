package org.lukos.api.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.lukos.api.domain.Member;
import org.lukos.api.service.MemberResponse;
import org.lukos.api.service.impl.MemberServiceImpl;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller("/api/members")
public class MemberController{

    @Inject
    MemberServiceImpl memberServiceImpl;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<String> index() {
        try {
            return getMemberBodyResponse(memberServiceImpl.getAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HttpResponse.serverError();
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<String> add(@Body String text) {
        List<Member> memberList = new ArrayList<>(1);
        memberList.add(memberServiceImpl.add(text));
        try {
            return getMemberBodyResponse(memberList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HttpResponse.serverError();
    }

    @Post("/edit/{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<String> edit(@PathVariable String key, @Body String text) {
        List<Member> memberList = new ArrayList<>(1);
        memberList.add(memberServiceImpl.edit(key, text));
        try {
            return getMemberBodyResponse(memberList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HttpResponse.serverError();
    }

    @Post("/delete/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<String> remove(@PathVariable String key) {
        try {
            memberServiceImpl.remove(key);
            return getMemberBodyResponse(memberServiceImpl.getAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HttpResponse.serverError();
    }

    private HttpResponse<String> getMemberBodyResponse(List<Member> memberList)
            throws JsonProcessingException {
        MemberResponse response = new MemberResponse("ok", memberList);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Response status OK");
        log.debug("Response with list size of {} and content {}", memberList.size(), memberList);
        return HttpResponse.ok(objectMapper.writeValueAsString(response));
    }

}