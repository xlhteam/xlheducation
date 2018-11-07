<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="../css/table.css" rel="stylesheet" type="text/css">

<style>
td {
	border-width: 1px;
	border-style: solid;
}
</style>

<script type="text/javascript" src="../js/jquery11.js"></script>

<script type="text/javascript">
	$(function() {
		//加载班级信息
		$.get("../class/getActiveClassforGson?classId=${stu.tclass.classId}", function(data) {
			$("#classId").html(data);
			$("#classId").change();
		});
		//加载学校
		$("#schoolId").load("../school/getAllforJson?schoolId=${stu.school.schoolId}");

		//班级连动
		$("#classId").change(function() {
			$("#classGuideId").load("../guide/getClassGuideByClass", {
				classId : $("#classId").val()
			});
			$("#jobGuideId").load("../guide/getJobGuideByClass", {
				classId : $("#classId").val()
			});
			$("#teacherId").load("../teacher/getTeacherByClass", {
				classId : $("#classId").val()
			});
			
			$("#degree").load("../common/getStatesById", {
				type : 3,
				stateId:36
			});
			
		});

        /**
		 * 增加验证学生身份证号是否存在功能 by Djp 2018-03-19
         */
          var inputIdcard=$("input[name='idcard']");
          inputIdcard.blur(function(){
			   $.get("../student/checkStuId",{idCard:$(this).val()},function (data){
			        if(data=="null"){
                        inputIdcard.css("color","green");
                        inputIdcard.next().html("");
                        $("input[type='submit']").attr("disabled",null);
					}else{
                        inputIdcard.css("color","red");
                        inputIdcard.next().html("在"+data+"班");
                        $("input[type='submit']").attr("disabled","disabled");
					}
			   })
		  })
        /**
         * 增加验证学生身份证号是否存在功能 by Djp 2018-03-19
         */

	});
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="updateStudentInfo" method="post">
        <input type="hidden" name="studentId" value="${stu.studentId}">
		<table border="1" align="center"
			style="background-color: silver; font-size: 6px; border: solid; border-width: 1px">
			<caption>学员信息管理</caption>
			<tr>
				<td>学生姓名</td>
				<td><input type="text" name="name" value="${stu.name}"></td>

				<td>性别</td>
				<td><input type="radio" name="sex" ${stu.sex=='男'?"checked='checked'":""}  value="男">男
					<input type="radio" name="sex" ${stu.sex=='女'?"checked='checked'":""}   value="女">女</td>
			<tr>
			<tr>
				<td>学历</td>
				<td><select name="degree.stateId" id="degree" style="width: 100px"></select></td>
				<td>身份证号</td>
				<td><input type="text" name="idcard" value="${stu.idcard}">&nbsp;&nbsp;&nbsp;
					<span style="color:royalblue;font-size: medium"></span></td>
			<tr>
			<tr>
				<td>班级</td>
				<td><select name="tclass.classId" id="classId"
					style="width: 100px">
				</select></td>
				<td>讲师</td>
				<td><select name="teacher.teacherId" id="teacherId"
					style="width: 100px"></select></td>
			<tr>
			<tr>
				<td>教务班主任</td>
				<td><select name="classGuide.guideId" id="classGuideId"
					style="width: 100px"></select></td>
				<td>就业班主任</td>
				<td><select name="jobGuide.guideId" id="jobGuideId"
					style="width: 100px"></select></td>
			<tr>
			<tr>
				<td>学校</td>
				<td><select name="school.schoolId" id="schoolId"
					style="width: 160px"></select></td>

				<td>专业</td>
				<td><input type="text" name="major" value="${stu.major}"></td>
			<tr>
			<tr>
				<td>年级</td>
				<td><input type="text" name="grade" value="${stu.grade}"></td>

				<td>联系电话</td>
				<td><input type="text" name="tel" value="${stu.tel}"></td>
			<tr>
			<tr>
				<td>父亲电话</td>
				<td><input type="text" name="ftel" value="${stu.ftel}"></td>

				<td>母亲电话</td>
				<td><input type="text" name="mtel" value="${stu.mtel}"></td>
			<tr>
			<tr>
				<td>家庭住址</td>

				<td><textarea rows="4" cols="30" name="address">${stu.address}</textarea></td>
				<td>备注</td>
				<td><textarea rows="4" cols="30" name="comment" >${stu.comment}</textarea></td>
			<tr>
			<tr align="right">
				<td colspan="4"><input type="submit" value="修改" > <input type="button" onclick="location.href='/edu/student/getAll'" value="取消" ></td>
			<tr>
		</table>
	</form>
</body>
</html>