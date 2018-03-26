public class Phase1 {	
	public void start()
	{
		System.out.println("Phase 1 started");
		long starttime = System.currentTimeMillis();
		CreateSubList subListOfStudent=new CreateSubList("bag1");
		subListOfStudent.go();
		CreateSubList subListOfEnrollment=new CreateSubList("bag2");
		subListOfEnrollment.go();	
		long endTime=System.currentTimeMillis();
		System.out.println("run time for phase1 is :" + (endTime - starttime)+"ms");
		
	}

}
