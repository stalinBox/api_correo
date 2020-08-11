package ec.gob.mag.correo.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import ec.gob.mag.correo.dto.EmailRequestDto;
import ec.gob.mag.correo.exception.NotFoundException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration configuration;

	@Autowired
	private MessageSource messageSource;

	@Value("${from}")
	private String from;

	@Override
	public String sendHTMLEmail(EmailRequestDto request, Map<String, String> model) {

		String response;
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			ClassPathResource header = new ClassPathResource("static/images/header-mail.jpg");
			ClassPathResource footer = new ClassPathResource("static/images/footer-mail.jpg");
			Template template = configuration.getTemplate("email.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

			mimeMessageHelper.setTo(request.getPara());
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setSubject(request.getAsunto());
			mimeMessageHelper.setText(html, true);
			mimeMessageHelper.addInline("header", header);
			mimeMessageHelper.addInline("footer", footer);
			javaMailSender.send(mimeMessage);
			response = "Email enviado a: " + request.getPara();
		} catch (MessagingException | IOException | TemplateException e) {
			throw new NotFoundException(
					String.format(messageSource.getMessage("ERROR", null, LocaleContextHolder.getLocale()),
							this.getClass().getName()));
		}
		return response;
	}

}
