package com.ncba.loop;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerSingletonInit {
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");


    static{
        postgresContainer.start();
    }
}
