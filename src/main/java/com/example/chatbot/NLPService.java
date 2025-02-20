package com.example.chatbot;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.FileInputStream;
import java.io.IOException;

public class NLPService {

    private DoccatModel model;

    public NLPService() throws IOException {
        InputStreamFactory dataIn = () -> new FileInputStream("src/main/resources/en-healthcare.bin");
        model = new DoccatModel(dataIn.createInputStream());
    }

    public String classifyInput(String userInput) throws IOException {
        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);
        double[] outcomes = categorizer.categorize(userInput);
        String category = categorizer.getBestCategory(outcomes);

        switch (category) {
            case "greeting":
                return "Hello! How can I assist you with your healthcare needs?";
            case "symptoms":
                return "I see you're describing symptoms. I recommend consulting a doctor for a professional opinion.";
            case "medicine":
                return "I can provide general information on medications, but please consult a healthcare professional before taking any.";
            case "goodbye":
                return "Take care! Stay healthy.";
            default:
                return "I'm still learning. Please rephrase your question.";
        }
    }
}
