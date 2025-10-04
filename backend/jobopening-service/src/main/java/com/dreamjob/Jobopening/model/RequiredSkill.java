package com.dreamjob.Jobopening.model;

public class RequiredSkill {

    private SkillName name;
    private int minimumYearsOfExperienceRequired;
    private boolean mandatory; // scale of 1–5

    public RequiredSkill() {}

    public RequiredSkill(SkillName name, int minimumYearsOfExperienceRequired, boolean mandatory) {
        this.name = name;
        this.minimumYearsOfExperienceRequired = minimumYearsOfExperienceRequired;
        this.mandatory = mandatory;
    }

    public SkillName getName() { return name; }
    public void setName(SkillName name) { this.name = name; }

    public int getMinimumYearsOfExperienceRequired() { return minimumYearsOfExperienceRequired; }
    public void setMinimumYearsOfExperienceRequired(int minimumYearsOfExperienceRequired) { this.minimumYearsOfExperienceRequired = minimumYearsOfExperienceRequired; }

    public boolean getMandatory() { return mandatory; }
    public void setMandatory(boolean mandatory) { this.mandatory = mandatory; }
}
