package com.baev.cook365.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baev.cook365.dao.MeasureUnitDao;
import com.baev.cook365.model.MeasureUnit;
import com.baev.cook365.service.MeasureUnitService;

/**
 * Реализация {@link MeasureUnitService}.
 *
 * @author Maxim Baev
 */
@Service("measureUnitService")
public class MeasureUnitServiceImpl implements MeasureUnitService {

	@Autowired
	private MeasureUnitDao measureUnitDao;

	@Override
	@Transactional(readOnly = true)
	public List<MeasureUnit> findAll() {
		return measureUnitDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public MeasureUnit findByName(String name) {
		return measureUnitDao.findByName(name);
	}
}
