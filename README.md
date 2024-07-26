Since `io.quarkus.arc.deployment.AnnotationsTransformerBuildItem.AnnotationsTransformerBuildItem(io.quarkus.arc.processor.AnnotationsTransformer)`
was deprecated and marked for removal, I'm unable to use the suggested `io.quarkus.arc.deployment.AnnotationsTransformerBuildItem.AnnotationsTransformerBuildItem(org.jboss.jandex.AnnotationTransformation)` constructor.

Check [`org.acme.greeting.extension.deployment.GreetingExtensionProcessor.transformRequestHandler`](deployment/src/main/java/org/acme/greeting/extension/deployment/GreetingExtensionProcessor.java#L40) to trigger the error
