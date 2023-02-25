package com.como.hogar.referencias.base;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BaseTest {

    @Container
    public static GenericContainer h2Container = new GenericContainer("h2:1.4.199")
            .withExposedPorts(1521)
            .withEnv("DATABASE_NAME", "testdb")
            .withEnv("DATABASE_USER", "testuser")
            .withEnv("DATABASE_PASSWORD", "testpassword");

    @BeforeAll
    public static void setupFlyway() {
        String jdbcUrl = String.format("jdbc:h2:tcp://%s:%d/testdb", h2Container.getContainerIpAddress(),
                h2Container.getFirstMappedPort());

        Flyway flyway = Flyway.configure().dataSource(jdbcUrl, "testuser", "testpassword").load();
        flyway.migrate();
    }
}
