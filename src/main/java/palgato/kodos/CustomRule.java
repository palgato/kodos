package palgato.kodos;

public class CustomRule {

    private String ruleName;
    private String ruleDescription;

    public CustomRule(String ruleName, String ruleDescription) {
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }
}
