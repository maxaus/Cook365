package com.baev.cook365.service;

import java.util.List;
import com.baev.cook365.model.MeasureUnit;

/**
 * Сервис для работы с единицами измерения.
 *
 * @author Maxim Baev
 */
public interface MeasureUnitService {

	List<MeasureUnit> findAll();

	MeasureUnit findByName(String name);
}
