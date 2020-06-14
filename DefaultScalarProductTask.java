package pl.edu.agh.automatedgrader.jtp2.lab4.interfaces;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DefaultScalarProductTask extends RecursiveTask<Double> implements ScalarProductTask{
	
	

	long threadID = DefaultMain.threadID;
	DefaultMain dm = new DefaultMain();
	List<Double> first = getFirstVector();
	List<Double> second = getSecondVector();
	int start; 
	int end;
	@Override
	public List<Double> getFirstVector() {
		return DefaultMain.firstVector;
	}

	@Override
	public List<Double> getSecondVector() {
		return DefaultMain.secondVector;
	}

	@Override
	public double computeScalarProduct() {
		double result = 0.0;
		double finalResult = 0.0;
		for( int i = start; i < end; i++) {
			result= first.get(i)*second.get(i);
			finalResult+= result;
		}
		return finalResult;
		
	}


	public DefaultScalarProductTask(long threadID, int start, int end, List<Double> first, List<Double> second) {
		super();
		this.threadID = DefaultMain.threadID;
		this.start = start;
		this.end = end;
		this.first = first;
		this.second = second;
	}

	@Override
	protected Double compute() {
		long newThreadID = Thread.currentThread().getId();
		if(threadID!= newThreadID) {
			System.out.println("Work has been stolen");
		}
	
		if (end - start == 1) {
			return first.get(start) * second.get(start);
		} else if (end - start == 0) {
			return 0.0;
		}
		
		int mid = (start + end) / 2;
		DefaultScalarProductTask left = new DefaultScalarProductTask(threadID, start, mid, first, second);
		DefaultScalarProductTask right = new DefaultScalarProductTask(threadID, mid, end, first, second);

		left.fork();
		right.fork();
		return left.join() + right.join();
		
		
	}

}
