package com.fa.training.group01.controller.admin;

import com.fa.training.group01.domain_model.Topic;
import com.fa.training.group01.service.impl.TopicServices;
import com.fa.training.group01.util.UrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TopicController {
    @Autowired
    TopicServices<Integer, Topic> generic;

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public String topicPage(Model model) {
        List<Topic> list = generic.ListTemp(UrlConstant.LIST_All_TOPIC, Topic[].class);
        model.addAttribute("listTopic", list);
        model.addAttribute("newTopic", new Topic());
        return "admin/topic-admin";
    }

    @RequestMapping(value = "/insertTopic", method = RequestMethod.POST)
    public RedirectView topicInsert(@ModelAttribute Topic topicEntity, RedirectAttributes redir) {
        boolean b = generic.InsertTemp(UrlConstant.INSERT_TOPIC, topicEntity);
        if (b == false) {
            redir.addFlashAttribute("error", "Insert failed");
        }
        return new RedirectView("/Quiz-FE/admin/topic");
    }

    @GetMapping("/editTopic/{id}")
    public RedirectView getTopic(@PathVariable int id, RedirectAttributes redir, Model model) {
        Topic entity = generic.GetTempById(UrlConstant.GET_TOPIC, Topic.class, id);
        if (entity != null) {
            redir.addFlashAttribute("topic", entity);
        } else {
            redir.addFlashAttribute("error", "get topic failed by id : " + id);
        }
        return new RedirectView("/Quiz-FE/admin/topic");
    }

    @RequestMapping(value = "/updateTopic", method = RequestMethod.POST)
    public RedirectView topicUpdate(@ModelAttribute Topic topic, RedirectAttributes redir) {
        boolean b = generic.UpdateTemp(UrlConstant.UPDATE_TOPIC, topic);
        if (b == false) {
            redir.addFlashAttribute("error", "Update failed");
        }
        return new RedirectView("/Quiz-FE/admin/topic");
    }

    @RequestMapping(value = "/deleteTopic", method = RequestMethod.GET)
    public RedirectView topicDelete(@RequestParam int id, Model model, RedirectAttributes redir) {
        boolean b = generic.DeleteTemp(UrlConstant.DELETE_TOPIC_ID, id);
        if (b == false) {
            redir.addFlashAttribute("error", "delete topic failed by id : " + id);
        }
        return new RedirectView("/Quiz-FE/admin/topic");
    }
}
