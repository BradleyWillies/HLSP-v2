package DC3160.HLSP.v2.model;

public class Session {
	User user;
	DailyEntry dailyEntry;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DailyEntry getDailyEntry() {
		return dailyEntry;
	}

	public void setDailyEntry(DailyEntry dailyEntry) {
		this.dailyEntry = dailyEntry;
	}
}
