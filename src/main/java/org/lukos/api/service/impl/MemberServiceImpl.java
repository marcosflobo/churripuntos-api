package org.lukos.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.lukos.api.domain.Member;
import org.lukos.api.service.MemberService;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Singleton
public class MemberServiceImpl implements MemberService {

    final Map<String, Member> memberList = new HashMap<>();

    @Override
    public Member add(String memberAsJSON) {
        Member member = null;
        try {
            member = jsonToMember(memberAsJSON);
            memberList.put(member.getName(), member);
            log.info("Member added with name {} and points {} ", member.getName(), member.getPoints());
        } catch (JsonProcessingException e) {
            log.error("Error parsing to JSON adding new member. Exception message: {}", e.getMessage());
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
            } else {
                log.info("Member '{}' not found in edit action.", memberName);
            }
        } catch (JsonProcessingException e) {
            log.error("Error parsing to JSON editing member. Member name: {}. Exception message: {}", memberName,
                    e.getMessage());
        }

        return member;
    }

    @Override
    public Member remove(String memberName) {
        Member ret = memberList.remove(memberName);
        log.info("Member '{}' removed", memberName);
        return ret;
    }

    @Override
    public Member findMember(String memberName) {
        log.info("Find member {}...", memberName);
        return memberList.get(memberName);
    }

    @Override
    public List<Member> getAll() {
        return new ArrayList<>(memberList.values());
    }

    private Member jsonToMember (String memberAsJSON) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.debug("Parsing member from JSONString to Object: {}", memberAsJSON);
        return objectMapper.readValue(memberAsJSON, Member.class);
    }
}
