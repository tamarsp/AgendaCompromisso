package com.wyden.AgendaCompromisso.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;

@Controller
public class FrontController {
	 @PatchMapping("/{path:[^\\.]+}/**")
	    public String forward() {
	        return "forward:/index.html";
	    }
}
