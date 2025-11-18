package com.wyden.AgendaCompromisso.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class FrontController {
	 @RequestMapping("/{path:[^\\.]+}/**")
	    public String forward() {
	        return "forward:/index.html";
	    }
}
