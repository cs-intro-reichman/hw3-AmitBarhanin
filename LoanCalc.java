// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

	static double epsilon = 0.001; // Approximation accuracy
	static int iterationCounter; // Number of iterations

	// Gets the loan data and computes the periodical payment.
	// Expects to get three command-line arguments: loan amount (double),
	// interest rate (double, as a percentage), and number of payments (int).
	public static void main(String[] args) {
		// Gets the loan data

		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
		iterationCounter = 0;

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the
	// periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {
		double end = loan;
		double rateInPercent = (rate / 100) + 1; // המספר שנכפיל אותו בend
		for (int i = 0; i < n; i++) {
			end = (end - payment) * rateInPercent;
		}
		return end;
	}

	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double payment = loan / n;
		while (endBalance(loan, rate, n, payment) > 0) {
			payment = payment + epsilon;
			iterationCounter++;
		}
		return payment;
	}

	// Uses bisection search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		double lowPayment = loan / n;
		double maxPayment = loan;
		double mid = (maxPayment + lowPayment)/2;
		while (Math.abs(endBalance(loan, rate, n, mid)) >= epsilon) {
			double current = endBalance(loan, rate, n, mid);
			if (current>0) {
				lowPayment = mid;
			}
			else
			{
				maxPayment = mid;
			}
			mid = (maxPayment + lowPayment)/2;
			iterationCounter++;
		}
		double payments = (maxPayment + lowPayment)/2;

		return payments;
	}
}