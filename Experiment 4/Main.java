import java.util.*;

class Card {
    private String symbol;
    private int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return symbol + " " + number;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, List<Card>> cardMap = new TreeMap<>(); // TreeMap for sorted keys

        System.out.println("Enter Number of Cards :");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter card " + i + ":");
            String symbol = sc.nextLine().trim();
            int number = sc.nextInt();
            sc.nextLine(); // consume newline

            Card card = new Card(symbol, number);

            cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
        }

        System.out.println("Distinct Symbols are :");
        for (String symbol : cardMap.keySet()) {
            System.out.print(symbol + " ");
        }
        System.out.println();

        for (String symbol : cardMap.keySet()) {
            List<Card> cards = cardMap.get(symbol);
            System.out.println("Cards in " + symbol + " Symbol");
            int sum = 0;

            for (Card c : cards) {
                System.out.println(c);
                sum += c.getNumber();
            }

            System.out.println("Number of cards : " + cards.size());
            System.out.println("Sum of Numbers : " + sum);
        }
    }
}
