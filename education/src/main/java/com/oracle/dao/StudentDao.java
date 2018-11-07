package com.oracle.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oracle.util.PageInfo;
import com.oracle.vo.Student;
import com.oracle.vo.StudentChange;
import com.oracle.vo.Tclass;
import com.oracle.vo.User;

public interface StudentDao {
	 void save(Student student);

	 List<Student> getAll();
	
	 Student getStudentById(@Param("studentId") Integer studentId);
	 void updateJob(Student student);
	
	 void changeStudent(StudentChange change);
	
	/**
	 * 更新学员的转班，休学，退学后的信息
	 *
	 */
	 void changeStudentState(StudentChange change);
	
	 void changeStudentStateToMyself(StudentChange change);
	
	/**
	 * 班级的学生结课
	 * @param clazz
	 */
	 void insertClassStudentToEnd(Tclass clazz);
	
	
	 void insertClassStudentToBegin(Student student);
	
	
	 List<Map> selectStudentChanges(int stuId);
	
	
	 List<Student> getJobList(@Param("param") Map<String,Object> map,@Param("page") PageInfo info);
	
	 int getJobListCount(@Param("param") Map<String,Object> map,@Param("page") PageInfo info);
	
	 void updateStudentToEnd(Tclass tclass);
	
	 Map<String,Object> getJobDetails(@Param("param") Map<String,Object> map);
	
	 List<Student> getStudentsByMap(@Param("param") Map<String,Object> map,@Param("page") PageInfo info);
	
	 List<Student> getStudentByClassId(@Param("classId") Integer classId);
	
	 int getStudentsCountByMap(@Param("param") Map<String,Object> map,@Param("page") PageInfo info);

	 void importStudent(Student stu);

	/**
	 * 检查学生的身份证号是否存在
	 * 如果存在则返回学生所在的班级编号
	 * 如果不存在返回null
	 * @param idCard
	 * @return  存在返回班级，否则返回null
	 * @author djp
	 */
	String checkStuIdCard(String idCard);

	/**
	 * 修改学生基本信息,修改的字段参考添加的字段
	 * 也就是说可以添加哪些内容就可以修改哪些内容
	 * @param student 学生实体
	 * @author DJP
	 */
	void updateStudent(Student student);

	/**
	 * 删除学生表
	 * @param studentId
	 */
	void deleteStudentById(String studentId);

	/**
	 * 删除studentchange表中记录
	 * @param studentId
	 * @author DJP
	 */
	void deleteStudentChangeByStudentId(String studentId);
	
}
