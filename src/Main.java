import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_YEAR= 12;
    final static byte PERCENT =100;
    public static void main(String[] args) {

        int principle= (int) readValue("Principal ($1k-$1M) : ",1,1000000);
        float annualInterest= (float) readValue("Annual Interest Rate (0%-30%):",0,30);
        byte years=(byte) readValue("Period (Years between 1-30):",1,30);

        double mortgage=calculateMortage(principle,annualInterest,years);
        String mortgageFormatted= NumberFormat.getCurrencyInstance().format(mortgage);
        printMortgage(mortgageFormatted);
        printPaymentShedule(principle, annualInterest, years);
    }

    private static void printPaymentShedule(int principle, float annualInterest, byte years) {
        System.out.println("\n");
        System.out.println("Payment Schedule");
        System.out.println("_________________");
        for(short month = 1; month <= years *MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principle, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    private static void printMortgage(String mortgageFormatted) {
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("_________");
        System.out.print("Mortgage : "+ mortgageFormatted);
    }

    public static double readValue(String prompt, int min, int max){
        Scanner scanner = new Scanner(System.in);
        double value=0;
        while (true){
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value>min && value<max)
                break;
            System.out.println("Enter value between "+min+" and "+max);
        }
        return value;
    }
    public static double calculateMortage(
            int principal,
            float annualInterest,
            byte years){

        short numberOfPayments= (short) (years*MONTHS_IN_YEAR);
        float monthlyInterest=annualInterest/PERCENT/MONTHS_IN_YEAR;

        double mortgage=principal*(monthlyInterest*Math.pow(1+monthlyInterest,numberOfPayments))
                /(Math.pow(1+monthlyInterest,numberOfPayments)-1);
        return mortgage;
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentMade){

        short numberOfPayments= (short) (years*MONTHS_IN_YEAR);
        float monthlyInterest=annualInterest/PERCENT/MONTHS_IN_YEAR;

        double balance = principal
                *(Math.pow(1 + monthlyInterest,numberOfPayments) - Math.pow(1+ monthlyInterest,numberOfPaymentMade))
                /(Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;

    }
}