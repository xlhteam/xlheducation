package com.oracle.service;

import java.util.List;
import java.util.Map;

import com.oracle.vo.Tclass;

public interface TclassService {
	 void save(Tclass c);
	
	 void endClass(Tclass c);
	
	 List<Tclass> getAll();
	
	 List<Tclass> getAll(String classType);
	
	 List<Tclass> getTclassByName(String className);
	
	 List<Tclass> getTclassByState(int state);
	
	 Tclass getClassById(int classId);
	
	 List<Tclass> getTclassByType(int typeId);
	
	 Map<String,Object> selectClassStatus(Tclass c);
	
	 List<Map<String,Object>> selectClassChanges(Tclass c);
}
