package com.zxw.childhood.passport.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zxw
 * @date 2020-06-24 12:12
 */
public class EnablePassportSsoCondition  extends SpringBootCondition {
    /**
     * Determine the outcome of the match along with suitable log output.
     *
     * @param context  the condition context
     * @param metadata the annotation metadata
     * @return the condition outcome
     */
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取注解对象的上下文,只能使用在WebSecurityConfigurerAdapter中,其他无法生效
        String[] enablers = context.getBeanFactory()
                .getBeanNamesForAnnotation(EnablePassportSso.class);
        ConditionMessage.Builder message = ConditionMessage
                .forCondition("@EnablePassport2Sso Condition");
        for (String name : enablers) {
            if (context.getBeanFactory().isTypeMatch(name,
                    WebSecurityConfigurerAdapter.class)) {
                return ConditionOutcome.match(message
                        .found("@EnablePassport2Sso annotation on WebSecurityConfigurerAdapter")
                        .items(name));
            }
        }
        return ConditionOutcome.noMatch(message.didNotFind(
                "@EnablePassport2Sso annotation " + "on any WebSecurityConfigurerAdapter")
                .atAll());
    }
}
