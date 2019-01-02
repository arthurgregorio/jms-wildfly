package br.eti.arthurgregorio.jms.application;

import br.eti.arthurgregorio.jms.domain.model.Item;
import br.eti.arthurgregorio.jms.domain.model.Order;

import javax.annotation.Resource;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 02/01/2019
 */
@Named
@ViewScoped
public class SendOrderBean implements Serializable {

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/OrderProcessor")
    private Queue queue;

    /**
     *
     */
    public void sendOrder() {

        final Order order = new Order();

        order.addItem(new Item("Tomate", 1, new BigDecimal(10)));
        order.addItem(new Item("Pimentão", 2, new BigDecimal(20)));
        order.addItem(new Item("Cebola", 3, new BigDecimal(30)));
        order.addItem(new Item("Alho", 4, new BigDecimal(40)));
        order.addItem(new Item("Alface", 5, new BigDecimal(50)));

        this.context.createProducer().send(this.queue, this.context.createObjectMessage(order));
    }
}
