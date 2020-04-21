package org.lukos.api.service;

import org.lukos.api.domain.Member;

import java.util.List;


public interface MemberService {

    Member add (String memberAsJSON);

    Member edit (String memberName, String memberAsJSON);

    Member remove (String memberName);

    Member findMember (String memberName);

    List<Member> getAll ();

}
