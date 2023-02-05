package DC3160.HLSP.v2.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import DC3160.HLSP.v2.service.DailyEntryService;

@Controller
@SessionAttributes("userSession")
public class DailyEntryFormController {
	@Autowired
	private DailyEntryService dailyEntryService;
	
	private static final Logger LOG = Logger.getLogger(RegisterController.class.getName());

	@GetMapping(path = "/dailyEntryForm")
	public ModelAndView doGet() {
		LOG.info("Get /dailyEntryForm");
		return new ModelAndView("dailyEntryForm.html");
	}

	@PostMapping(path = "/dailyEntryForm")
	public ModelAndView doPost(@RequestParam Map<String, String> formData,
			@ModelAttribute("userSession") Session userSession) {
		LOG.info("Post /dailyEntryForm validating input...");
		// create variables for the entry form values
		int mealCalories = 0;
		int exerciseCalories = 0;
		int exerciseTime = 0;
		int exerciseSteps = 0;
		int workTime = 0;
		int workStress = 0;
		int sleepTime = 0;
		int sleepRestfulness = 0;
		int meditationTime = 0;

		// checks if request parameter has a value then gets value if it exists
		if (formData.get("formMealCalories") != "") {
			mealCalories = Integer.parseInt(formData.get("formMealCalories"));
		}

		if (formData.get("formExerciseCalories") != "") {
			exerciseCalories = Integer.parseInt(formData.get("formExerciseCalories"));
		}

		if (formData.get("formExerciseTime") != "") {
			exerciseTime = Integer.parseInt(formData.get("formExerciseTime"));
		}

		if (formData.get("formExerciseSteps") != "") {
			exerciseSteps = Integer.parseInt(formData.get("formExerciseSteps"));
		}

		if (formData.get("formWorkTime") != "") {
			workTime = Integer.parseInt(formData.get("formWorkTime"));
		}

		if (formData.get("formWorkStress") != "") {
			workStress = Integer.parseInt(formData.get("formWorkStress"));
		} else if (userSession != null) {
			workStress = userSession.getDailyEntry().getWorkStress();
		}

		if (formData.get("formSleepTime") != "") {
			sleepTime = Integer.parseInt(formData.get("formSleepTime"));
		}

		if (formData.get("formSleepRestfulness") != "") {
			sleepRestfulness = Integer.parseInt(formData.get("formSleepRestfulness"));
		} else if (userSession != null) {
			sleepRestfulness = userSession.getDailyEntry().getSleepRestfulness();
		}

		if (formData.get("formMeditationTime") != "") {
			meditationTime = Integer.parseInt(formData.get("formMeditationTime"));
		}

		// validate daily entry values and create errors if any are invalid
		ArrayList<String> formErrors = DailyEntry.validateDailyEntry(mealCalories, exerciseCalories, exerciseTime,
				exerciseSteps, workTime, workStress, sleepTime, sleepRestfulness, meditationTime);

		if (formErrors.size() == 0) {
			LOG.info("Input valid, entering daily entry");
			// use user in session to get id
			int userId = userSession.getUser().getId();

			DailyEntry newDailyEntry = new DailyEntry(userId, Integer.valueOf(mealCalories),
					Integer.valueOf(exerciseCalories), Integer.valueOf(exerciseTime), Integer.valueOf(exerciseSteps),
					Integer.valueOf(workTime), Integer.valueOf(workStress), Integer.valueOf(sleepTime),
					Integer.valueOf(sleepRestfulness), Integer.valueOf(meditationTime),
					new Date(System.currentTimeMillis()));
			
			dailyEntryService.add(newDailyEntry);

			// add the daily attributes to the form attributes to get daily totals
			// and assign the new attributes to the dailyEntry
			userSession.getDailyEntry().setMealCalories(mealCalories += userSession.getDailyEntry().getMealCalories());
			userSession.getDailyEntry().setExerciseCalories(exerciseCalories += userSession.getDailyEntry().getExerciseCalories());
			userSession.getDailyEntry().setExerciseTime(exerciseTime += userSession.getDailyEntry().getExerciseTime());
			userSession.getDailyEntry().setExerciseSteps(exerciseSteps += userSession.getDailyEntry().getExerciseSteps());
			userSession.getDailyEntry().setWorkTime(workTime += userSession.getDailyEntry().getWorkTime());
			userSession.getDailyEntry().setWorkStress(workStress);
			userSession.getDailyEntry().setSleepTime(sleepTime += userSession.getDailyEntry().getSleepTime());
			userSession.getDailyEntry().setSleepRestfulness(sleepRestfulness);
			userSession.getDailyEntry().setMeditationTime(meditationTime += userSession.getDailyEntry().getMeditationTime());
			
			// set the session attributes and return to the dashboard
			return new ModelAndView("dashboard.html");
		} else {
			LOG.info("Invalid input, errors: " + formErrors.toString());
			// redirect back to login with error for new login attempt
			ModelAndView modelAndView = new ModelAndView("dailyEntryForm.html");
			modelAndView.addObject("errors", formErrors);
			return modelAndView;
		}
	}
}
