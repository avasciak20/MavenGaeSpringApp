package pl.lukaz.poligon.gae.workservice.web;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lukaz.poligon.gae.workservice.model.NewUser;
import pl.lukaz.poligon.gae.workservice.model.PositionFormBean;
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
		binder.setAllowedFields(new String[] {"firstName", "lastName", "email", "password", "*companyName",  "*positionName"});
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newUserForm(Model model){
		NewUser newUser = new NewUser();
		newUser.getPositions().add(new PositionFormBean("bamab1","tamtam"));
		newUser.getPositions().add(new PositionFormBean("bamab2","tamtamasdf"));
		newUser.getPositions().add(new PositionFormBean("bamab3","tamtamagaga"));
		
		model.addAttribute("newUserForm",newUser);
		
		return "account/new";
	}
	
	@RequestMapping(value ="new", method = RequestMethod.POST)
	public String processCreateForm(@ModelAttribute("newUserForm") @Valid NewUser newUser, BindingResult result, Model model){
		
		if(result.hasErrors())return "account/new";
		
		User user=new User(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword());
		userRepository.save(user);
		
		model.addAttribute(user);
		
		return "redirect:registration_ok";
	}
}
