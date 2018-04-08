package br.com.rr.feed.swagger;

import br.com.rr.feed.vo.AccountCredentialsVO;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1003)
public class LoginListingScannerPlugin implements ApiListingScannerPlugin {

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return new ArrayList<ApiDescription>(
            Arrays.asList(
                new ApiDescription(
                "/login",
                "Login via usuario/senha",
                Arrays.asList(
                    new OperationBuilder(
                    new CachingOperationNameGenerator())
                        .authorizations(new ArrayList())
                        .codegenMethodNameStem("loginPOST")
                        .method(HttpMethod.POST)
                        .notes("login via POST")
                        .parameters(
                            Arrays.asList(
                                new ParameterBuilder()
                                    .description("usuario")
                                    .type(new TypeResolver().resolve(AccountCredentialsVO.class))
                                    .name("accountCredentials")
                                    .parameterType("body")
                                    .parameterAccess("access")
                                    .required(true)
                                    .modelRef(new ModelRef("accountCredentials"))
                                    .build()))
                        .build()),
                    false)));
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
