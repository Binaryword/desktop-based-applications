package binary.school.quickapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import binary.school.quickapp.model.Student;
import binary.school.quickapp.service.StudentService;

@RestController
public class StudentController {
		
		@Autowired
		StudentService studentService ; 
	
		@RequestMapping("/students")
		public List<Student> getStudents() {
			
			return studentService.getStudents() ; 
		
		}// end of method
		
		@RequestMapping("/students/{stud}")
		public Student getStudent(@PathVariable("stud")  int id) {
			return studentService.getStudent(id); 
		}
		
		@RequestMapping(method = RequestMethod.POST , value = "/students")
		public void addStudent(@RequestBody Student student) {
			
			studentService.addStudent(student) ; 
			
		}
		
		@RequestMapping(method=RequestMethod.PUT , value="/students/{id}")
		public void updateStudent(@RequestBody  Student student ,  @PathVariable int id ) {
			studentService.updateStudent(student , id);
		}
	
}
