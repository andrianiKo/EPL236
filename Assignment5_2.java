import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shares implements Comparable {
	
	int sell, buy, profit;
	
	public Shares(int s, int e){
			if (s>=e){
				buy=e;
				sell=s;
			}else{
				buy=s; 
				sell=e;
			}
			profit=Integer.max(0, e-s);
	}
	
	
	static public Shares findProfit(List<Integer> t){
		if (t.size()==2){
			return new Shares(t.get(0), t.get(1));
		}
	
		int m=t.size()/2;
		List<Integer> left= t.subList(0, m);
		List<Integer> right= t.subList(m, t.size());
	
		Shares l=findProfit(left);
		Shares r=findProfit(right);
		
		Shares both = new Shares(Collections.min(left),Collections.max(right));
		
		List<Shares> shares = Arrays.asList(new Shares[]{both,l,r});
		return Collections.max(shares);
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int n=(int)Math.pow(2, 27);
		Integer p[]=new Integer[n];
		//long startTime=System.nanoTime();
		for (int i=0; i<n; i++){
			p[i]=(int)Math.random()*50;
		}
		List<Integer> aa=Arrays.asList(p);
		findProfit(aa);
		//long duration=System.nanoTime()-startTime;
		
//		PrintWriter writer = new PrintWriter("dq1.txt", "UTF-8");
//		List<Integer> aa;
//		Double sumSameTable[]=new Double[10];
//		Double sumNTable[]=new Double[10];
		
//		for (int i=2; i<=Math.pow(2, 18); i=i*2){			// run the experiment for different n: 1<=n<=1024 and n is a power of 2
//			Integer []p= new Integer[i];
//			
//			for (int c=0; c<10; c++){				// run the algorithm for same n but different table values
//				for (int j=0; j<i; j++)				// select random numbers for the table (share values)
//					p[j]=(int)(Math.random()*100+1);
//				
//				writer.println("n="+i);
//
//				for (int k=0; k<10; k++){			// run the algorithm 10 times for the same table
//					aa= Arrays.asList(p);
//					double startTime=System.nanoTime();
//					Shares s= findProfit(aa);
//					double duration=System.nanoTime()-startTime;
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

//		if (s.profit==0){
//			System.out.println("There is no profit.");
//		}else{
//			int bindex = aa.indexOf(s.buy);
//			int sindex = aa.lastIndexOf(s.sell);
//			System.out.println("buy day:"+(bindex+1)+"\nsell day:"+(sindex+1)+"\nprofit:"+s.profit);
//		}
		
	}

	@Override
	public int compareTo(Object arg0) {
		return this.profit - ((Shares) arg0).profit;
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
