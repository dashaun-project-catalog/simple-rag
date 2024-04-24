package dev.dashaun.ai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class ApplicationTests {

    @Bean
    OllamaContainer ollamaContainer() throws Exception {
        OllamaContainer instance = new OllamaContainer(
                DockerImageName.parse("dashaun/ollama-mistral:7B")
                        .asCompatibleSubstituteFor("ollama/ollama")
        );
        instance.start();
        instance.execInContainer("ollama", "pull", "mistral");
        System.setProperty("OLLAMA_HOST", instance.getHost());
        System.setProperty("OLLAMA_PORT", instance.getMappedPort(11434).toString());
        return instance;
    }

    @Test
    void contextLoads() {
    }

}
