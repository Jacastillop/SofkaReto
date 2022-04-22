/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package astrochallenge;

/**
 *
 * @author jhona
 */
public class PruebaReto3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Game quizChallenge = new Game();
        quizChallenge.start();

        while (quizChallenge.isGameON()) {
            quizChallenge.startRound();
            if (quizChallenge.isGameON()) {                
            quizChallenge.wishContinue();
            quizChallenge.nextRound();
            }
        }
        quizChallenge.gameOver();
        quizChallenge.showPlayerRanks();
//        
    }

}
