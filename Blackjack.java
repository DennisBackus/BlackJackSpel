package BlackJack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Blackjack {
	boolean finished = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Blackjack Spel = new Blackjack();
		Spel.playGame();
		while(!Spel.finished) {
			Spel.playAgain();
		}
	}

	void playGame() {
		ArrayList<Card> Deck = createDeck(); //maak een deck aan
		ArrayList<Card> Hand = new ArrayList(); //maak een lege hand aan
		Scanner input = new Scanner(System.in);
		int total_points = 0;
		boolean rondeFinish = false;
		while(!rondeFinish) {  //begin spel loop
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
					if(card1.isAce == true && card2.isAce == true) { //Dit zorgt ervoor dat wanneer er 2 azen getrokken worden, de 2e aas
						card2.points = 1;
					}
					total_points += Hand.get(0).points + Hand.get(1).points; //totale hand punten berekenen

					if(total_points == 21) {
						System.out.println("BlackJack!");
						rondeFinish = true;
					}

					System.out.println("U trok de kaarten " + Hand.get(0).getFullCard_name() + " en " + Hand.get(1).getFullCard_name() + " deze geven samen " + total_points + " punten");

				}
				else { //als je al 2 kaarten hebt, pak nog maar 1 kaart per keer
					Card card1 = Deck.get(0);
					Deck.remove(0);
					Hand.add(card1);
					total_points += card1.points;
					System.out.println("U trok de kaart " + card1.getFullCard_name() + " en deze geeft " + card1.points + " punt(en)");
					if(total_points > 21) { //kijk of je boven de 21 uit komt
						boolean hasChanged = false;
						for(int i = 0; i < Hand.size(); i++) {
							if(Hand.get(i).isAce == true && Hand.get(i).points == 11) { //Als in de hand een aas zit, en deze waarde is nog gelijk aan 11, verander de waarde en haal 10 van het totaal aantal punten af
								Hand.get(i).points = 1;
								total_points -= 10;
								System.out.println("Een van uw kaarten was een " + Hand.get(i).getFullCard_name() + ", maar omdat uw totaal 21 overschreed is de waarde is nu 1");

								hasChanged = true; //dit zorgt ervoor dat als een aas aangepast is, dus total niet meer > 21, dat je niet 'verliest'
								break;
							}
						}
						if(hasChanged == false){ {System.out.println("U verliest."); //als hij >21 zit en geen aas verandert is --> je verliest
						rondeFinish = true;}
						}

					}
					if(total_points == 21) { //Zo win je
						System.out.println("BlackJack!");
						rondeFinish = true;

					}
					System.out.println("U heeft " + total_points + "punten."); //is wel fijn om te weten
				}
			}
			else if(sc_input.equals("p")) {
				System.out.println("U heeft gepast.");
				for(int i = 1; i-1 < Hand.size(); i++) { //loop door je hand heen om kaarten uit hand te verkrijgen.
					System.out.println("Kaart " + i + " in uw hand was een " + Hand.get(i-1).getFullCard_name());
				}
				System.out.println("U had in totaal " + total_points + ".");
				rondeFinish = true;

			}
			else if(sc_input.equals("q")) {
				finished = true;
			}

		}



	}
	void playAgain() {
		System.out.println("Wilt u opnieuw spelen? J/N");
		Scanner input = new Scanner(System.in);
		String sc_input = input.nextLine();
		if(sc_input.equals("j") ) {
			playGame();
		}
		else if (sc_input.equals("n")) {
			finished = true;
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
}