package com.paulentine.android.capstone;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.services.actions_fulfillment.v2.model.User;
import com.paulentine.android.capstone.model.Recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class VoiceFulfillment extends DialogflowApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceFulfillment.class);

    @ForIntent("Default Welcome Intent")
    public ActionResponse defaultWelcome(ActionRequest request) {
        ResponseBuilder rb = getResponseBuilder(request);
        rb.add("Welcome to Look Ma! Say next step to begin");
        return rb.endConversation().build();
    }

    @ForIntent("Repeat Step")
    public ActionResponse repeatStep(ActionRequest request) {
        ResponseBuilder rb = getResponseBuilder(request);

        // How do we pass which recipe we're referring to?
        String stepText = Recipe.currRecipe.readInstruction();
        rb.add("You said " + stepText);
        return rb.endConversation().build();
    }

//    @ForIntent("Next Step")
//    public ActionResponse nextStep(ActionRequest request) {
//        ResponseBuilder rb = getResponseBuilder(request);
//
//        String stepText = Recipe.
//    }


//    @ForIntent("Read step")
//    public ActionResponse repeatStep(ActionRequest request) {
//        LOGGER.info("Read step intent start.");
//        ResponseBuilder responseBuilder = getResponseBuilder(request);
//        ResourceBundle rb = ResourceBundle.getBundle("resources");
//        User user = request.getUser();
//
//        if (user != null && user.getLastSeen() != null) {
//            responseBuilder.add(rb.getString("welcome_back"));
//        } else {
//            responseBuilder.add(rb.getString("welcome"));
//        }
//
//        LOGGER.info("This intent end.");
//        return responseBuilder.build();
//    }
//
//    @ForIntent("Next")
//    public ActionResponse next(ActionRequest request) {
//        LOGGER.info("Next intent start.");
//        ResponseBuilder responseBuilder = getResponseBuilder(request);
//        ResourceBundle rb = ResourceBundle.getBundle("resources");
//
//        responseBuilder.add(rb.getString("bye")).endConversation();
//        LOGGER.info("Next intent end.");
//        return responseBuilder.build();
//    }
}

