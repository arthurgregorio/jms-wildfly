package br.eti.arthurgregorio.jms.domain.services;

import br.eti.arthurgregorio.jms.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 02/01/2019
 */
@MessageDriven(
        name = "orderProcessorService",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/OrderProcessor")
        })
public class OrderProcessor implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

    /**
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {

        final String now = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());

        if (message instanceof ObjectMessage) {

            final ObjectMessage objectMessage = (ObjectMessage) message;

            try {
                final Order order = (Order) objectMessage.getObject();

                System.out.println(MessageFormat.format("Processando pedido {0} recebido em {1}",
                        order.getNumber(), now));

                order.getItems().forEach(item -> {
                    System.out.println("Processando item " + item.getName());
                });

                final String orderTotalText = NumberFormat.getCurrencyInstance().format(order.getTotalValue());

                System.out.println(MessageFormat.format("Pedido {0} no valor de {1} processado com sucesso!",
                        order.getNumber(), orderTotalText));
            } catch (JMSException ex) {
                this.logger.error("Erro ao desempacotar a mensagem!", ex);
            }
        }
    }
}
