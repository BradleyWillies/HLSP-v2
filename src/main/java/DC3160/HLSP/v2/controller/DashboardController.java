package DC3160.HLSP.v2.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import DC3160.HLSP.v2.model.Session;
import DC3160.HLSP.v2.service.UserService;

@Controller
@SessionAttributes("userSession")
public class DashboardController {
	@Autowired
	private UserService userService;
	
	private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());

	@PostMapping(path = "/updateFilter")
	public @ResponseBody void updateFilter(@RequestParam Map<String, String> formData,
			@ModelAttribute("userSession") Session userSession) {
		LOG.info("Post /updateFilter with filter value: " + formData.get("filter") + " for user id: " + userSession.getUser().getId());
	    userService.setDashboardFilter(formData.get("filter"), userSession.getUser().getId());
		userSession.getUser().setDashboardFilter(formData.get("filter"));
    }
	
	@GetMapping(path = "/dashboard")
	public String getDashboard() {
		LOG.info("Get /dashboard");
		return "dashboard.html";
	}
}
