package com.jrodolfo.hibernateset.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.lang.invoke.MethodHandles;


/**
 * One of two Main entities of this app. Class MessageB implements equals().
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-05
 */
@Entity @Table(name = "messageb")
public class MessageB {

    @Transient
    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="id")
    private Long id;

    @Column(name="text")
    private String text;

    public MessageB(){}

    public MessageB(String text) {
        this.text = text;
    }

    public MessageB(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        String defaultToString = super.toString();
        String moreInfo = "identityHashCode=" + Integer.toHexString(System.identityHashCode(this));
        return "messageB{" +
                "id=" + id +
                ", text='" + text + '\'' +
                "} " + defaultToString + " " + moreInfo;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof MessageB)) return false;
        MessageB messageB = (MessageB) that;
        if (!getId().equals(messageB.getId())) return false;
        return getText().equals(messageB.getText());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getText().hashCode();
        return result;
    }

    public void compare(MessageB that) {
        checkIdentity(that);
        checkEquality(that);
    }

    public boolean checkIdentity(MessageB that) {
        logger.debug("\nChecking if the following objects are identical:\n" + this + '\n' + that);
        boolean areIdentical = (this == that);
        if (areIdentical) {
            logger.debug("They are identical.");
        } else {
            logger.debug("They are NOT identical.");
        }
        return areIdentical;
    }

    public boolean checkEquality(MessageB that) {
        logger.debug("\nChecking if the following objects are equal:\n" + this + '\n' + that);
        boolean areEqual = this.equals(that);
        if (areEqual) {
            logger.debug("They are equal.");
        } else {
            logger.debug("They are NOT equal.");
        }
        return areEqual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}












