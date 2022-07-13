package ru.lastenko.studenttest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lastenko.studenttest.service.Assistant;
import ru.lastenko.studenttest.service.ConsoleAssistant;

import java.util.Scanner;

@Configuration
public class AssistantConfig {

    @Bean
    public Assistant assistant() {
        return new ConsoleAssistant(new Scanner(System.in));
    }
}
