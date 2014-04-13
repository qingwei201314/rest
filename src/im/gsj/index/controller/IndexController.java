package im.gsj.index.controller;

import im.gsj.index.service.IndexService;
import im.gsj.user.dto.User;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Resource
	private IndexService indexService;
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index() {
		User user = indexService.getUser("4028818240b5fe8e0140b5ff11370000");
		return "/index";
	}
	
}
