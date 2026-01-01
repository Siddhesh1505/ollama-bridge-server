package com.example.testAI.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // ALLOW REACT ACCESS
public class OllamaController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String OLLAMA_URL = "http://localhost:11434/api";

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt, @RequestParam String model) {
        String url = OLLAMA_URL + "/generate";
        Map<String, Object> request = Map.of(
                "model", model,
                "prompt", prompt,
                "stream", false
        );

        try {
            Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);
            return (String) response.get("response");
        } catch (Exception e) {
            return "Backend Error: " + e.getMessage();
        }
    }

    @GetMapping("/models")
    public Map<String, Object> getModels() {
        // Fetch actual models from Ollama's local engine
        return restTemplate.getForObject(OLLAMA_URL + "/tags", Map.class);
    }
}