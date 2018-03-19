package com.oracle.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oracle.util.PageInfo;
import com.oracle.vo.Student;
import com.oracle.vo.StudentChange;
import com.oracle.vo.User;

public interface StudentService {
	
	public void save(Student student);
	
	public List<Student> getAll();
	
	public Student getStudentById(Integer studentId);
	
	public void updateJob(Student student);
	
	public void changeStudent(StudentChange change);
	
	@SuppressWarnings("rawtypes")
	public List<Map> selectStudentChanges(int stuId);
	
	public List<Student> getJobList(Map<String,Object> map,PageInfo info);
	
	public Map<String,Object> getJobDetails(Map<String,Object> map);
	
	public List<Student> getStudentsByMap(Map<String,Object> map,PageInfo info);
	
	public void importStudent(Student stu);
	
	public List<Student> getStudentByClassId( Integer classId);
	/**
	 * 检查学生的身份证号是否存在
	 * 如果存在则返回学生所在的班级编号
	 * 如果不存在返回null
	 * @param idCard
	 * @return  存在返回班级，否则返回null
	 * @author djp
	 */
	String checkStuIdCardService(String idCard);
}
