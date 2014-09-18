package com.baev.cook365.model.calendar;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Объект для представления месяца в календаре.
 *
 * @author Maxim Baev
 */
public class Month {

	/**
	 * Номер текущего дня в месяце.
	 */
    private int currentDayOfMonth;

	/**
	 * Год.
	 */
    private int year;

	/**
	 * Номер месяца в году.
	 */
    private int monthOfYear;

	/**
	 * Недели в месяце.
	 */
    private List<Week> weeks;

	/**
	 * Первые дни в календаре, не входящие в текущий месяц.
	 */
    private List<Day> offsetDays;

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    public List<Day> getOffsetDays() {
        return offsetDays;
    }

    public void setOffsetDays(List<Day> offsetDays) {
        this.offsetDays = offsetDays;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public int getCurrentDayOfMonth() {
        return currentDayOfMonth;
    }

    public void setCurrentDayOfMonth(int currentDayOfMonth) {
        this.currentDayOfMonth = currentDayOfMonth;
    }

    public int getPrevMonth() {
        return monthOfYear != 1 ? monthOfYear - 1 : 12;
    }

    public int getPrevMonthYear() {
        return monthOfYear != 1 ? year : year -1;
    }

    public int getNextMonth() {
        return monthOfYear != 12 ? monthOfYear + 1 : 1;
    }

    public int getNextMonthYear() {
        return monthOfYear != 12 ? year : year + 1;
    }

    public String getName() {
        String name = "Январь";
        switch (monthOfYear) {
            case 1:
                name = "Январь";
                break;
            case 2:
                name = "Февраль";
                break;
            case 3:
                name = "Март";
                break;
            case 4:
                name = "Апрель";
                break;
            case 5:
                name = "Май";
                break;
            case 6:
                name = "Июнь";
                break;
            case 7:
                name = "Июль";
                break;
            case 8:
                name = "Август";
                break;
            case 9:
                name = "Сентябрь";
                break;
            case 10:
                name = "Октябрь";
                break;
            case 11:
                name = "Ноябрь";
                break;
            case 12:
                name = "Декабрь";
                break;
        }
        return name;
    }
}
