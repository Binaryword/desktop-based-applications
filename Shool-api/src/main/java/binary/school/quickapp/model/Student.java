package binary.school.quickapp.model;

public class Student {

		private int id ; 
		private String name ; 
		private String matricNo ; 
		
		public Student() {}

		public Student(int id, String name, String matricNo) {
			super();
			this.id = id;
			this.name = name;
			this.matricNo = matricNo;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMatricNo() {
			return matricNo;
		}

		public void setMatricNo(String matricNo) {
			this.matricNo = matricNo;
		}

	
		
		
}
