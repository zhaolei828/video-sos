package com.derder.admin.web;

import com.derder.business.model.City;
import com.derder.business.model.CityUserStat;
import com.derder.business.model.CityUserStatDetail;
import com.derder.business.service.CityService;
import com.derder.business.service.VideoService;
import com.derder.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@Autowired
	CityService cityService;

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
			, @RequestParam(defaultValue = "") String cityCode
			,@RequestParam(defaultValue = "1") int pageNumber
			,@RequestParam(defaultValue = "20") int limitNum
			,String strStatDate) {
		Date statDate = null;
		if (null != strStatDate){
			statDate = DateUtil.string2Date(strStatDate);
		}
		City city = cityService.getCity(cityCode);
		Page<CityUserStatDetail> page = videoService.getCityUserStatDetailList(cityCode, statDate ,pageNumber,limitNum);
		model.put("page",page);
		model.put("city",city);
		return "stat_detail";
	}
}
