package com.fa.training.group01.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.service.ISectionService;

@Controller
@RequestMapping(value = "/admin")
public class SectionController {
	@Autowired
	@Qualifier("hypermediaRestTemplate")
	private RestTemplate template;

	@Autowired
	private ISectionService sectionService;
	
	
}
