package com.oracle.handler;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.service.StudentService;
import com.oracle.util.PageInfo;
import com.oracle.vo.Student;
import com.oracle.vo.StudentChange;


@RequestMapping("/student")
@Controller
public class StudentHandler {

	@Autowired
	StudentService studentService;
	
	//增加学生
	@RequestMapping("/save")
	public String save(Student stu){
		studentService.save(stu);
		return "redirect:getAll";
	}
	
	//更新就业信息
	@RequestMapping("/updateJob")
	public String updateJob(Student stu,String flag){
		studentService.updateJob(stu);
		if("class".equals(flag)){
			return "redirect:/class/listStudentsByClassId?classId="+stu.getTclass().getClassId();
		}else{
			return "redirect:/student/getAll";
		}
		
	}
	
	//直接访问JSP
	@RequestMapping("/{path}")
	public String path(@PathVariable("path") String p){		
		return "student/"+p;
	}
	
	//查询所有班级
	@RequestMapping("/getAll")
	public String getAll(Map<String,Object> map,Integer schoolId,String className,String stuName,HttpServletRequest request){
		PageInfo info=new PageInfo(request);
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		
		paramMap.put("schoolId", schoolId);
		paramMap.put("className", className);
		paramMap.put("stuName", stuName);
		//存储条件
		map.put("paramMap", paramMap);
		List<Student> list=studentService.getStudentsByMap(paramMap,info);
		map.put("list", list);
		return "student/listStudent";
	}
	
	
	//查询学生信息,显示学员的详细信息
	@RequestMapping("/getStudentById")
	public String getStudentById(Map<String,Object> map,Integer studentId){
		Student stu=studentService.getStudentById(studentId);
		map.put("stu", stu);
		@SuppressWarnings("rawtypes")
		List<Map> changes=studentService.selectStudentChanges(studentId);
		map.put("changes", changes);
		return "student/viewStudentDetail";
	}
	
	
	//查询出学生的显示,显示更新就业信息页面
	@RequestMapping("/viewStudentJobById")
	public String viewStudentJobById(Map<String,Object> map,Integer studentId,String flag){
		Student stu=studentService.getStudentById(studentId);
		map.put("stu", stu);
		if(flag!=null){
			map.put("flag", flag);
		}
		return "student/viewStudentjob";
	}
	
	/**
	 * 改变学生的状态，开班，转换，休学等业务
	 * @param change
	 * @return
	 */
	@RequestMapping("/changeStudent")
	public String changeStudent(StudentChange change,String flag){
		this.studentService.changeStudent(change);
		if("class".equals(flag)){
			if(change.getToClass()!=null&&change.getToClass().getClassId()!=null){
				return "redirect:/class/listStudentsByClassId?classId="+change.getToClass().getClassId();
			}else{
				return "redirect:/class/listStudentsByClassId?classId="+change.getFromClass().getClassId();
			}
		}else{
			return "redirect:/student/getAll";
		}
	}
	
	/**
	 * 显示改变学生的状态，开班，转换，休学等业务的页面
	 * @return
	 */
	@RequestMapping("/viewChangeStudent")
	public String viewChangeStudent(Map<String,Object> map,Integer studentId,String flag){
		Student stu=studentService.getStudentById(studentId);
		map.put("stu", stu);
		if(flag!=null){
			map.put("flag", flag);
		}
		map.put("date", new java.util.Date());
		return "student/viewChangeStudent";
	}
	
	

	
	
	//查询就业学员
	@RequestMapping("/getJobList" )
	public String getJobList(Map<String,Object> map,Integer classId,Integer schoolId,Integer classTypeId,Date startDate,Date endDate,HttpServletRequest request){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("schoolId", schoolId);
		paramMap.put("classTypeId", classTypeId);
		paramMap.put("classId", classId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		PageInfo info=new PageInfo(request);
		List<Student> list=studentService.getJobList(paramMap,info);
		map.put("list", list);
		//就业统计
		map.put("paramMap", paramMap);
		Map<String,Object> jobMap=this.studentService.getJobDetails(paramMap);
		map.put("jobMap", jobMap);
		return "student/listStudentJob";
	}
	/**
	 * 检查学生的身份证号是否存在
	 * 如果存在则返回学生所在的班级编号
	 * 如果不存在返回null
	 * @param idCard
	 * @return  存在返回班级，否则返回null
	 */
	@RequestMapping("/checkStuId")
	public  void  checkStudentExist(String idCard, HttpServletResponse response) throws IOException {
		response.getWriter().print(studentService.checkStuIdCardService(idCard));
	}


	/**
	 * 查询学生信息用于修改学生信息
	 * @param studentId 学生编号
	 * @code by DJP
	 */
	@RequestMapping("/showStudentInfo")
	public String showStudentInfoForUpdate(String studentId,Map<String,Object> map){
		Student student=studentService.getStudentById(Integer.parseInt(studentId));
		map.put("stu",student);
		return "student/showStudentInfo";
	}

    @RequestMapping("/updateStudentInfo")
	public String updateStudentInfo(Student student){
		System.out.println(student);
		studentService.updateStudent(student);
		return "redirect:/student/getAll";
	}

	/**
	 * 删除学生的
	 * @param studentId
	 * @return 查询页面
	 * @author DJP
	 */
	@RequestMapping("/deleteStudent")
	public String deleteStudentById(String studentId){
		System.out.println(studentId);
		studentService.deleteStudentById(studentId);
		return "redirect:getAll";
	}
}
