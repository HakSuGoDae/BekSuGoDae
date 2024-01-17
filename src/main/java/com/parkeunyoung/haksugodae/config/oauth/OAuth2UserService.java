package com.parkeunyoung.haksugodae.config.oauth;


import com.parkeunyoung.haksugodae.config.oauth.provider.KakaoUserInfo;
import com.parkeunyoung.haksugodae.config.oauth.provider.OAuth2UserInfo;
import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;

        if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String name = provider + "_" + providerId;
        Optional<Member> user = memberRepository.findByName(name);

        HashMap<String, Object> nameMap = new HashMap<>();
        nameMap.put("name", name);

        if (!user.isPresent()) {
            memberRepository.save(
                    Member.builder()
                            .name(name)
                            .build()
            );
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                nameMap, "name"
        );
    }
}
