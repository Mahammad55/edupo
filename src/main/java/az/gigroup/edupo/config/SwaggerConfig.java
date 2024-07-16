package az.gigroup.edupo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        title = "EduPo",
        description = "A customer Registration Platform",
        version = "v1.0",
        contact = @Contact(
                name = "Israfil Isgandarov",
                email = "isgandarov.israfil@gmail.com",
                url = "https://github.com/Mahammad55/edupo"
        )
))
public class SwaggerConfig {
}
