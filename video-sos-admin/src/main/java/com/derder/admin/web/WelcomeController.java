package com.derder.admin.web;

import com.derder.business.model.CityUserStat;
import com.derder.business.model.CityUserStatDetail;
import com.derder.business.service.VideoService;
import com.derder.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {
	@Autowired
	VideoService videoService;

	@RequestMapping("/")
	public String index(Map<String, Object> model
				, @RequestParam(defaultValue = "20") int limitNum) {
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

	@RequestMapping("/stat_detail")
	public String statDetail(Map<String, Object> model
			, @RequestParam(defaultValue = "0755") String cityCode
			,@RequestParam(defaultValue = "1") int pageNo
			,@RequestParam(defaultValue = "20") int limitNum
			,String strStatDate) {
		int fromIndex = (pageNo-1)*limitNum;
		Date statDate = null;
		if (null != strStatDate){
			statDate = DateUtil.string2Date(strStatDate);
		}
		List<CityUserStatDetail> list = videoService.getCityUserStatDetailList(cityCode, statDate ,fromIndex,limitNum);
		model.put("cityUserStatDetailList",list);
		return "stat_detail";
	}
}
