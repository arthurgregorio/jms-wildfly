package br.eti.arthurgregorio.jms.domain.model;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 02/01/2019
 */
public class Item implements Serializable {

    @Getter
    private String name;
    @Getter
    private int quantity;
    @Getter
    private BigDecimal unitaryValue;
    @Getter
    private BigDecimal totalValue;

    /**
     *
     * @param name
     * @param quantity
     * @param unitaryValue
     */
    public Item(String name, int quantity, BigDecimal unitaryValue) {
        this.name = name;
        this.quantity = quantity;
        this.unitaryValue = unitaryValue;
        this.totalValue = unitaryValue.multiply(new BigDecimal(quantity));
    }
}
