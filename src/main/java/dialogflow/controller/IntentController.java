package dialogflow.controller;


import dialogflow.domain.IntentManagement;
import dialogflow.model.Intent;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class IntentController {
    @PostMapping("/createIntents")
    public String createIntents(@RequestBody Intent intent) throws Exception {
        System.out.println(intent);
        createIntent(intent);
        return "Valid Intent configured !!";
    }

    @DeleteMapping("/deleteIntent")
    public String deleteIntent(@RequestBody Intent intent) throws Exception {
        deleteIntent1(intent);
        return "Intent deleted !!";
    }

    private void deleteIntent1(Intent intent) throws Exception {
        String PROJECT_ID = System.getenv().get("GOOGLE_CLOUD_PROJECT");
        String INTENT_DISPLAY_NAME = intent.getIntentName();
        IntentManagement intentManagement = new IntentManagement();
        List<String> intentIds = intentManagement.getIntentIds(INTENT_DISPLAY_NAME, PROJECT_ID);
        System.out.println(intentIds);

        for (String intentId : intentIds) {
            intentManagement.deleteIntent(intentId, PROJECT_ID);
        }
    }

    public void createIntent(Intent intent) throws Exception {
        // Create the intent
        String PROJECT_ID = System.getenv().get("GOOGLE_CLOUD_PROJECT");
        String INTENT_DISPLAY_NAME = intent.getIntentName();
        IntentManagement intentManagement = new IntentManagement();
        List<String> trainingPhrasesParts =
                intent.getIntentTexts()
                        .stream()
                        .map(p -> p.intentTextName)
                        .collect(Collectors.toList());
        intentManagement.createIntent(INTENT_DISPLAY_NAME, PROJECT_ID, trainingPhrasesParts, trainingPhrasesParts);
    }
}
