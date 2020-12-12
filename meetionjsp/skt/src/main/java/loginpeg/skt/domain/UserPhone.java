package loginpeg.skt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@ToString
public class UserPhone {
    private String userCarrierNum;
    private String userAssignmentNum;
    private String userRanNum;

    public UserPhone() {
    }

    public UserPhone(String userCarrierNum, String userAssignmentNum, String userRanNum) {
        this.userCarrierNum=userCarrierNum;
        this.userAssignmentNum=userAssignmentNum;
        this.userRanNum=userRanNum;
    }
}
