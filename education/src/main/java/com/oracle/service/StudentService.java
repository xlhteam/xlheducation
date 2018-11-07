package com.oracle.service;

import java.util.List;
import java.util.Map;
import com.oracle.util.PageInfo;
import com.oracle.vo.Student;
import com.oracle.vo.StudentChange;
public interface StudentService {
	
	 void save(Student student);
	
	 List<Student> getAll();
	
	 Student getStudentById(Integer studentId);
	
	 void updateJob(Student student);
	
	 void changeStudent(StudentChange change);
	

	 List<Map> selectStudentChanges(int stuId);
	
	 List<Student> getJobList(Map<String,Object> map,PageInfo info);
	
	 Map<String,Object> getJobDetails(Map<String,Object> map);
	
	 List<Student> getStudentsByMap(Map<String,Object> map,PageInfo info);
	
	 void importStudent(Student stu);
	
	 List<Student> getStudentByClassId( Integer classId);
	/**
	 * 检查学生的身份证号是否存在
	 * 如果存在则返回学生所在的班级编号
	 * 如果不存在返回null
	 * @param idCard 学生id
	 * @return  存在返回班级，否则返回null
	 * @author djp
	 */
	String checkStuIdCardService(String idCard);

	/**
	 * 修改学生基本信息
	 * @param student 学生实体
	 * @author DJP
	 */
	void updateStudent(Student student);

	/**
	 * 根据指定id删除学生
	 * @param studentId
	 */
	void deleteStudentById(String studentId);

}
