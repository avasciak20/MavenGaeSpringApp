package pl.lukaz.poligon.gae.workservice.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lukaz.poligon.gae.workservice.model.NewUser;
import pl.lukaz.poligon.gae.workservice.model.User;
import pl.lukaz.poligon.gae.workservice.server.UserRepository;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserRepository userRepository;
	
	private static Log log=LogFactory.getLog(AccountController.class);
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newUserForm(Model model){
		model.addAttribute("newUserForm",new NewUser());
		
		return "account/new";
	}
	
	@RequestMapping(value ="", method = RequestMethod.POST)
	public String processCreateForm(@ModelAttribute("newUserForm") NewUser newUser, Model model){
		
		User user=new User(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword());
		userRepository.save(user);
		
		model.addAttribute(user);
		
		return "redirect:registration_ok";
	}
}
