package ru.uchebka.llm;
import java.util.List;import java.util.Map;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.http.*;import org.springframework.stereotype.Component;import org.springframework.web.client.RestClient;
import ru.uchebka.config.YandexProperties;import io.github.resilience4j.retry.annotation.Retry;import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Component public class YandexAgentClient implements LlmClient{
 private static final Logger log=LoggerFactory.getLogger(YandexAgentClient.class);private final RestClient client;private final YandexProperties p;
 public YandexAgentClient(RestClient yandexRestClient,YandexProperties properties){client=yandexRestClient;p=properties;}
 @Override @Retry(name="yandex") @CircuitBreaker(name="yandex") public String generate(String instruction,String material){
  if(p.apiKey()==null||p.apiKey().isBlank())throw new IllegalStateException("YANDEX_API_KEY is not configured");
  if(p.promptId()==null||p.promptId().isBlank())throw new IllegalStateException("YANDEX_PROMPT_ID is not configured");
  log.info("Calling Yandex Agent; materialLength={}",material.length());
  Map<String,Object> webSearch=Map.of("type","web_search","filters",Map.of("allowed_domains",List.of()),"search_context_size","medium");
  Map<String,Object> body=Map.of("prompt",Map.of("id",p.promptId()),"input",instruction+"\n\nУЧЕБНЫЙ МАТЕРИАЛ:\n"+material,"tools",List.of(webSearch));
  Map<?,?> response=client.post().uri("/responses").header(HttpHeaders.AUTHORIZATION,"Bearer "+p.apiKey()).header("OpenAI-Project",p.projectId()==null?"":p.projectId()).contentType(MediaType.APPLICATION_JSON).body(body).retrieve().body(Map.class);
  if(response==null||response.get("output_text")==null)throw new IllegalStateException("Yandex Agent returned empty response");return response.get("output_text").toString();
 }
}