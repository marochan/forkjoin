package pl.edu.agh.automatedgrader.jtp2.lab4.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DefaultMain   implements Main {
	static  List<Double> firstVector;
	static  List<Double> secondVector;
	DefaultScalarProductTask task;

	static long threadID = Thread.currentThread().getId();
	
	public static void main(String[] args) {
		DefaultMain dm = new DefaultMain();
		List<Double> first = new ArrayList<>();
		List<Double> second = new ArrayList<>();
		for (int i = 0; i < 100; i++)
		{
			first.add(Double.valueOf(i));
			second.add(Double.valueOf(i));
		}
		System.out.println(dm.computeScalarProduct(first, second));
	}
	@Override
	public double computeScalarProduct(List<Double> first, List<Double> second) {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		task = new DefaultScalarProductTask(threadID, 0, first.size(), firstVector, secondVector);
		pool.execute(task);
		return task.join();
		
	}
	@Override
	public ForkJoinTask<Double> getForkJoinTask() {
	 return task;
	


}
}