package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.Dao.CardRepository;
import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("cards")
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getCards(){
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getCardsFilterColor(@PathVariable String color){
        CardValidation.isNullOrEmpty(color);
        CardValidation.isColorEqualsEnum(color);

        return cardRepository.findByColor(color);
    }

    @PostMapping
    public Card saveCard(@RequestBody Card card){
        CardValidation.isNullOrEmpty(String.valueOf(card.getType()));
        return cardRepository.save(card);
    }

    @PutMapping("/")
    public Card updateCard(@RequestBody Card card){
        CardValidation.isNullOrEmpty(String.valueOf(card.getType()));
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card deleteCard(@PathVariable long id){
        return cardRepository.remove(id);
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getCardFilterValue(@PathVariable Integer value){
        CardValidation.isNullOrEmpty(String.valueOf(value));
        return  cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getCardsFilterType(@PathVariable String type){
        CardValidation.isNullOrEmpty(type);
        return cardRepository.findByType(type);
    }
}
