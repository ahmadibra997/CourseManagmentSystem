package coresy;

import java.util.ArrayList;
import java.util.List;

public class AdvisorTemplate extends UserTemplate {
    List<SpecialRequest> specialRequests = new ArrayList<SpecialRequest>();

    public AdvisorTemplate(int userId, String username, String password, String firstName, String lastName, List<SpecialRequest> specialRequests) {
        super(userId, username, password, firstName, lastName, "Advisor");
        this.specialRequests = specialRequests;

    }

    public AdvisorTemplate(int userId, String username, String password, String firstName, String lastName) {
        super(userId, username, password, firstName, lastName, "Advisor");

    }

    public AdvisorTemplate(int userId, String username, String firstName, String lastName) {
        super(userId, username, firstName, lastName, "Advisor");
    }

    public List<SpecialRequest> getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(List<SpecialRequest> specialRequests) {
        this.specialRequests = specialRequests;
    }

    public void addSpecialRequest(SpecialRequest specialRequest) {
        this.specialRequests.add(specialRequest);
    }

    public void removeSpecialRequest(SpecialRequest specialRequest) {
        this.specialRequests.remove(specialRequest);
    }
}
