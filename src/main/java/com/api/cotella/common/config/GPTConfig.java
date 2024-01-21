package com.api.cotella.common.config;

import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"local", "dev", "prod"})
@Configuration
public class GPTConfig {
  public final static String MODEL = "gpt-3.5-turbo";
  public final static double TEMPERATURE = 0.7;
  public final static Duration TIME_OUT = Duration.ofSeconds(100);

  @Value("${openai.api.key}")
  private String token;

  @Profile({"local", "dev", "prod"})
  @Bean
  public OpenAiService openAiService() {
    return new OpenAiService(token, TIME_OUT);
  }
}
