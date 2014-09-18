package com.baev.cook365.form;

/**
 * Объект, представляющий форму поиска.
 *
 * @author Maxim Baev
 */
public class SearchForm {

	/**
	 * Строка поиска.
	 */
	private String searchString;

	/**
	 * Номер страницы результатов.
	 */
	private int page = 0;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
