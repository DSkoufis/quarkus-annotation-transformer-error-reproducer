package org.acme.greeting.extension.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

class CustomTest {

  @RegisterExtension
  static final QuarkusUnitTest config = new QuarkusUnitTest()
      .withApplicationRoot(jar -> jar.addClass(TestRequestHandler.class));

  @Test
  void testAppStarts() {
    //@formatter:off
    given()
        .contentType("application/json").accept("application/json")
        .body("{}")
        .when().post()
        .then().statusCode(200);
    //@formatter:on
  }
}
