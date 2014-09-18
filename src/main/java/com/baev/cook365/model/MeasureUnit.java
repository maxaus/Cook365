package com.baev.cook365.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Единица измерения.
 *
 * @author Maxim Baev
 */
@Entity
@Table(name = "measure_unit")
public class MeasureUnit extends AbstractEntity {

	private static final long serialVersionUID = 9181747766383908936L;

	/**
	 * Название.
	 */
	private String name;

	/**
	 * Множитель, для вычисления относительно базовой единицы измерения (например, 1000, если базовая единица измерения грамм, а текущая - килограмм).
	 */
	private int ratio;

	/**
	 * Базовая единица измерения.
	 */
	@ManyToOne
	@JoinColumn(name = "parentId")
	private MeasureUnit parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public MeasureUnit getParent() {
		return parent;
	}

	public void setParent(MeasureUnit parent) {
		this.parent = parent;
	}
}
