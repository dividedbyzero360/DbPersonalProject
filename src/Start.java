import java.util.*;
public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = 0;
		long endTime=0;
		System.out.println("Please enter 1 for nested join technique or 2 for sort based join");
		Scanner scan =new Scanner(System.in);
		int choice = 2;//Integer.parseInt(scan.nextLine());
		startTime=System.currentTimeMillis();
		if(choice==1){
			
		}
		else{
			Phase1 phase1=new Phase1();
			phase1.start();
			
			
		}
		endTime=System.currentTimeMillis();
		System.out.println("Total time taken to run the process is "+(endTime-startTime)+"ms");
	}

}
