package com.example.helloworld;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberRepository memberRepository;

    // 모든 사용자 조회
    @GetMapping
    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    // 새로운 사용자 추가
    @PostMapping
    public Member createUser(@RequestBody Member member) {
        return memberRepository.save(member);
    }
}