package com.baev.cook365.model.calendar;

/**
 * Объект для представления дня в календаре.
 *
 * @author Maxim Baev
 */
public class Day {

	/**
	 * Номер дня недели.
	 */
	private int dayOfWeek;

	/**
	 * Номер дня в месяце.
	 */
	private int dayOfMonth;

	/**
	 * Название дня (Понедельник, Вторник и т.д.)
	 */
	private String name;

	/**
	 * Ссылка для перехода на страницу с рецептами за день.
	 */
	private String link;

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public boolean isHoliday() {
		return dayOfWeek == 6 || dayOfWeek == 7;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
