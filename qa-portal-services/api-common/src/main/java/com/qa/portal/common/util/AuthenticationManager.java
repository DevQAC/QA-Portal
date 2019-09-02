package com.qa.portal.common.util;

public interface AuthenticationManager {

	String getAuthentication(String clientId, String clientSecret);
}
