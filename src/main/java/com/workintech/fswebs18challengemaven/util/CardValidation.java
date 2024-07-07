package com.workintech.fswebs18challengemaven.util;


import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CardValidation {

    public static void isNullOrEmpty(String data){
        if(data == null || data.isEmpty()){
            throw new CardException("Data not be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

   public static void isCardsListHaveCard(List<Card> cards){
       if(cards.isEmpty()){
           throw new CardException("Empty data", HttpStatus.NOT_FOUND);
       }
   }


    public static void isColorEqualsEnum(String color) {
        boolean isValid = false;
        for(Color enumColor: Color.values()){
            if(enumColor.name().equalsIgnoreCase(color)){
                isValid = true;
                break;
            }
        }
        if(!isValid){
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
    }
}
