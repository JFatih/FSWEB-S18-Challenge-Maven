package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cards",schema="s18Challenge")
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="value")
    private Integer value;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Color color;


    public Card(Integer value, Color color) {
        this.value = value;
        this.color = color;
        this.type = null;
    }

    public Card(Type type, Color color) {
        if(type == Type.JOKER){
            this.value = null;
            this.type = type;
            this.color = null;
        }else{
            this.value = null;
            this.type = type;
            this.color = color;
        }
    }
}
