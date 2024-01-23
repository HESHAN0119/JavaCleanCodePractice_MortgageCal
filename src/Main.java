import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int principle=0;
        float annualInterest=0;
        byte years=0;

        Scanner scan= new Scanner(System.in);

        while (true) {
            System.out.print("Principal ($1k-$1M) : ");
            principle = scan.nextInt();
            if (principle >=1000 && principle <=1_000_000)
                break;
            System.out.println("Enter Value between $1k-$1M");
        }
        while (true){
            System.out.print("Annual Interest Rate (0%-30%):");
            annualInterest=scan.nextFloat();
            if (annualInterest>0 && annualInterest<=30 )
                break;
            System.out.println("Annual Interest Rate should be 0%-30%");
        }
        while (true){
            System.out.print("Period (Years between 1-30):");
            years=scan.nextByte();
            if (years>=1 && years <=30)
                break;
            System.out.println("Year should be between 1-30");
        }
        double mortgage=calculateMortage(principle,annualInterest,years);
        String mortgageFormatted= NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.print("Mortgage : "+mortgageFormatted);
    }
    public static double calculateMortage(
            int principal,
            float annualInterest,
            byte years){

        final byte MONTHS_IN_YEAR= 12;
        final byte PERCENT =100;

        short numberOfPayments= (short) (years*MONTHS_IN_YEAR);
        float monthlyInterest=annualInterest/PERCENT/MONTHS_IN_YEAR;

        double mortgage=principal*(monthlyInterest*Math.pow(1+monthlyInterest,numberOfPayments))
                /(Math.pow(1+monthlyInterest,numberOfPayments)-1);
        return mortgage;
    }
}