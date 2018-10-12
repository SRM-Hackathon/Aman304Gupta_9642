package blockcontract.blockpal;


public class borrower {

    String s1,s2,s3,s4,s5,s6,id;

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }

    public String getS4() {
        return s4;
    }

    public String getS5() {
        return s5;
    }

    public String getS6() {
        return s6;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public borrower(String s1, String s2, String s3, String s4, String s5, String s6, String id) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.id=id;
    }
}
