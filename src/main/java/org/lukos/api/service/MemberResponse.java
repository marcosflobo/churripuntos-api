package org.lukos.api.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.lukos.api.domain.Member;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("member")
    private List<Member> members;

    public MemberResponse() {
    }

    public MemberResponse(String message, List<Member> members) {
        this.message = message;
        this.members = members;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
