package org.acme.greeting.extension.runtime;

import org.jboss.logging.Logger;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@CustomAnnotation
@Priority(0)
@Interceptor
public class CustomInterceptor {

  private static final Logger LOG = Logger.getLogger(CustomInterceptor.class);

  @AroundInvoke
  Object logAndContinue(InvocationContext invocationContext) throws Exception {
    LOG.info("This request was intercepted");
    return invocationContext.proceed();
  }
}
