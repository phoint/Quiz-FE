package com.fa.training.group01.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class PartController {
	
	@RequestMapping(value = "/edit-part", params = {"id"})
	public String editPart() {
		
		
		
		return "admin/edit-part";
	}

}
