package com.hortonworks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shubham
 */
@RequestMapping("/api/**")
@RestController
public class TestContoller {
	@Autowired
	private TestService testService;

	 @RequestMapping("/")
	    String home() {
		 String value = testService.check("select * from SYSTEM.STATS");
		 return value;
	 }
}
