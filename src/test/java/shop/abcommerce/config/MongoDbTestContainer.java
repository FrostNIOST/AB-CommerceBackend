package shop.abcommerce.config;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.mongodb.MongoDBContainer;

public interface MongoDbTestContainer {
    // Lazy initialization - only create if MongoDB_URI is not set
    class ContainerHolder {

        static final MongoDBContainer mongoDbContainer = createContainer();

        private static MongoDBContainer createContainer() {
            LoggerFactory.getLogger(MongoDbTestContainer.class).info("Initializing TestContainers MongoDB container");
            return new MongoDBContainer("mongo:8.2.9")
                .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(MongoDbTestContainer.class)))
                .withReuse(true);
        }

        static synchronized String getReplicaSetUrl() {
            if (!mongoDbContainer.isRunning()) {
                mongoDbContainer.start();
            }
            return mongoDbContainer.getReplicaSetUrl();
        }
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        // Check for local MongoDB first via environment variable
        String mongoUri = System.getenv("MONGODB_URI");
        if (mongoUri != null && !mongoUri.isEmpty()) {
            LoggerFactory.getLogger(MongoDbTestContainer.class).info("Using MongoDB URI from environment: {}", mongoUri);
            // Use spring.data.mongodb.uri for Spring Boot property binding
            registry.add("spring.data.mongodb.uri", () -> mongoUri);
        } else {
            LoggerFactory.getLogger(MongoDbTestContainer.class).info("Using TestContainers MongoDB container");
            // Use spring.data.mongodb.uri for Spring Boot property binding
            registry.add("spring.data.mongodb.uri", ContainerHolder::getReplicaSetUrl);
        }
    }
}
