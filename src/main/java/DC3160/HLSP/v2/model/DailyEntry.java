package DC3160.HLSP.v2.model;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "daily_entry")
public class DailyEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="user_id")
	private int userId;
	@Column(name="meal_calories")
	private Integer mealCalories;
	@Column(name="exercise_calories")
	private Integer exerciseCalories;
	@Column(name="exercise_time")
	private Integer exerciseTime;
	@Column(name="exercise_steps")
	private Integer exerciseSteps;
	@Column(name="work_time")
	private Integer workTime;
	@Column(name="work_stress")
	private Integer workStress;
	@Column(name="sleep_time")
	private Integer sleepTime;
	@Column(name="sleep_restfulness")
	private Integer sleepRestfulness;
	@Column(name="meditation_time")
	private Integer meditationTime;
	@Column(name="entry_date")
	private Date entryDate;

	public DailyEntry() {
		this.userId = 0;
		this.mealCalories = 0;
		this.exerciseCalories = 0;
		this.exerciseTime = 0;
		this.exerciseSteps = 0;
		this.workTime = 0;
		this.workStress = 0;
		this.sleepTime = 0;
		this.sleepRestfulness = 0;
		this.meditationTime = 0;
	}

	public DailyEntry(int userId, Integer mealCalories, Integer exerciseCalories, Integer exerciseTime, Integer exerciseSteps, Integer workTime,
			Integer workStress, Integer sleepTime, Integer sleepRestfulness, Integer meditationTime, Date entryDate) {
		super();
		this.userId = userId;
		this.mealCalories = mealCalories;
		this.exerciseCalories = exerciseCalories;
		this.exerciseTime = exerciseTime;
		this.exerciseSteps = exerciseSteps;
		this.workTime = workTime;
		this.workStress = workStress;
		this.sleepTime = sleepTime;
		this.sleepRestfulness = sleepRestfulness;
		this.meditationTime = meditationTime;
		this.entryDate = entryDate;
	}

	public static ArrayList<String> validateDailyEntry(Integer mealCalories, Integer exerciseCalories, Integer exerciseTime,
			Integer exerciseSteps, Integer workTime, Integer workStress, Integer sleepTime, Integer sleepRestfulness, Integer meditationTime) {
		ArrayList<String> errors = new ArrayList<String>();
		final int MINUTES_IN_DAY = 1440;
		final int HOURS_IN_DAY = 24;

		try {
			// validating mealCalories
			if (mealCalories < 0) {
				errors.add("Meals - Calories must be a positive number");
			}

			// validating exerciseCalories
			if (exerciseCalories < 0) {
				errors.add("Exercise - Calories burned must be a positive number");
			}

			// validating exerciseTime
			if (exerciseTime < 0) {
				errors.add("Exercise - Time exercising must be a positive number");
			}
			if (exerciseTime > MINUTES_IN_DAY) {
				errors.add("Exercise - Time must not exceed " + MINUTES_IN_DAY
						+ ", since this is the total minutes in a day");
			}

			// validating exerciseSteps
			if (exerciseSteps < 0) {
				errors.add("Exercise - Step count must be a positive number");
			}

			// validating workTime
			if (workTime < 0) {
				errors.add("Work - Time worked must be a positive number");
			}
			if (workTime > HOURS_IN_DAY) {
				errors.add("Work - Time worked must not exceed " + HOURS_IN_DAY
						+ ", since this is the total hours in a day");
			}

			// validating workStress
			if (workStress < 0 || workStress > 3) {
				errors.add("Invalid value entered for Work Stressfulness");
			}

			// validating sleepTime
			if (sleepTime < 0) {
				errors.add("Sleep - Time slept must be a positive number");
			}
			if (sleepTime > HOURS_IN_DAY) {
				errors.add("Sleep - Time slept must not exceed " + HOURS_IN_DAY
						+ ", since this is the total hours in a day");
			}

			// validating sleepRestfulness
			if (sleepRestfulness < 0 || sleepRestfulness > 3) {
				errors.add("Invalid value entered for Sleep Restfulness");
			}

			// validating meditationTime
			if (meditationTime < 0) {
				errors.add("Meditation - Time must be a positive number");
			}
			if (meditationTime > MINUTES_IN_DAY) {
				errors.add("Meditation - Time must not exceed " + MINUTES_IN_DAY
						+ ", since this is the total minutes in a day");
			}
		} catch (Exception e) {
			errors.add("Unable to submit daily entry. The problem is: " + e.getMessage());
		}

		return errors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getMealCalories() {
		return mealCalories;
	}

	public void setMealCalories(Integer mealCalories) {
		if (mealCalories == null)
			this.mealCalories = 0;
		else
			this.mealCalories = mealCalories;
	}

	public Integer getExerciseCalories() {
		return exerciseCalories;
	}

	public void setExerciseCalories(Integer exerciseCalories) {
		if (exerciseCalories == null)
			this.exerciseCalories = 0;
		else
			this.exerciseCalories = exerciseCalories;
	}

	public Integer getExerciseTime() {
		return exerciseTime;
	}

	public void setExerciseTime(Integer exerciseTime) {
		if (exerciseTime == null)
			this.exerciseTime = 0;
		else
			this.exerciseTime = exerciseTime;
	}

	public Integer getExerciseSteps() {
		return exerciseSteps;
	}

	public void setExerciseSteps(Integer exerciseSteps) {
		if (exerciseSteps == null)
			this.exerciseSteps = 0;
		else
			this.exerciseSteps = exerciseSteps;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		if (workTime == null)
			this.workTime = 0;
		else
			this.workTime = workTime;
	}

	public Integer getWorkStress() {
		return workStress;
	}

	public void setWorkStress(Integer workStress) {
		if (workStress == null)
			this.workStress = 0;
		else
			this.workStress = workStress;
	}

	public Integer getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(Integer sleepTime) {
		if (sleepTime == null)
			this.sleepTime = 0;
		else
			this.sleepTime = sleepTime;
	}

	public Integer getSleepRestfulness() {
		return sleepRestfulness;
	}

	public void setSleepRestfulness(Integer sleepRestfulness) {
		if (sleepRestfulness == null)
			this.sleepRestfulness = 0;
		else
			this.sleepRestfulness = sleepRestfulness;
	}

	public Integer getMeditationTime() {
		return meditationTime;
	}

	public void setMeditationTime(Integer meditationTime) {
		if (meditationTime == null)
			this.meditationTime = 0;
		else
			this.meditationTime = meditationTime;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
