package binary.school.quickapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;

import binary.school.quickapp.model.Student;

@Service
public class StudentService {

	private List<Student> studentList = new ArrayList<>(Arrays.asList(new Student(1 , "akin" , "2014/1/50685ct") , 
			new Student(2 , "jame" , "2014/1/50686ct"),
			new Student(3, "ade" , "2014/1/50687ct"),
					new Student(4 , "bola" , "2014/1/50688ct")    ));
	
	
	
	
	public List<Student> getStudents(){
		return studentList ; 
	}
	
	public Student getStudent(int id) {
		
		Student student = studentList.stream().filter(s -> String.valueOf(s.getId()).equals(String.valueOf(id))).findFirst().get() ; 
		return student ; 
		
	}// end of method

	public void addStudent(Student student) {
		
		studentList.add(student); 
		
	}// end of method

	public void updateStudent(Student student , int id) {

		ListIterator<Student> it = studentList.listIterator(); 
		while(it.hasNext()) 
		{
			Student stud = it.next() ;
				if(stud.getId()==id) 
				{
					it.set(student);
						return ; 
				}
		}
		
	}// end of method
	
	
	
}
