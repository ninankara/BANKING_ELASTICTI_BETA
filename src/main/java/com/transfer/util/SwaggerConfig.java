package com.transfer.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDate;

@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi depositApi() {
        return GroupedOpenApi.builder()
                .group("Transfer")
                .packagesToScan("com.transfer")
                .addOperationCustomizer(customGlobalHeaders())
                .build();
    }
    @Bean
    public OperationCustomizer customGlobalHeaders()
    {
        return (Operation operation, HandlerMethod handlerMethod) -> {

            Parameter key = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema()._default("ba125f31-68c8-4fc3-9a44-584bb51dbc48"))
                    .name("CH-Auth-Token")
                    .required(true);
            operation.addParametersItem(key);

            Parameter param1 = new Parameter()
                    .in(ParameterIn.HEADER.toString())
                    .schema(new StringSchema()._default("ONEQ005"))
                    .name("sysinfo.user_id")
                    .required(true);
            operation.addParametersItem(param1);

            try {
                Parameter param2 = new Parameter()
                        .in(ParameterIn.HEADER.toString())
                        .schema(new StringSchema()._default("TESTING"))
                        .name("sysinfo.trsc_dt")
                        .required(true);
                operation.addParametersItem(param2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }



            return operation;
        };
    }
}