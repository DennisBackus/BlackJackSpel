import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Blackjack Spel = new Blackjack();
ArrayList<Card> Deck = Spel.createDeck(); //maak een deck aan
ArrayList<Card> Hand = new ArrayList(); //maak een lege hand aan
boolean finished = false;
Scanner input = new Scanner(System.in);
int total_points = 0;
while(!finished) {  //begin spel loop
Collections.shuffle(Deck);  //shuffle deck
System.out.println("Voer K in voor een kaart, P in voor een pass, of voer q in om het spel te stoppen");
String sc_input = input.next();
if(sc_input.equals("k")) {
	if(Hand.size() < 2) { //zorgt ervoor dat de eerste dealing 2 kaarten geeft ipv 1
	Card card1 = Deck.get(0);
	Deck.remove(0);
	Hand.add(card1);
	Card card2 = Deck.get(0);
	Deck.remove(0);
	Hand.add(card2);
	total_points += Hand.get(0).points + Hand.get(1).points; //totale hand punten berekenen
/*	boolean aces = Spel.checkforAces(Hand, total_points);
	if(aces == true) {                                       //hier gaat het nog mis, doet niks dit
		for(int i = 0; i < Hand.size(); i++) {
			if(Hand.get(i).card_id == 14) {
				Hand.get(i).points = 1;
				total_points =- 10;
			}
	}
	
	} */
	
	System.out.println("U trok de kaarten " + Hand.get(0).getFullCard_name() + " en " + Hand.get(1).getFullCard_name() + " deze geven samen " + total_points + " punten");
	
	}
	else { //als je al 2 kaarten hebt, pak nog maar 1 kaart per keer
		Card card1 = Deck.get(0);
		Deck.remove(0);
		Hand.add(card1);
		System.out.println("U trok de kaart " + card1.getFullCard_name() + " en deze geeft " + card1.points + " punten");
		total_points += card1.points;
		System.out.println("U heeft nu " + total_points + "punten.");
	
	}
}
else if(sc_input.equals("p")) {
	System.out.println("U heeft gepast.");
	for(int i = 1; i-1 < Hand.size(); i++) { //loop door je hand heen om kaarten uit hand te verkrijgen.
		System.out.println("Kaart " + i + " in uw hand is een " + Hand.get(i-1).full_name);
	}
	System.out.println("U had in totaal " + total_points + ".");
	finished = true;
}
else if(sc_input.equals("q")) {
	finished = true;
}
else {
	
}
}
	}



ArrayList<Card> createDeck(){
	
ArrayList<Card> Deck = new ArrayList();
CARDNUMBER_LOOP :	for(int i = 2; i < 15; i++) { //loop om alle kaarten values te creeeren (2 t/m Aas)
		ICON_LOOP : for(int j = 0; j < 4; j++) { //loop om elke kaart value 4 soorten te geven (Harten, Schoppen enz)
			Card next = new Card();
			next.setCard_id(i); //laat het programma onthouden welke kaart value de kaart heeft
			next.setIcon_id(j); // laat het programma onthouden welke soort kaart het is
			Deck.add(next); // voeg de kaart toe aan het deck, herhaal dit voor alle kaarten die gemaakt worden
		}
	}
return Deck;
}

boolean checkforAces(ArrayList<Card> Hand, int total_points) { //de bedoeling was om te kijken of je een aas in je hand had, maar helaas :(
	int check = 0;
	for(int i = 0; i < Hand.size(); i++) {
		if(Hand.get(i).card_id == 14 && total_points > 21) {
			check = 1;
	}
}
	if(check == 1) {
		return true;
	}
	else { return false;}
}
}