package pl.lukaz.poligon.gae.workservice.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private Log log=LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value="hello")
	public void hello(Model model){
		model.addAttribute("message", "Hello world");
	}
}
