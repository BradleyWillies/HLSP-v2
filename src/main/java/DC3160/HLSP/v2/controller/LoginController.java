package DC3160.HLSP.v2.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import DC3160.HLSP.v2.model.DailyEntry;
import DC3160.HLSP.v2.model.Session;
import DC3160.HLSP.v2.model.User;
import DC3160.HLSP.v2.service.DailyEntryService;
import DC3160.HLSP.v2.service.UserService;

@Controller
@SessionAttributes("userSession")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private DailyEntryService dailyEntryService;
	
	private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());

	@ModelAttribute(name = "userSession")
	public Session setUserSession() {
        return new Session();
    }

	@GetMapping(path = "/login")
	public String doGet(@ModelAttribute("userSession") Session userSession) {
		LOG.info("Get /login");
		// if the session exists go to the dashboard
		if (userSession.getUser() != null) {
			return "dashboard.html";
		}
		// otherwise return the login view
		else {
			return "login.html";
		}
	}

	@PostMapping(path = "/login")
	public ModelAndView doPost(@RequestParam Map<String, String> formData, 
			@ModelAttribute("userSession") Session userSession) {
		LOG.info("Post /login with email: " + formData.get("email"));
		// get the input credentials
		String email = formData.get("email");
		String password = formData.get("password");

		// get the userId from the database using the input credentials
		Integer userId = userService.validateUserCredentials(email, password);
		
		// if user exists in database direct them to the dashboard
		if (userId != null) {
			// get the user from the database and set as session attribute
			User user = userService.findById(userId).get();
			userSession.setUser(user);
        	
        	// get dailyEntry for user with totals of entries for today's date
        	DailyEntry dailyEntry = dailyEntryService.getTodaysDailyEntryForUser(userId);
        	userSession.setDailyEntry(dailyEntry);

			// add to model and session attributes and direct to dashboard
        	return new ModelAndView("dashboard.html");
		} else {
			// redirect back to login with error for new login attempt
			ModelAndView modelAndView = new ModelAndView("login.html");
        	modelAndView.addObject("error", "Invalid username and password");
        	return modelAndView;
		}
	}
}
