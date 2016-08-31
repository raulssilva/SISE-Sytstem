package br.ufrn.imd.sise.user.model;

import java.util.List;

public class Prefferences {
	
	private Department department;
	private Course course;
	private List<CourseClass> coursesClass;
	private User user;
	
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
	
	
}