

public class Process {

	private String processId ;
	private int arrivalTime ;
	private int burstTime ;
	private int completionTime ;
	private int turnArroundTime ;
	private int waitingTime ;
	public static int quantumTime ;
	private boolean inProcess = false ; 
	private boolean initBurst = false ; 
	private int initBurstTime = 0 ; 


	public Process(){

	}

	public Process(String processId ,  int arrivalTime , int burstTime){
		this.processId = processId ;
		this.arrivalTime = arrivalTime ;
		this.burstTime = burstTime ;
	}

	
	
	
	
	public int getInitBurstTime() {
		return initBurstTime;
	}

	public void setInitBurstTime(int initBurstTime) {
		this.initBurstTime = initBurstTime;
	}

	public boolean isInitBurst() {
		return initBurst;
	}

	public void setInitBurst(boolean initBurst) {
		this.initBurst = initBurst;
	}

	public boolean isInProcess() {
		return inProcess;
	}

	public void setInProcess(boolean inProcess) {
		this.inProcess = inProcess;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public int getTurnArroundTime() {
		return turnArroundTime;
	}

	public void setTurnArroundTime(int turnArroundTime) {
		this.turnArroundTime = turnArroundTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public static int getQuantumTime() {
		return quantumTime;
	}

	public static void setQuantumTime(int quantumTime) {
		Process.quantumTime = quantumTime;
	}

	

	@Override
	public String toString() {
		return "Process [ processId=" + processId + ", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime
				+ ", completionTime=" + completionTime + ", turnArroundTime=" + turnArroundTime + ", waitingTime="
				+ waitingTime + ", inProcess=" + inProcess + " ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + burstTime;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Process other = (Process) obj;
		if (burstTime != other.burstTime)
			return false;
		return true;
	}




}
