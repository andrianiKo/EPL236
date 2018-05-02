import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;

public class SharesDP {
	
	static public long findProfit(int []opt, int []p){
		long start=System.nanoTime();
		opt[0]= 0;
		for (int j=1; j<p.length; j++){
			int x= opt[j-1]+ (p[j]-p[j-1]);
			if (x>0)
				opt[j]=x;
			else
				opt[j]=0;
		}
		
		int max=0;
		int selDay=0;
		for (int i=0; i<p.length; i++){
			if (opt[i]>max){
				max=opt[i];
				selDay=i;
			}
		}
		
		int buyDay=-1;
		for (int i=selDay; i>=0; i--){
			if (opt[i]==0){
				buyDay=i;
				break;
			}
		}

		if(max!=0){
			System.out.println("Buy day: "+ (buyDay+1));
			System.out.println("Sell day:"+(selDay+1) + "\nProfit:  " + max*1000);
		} else 
			System.out.println("There is no profit.");
		return System.nanoTime()-start;
		
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		for (int k=1; k<=16; k++){
			int []p=new int[k]; // value of the shares for each of the n days
			System.out.print("Input of size "+k+": ");
			for (int i=0; i<k; i++){
				p[i]=(int)(Math.random()*50+1);
				System.out.print(p[i]+" ");
			}
			System.out.println();
			int opt[]=new int[k]; // maximum possible profit for each of the n days
			findProfit(opt, p);
		}
		
		/** Code to run the experiment **/
//		PrintWriter writer = new PrintWriter("ex22.txt", "UTF-8");
//		double duration; 
//		Double sumSameTable[]=new Double[10];
//		Double sumNTable[]=new Double[10];
//		
//		for (int i=1; i<=1048576; i=i*2){			// run the experiment for different n; 1<=n<=1024 and n is a power of 2
//			int []p=new int [i];
//			int []opt=new int [i];
//			
//			for (int c=0; c<10; c++){				// run the algorithm for same n but different table values
//				for (int j=0; j<i; j++)				// select random numbers for the table (share values)
//					p[j]=(int)(Math.random()*100+1);
//				
//				writer.println("n="+i);
//
//				for (int k=0; k<10; k++){			// run the algorithm 10 times for the same table
//					duration=findProfit(opt, p);
//					sumSameTable[k]=duration;
//					writer.println(duration);
//				}
//				double avg=findAverage(sumSameTable);
//				writer.println("average execution time:"+ avg);
//				sumNTable[c]=avg;
//			}
//				writer.println("total average for n="+i+":"+findAverage(sumNTable));
//		}
//		
//		writer.close();
	}

	static public double findAverage(Double a[]){
		double max=(double) Collections.max(Arrays.asList(a));
		double min=(double) Collections.min(Arrays.asList(a));
		Double sum=0.0;
		for (int i=0; i<a.length; i++){
			sum=sum+a[i];
		}
		sum=sum-max-min;
		return sum/a.length;
	}
}
