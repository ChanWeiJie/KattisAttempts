import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Cardtrading {

    public static void main(String[] args) throws IOException {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(r);
        PrintWriter writer = new PrintWriter(System.out);

        //reading N(deck), T(card types), K(combos)
        String deckInfo = bf.readLine();
        String[] deckInfoArr = deckInfo.split(" ");
        int deck = Integer.parseInt(deckInfoArr[0]);
        int cardType = Integer.parseInt(deckInfoArr[1]);
        int combos = Integer.parseInt(deckInfoArr[2]);

        //An array to store the quantity of each type of cards
        int[] Quantity = new int[cardType + 1];
        for(int i = 1; i < Quantity.length; i++) {
            Quantity[i] = 0;
        }

        // reading in the cards in antony's deck
        deckInfo = bf.readLine();
        deckInfoArr = deckInfo.split(" ");
        for (int i = 0; i < deck; i++) {
            int type = Integer.parseInt(deckInfoArr[i]);
            Quantity[type] += 1; // counting how many of each type of card he has
        }

        //reading in each card types buy price and selling price
        ArrayList<Card> ctProfitTotal = new ArrayList<>();
        for(int i = 0; i < cardType; i++){
            deckInfo = bf.readLine();
            String[] cardTypes = deckInfo.split(" ");
            int bp = Integer.parseInt(cardTypes[0]);
            int sp = Integer.parseInt(cardTypes[1]);

            //calculating total buy price, total sell price
            int totalBP = (2 - Quantity[(i + 1)]) * bp; //max each type of card is 2 so calculate the offset
            int totalSP = Quantity[(i + 1)] * sp;
            Card c = new Card(totalBP, totalSP);
            ctProfitTotal.add(c);
        }

        //sort by total prices (BP + SP)
        ctProfitTotal.sort(new CardTypeByTotal());
        long profit = 0;
        for(int i = 0; i < combos; i++) { //buy the first K combos as they are the cheapest
            profit -= ctProfitTotal.get(i).getBuyPrice();
        }
        for(int i = combos; i < ctProfitTotal.size(); i++) { //sell the rest T - K (profit the most)
            profit += ctProfitTotal.get(i).getSellPrice();
        }
        writer.write(Long.toString(profit));
        r.close();
        bf.close();
        writer.flush();
        writer.close();
    }
}

class Card {
    int buyPrice;
    int sellPrice;
    int totalPrice;

    //constructor
    public Card(int bp, int sp) {
        this.buyPrice = bp;
        this.sellPrice = sp;
        this.totalPrice = bp + sp;
    }
    //accessors
    public int getBuyPrice() { return buyPrice; }
    public int getSellPrice() { return sellPrice; }
    public int getTotalPrice() { return totalPrice; }
}
class CardTypeByTotal implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) { return o1.getTotalPrice() - o2.getTotalPrice(); }
}

