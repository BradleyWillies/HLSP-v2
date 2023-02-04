package DC3160.HLSP.v2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userSession")
public class LogoutController {

	@GetMapping(path = "/logout")
	public String doGet(HttpSession session, Model model) {
		// invalidate user session attribute
		session.invalidate();
		model.asMap().clear();
		
		// direct to index
		return "index.html";
	}
}
