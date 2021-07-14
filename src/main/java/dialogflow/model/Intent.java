package dialogflow.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Intent {
    private String intentName;
    private List<IntentText> intentTexts;

}
