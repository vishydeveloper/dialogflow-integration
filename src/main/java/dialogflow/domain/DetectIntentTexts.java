/*
  Copyright 2017, Google, Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package dialogflow.domain;

// [START dialogflow_import_libraries]
// Imports the Google Cloud client library

import com.google.cloud.dialogflow.v2.*;
import com.google.cloud.dialogflow.v2.TextInput.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
// [END dialogflow_import_libraries]


/**
 * DialogFlow API Detect Intent sample with text inputs.
 */
public class DetectIntentTexts {

  // [START dialogflow_detect_intent_text]
  /**
   * Returns the result of detect intent with texts as inputs.
   *
   * Using the same `session_id` between requests allows continuation of the conversation.
   * @param projectId Project/Agent Id.
   * @param texts The text intents to be detected based on what a user says.
   * @param sessionId Identifier of the DetectIntent session.
   * @param languageCode Language code of the query.
   */
  public static void detectIntentTexts(String projectId, List<String> texts, String sessionId,
                                       String languageCode) throws Exception {
    // Instantiates a client
    try (SessionsClient sessionsClient = SessionsClient.create()) {
      // Set the session name using the sessionId (UUID) and projectID (my-project-id)
      SessionName session = SessionName.of(projectId, sessionId);
      System.out.println("Session Path: " + session.toString());

      // Detect intents for each text input
      for (String text : texts) {
        // Set the text (hello) and language code (en-US) for the query
        Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

        // Build the query with the TextInput
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        // Performs the detect intent request
        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

        // Display the query result
        QueryResult queryResult = response.getQueryResult();

        System.out.println("====================");
        System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
        System.out.format("Detected Intent: %s (confidence: %f)\n",
            queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
        System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());
      }
    }
  }
  // [END dialogflow_detect_intent_text]

  // [START run_application]
  public static void main(String[] args) throws Exception {
    ArrayList<String> texts = new ArrayList<>();
    String projectId = "";
    String sessionId = UUID.randomUUID().toString();
    String languageCode = "en-US";

    try {
      String command = args[0];
      if (command.equals("--projectId")) {
        projectId = args[1];
      }

      for (int i = 2; i < args.length; i++) {
        switch (args[i]) {
          case "--sessionId":
            sessionId = args[++i];
            break;
          case "--languageCode":
            languageCode = args[++i];
            break;
          default:
            texts.add(args[i]);
            break;
        }
      }

    } catch (Exception e) {
      System.out.println("Usage:");
      System.out.println("mvn exec:java -DDetectIntentTexts "
          + "-Dexec.args=\"--projectId PROJECT_ID --sessionId SESSION_ID "
          + "'hello' 'book a meeting room' 'Mountain View' 'tomorrow' '10 am' '2 hours' " 
          + "'10 people' 'A' 'yes'\"\n");

      System.out.println("Commands: text");
      System.out.println("\t--projectId <projectId> - Project/Agent Id");
      System.out.println("\tText: \"hello\" \"book a meeting room\" \"Mountain View\" \"tomorrow\" "
          + "\"10am\" \"2 hours\" \"10 people\" \"A\" \"yes\"");
      System.out.println("Optional Commands:");
      System.out.println("\t--languageCode <languageCode> - Language Code of the query (Defaults "
          + "to \"en-US\".)");
      System.out.println("\t--sessionId <sessionId> - Identifier of the DetectIntent session "
          + "(Defaults to a random UUID.)");
    }

    detectIntentTexts(projectId, texts, sessionId, languageCode);
  }
  // [END run_application]
}
