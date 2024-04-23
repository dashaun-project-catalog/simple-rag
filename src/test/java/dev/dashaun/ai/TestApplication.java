package dev.dashaun.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestApplication {

    @Bean
    OllamaContainer ollamaContainer() throws Exception {
        OllamaContainer instance = new OllamaContainer(DockerImageName.parse("ollama/ollama:0.1.26")).withExposedPorts(11434);
        instance.start();
        instance.execInContainer("ollama", "pull", "mistral");
        instance.commitToImage("dashaun/ollama-mistral:7B");
        System.setProperty("OLLAMA_HOST", instance.getHost());
        System.setProperty("OLLAMA_PORT", instance.getMappedPort(11434).toString());
        return instance;
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestApplication.class).run(args);
    }

}
