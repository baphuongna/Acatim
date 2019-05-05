package com.acatim.acatimver1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import org.springframework.transaction.annotation.Transactional;
import com.acatim.acatimver1.dao.StudentDAO;
import com.acatim.acatimver1.dao.StudyCenterDAO;
import com.acatim.acatimver1.dao.TeacherDAO;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.entity.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Acatimver1ApplicationTests {

	@Autowired
	private TeacherDAO teacherDAO;

	@Test
	@Transactional
	@Rollback(true)
	public void testAddTeacher() {
		Teacher teacherAdd = new Teacher();
		teacherAdd.setUserName("Quangtran");
		teacherAdd.setGender(true);
		teacherAdd.setDescription("Có tâm với việc dạy học");
		teacherDAO.addTeacherInfo(teacherAdd);
		Teacher teacher = teacherDAO.getTeacherByAccount("Quangtran");
		Assert.assertEquals(teacherAdd.getUserName(), teacher.getUserName());
	}

	@Autowired
	private StudentDAO studentDAO;
	@Test
	@Transactional
	@Rollback(true)
	public void testAddStudent() {
		Student studentAdd = new Student();
		studentAdd.setUserName("Quangtran");
		studentAdd.setGender(true);
		studentDAO.addStudentInfo(studentAdd);
		Student student = studentDAO.findInfoUserAccount("Quangtran");
		Assert.assertEquals(studentAdd.getUserName(), student.getUserName());
	}
	
}
