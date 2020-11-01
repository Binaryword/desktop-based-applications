package binary.school.quickapp.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	 @ResponseBody                                                                                                                                                     
	public String sayHello() 
	{
		return "login.jsp";
	}
	
}
