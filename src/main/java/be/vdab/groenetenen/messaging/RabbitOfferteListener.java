package be.vdab.groenetenen.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import be.vdab.groenetenen.mail.MailSender;

@Component
public class RabbitOfferteListener {
	private final MailSender mailSender;

	public RabbitOfferteListener(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RabbitListener(queues = RabbitMqConfig.QUEUE_OFFERTES)
	public void OntvangOfferte(OfferteEnOffertesURL offerteEnOffertesURL) {
		mailSender.nieuweOfferte(offerteEnOffertesURL.getOfferte(), offerteEnOffertesURL.getOffertesURL());
	}
}
