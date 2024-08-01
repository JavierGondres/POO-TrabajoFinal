package backend.enums;

public enum Specialty {
    INTERNAL_MEDICINE("Internal Medicine"),
    PEDIATRICS("Pediatrics"),
    GYNECOLOGY("Gynecology"),
    GENERAL_SURGERY("General Surgery"),
    CARDIOLOGY("Cardiology"),
    NEUROLOGY("Neurology"),
    PSYCHIATRY("Psychiatry"),
    DERMATOLOGY("Dermatology"),
    ENDOCRINOLOGY("Endocrinology"),
    OPHTHALMOLOGY("Ophthalmology"),
    ORTHOPEDIC("Orthopedic"),
    OTORHINOLARYNGOLOGY("Otorhinolaryngology"),
    ONCOLOGY("Oncology"),
    EMERGENCY_MEDICINE("Emergency Medicine"),
    RADIOLOGY("Radiology"),
    ANESTHESIOLOGY("Anesthesiology"),
    SPORTS_MEDICINE("Sports Medicine"),
    INFECTIOUS_DISEASES("Infectious Diseases"),
    UROLOGY("Urology"),
    NEPHROLOGY("Nephrology"),
    PULMONOLOGY("Pulmonology"),
    RHEUMATOLOGY("Rheumatology"),
    HEMATOLOGY("Hematology"),
    FAMILY_MEDICINE("Family Medicine"),
    GERIATRICS("Geriatrics"),
    PATHOLOGY("Pathology");

    private final String displayName;

    Specialty(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}