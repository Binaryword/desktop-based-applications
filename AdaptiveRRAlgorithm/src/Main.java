
public class Main {
	
	
	public static void main(String[] agrs){
		
		System.out.println("hello word");
		
		Processes p = new Processes();
//		p.addProcess(new Process("P1" , 0 , 15 ));
//		p.addProcess(new Process("P2" , 0 , 32 ));
//		p.addProcess(new Process("P3" , 0 , 10 ));
//		p.addProcess(new Process("P4" , 0 , 26 ));
//		p.addProcess(new Process("P5" , 0 , 20 ));
		
		p.addProcess(new Process("P1" , 0 , 18 ));
		p.addProcess(new Process("P2" , 3 , 23 ));
		p.addProcess(new Process("P3" , 4 , 10 ));
		p.addProcess(new Process("P4" , 8 , 35 ));
		p.addProcess(new Process("P5" , 10 , 14 ));
//		
		
		p.showProcessInformations();
		p.sortProcess();
		p.setProcessSize(p.getSize());
		p.roundRob();
		System.out.println();
		p.printCompletedProcess();
		
		
		
		
	}

}
