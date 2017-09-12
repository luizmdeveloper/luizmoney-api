package com.luizmoneyapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmoneyapi.config.property.LuizMoneyApiProperty;

@RestController
@RequestMapping("/tokens")
public class Tokens {
	
	@Autowired
	private LuizMoneyApiProperty LuizMoneyApiProperty;

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {		
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(LuizMoneyApiProperty.getSeguranca().isEnabledHttps()); 
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
	}
}
