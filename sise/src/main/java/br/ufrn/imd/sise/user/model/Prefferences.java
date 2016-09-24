package br.ufrn.imd.sise.user.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Prefferences {
	
	private Department department;
	private Course course;
	private List<CourseClass> coursesClass;
	private User user;
	
	public Prefferences() {
		super();
	}

	public Prefferences(Course course, List<CourseClass> coursesClass, User user) {
		super();
		this.course = course;
		this.coursesClass = coursesClass;
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
		
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<CourseClass> getCoursesClass() {
		return coursesClass;
	}

	public void setCoursesClass(List<CourseClass> coursesClass) {
		this.coursesClass = coursesClass;
	}

	@Override
	public String toString() {
		return "Prefferences [department=" + department + ", course=" + course + ", coursesClass=" + coursesClass
				+ ", user=" + user + "]";
	}

//	public  Set<String> getTermsSet() {
//		System.out.println("-----------");
//		Set<String> terms = new HashSet<>();
//		for (CourseClass course : coursesClass) {
//			String nameDisciplina = course.getSubject().getName();
//			terms.add(nameDisciplina);
//			System.out.println(nameDisciplina);
//		}
//		System.out.println("-----------");
//		return terms;
//		
//	}
	
}