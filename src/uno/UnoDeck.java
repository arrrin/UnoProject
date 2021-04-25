/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author acer
 */
public class UnoDeck {
    private UnoCard[] cards;
    private int cardInDeck;
    
    public UnoDeck()
    {        
        cards = new UnoCard[108];
    }
    
    public void reset()
    {
       
        UnoCard.Color[] colors = UnoCard.Color.values();
        cardInDeck =0;
        
        //fill 4 color to all card 
        for (int i = 0; i < colors.length-1; i++) {
            UnoCard.Color color = colors[i];
            
            // fill color with card 0 108-4 = 104 cards left
            cards[cardInDeck++] = new  UnoCard(color, UnoCard.Value.getValue(0));
            
            //fill color with card 1-9 104-80 = 24 cards left
            for(int j = 0; j < 10; j++)
            {
                cards[cardInDeck++] = new  UnoCard(color, UnoCard.Value.getValue(j));
                cards[cardInDeck++] = new  UnoCard(color, UnoCard.Value.getValue(j));
            }
            
            //special card arrays loop color filled 
            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.DrawTwo,UnoCard.Value.Skip,UnoCard.Value.Reverse};
            for(UnoCard.Value value : values)
            {
                cards[cardInDeck++] = new  UnoCard(color,value);
                cards[cardInDeck++] = new  UnoCard(color,value);
            }
        }    
            //wild cards fill to deck  
            UnoCard.Value[] values = new UnoCard.Value[]{UnoCard.Value.Wild,UnoCard.Value.WildFour};
            for(UnoCard.Value value : values)
            {
                for (int i = 0; i < 4; i++) {
                   cards[cardInDeck++] = new  UnoCard(UnoCard.Color.Wild,UnoCard.Value.WildFour); 
                }
                
                
            }
    }
    
    //replace card with arraylist
    public void  replaceDeckWith(ArrayList<UnoCard> cards)
    {
        this.cards = cards.toArray(new UnoCard[cards.size()]);
        this.cardInDeck =this.cards.length;
    }
    
    public boolean isEmpty()
    {
        return cardInDeck==0;
    }
    
    public void shuffleDeck(){
        int n = cards.length;
        Random random = new Random();
        
        for (int i = 0; i < cards.length; i++) {
            int randomValue = i + random.nextInt(n-i);
            UnoCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }
    
    public UnoCard drawCard() throws IllegalArgumentException
    {
        if(isEmpty())
        {
            throw new IllegalArgumentException("Cannot draw a card cause there is no card in a deck");
        }
        return  cards[--cardInDeck];
    }
    
    public ImageIcon drawCardImage() throws IllegalArgumentException
    {
        if(isEmpty())
        {
            throw new IllegalArgumentException("Cannot draw a card cause there is no card in a deck");
        }
        return new ImageIcon(cards[--cardInDeck].toString() + ".png");
    }
    
    //draw many cards
    public UnoCard[] drawCard(int n) throws IllegalArgumentException
    {
        if(n < 0)
        {
            throw new IllegalArgumentException("Must draw positive cards");
        }
        if(n > cardInDeck)
        {
            throw new IllegalArgumentException("Cannot draw more card cause there is no card in deck");
        }
        UnoCard[] ret = new  UnoCard[n];
        
        for (int i = 0; i < n; i++) {
            ret[i] = cards[--cardInDeck];
            
        }
        return ret;
    }
}
