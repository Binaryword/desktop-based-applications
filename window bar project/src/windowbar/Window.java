package windowbar;

public enum Window {

	
		CLOSE("close") , MINIMIZE("min") , MINIMIZE_CLOSE("min_close"); 
		
		private String desc ; 
		
		Window(String desc){
			
			this.desc = desc ; 
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		
		
		
}

