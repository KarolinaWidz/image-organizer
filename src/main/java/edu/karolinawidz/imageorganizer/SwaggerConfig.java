package edu.karolinawidz.imageorganizer;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
//@PropertySource("application.yml")
public class SwaggerConfig {

	private static final String NO_ERROR_REGEX = "(?!.*error).*$";
	private String title;
	private String version;
	private String description;
	private String contactName;
	private String contactAddress;
	private String contactUrl;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactUrl() {
		return contactUrl;
	}

	public void setContactUrl(String contactUrl) {
		this.contactUrl = contactUrl;
	}

	@Bean
	public Docket SwaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("events")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(paths())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Image Organizer")
				.description("Organize your images and memes with tags!")
				.version("1.0")
				.contact(new Contact("Karolina Widz", "https://github.com/KarolinaWidz","karolinawidz97@gmail.com" ))
				.build();
	}



	private Predicate<String> paths() {
		return or(
				regex("/image.*"),
				regex("/user.*"));
	}
}