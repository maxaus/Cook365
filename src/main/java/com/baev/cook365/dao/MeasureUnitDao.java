package com.baev.cook365.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.baev.cook365.model.MeasureUnit;

/**
 * @author Maxim Baev
 */
@Repository("measureUnitDao")
public interface MeasureUnitDao extends JpaRepository<MeasureUnit, Long> {

	MeasureUnit findByName(String name);
}
