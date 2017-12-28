import java.util.Scanner;
/**
* MATH3312:Project 2 Java version
* Solve Divided Difference and predict outcome 
* * @author WONG, Kin Fat Huanqiang
*
*/
public class DividedDifference {
	protected static double[] computeDividedDifference(final double x[], final double y[]) { 
		final double[] divdiff = y.clone(); // initialization
		
		final int n = x.length;
		final double[] a = new double [n]; 
		a[0] = divdiff[0];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n-i; j++) {
				final double denominator = x[j+i] - x[j];
				divdiff[j] = (divdiff[j+1] - divdiff[j]) / denominator;
			}
			a[i] = divdiff[0];
		}
		
		return a; 
	}
	protected static double computePopulation(double divDiffCoefficients[], double year[], double var) {
		double value = 0;
		int n = year.length;
		// a[0] + a[1](x-year[0]) + a[2](x-year[0])(x-year[1]) + ... 
		double[] temp = new double[n];
		for(int i = 0; i < temp.length; i++) {
		    if(i == 0)
		    		temp[0] = 1;      //a[0]*temp[0]
		    else
		    		temp[i] = temp[i-1]*(var - year[i-1]); 	//(x-year[0])...(x-year[n-1])
		}
		    for(int j = 0; j < temp.length; j++)
		    		// a[0] + ... + a[n](x-year[0])...(x-year[n-1]) value = value + divDiffCoefficients[j]*temp[j];
		    		value = value + divDiffCoefficients[j]*temp[j];
		    	
		    	return value;
	}
	public static void main(String[] args) {
		// Year 1940 1950 1960 1970 1980 1990
		//Population(in thousands) 132,165 151,326 179,323 203,302 226,542 249,633
		final double[] yearArr = {1940, 1950, 1960, 1970, 1980, 1990};
		final double[] populationArr = {132165, 151326, 179323, 203302, 226542, 249633};
		System.out.print(" Year "); 
		for(int i = 0; i < yearArr.length; i++)
			System.out.printf(" %8d ", (int)yearArr[i]); 
		System.out.println();
		System.out.print("Population(in thousands)"); 
		for(int i = 0; i < populationArr.length; i++)
			System.out.printf(" %,8d ", (int)populationArr[i]); 
		System.out.println();
		
		double[] divDiffCoefficents = computeDividedDifference(yearArr, populationArr);
		System.out.println("Divided Difference Coefficients: "); 
		for(int i = 0; i < divDiffCoefficents.length; i++)
			System.out.printf(" a[%d] = %f ", i, divDiffCoefficents[i]); 
		System.out.println();
		System.out.println();
		
		
		Scanner sc = new Scanner(System.in); System.out.print("Please input year(int): ");
		try {
				int var = sc.nextInt(); 
				if(var < 0)
					throw new Exception("Error: Year should be greater than 0"); 
				System.out.println("The population(in thousands) in Year " + var + " is "
							+ computePopulation(divDiffCoefficents, yearArr, var) ); 
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Program is going to terminate, please input a correct integer year next time!");
				System.exit(0);
			}
		finally {
			sc.close();
			System.out.println("Thank you for using the program.");
		}
	}
}