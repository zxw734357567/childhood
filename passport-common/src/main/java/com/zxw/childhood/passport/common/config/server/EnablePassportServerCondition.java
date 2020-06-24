package com.zxw.childhood.passport.common.config.server;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zxw
 * @date 2020-06-16 20:21
 */
public class EnablePassportServerCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata metadata) {
        //条件上下文
        String[] enablers = context.getBeanFactory()
                .getBeanNamesForAnnotation(EnablePassportServer.class);
        ConditionMessage.Builder message = ConditionMessage
                .forCondition("@EnablePassportServer Condition");
        for (String name : enablers) {
            if (context.getBeanFactory().isTypeMatch(name,
                    WebSecurityConfigurerAdapter.class)) {
                return ConditionOutcome.match(message
                        .found("@EnablePassportServer annotation on WebSecurityConfigurerAdapter")
                        .items(name));
            }
        }
        return ConditionOutcome.noMatch(message.didNotFind(
                "@EnablePassportServer annotation " + "on any WebSecurityConfigurerAdapter")
                .atAll());
    }
}
