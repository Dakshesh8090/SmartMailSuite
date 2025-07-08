package com.email.writer.service;


import com.email.writer.model.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webclient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webclientBuilder) {
        this.webclient = webclientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){
        //build the prompt -------- which go as input in the api
        String prompt = buildPrompt(emailRequest);


        //craft a request because we have already seen that not a direct prompt as a text is sent
        Map<String, Object> requestBody = Map.of(
                "contents" ,new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );


        //do request and get response
        String response = webclient.post()                    //Yahan hum POST request bhej rahe hain.
                .uri(geminiApiUrl+geminiApiKey)           //Ye API ka address (URL) set kar raha hai. ex o pura URI ban gaya: https://api.gemini.com/send?key=YOUR_KEY
                .header("Content-Type", "application/json")                 //Ye header batata hai ki hum JSON format mai data bhej rahe hain.
                .bodyValue(requestBody)
                .retrieve()                                   //Basically, yeh WebClient ko bolta hai: "ab server se response uthao.
                .bodyToMono(String.class)                     //Hum bol rahe hain ki response ko String format mai convert karo.
                .block();                                     //Ye line bolti hai: "Ab ruk jao jab tak response mil nahi jaata."

        //Extract response and Return response
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }catch (Exception e){
            return "Error processing request: " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please do not generate a subject line ");
        if(emailRequest.getTone()!= null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());

        return prompt.toString();
    }
}
