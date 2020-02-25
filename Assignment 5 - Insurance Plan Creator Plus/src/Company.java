import java.util.ArrayList;
import java.util.HashMap;

/*
    A company can be owned either directly by a person, or indirectly
    through another company. The company has an owner and a value,
    similar to an insurable asset (home, car etc.)
 */
class Company extends Insurable {
    private String companyName;

    static final String inputTag = "COMPANY";

    Company(HashMap<String, Tag> tags) {
        super(tags);
        companyName = tags.get("COMPANY_NAME").getValue();

    }

    public String getCompanyName() {
        return companyName;
    }


    public static String getInputTag() {
        return inputTag;
    }
}
