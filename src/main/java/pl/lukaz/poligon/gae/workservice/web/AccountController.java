package pl.lukaz.poligon.gae.workservice.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import pl.lukaz.poligon.gae.workservice.model.UserFormBean;
import pl.lukaz.poligon.gae.workservice.model.PositionFormBean;
import pl.lukaz.poligon.gae.workservice.model.User;
import pl.lukaz.poligon.gae.workservice.server.UserRepository;
import pl.lukaz.poligon.gae.workservice.web.editor.GoogleDatastoreKeyEditor;
import pl.lukaz.poligon.gae.workservice.web.exception.ResourceNotFoundException;

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
		binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class));
		binder.registerCustomEditor(Key.class, new GoogleDatastoreKeyEditor());
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newUserForm(Model model){
		UserFormBean newUser = new UserFormBean();
		newUser.getPositions().add(new PositionFormBean("bamab1","tamtam"));
		newUser.getPositions().add(new PositionFormBean("bamab2","tamtamasdf"));
		newUser.getPositions().add(new PositionFormBean("bamab3","tamtamagaga"));
		
		model.addAttribute("newUserForm",newUser);
		
		return "account/new";
	}
	
	@RequestMapping(value ="new", method = RequestMethod.POST)
	public String processCreateForm(@ModelAttribute("newUserForm") @Valid UserFormBean newUser, BindingResult result, Model model){
		
		if(result.hasErrors())return "account/new";
		
		userRepository.save(newUser.buildUserClass());
		
		return "redirect:registration_ok";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void list(Model model){
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
	}
	
	@RequestMapping(value = "/id/{stringKey}", method = RequestMethod.GET)
	public String show(@PathVariable String stringKey, Model model){
		Key key=null;
		if(stringKey!=null){
			key=KeyFactory.stringToKey(stringKey);
		}
		
		if(key!=null){
			User user=userRepository.findById(key);
			model.addAttribute(user);
			return "account/show";
		}else{
			throw new ResourceNotFoundException();
		}
	}
}
