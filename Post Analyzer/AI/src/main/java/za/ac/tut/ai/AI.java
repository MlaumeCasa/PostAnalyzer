package za.ac.tut.ai;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

/**
 *
 * @author hexaredecimal
 */
public class AI {
    
    private static ChatModel gemini = GoogleAiGeminiChatModel.builder()
            .apiKey(System.getenv("GEMINI_API_KEY"))
            .modelName("gemini-1.5-flash")
            .build();

    public static String chat(String prompt) {
        ChatResponse chatResponse = gemini.chat(ChatRequest.builder()
                .messages(UserMessage.from(prompt))
                .build());
        String response = chatResponse.aiMessage().text();
        return response;
    }
}
