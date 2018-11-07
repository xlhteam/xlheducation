package com.oracle.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.service.SchoolService;
import com.oracle.util.Select;
import com.oracle.vo.School;

@Controller
@RequestMapping("/school")
public class SchoolHandler {

	@Autowired
	SchoolService schoolService;
	
	
	@RequestMapping("/getAllforJson")
	@ResponseBody
	public String getAllforJson(String schoolId){
		Select select=new Select();
		List<School> list=schoolService.getAll();
		select.putAll(list, "schoolId", "name",schoolId);
		return select.toString();
	}


	
	
	@RequestMapping("/getAll")
	public String getAll(Map<String,Object> map){
		List<School> list=schoolService.getAll();
		map.put("list", list);
		return "school/listSchools";
	}
	
	@RequestMapping("/{path}")
	public String path(@PathVariable("path") String path){
		return "school/"+path;
	}
	
	
	@RequestMapping("/save")
	public String save(School school){
		this.schoolService.save(school);
		
		return "redirect:getAll";
	}
	
	
}
