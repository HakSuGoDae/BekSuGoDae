package com.parkeunyoung.haksugodae.config.oauth.provider;

public interface OAuth2UserInfo {

    String getProviderId();
    String getProvider();
    String getName();
}
