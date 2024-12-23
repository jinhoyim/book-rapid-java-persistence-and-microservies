package book.apress.rapidjavapersistencemicroservice.eblogreactivech08.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;
import java.util.Objects;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    private final Environment environment;

    public DatabaseConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(builder()
                .option(DRIVER, "postgresql")
                .option(HOST, "localhost")
                .option(PORT, 5432)
                .option(USER, "postgres")
                .option(PASSWORD, Objects.requireNonNull(
                        environment.getProperty("LOCAL_DEVDB_SUPER_PASSWORD")))
                .option(DATABASE, "eblog_r2")
                .option(LOCK_WAIT_TIMEOUT, Duration.ofSeconds(10L))
                .option(STATEMENT_TIMEOUT, Duration.ofSeconds(30L))
                .build());
    }
}
