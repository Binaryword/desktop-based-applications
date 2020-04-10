package message;

public class Message {
	
	private String message ; 
	private String title ; 
	
	public Message(){
		
		this("No message");
		
	}
	
	public Message(String message){
		
		this.message = message ; 
	}
	
	
	public Message(String message , String title){
		
		this.message  = message ; 
		this.title =  title ; 
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	
	
}
