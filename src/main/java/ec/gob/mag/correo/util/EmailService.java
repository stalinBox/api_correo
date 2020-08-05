package ec.gob.mag.correo.util;

import java.util.Map;

import org.springframework.stereotype.Service;

import ec.gob.mag.correo.dto.EmailRequestDto;

@Service
public interface EmailService {

	String sendHTMLEmail(EmailRequestDto request, Map<String, String> model);

}
