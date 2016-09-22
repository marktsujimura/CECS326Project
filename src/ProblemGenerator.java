import java.util.Random;

public class ProblemGenerator {

	public ProblemGenerator(){
		
	}
	
	
	/*
	 * Level 0: Basic operations
	 * Level 1: Algebra
	 * Level 2: Trigonometry
	 * Level 3: Matrices
	 * Level 4: Calculus
	 * Level 5: 
	 */
	public String[] generate(int level){
		Random rand = new Random();
		String[] problem = new String[2];
		problem[0] = "0";
		problem[1] = "0";
		
		
		// Basic operations
		if(level == 0){
			int operation = rand.nextInt(4);
			// Addition
			if(operation == 0){
				int operand1 = rand.nextInt(90) + 10;
				int operand2 = rand.nextInt(90) + 10;
				problem[0] = String.valueOf(operand1 + operand2);
				problem[1] = String.valueOf(operand1) +" + " +String.valueOf(operand2);
				return problem;
			}
			// Subtraction
			else if(operation == 1){
				int operand1 = rand.nextInt(90) + 10;
				int operand2 = rand.nextInt(90) + 10;
				problem[0] = String.valueOf(operand1 - operand2);
				problem[1] = String.valueOf(operand1) +" - " +String.valueOf(operand2);
				return problem;
			}
			// Multiplication
			else if(operation == 2){
				int operand1 = rand.nextInt(20) + 10;
				int operand2 = rand.nextInt(20) + 10;
				problem[0] = String.valueOf(operand1 * operand2);
				problem[1] = String.valueOf(operand1) +" * " +String.valueOf(operand2);
				return problem;
			}
			// Division
			else{
				int operand1 = rand.nextInt(30) + 10;
				problem[0] = String.valueOf(rand.nextInt(10) + 3);
				int operand2 = operand1 * Integer.valueOf(problem[0]);
				problem[1] = String.valueOf(operand2) +" / " +String.valueOf(operand1);
				return problem;
			}
		}
		// Algebra
		/*else if(level == 1){
			// Quadratic equation
			int answer = rand.nextInt(12) + 1;
			int b = rand.nextInt(12) + 1;
			int c = -answer*answer - b*answer;
			problem[0] = String.valueOf(answer);
			problem[1] = "x^2 + " +String.valueOf(b) +"x + " +String.valueOf(c) +"= 0";
			return problem;
		}*/
		else{
			return problem;
		}
	}
}
