package com.baev.cook365.model.calendar;


import java.util.List;

/**
 * Объект для представления недели в календаре.
 *
 * @author Maxim Baev
 */
public class Week {

	/**
	 * Дни в неделе.
	 */
	private List<Day> days;

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}
}
