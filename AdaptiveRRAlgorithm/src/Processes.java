import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Processes{

 private List<Process> processes ;
 private List<Process> readyQueue ;
 private List<Process> sReadyQueue ;
 private int cpuCurrentTime ;
 private LinkedList<Process> executedProcess = new LinkedList<>();
 private LinkedList<Process> completeProcess = new LinkedList<>();
 private boolean dynamicQT = false ;
 private int processSize = 0 ;
 private double AWT ;
 private double ATAT ;
 private int contextSwitch = 0 ;
 private boolean recalculateQT = false ;



 public Processes(){

	 processes = new ArrayList<Process>() ;
	 readyQueue = new ArrayList<Process>();
	 sReadyQueue = new ArrayList<Process>();
	 cpuCurrentTime = 0 ;

 }


 public int getProcessSize() {
	return processSize;
}

 public void addAllProcess(){
	 sReadyQueue.addAll(processes);
 }


public void setProcessSize(int processSize) {
	this.processSize = processSize;
}

public int getSize(){

	return processes.size() ;
}

public void addProcess(Process process){

	 processes.add(process);
 }

 public void removeProcess(Process process){

	 processes.remove(process);

 }

 public List<Process> getProcesses(){

	 return processes;
 }

 public void sortProcess(){

	 Collections.sort(processes, new Comparator<Process>(){

			@Override
			public int compare(Process arg0, Process arg1) {

				return Integer.valueOf(arg0.getBurstTime()).compareTo(Integer.valueOf(arg1.getBurstTime())) ;
			}


		});
 }

 public void sortProcess(List<Process> pro){

	 Collections.sort(pro, new Comparator<Process>(){

			@Override
			public int compare(Process arg0, Process arg1) {

				return Integer.valueOf(arg0.getBurstTime()).compareTo(Integer.valueOf(arg1.getBurstTime())) ;
			}


		});
 }


 public void showProcessInformations(){



	 for(Process x : processes){

		System.out.printf("%s %n" , x.toString() );

	 }

	 System.out.println("End Lines.......");
 }


public void showProcessInformations(String title , List<Process> pro){

	 System.out.println(title);

	 for(Process x : pro){

		System.out.printf("%s %n" , x.toString() );

	 }

	 System.out.println("\n\n End Lines.......");
 }


 public void initiateReadyQueue(){


		 for(Process p : processes){

//		  loop through each process to check the smallest burst time and add to ready queue...

			 if(p.getArrivalTime() <= cpuCurrentTime){

				 readyQueue.add(p);
				 p.setInProcess(false);

			 }else{

				 p.setInProcess(true);
			 }


		 }// end of for loop....



		 // remove all process in ready queue from processes..
		 updateProcess(processes);


		 // sort the ready queue based on burst time....
		Collections.sort(readyQueue, new Comparator<Process>(){

			@Override
			public int compare(Process arg0, Process arg1) {

				return Integer.valueOf(arg0.getBurstTime()).compareTo(Integer.valueOf(arg1.getBurstTime())) ;
			}


		});

 }


public void updateProcess(List<Process> processes){

	List<Process> tempProcess = new ArrayList<>();

	for(Process p : processes){
		if(p.isInProcess()==true)
			tempProcess.add(p);
	}

	processes.clear();
	processes.addAll(tempProcess) ;
	showProcessInformations("\nPROCESSESS", processes);
	if(processes.isEmpty())
		System.out.println("NO AVAILABLE PROCESSS");

}

 public List<Process> getReadyQueue(){

	 return readyQueue ;
 }

 public List<Process> getSortedReadyQueue(){

	 return sReadyQueue ;
 }


 public void generateQuantumTime(){

	 try{



			 int qtA =  readyQueue.get(readyQueue.size()-1).getBurstTime() ;
			 int qtB =  readyQueue.get(readyQueue.size()-2).getBurstTime() ;


			 int qtAB  = (qtA + qtB) / 2 ;

			 int rqSize = getProcessSize() / 2 ;
			 System.out.println("PROCESS SIZE " + getProcessSize() );

			 Process.setQuantumTime(qtAB + rqSize) ;
			 System.out.println("QUANTUM TIME " + Process.getQuantumTime() );



	 }catch(ArrayIndexOutOfBoundsException ex){

		 System.out.println("this section of code is run");

		 if(!dynamicQT){
			 dynamicQT = true ;

			 recalculateQT = true ;

			 int sum = 0 ;

			 for(int i = 0 ; i < readyQueue.size() ; i++){

				 sum += readyQueue.get(i).getBurstTime() ;
			 }

			// int qtA =  readyQueue.get(readyQueue.size()-1).getBurstTime() ;
			 int qtB =  sum ;

			 int qtAB  = (qtB);

			 int rqSize = readyQueue.size() / 1 ;

			 Process.setQuantumTime(qtAB + rqSize) ;


		 }else{

			 recalculateQT = false ;

			 int sum = 0 ;

			 for(int i = 0 ; i < readyQueue.size() ; i++){

				 sum += readyQueue.get(i).getBurstTime() ;
			 }

			// int qtA =  readyQueue.get(readyQueue.size()-1).getBurstTime() ;
			 int qtB =  sum ;

			 int qtAB  = (qtB) / 2 ;

			 int rqSize = readyQueue.size() / 1 ;

			 Process.setQuantumTime(qtAB + rqSize) ;

			 return ;
		 }




	 }






 }



 public void roundRob(){



	 // initiate and sorting the ready queue...
	 initiateReadyQueue();
	 showProcessInformations("Ready Queue data", getReadyQueue());


	 // generating initial quantum time...
	 generateQuantumTime();
	 System.out.printf("Quantum Time %d " , Process.getQuantumTime()) ;


	 System.out.print("\t [ Current Cpu Time : " + cpuCurrentTime + " ]");


	 while(!readyQueue.isEmpty())
	 {

		 for(Process process : readyQueue)
		 {
			 execute(process) ;

		 }

		 readyQueue.clear();
		 initiateReadyQueue() ;
		 if(recalculateQT)
			 generateQuantumTime();

	 }//end of while loop

	 //showProcessInformations("PROCESS..... " , processes);
	 readyQueue.clear();
	 readyQueue.addAll(executedProcess);
	 executedProcess.clear();

		// initiateReadyQueue() ;

		 showProcessInformations("Sorted Ready Queue data", getReadyQueue());

		 // remove process with zero burst time
		// trimReadyQueue() ;
		 System.out.println("\nready queue size after REMOVAL: " + readyQueue.size()) ;
		 showProcessInformations("Ready Queue", getReadyQueue());

		 contextSwitch++ ;





	     if(!readyQueue.isEmpty())
			 roundRob();


 }

 public void execute(Process process){

	 int tempBurstTime = 0  ;
	// int initBT = 0 ;
	 boolean isComplete = false ;

	 if(!process.isInitBurst())
	 {
		 process.setInitBurst(true);
		 process.setInitBurstTime(process.getBurstTime());
	 }



	if(process.getBurstTime()==0)
		return ;

	 if(process.getBurstTime() >= Process.getQuantumTime()){
		 tempBurstTime = Process.getQuantumTime() ;
		 process.setBurstTime(process.getBurstTime() - Process.getQuantumTime());
		 executedProcess.add(process) ;
	 }else if(process.getBurstTime() < Process.getQuantumTime()){
		 tempBurstTime = process.getBurstTime() ;
		 process.setBurstTime(0);
		 isComplete = true ;
		 completeProcess.add(process) ;

	 }else{

		 System.out.println("nothing implemented");
	 }


	 cpuCurrentTime += tempBurstTime ;
	 System.out.print("\t [ Current Cpu Time : " + cpuCurrentTime + " ]");

	 if(isComplete)
		 process.setCompletionTime(cpuCurrentTime);


 }// end of execute method..


 public void trimReadyQueue(){

	 List<Process> nextSwitch = new ArrayList<>();

	 for(int i=0 ; i<getReadyQueue().size() ; i++)
	 {

		 if(getReadyQueue().get(i).getBurstTime()!=0)
		 {
			 System.out.println("Ready condition meet :" + getReadyQueue().get(i).toString()) ;
			 nextSwitch.add(getReadyQueue().get(i)) ;
		 }// end of if....

	 }

	 //re initialize the ready queue...
	 readyQueue.clear();
	 readyQueue.addAll(nextSwitch) ;

 }// end of method....





 public void setProcessDetail(){



	 for(Process process : completeProcess)
	 {

		 int TAT = process.getCompletionTime() - process.getArrivalTime() ;
		 process.setTurnArroundTime(TAT);
		 ATAT += TAT ;

		 int WT = process.getTurnArroundTime() - process.getInitBurstTime() ;
		 process.setWaitingTime(WT);
		 AWT += WT ;

		 process.setBurstTime(process.getInitBurstTime());

	 }


	 ATAT = ATAT / completeProcess.size();
	 AWT = AWT / completeProcess.size() ;

 }

 public void printCompletedProcess(){

	 setProcessDetail();

	 System.out.println("Completed Data Process ");

	 for(Process p : completeProcess)
	 {
		 System.out.println(p.toString());
	 }

	 System.out.println(" DATA ");
	 System.out.println("Average WT = " + AWT + "\t Average TAT = " +  ATAT );
	 System.out.println("Context Switch : " + contextSwitch);

 }


} // end of class
