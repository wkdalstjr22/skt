package loginpeg.skt.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter @Getter
public class UserEmail {
    private String userLocalPart;
    private String userDomainPart;
    private String userDomainChoicePart;

    public UserEmail() {

    }

    public UserEmail(String userLocalPart, String userDomainPart, String userDomainChoicePart) {
        this.userLocalPart=userLocalPart;
        this.userDomainPart=userDomainPart;
        this.userDomainChoicePart=userDomainChoicePart;
    }
}
