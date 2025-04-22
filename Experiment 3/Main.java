import java.util.*;

abstract class Account {
    double interestRate;
    double amount;
    abstract double calculateInterest() throws InvalidInputException;
}

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class SBAccount extends Account {
    String accountType;

    SBAccount(double amount, String accountType) {
        this.amount = amount;
        this.accountType = accountType;
    }

    @Override
    double calculateInterest() throws InvalidInputException {
        if (amount < 0) throw new InvalidInputException("Invalid amount. Please enter a positive value.");

        if (accountType.equalsIgnoreCase("normal")) {
            interestRate = 0.04;
        } else if (accountType.equalsIgnoreCase("nri")) {
            interestRate = 0.06;
        } else {
            throw new InvalidInputException("Invalid account type.");
        }
        return amount * interestRate;
    }
}

class FDAccount extends Account {
    int noOfDays;
    int age;

    FDAccount(double amount, int noOfDays, int age) {
        this.amount = amount;
        this.noOfDays = noOfDays;
        this.age = age;
    }

    @Override
    double calculateInterest() throws InvalidInputException {
        if (amount < 0 || noOfDays < 0 || age < 0)
            throw new InvalidInputException("Invalid input values. Please enter correct values.");

        if (amount < 10000000) {
            if (noOfDays >= 7 && noOfDays <= 14)
                interestRate = age >= 60 ? 0.05 : 0.045;
            else if (noOfDays <= 29)
                interestRate = age >= 60 ? 0.0525 : 0.0475;
            else if (noOfDays <= 45)
                interestRate = age >= 60 ? 0.06 : 0.055;
            else if (noOfDays <= 60)
                interestRate = age >= 60 ? 0.075 : 0.07;
            else if (noOfDays <= 184)
                interestRate = age >= 60 ? 0.08 : 0.075;
            else
                interestRate = age >= 60 ? 0.085 : 0.08;
        } else {
            if (noOfDays >= 7 && noOfDays <= 14)
                interestRate = 0.065;
            else if (noOfDays <= 29)
                interestRate = 0.0675;
            else if (noOfDays <= 45)
                interestRate = 0.0675;
            else if (noOfDays <= 60)
                interestRate = 0.08;
            else if (noOfDays <= 184)
                interestRate = 0.085;
            else
                interestRate = 0.1;
        }

        return amount * interestRate;
    }
}

class RDAccount extends Account {
    int noOfMonths;
    double monthlyAmount;
    int age;

    RDAccount(int noOfMonths, double monthlyAmount, int age) {
        this.noOfMonths = noOfMonths;
        this.monthlyAmount = monthlyAmount;
        this.age = age;
    }

    @Override
    double calculateInterest() throws InvalidInputException {
        if (monthlyAmount < 0 || noOfMonths <= 0 || age < 0)
            throw new InvalidInputException("Invalid input values. Please enter correct values.");

        switch (noOfMonths) {
            case 6: interestRate = age >= 60 ? 0.08 : 0.075; break;
            case 9: interestRate = age >= 60 ? 0.0825 : 0.0775; break;
            case 12: interestRate = age >= 60 ? 0.085 : 0.08; break;
            case 15: interestRate = age >= 60 ? 0.0875 : 0.0825; break;
            case 18: interestRate = age >= 60 ? 0.09 : 0.085; break;
            case 21: interestRate = age >= 60 ? 0.0925 : 0.0875; break;
            default: throw new InvalidInputException("Invalid number of months for RD.");
        }

        return (monthlyAmount * noOfMonths * interestRate);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Select the option:");
            System.out.println("1. Interest Calculator –SB");
            System.out.println("2. Interest Calculator –FD");
            System.out.println("3. Interest Calculator –RD");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the Average amount in your account:");
                        double sbAmount = sc.nextDouble();
                        System.out.println("Enter account type (Normal/NRI):");
                        String type = sc.next();
                        SBAccount sb = new SBAccount(sbAmount, type);
                        System.out.println("Interest gained: Rs. " + sb.calculateInterest());
                        break;
                    case 2:
                        System.out.println("Enter the FD amount:");
                        double fdAmount = sc.nextDouble();
                        System.out.println("Enter the number of days:");
                        int days = sc.nextInt();
                        System.out.println("Enter your age:");
                        int age = sc.nextInt();
                        FDAccount fd = new FDAccount(fdAmount, days, age);
                        System.out.println("Interest gained is: " + fd.calculateInterest());
                        break;
                    case 3:
                        System.out.println("Enter the monthly amount:");
                        double monthly = sc.nextDouble();
                        System.out.println("Enter the number of months:");
                        int months = sc.nextInt();
                        System.out.println("Enter your age:");
                        int rdAge = sc.nextInt();
                        RDAccount rd = new RDAccount(months, monthly, rdAge);
                        System.out.println("Interest gained is: " + rd.calculateInterest());
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
