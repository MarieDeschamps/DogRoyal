package io.dog.dao;

import javax.persistence.EntityManager;

import io.dog.dto.Card;

public class CardDao {

    EntityManager em;

    public CardDao(EntityManager em){
        this.em=em;
    }

    public void create(Card card){
        em.persist(card);
    }

    public void delete(Card card){
        em.remove(card);

    }

    public void update(Card card){

    }

}
