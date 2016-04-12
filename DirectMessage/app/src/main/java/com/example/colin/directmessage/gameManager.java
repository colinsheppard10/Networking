package com.example.colin.directmessage;

import java.util.Random;

/**
 * Created by Colin on 4/7/16.
 */
public class gameManager {
        gameManager(player player1, player player2){

            int[] cards = new int[52];
            int dealt = 0;
            int turn = 1;
            Random random = new Random();

            while (dealt < 52) {
                int number = random.nextInt(52);

                if (cards[number] == 0) {
                    if (turn == 1) {
                       // player1.cardValue.add(number,(dealt / 2));
                        player1.cardValue.add(number);
                        turn = 2;
                    } else {
                       // player2.cardValue.add(number,(dealt / 2));
                        player2.cardValue.add(number);

                        turn = 1;
                    }
                    cards[number] = 1;
                    dealt += 1;
                }
            }
        }


}
