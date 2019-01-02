package br.eti.arthurgregorio.jms.domain.model;

import br.eti.arthurgregorio.jms.infrastructure.misc.CodeGenerator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 02/01/2019
 */
public class Order implements Serializable {

    @Getter
    @Setter
    public String number;
    @Getter
    @Setter
    public BigDecimal totalValue;

    @Getter
    @Setter
    private Status status;

    private final Set<Item> items;

    /**
     *
     */
    public Order() {
        this.number = CodeGenerator.alphanumeric(6);
        this.status = Status.OPEN;
        this.items = new HashSet<>();
    }

    /**
     *
     * @param item
     */
    public void addItem(Item item) {
        this.items.add(item);
        this.calculateTotal();
    }

    /**
     *
     * @param item
     */
    public void removeItem(Item item) {
        this.items.remove(item);
        this.calculateTotal();
    }

    /**
     *
     * @return
     */
    public Set<Item> getItems() {
        return Collections.unmodifiableSet(this.items);
    }

    /**
     *
     */
    private void calculateTotal() {
        this.totalValue = this.items.stream()
                .map(Item::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
