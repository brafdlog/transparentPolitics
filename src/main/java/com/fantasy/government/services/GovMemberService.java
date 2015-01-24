package com.fantasy.government.services;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.fantasy.government.services.data.GovMember;

@Component
public class GovMemberService {

    public Integer getMemberGrade(GovMember member) {
        return new Random().nextInt(100);
    }
}
