package com.fa.training.group01.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fa.training.group01.domain_model.Part;
import com.fa.training.group01.domain_model.Quiz;
import com.fa.training.group01.domain_model.Section;
import com.fa.training.group01.service.IPartService;
import com.fa.training.group01.service.ISectionService;

@Controller
@RequestMapping(value = "/admin")
public class PartController {

	@Autowired
	IPartService partService;

	@Autowired
	ISectionService sectionService;

	@GetMapping(value = "/edit-part", params = { "id" })
	public String editPart(@RequestParam("id") int id, Quiz quiz,
			ModelMap model) {

		Part thePart = partService.findById(id);
		model.addAttribute("part", thePart);

		return "admin/edit-parts";
	}

	@RequestMapping(value = "/edit-part", params = { "id", "addSection" })
	public String addSection(@ModelAttribute("part") Part part,
			BindingResult bindingResult, ModelMap model) {
		Section newSection = sectionService.save(new Section());
		System.out.println(newSection);
		part.getSections().add(newSection);
		partService.addSection(part);
		System.out.println(part);

		return "admin/edit-parts";
	}

	@RequestMapping(value = { "/edit-part" }, params = { "id",
			"removeSection" })
	public String removeSection(@ModelAttribute("part") Part part,
			BindingResult bindingResult, ModelMap model,
			@RequestParam("removeSection") int sectionIndex) {
		part.getSections().remove(sectionIndex);
		partService.addSection(part);
		System.out.println(part);
		return "admin/edit-parts";
	}

}
