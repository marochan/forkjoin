package pl.edu.agh.automatedgrader.jtp2.lab4.interfaces;

import java.util.List;

public interface ScalarProductTask
{
	List<Double> getFirstVector();

	List<Double> getSecondVector();

	//used only for single thread computation
	double computeScalarProduct();

}
