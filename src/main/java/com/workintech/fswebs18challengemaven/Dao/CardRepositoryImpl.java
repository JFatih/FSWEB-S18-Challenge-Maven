package com.workintech.fswebs18challengemaven.Dao;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository{

    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        Color enumColor = Color.valueOf(color);
        TypedQuery<Card> cardFilterColor = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :enumColor",Card.class);
        cardFilterColor.setParameter("enumColor",enumColor);
        if(findAll().isEmpty()){
            throw new CardException("Empty data", HttpStatus.NOT_FOUND);
        }
        return cardFilterColor.getResultList();
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> allCards = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        return allCards.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> cardsFilterValue = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = value", Card.class);
        cardsFilterValue.setParameter("value",value);
        return cardsFilterValue.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        Type enumType = Type.valueOf(type);
        TypedQuery<Card> cardsFilterType = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :enumType", Card.class);
        cardsFilterType.setParameter("enumType", enumType);
        return cardsFilterType.getResultList();
    }

    @Override
    @Transactional
    public Card update(Card card) {
        entityManager.merge(card);
        return card;
    }

    @Transactional
    @Override
    public Card remove(long id) {
        Card removeCard = entityManager.find(Card.class,id);
        entityManager.remove(removeCard);
        return removeCard;
    }
}
