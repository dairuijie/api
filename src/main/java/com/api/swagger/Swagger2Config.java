package com.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @ClassName:  Swagger2Config   
 * @Description:TODO(swagger 配置)   
 * @author: drj 
 * @date:   2019年1月19日 下午8:10:52   
 *     
 * @Copyright: 2019 
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.api")).paths(PathSelectors.any())
				.build();
	}//PathSelectors.regex("/v1/")

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("dairuijie Restful API").description("代瑞杰 API")
				.version("1.0").build();

	}
}
