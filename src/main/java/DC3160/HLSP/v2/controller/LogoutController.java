package DC3160.HLSP.v2.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userSession")
public class LogoutController {
	
	private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());

	@GetMapping(path = "/logout")
	public String doGet(HttpSession session, Model model) {
		LOG.info("Get /logout");
		// invalidate user session attribute
		session.invalidate();
		model.asMap().clear();
		
		// direct to index
		return "index.html";
	}
}
