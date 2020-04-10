package org.lukos.api.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.lukos.api.domain.Member;
import org.lukos.api.service.MemberResponse;
import org.lukos.api.service.MemberService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller("/api/members")
public class MemberController{

    @Inject
    MemberService memberService;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<String> index() {
        try {
            return getMemberBodyResponse(memberService.getAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HttpResponse.serverError();
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<String> add(@Body String text) {
        Member a = memberService.add(text);
        List<Member> memberList = new ArrayList<>(1);
        memberList.add(a);
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
            memberService.remove(key);
            return getMemberBodyResponse(memberService.getAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HttpResponse.serverError();
    }

    private HttpResponse<String> getMemberBodyResponse(List<Member> memberList)
            throws JsonProcessingException {
        MemberResponse response = new MemberResponse("ok", memberList);
        ObjectMapper objectMapper = new ObjectMapper();
        return HttpResponse.ok(objectMapper.writeValueAsString(response));
    }

}