package br.ufrn.imd.sise.user;

public class Prefferences {
	private Department department;
	private Course course;
	private ClassRoom classRoom;
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
	
	public ClassRoom getClassRoom() {
		return classRoom;
	}
	
	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}