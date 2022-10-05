package io.probono.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //자바 소스가 설정 파일로 쓰인다는 의미
@EnableSwagger2  
public class SwaggerConfig {
	
	/* 1. Docket(DocumentationType.SWAGGER_2) 
	 * 	- swagger 설정 필수 핵심 bean
	 * 	- swagger 버전에 따른 차이점
	 * 
	 * 2. .ignoredParameterTypes(ApiIgnore.class)
	 * 	- @ApiIgnore HttpSession session 처럼 특정  parameter를 사용자에게 은닉 가능하게 하는 설정
	 * 	- parameter 정보 선별해서 문서에 기록시에는 필수
	 * 
	 * 3. apiInfo(swaggerInfo())
	 * - swaggerInfo() 사용자 정의 메소드가 parameter
	 * - 제목, 설명 등 header 부분을 별도의 메소드로 개발해서 호출해서 적용
	 * 
	 * 4. apis() 
	 * 	- 컨트롤러가 존재하는 package 등록
	 *  - 서비스 하고자 하는 서비스들(기능, 방식, parameter, 반환값, 오류 메세지) 소개 , 필수 
	 *  
	 * 5. useDefaultResponseMessages(false)
	 * 	- true
	 * 		- 하단 코드 설정과 무관한 상태 정보도 서비스
	 *  - false
	 *  	- 코드상에 다음 처럼 설정한 응답 상태 정보만 doc에 서비스
	 *  
	 *  @ApiResponses({ @ApiResponse(code = 200, message = "OK !!"),...
	 */
    @Bean //스프링 빈으로 등록 의미
    public Docket swaggerApi() {
    	
        return new Docket(DocumentationType.SWAGGER_2)
        		.ignoredParameterTypes(ApiIgnore.class)
        		.apiInfo(swaggerInfo())
        		.select()
                .apis(RequestHandlerSelectors.basePackage("io.probono.controller"))
                .build()
                .useDefaultResponseMessages(false); 
    }
    

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("probonoDoc")
                .description("probono method 설명\n4조\nrest-controller만 봐주세요.")
                .license("license : playdata").licenseUrl("https://playdata.io/")
                .version("1.0.0")
                .termsOfServiceUrl("http://localhost:8080/step19/index.html")
                .build();
    }
}
