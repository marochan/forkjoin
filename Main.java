package pl.edu.agh.automatedgrader.jtp2.lab4.interfaces;

import java.util.List;
import java.util.concurrent.ForkJoinTask;

public interface Main
{
	double computeScalarProduct(List<Double> first, List<Double> second);

	ForkJoinTask<Double> getForkJoinTask();
}
