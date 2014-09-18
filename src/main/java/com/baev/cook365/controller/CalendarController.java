package com.baev.cook365.controller;

import java.util.*;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.baev.cook365.model.calendar.Day;
import com.baev.cook365.model.calendar.Month;
import com.baev.cook365.model.calendar.Week;

/**
 * Контроллер для показа календаря и вычисления текущей даты.
 *
 * Created by Maxim Baev.
 * Date: 05.10.11
 * Time: 11:09
 */
@Controller
public class CalendarController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CalendarController.class);

    @RequestMapping(value = "/calendar", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView showCalendar(@RequestParam("month") String monthOfYearStr)  {
        DateTime dt = new DateTime();
		int currentMonthOfYear = dt.getMonthOfYear();

		int monthOfYear;
		if (monthOfYearStr != null && !monthOfYearStr.isEmpty()) {
			monthOfYear = Integer.valueOf(monthOfYearStr);
		} else {
			monthOfYear = currentMonthOfYear;
		}
        Month month = new Month();
        month.setMonthOfYear(monthOfYear);
        return new ModelAndView("calendar", "month", month);
    }

	@RequestMapping(value = "/calendar_body", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showCalendarBody(@RequestParam("monthOfYear") String monthOfYearStr, @RequestParam("year") String yearStr) {
		LOGGER.debug("Show calendar");

		DateTime dt = new DateTime();

		int currentDayOfMonth = 0;
		int currentMonthOfYear = dt.getMonthOfYear();
        int currentYear = dt.getYear();

        //Adjust month

		int monthOfYear;
		if (monthOfYearStr != null && !monthOfYearStr.isEmpty()) {
			monthOfYear = Integer.valueOf(monthOfYearStr);
		} else {
			monthOfYear = currentMonthOfYear;
		}

		if (monthOfYear == currentMonthOfYear) {
			currentDayOfMonth = dt.getDayOfMonth();
		} else if (monthOfYear - currentMonthOfYear > 0) {
			dt = dt.plusMonths(monthOfYear - currentMonthOfYear);
		} else {
			dt = dt.minusMonths(currentMonthOfYear - monthOfYear);
		}

        //Adjust year

        int year;
        if (yearStr != null && !yearStr.isEmpty()) {
			year = Integer.valueOf(yearStr);
		} else {
			year = currentYear;
		}

        if (year - currentYear > 0) {
			dt = dt.plusYears(year - currentYear);
		} else {
			dt = dt.minusYears(currentYear - year);
		}

		DateTime actualDate = dt.minusDays(dt.getDayOfMonth() - 1);
		dt = dt.minusDays(dt.getDayOfMonth() - 1);
		int monthStartDayOfWeek = dt.getDayOfWeek();
		int dayOffset = monthStartDayOfWeek - 1;
		int dayOfMonthStart = dt.getDayOfYear();
		dt.getMonthOfYear();
		dt = dt.plusMonths(1);

		int dayOfMonthEnd = dt.getDayOfYear();
		int monthEndDayOfWeek = dt.getDayOfWeek() - 1;

        // dayOfMonthEnd=1 if current month is december (month length is 31 days)
		int daysInMonth = dayOfMonthEnd != 1 ? dayOfMonthEnd - dayOfMonthStart : 31;

		int weeksInMonth = (int) Math.ceil((double) daysInMonth / 7);
		if (dayOffset + daysInMonth > weeksInMonth * 7) {
			weeksInMonth++;
		}

		Month month = new Month();
		List<Day> offsetDays = new ArrayList<Day>(dayOffset);
		for (int i = 0; i < dayOffset; i++) {
			Day day = new Day();
			offsetDays.add(day);
		}
        month.setYear(actualDate.getYear());
        month.setMonthOfYear(actualDate.getMonthOfYear());
		month.setOffsetDays(offsetDays);
		month.setCurrentDayOfMonth(currentDayOfMonth);
		List<Week> weeks = new ArrayList<Week>(weeksInMonth);
		for (int i = 0; i < weeksInMonth; i++) {
			Week week = new Week();
			List<Day> days = new ArrayList<Day>();
			int daysInWeek = 7;
			if (i == 0) {
				daysInWeek = 7 - dayOffset;
			} else if (i == weeksInMonth - 1) {
				daysInWeek = monthEndDayOfWeek;
			}
			for (int j = 0; j < daysInWeek; j++) {
				Day day = new Day();
				day.setDayOfMonth(actualDate.getDayOfMonth());
				day.setDayOfWeek(actualDate.getDayOfWeek());
				actualDate.getMillis();
				days.add(day);
				actualDate = actualDate.plusDays(1);
			}

			week.setDays(days);
			weeks.add(week);
		}
		month.setWeeks(weeks);

		return new ModelAndView("calendar_body", "month", month);
	}
}
