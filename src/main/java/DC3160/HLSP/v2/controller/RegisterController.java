package DC3160.HLSP.v2.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import DC3160.HLSP.v2.model.DailyEntry;
import DC3160.HLSP.v2.model.User;
import DC3160.HLSP.v2.model.Session;
import DC3160.HLSP.v2.service.DailyEntryService;
import DC3160.HLSP.v2.service.UserService;

@Controller
@SessionAttributes("userSession")
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private DailyEntryService dailyEntryService;
	
	@ModelAttribute(name = "userSession")
    public Session setUserSession() {
        return new Session();
    }
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping(path = {"/register", ""})
	public String doGet(@ModelAttribute("userSession") Session userSession) {
		// if the session exists go to the dashboard
		if (userSession.getUser() != null) {
			return "dashboard.html";
		}
		// otherwise return the register view
		else {
			return "index.html";
		}
	}
	
	@PostMapping(path = "/register")
	public ModelAndView doPost(@RequestParam Map<String, String> formData, 
			@ModelAttribute("userSession") Session userSession) {
        // get the input credentials
        String email = formData.get("email");
        String password = formData.get("password");
        
        // check if user with email exists in db
        Integer userId = userService.getIdForEmail(email);
        
	    // validate user credentials and create errors if credentials are invalid or user exists in db
	    ArrayList<String> credentialErrors = User.validateCredentials(email, password);
	    if (userId != null) {
	    	credentialErrors.add("A user with that email already exists");
	    }
        
        // if there is a problem creating the user, such as one already exists, reload page
        if (credentialErrors.size() > 0) {
        	// set the list of errors as a model variable
        	ModelAndView modelAndView = new ModelAndView("index.html");
        	modelAndView.addObject("errors", credentialErrors);
        	// reload page to display errors
        	return modelAndView;
        } else {
        	// insert new user record in database, will return user with new id
        	// set the sessionUser as this new user
        	User user = userService.add(new User(email, password));
        	userSession.setUser(user);
        	DailyEntry dailyEntry = dailyEntryService.add(new DailyEntry());
        	userSession.setDailyEntry(dailyEntry);
        	
        	// direct to dashboard
        	return new ModelAndView("dashboard.html");
        }
	}
}
