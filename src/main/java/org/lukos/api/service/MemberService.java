package org.lukos.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.lukos.api.domain.Member;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Singleton
public class MemberService implements IMemberService {

    Map<String, Member> memberList = new HashMap<>();

    @Override
    public Member add(String memberAsJSON) {
        Member member = null;
        try {
            member = jsonToMember(memberAsJSON);
            memberList.put(member.getName(), member);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Member edit(String memberName, String memberAsJSON) {
        Member member = null;
        try {
            Member memberInput = jsonToMember(memberAsJSON);
            member = findMember(memberName);
            if (member != null) {
                member.setPoints(memberInput.getPoints());
                log.info("Member {} edited to {}!", memberName, member);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return member;
    }

    @Override
    public Member remove(String memberName) {
        return memberList.remove(memberName);
    }

    @Override
    public Member findMember(String memberName) {
        return memberList.get(memberName);
    }

    @Override
    public List<Member> getAll() {
        return new ArrayList<>(memberList.values());
    }

    private Member jsonToMember (String memberAsJSON) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(memberAsJSON, Member.class);
    }
}
