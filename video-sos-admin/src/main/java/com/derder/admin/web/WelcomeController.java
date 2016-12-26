package com.derder.admin.web;

import com.derder.business.model.CityUserStat;
import com.derder.business.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {
	@Autowired
	VideoService videoService;

	@RequestMapping("/")
	public String index(Map<String, Object> model
				, @RequestParam(defaultValue = "10") int limitNum) {
		List<CityUserStat> cityUserStatList = videoService.cityUserStatList(limitNum);
		model.put("cityUserStatList",cityUserStatList);
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/email_setting")
	public String emailSetting() {
		return "email_setting";
	}
}
