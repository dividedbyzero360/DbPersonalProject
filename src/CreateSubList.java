import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CreateSubList {
	public int numberOfSublistCreated=0;
	public String fileName;

	public CreateSubList(String fileName){
		this.fileName=fileName;
	}

	private  long getAvailableMemory(){
		System.gc();
		//https://stackoverflow.com/questions/3571203/what-are-runtime-getruntime-totalmemory-and-freememory?answertab=votes#tab-top
		Runtime runtime = Runtime.getRuntime();
		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
		return runtime.maxMemory() - usedMemory;
	}
	private static String readFile(BufferedReader bufferedReader) {
		String line="";
		try
		{
			line=bufferedReader.readLine();
			if(line!=null){
				line=line.trim();
				if(line.length()==0){
					line=null;
				}	
			}
		}
		catch(IOException ex){
			System.out.println("Exception in reading file ");
		}
		return line;
	}

	public void go(){
		ArrayList<String> input=new ArrayList<String>();
		long availableFreeMemory=getAvailableMemory();
		long safeFreeMemory= (availableFreeMemory/3);
		String line="";
		try{
			FileReader fileReader = new FileReader(this.fileName+".txt");
			BufferedReader bufferedReader =new BufferedReader(fileReader);
			while(true){
				if(availableFreeMemory>=safeFreeMemory){
					line=readFile(bufferedReader);
					if(line==null)
					{
						bufferedReader.close();
						CreateSortedSubList(input,"sorted"+fileName+"_"+(++numberOfSublistCreated)+".txt");
						return;  // File exhausted
					}
					else
					{
						//System.out.println(Thread.currentThread().getId()+" "+input.size());
						input.add(line); 
						availableFreeMemory-=300; //(Takes less time but creates more  subsortedfiles)
						//availableFreeMemory-=170;(Takes more time but creates  less subsortedfiles) 
					}
				}
				else
				{
					CreateSortedSubList(input,"sorted"+fileName+"_"+(++numberOfSublistCreated)+".txt");
					availableFreeMemory=getAvailableMemory();
					safeFreeMemory=(availableFreeMemory/3);
				}

			}
		}
		catch(OutOfMemoryError ex){
			System.out.println(numberOfSublistCreated);
			System.out.println(getAvailableMemory());
			System.out.println("Out of memory in phase 1");
			System.exit(1);
		}
		catch(Exception ex){
			System.out.println("Error occured here "+ ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void CreateSortedSubList(ArrayList<String> input, String sortedSubListFileName) {
		 input.sort((x,y)-> x.substring(0, 8).compareTo(y.substring(0, 8)));
		WriteTheSortedSubListToAFile(input,sortedSubListFileName);
		input.clear();
		System.gc();

	}

	private void WriteTheSortedSubListToAFile(ArrayList<String> input,	String sortedSubListFileName) {
		try{
			FileWriter fw=new FileWriter(sortedSubListFileName,true);
			BufferedWriter bw=new BufferedWriter(fw);
			try{
				input.forEach(x-> {
					try{
						bw.write(x);
						bw.newLine();
					}catch(Exception ex){
						System.out.println("Exception occured inside WriteTheSortedSubListToAFile2. Exception is "+ ex.getMessage());
					}
				});
			}catch(Exception ex){
				System.out.println("Exception occured inside WriteTheSortedSubListToAFile-2. Exception is "+ ex.getMessage());
			}finally{
				bw.flush();
				bw.close();
			}
		}catch(Exception ex){
			System.out.println("Exception occured at WriteTheSortedSubListToAFile method");
		}
	}

}
