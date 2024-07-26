package org.acme.greeting.extension.deployment;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.util.List;

import org.acme.greeting.extension.runtime.CustomAnnotation;
import org.acme.greeting.extension.runtime.CustomInterceptor;
import org.jboss.jandex.AnnotationTransformation;
import org.jboss.jandex.DotName;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.AnnotationsTransformerBuildItem;
import io.quarkus.arc.processor.AnnotationsTransformer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import jakarta.enterprise.context.control.ActivateRequestContext;

class GreetingExtensionProcessor {
  private static final List<DotName> HANDLERS = List.of(
      DotName.createSimple(RequestHandler.class.getName()),
      DotName.createSimple(RequestStreamHandler.class.getName()));
  private static final String HANDLER_METHOD_NAME = "handleRequest";

  private static final String FEATURE = "greeting-extension";

  @BuildStep
  FeatureBuildItem feature() {
    return new FeatureBuildItem(FEATURE);
  }

  @BuildStep
  public AdditionalBeanBuildItem registerInterceptor() {
    return new AdditionalBeanBuildItem(CustomInterceptor.class);
  }

  // Decorate the RequestHandler/RequestStreamHandler method with the required annotation
  @BuildStep
  AnnotationsTransformerBuildItem transformRequestHandler() {
    // Uncomment the following line and the build (mvn clean verify) will fail
    return doesNotWork();
//    return works();
  }

  private AnnotationsTransformerBuildItem doesNotWork() {
    return new AnnotationsTransformerBuildItem(AnnotationTransformation.forMethods()
        .whenMethod(m -> m.name().equals(HANDLER_METHOD_NAME))
        .whenMethod(m -> m.declaringClass().interfaceNames().stream().anyMatch(HANDLERS::contains))
        .transform(t -> {
          t.add(CustomAnnotation.class);
          t.add(ActivateRequestContext.class);
        }));
  }

  private AnnotationsTransformerBuildItem works() {
    return new AnnotationsTransformerBuildItem(AnnotationsTransformer.appliedToMethod()
        .whenMethod(m -> m.name().equals(HANDLER_METHOD_NAME))
        .whenMethod(m -> m.declaringClass().interfaceNames().stream().anyMatch(HANDLERS::contains))
        .thenTransform(t -> {
          t.add(CustomAnnotation.class);
          t.add(ActivateRequestContext.class);
        }));
  }
}
