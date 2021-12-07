package DTOs;

public class Doctor {
    private Integer doc_id;
    private String doc_license;
    private String doc_first_name;
    private String doc_last_name;
    private String doc_proffession;
    private String doc_area;

    public Integer getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Integer doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_license() {
        return doc_license;
    }

    public void setDoc_license(String doc_license) {
        this.doc_license = doc_license;
    }

    public String getDoc_first_name() {
        return doc_first_name;
    }

    public void setDoc_first_name(String doc_first_name) {
        this.doc_first_name = doc_first_name;
    }

    public String getDoc_last_name() {
        return doc_last_name;
    }

    public void setDoc_last_name(String doc_last_name) {
        this.doc_last_name = doc_last_name;
    }

    public String getDoc_proffession() {
        return doc_proffession;
    }

    public void setDoc_proffession(String doc_proffession) {
        this.doc_proffession = doc_proffession;
    }

    public String getDoc_area() {
        return doc_area;
    }

    public void setDoc_area(String doc_area) {
        this.doc_area = doc_area;
    }
}