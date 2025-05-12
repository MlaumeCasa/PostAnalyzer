package za.ac.tut.models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AILanguageModel {

    // Insert Valid OpenAI API key here - Visist https://platform.openai.com/api-keys to get API KEY
    private static final String OPENAI_API_KEY = "OPENAI_API_KEY_GOES_HERE";
    private String prompt = "Rate the following post with a percentage as a double, and respond with only the number - Post Content == ";
    private String response;

    //Constructor(s)
    public AILanguageModel(String prompt) throws IOException {
        this.prompt += prompt;
        this.response = getAnalytics(prompt);
    }
    //User-defined Methods
    public String getResponse(String chatResponse) {
        int contentIndex = chatResponse.indexOf("content") + 11;
        int refusalIndex = chatResponse.indexOf("refusal") - 11;
        String subString = chatResponse.substring(contentIndex, refusalIndex);
        return subString;
    }

    private String getAnalytics(String userprompt) throws IOException {
        String openAIURLString = "https://api.openai.com/v1/chat/completions";
        String OpenAI_API = getOPENAI_API_KEY();
        String model = "gpt-4"; // Updated model name
        userprompt = prompt + userprompt;

        URL url = new URL(openAIURLString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + OpenAI_API);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String payload = "{"
                + "\"model\": \"gpt-4o-mini\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \""+userprompt+"\"}]"
                + "}";

        try (OutputStream os = con.getOutputStream()) {
            os.write(payload.getBytes("UTF-8"));
        }

        int responseCode = con.getResponseCode();
        InputStream is = (responseCode == 200) ? con.getInputStream() : con.getErrorStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            responseBuilder.append(line);
        }

        // Manually Extract the message content
        String chatResponse = responseBuilder.toString();

        int contentIndex = chatResponse.indexOf("content") + 11;
        int refusalIndex = chatResponse.indexOf("refusal") - 11;
        String subString = chatResponse.substring(contentIndex, refusalIndex);
        
        return subString;
    }

    //Main Method - Testing Method
    public static void main(String[] args) throws IOException {
        AILanguageModel AIModel = new AILanguageModel("I feel so happy today!");
        System.out.println("Prompt: " + AIModel.getPrompt());
        System.out.println("Response: " + AIModel.getResponse());
    }
    
    //Getter and Setters
    public static String getOPENAI_API_KEY() {
        return OPENAI_API_KEY;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    public String getPrompt() {
        return prompt;
    }
    
    public String getResponse() {
        return response;
    }
}
