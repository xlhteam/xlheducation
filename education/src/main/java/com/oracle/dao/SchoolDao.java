package com.oracle.dao;

import java.util.List;

import com.oracle.vo.School;

public interface SchoolDao {

	public List<School> getAll();
	
	public void save(School school);
}
