package com.oracle.service;

import java.util.List;

import com.oracle.vo.School;

public interface SchoolService {
	 List<School> getAll();
	
	 void save(School school);
}
