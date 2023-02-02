package DC3160.HLSP.v2.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DC3160.HLSP.v2.model.DailyEntry;
import DC3160.HLSP.v2.repository.DailyEntryRepository;

@Service
public class DailyEntryService {
	@Autowired
	private DailyEntryRepository dailyEntryRepository;
	
	public DailyEntry add(DailyEntry dailyEntry) {
		return dailyEntryRepository.save(dailyEntry);
	}
	
	public DailyEntry getTodaysDailyEntryForUser(Integer userId) {
		DailyEntry returnDailyEntry = new DailyEntry();
		Iterable<DailyEntry> allDailyEntries = dailyEntryRepository.findAll();
		for (DailyEntry dailyEntry : allDailyEntries) {
			if (dailyEntry.getEntryDate() != null) {
				if (dailyEntry.getUserId() == userId && dailyEntry.getEntryDate().toLocalDate().equals(LocalDate.now())) {
					returnDailyEntry.setMealCalories(returnDailyEntry.getMealCalories() + dailyEntry.getMealCalories());
					returnDailyEntry.setExerciseCalories(returnDailyEntry.getExerciseCalories() + dailyEntry.getExerciseCalories());
					returnDailyEntry.setExerciseTime(returnDailyEntry.getExerciseTime() + dailyEntry.getExerciseTime());
					returnDailyEntry.setExerciseSteps(returnDailyEntry.getExerciseSteps() + dailyEntry.getExerciseSteps());
	            	returnDailyEntry.setWorkTime(returnDailyEntry.getWorkTime() + dailyEntry.getWorkTime());
	            	returnDailyEntry.setWorkStress(dailyEntry.getWorkStress());
	            	returnDailyEntry.setSleepTime(returnDailyEntry.getSleepTime() + dailyEntry.getSleepTime());
	            	returnDailyEntry.setSleepRestfulness(dailyEntry.getSleepRestfulness());
	            	returnDailyEntry.setMeditationTime(returnDailyEntry.getMeditationTime() + dailyEntry.getMeditationTime());
				}
			}
		}
		return returnDailyEntry;
	}
}
