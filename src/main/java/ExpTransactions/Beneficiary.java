package ExpTransactions;

public class Beneficiary {
    private int id;
    private String name;
    private String phone;
    private String eMail;
    private String address;
    private String accountNumber;
    private String ifsc;

    public Beneficiary(int id, String name, String phone, String eMail, String address, String account, String ifsc) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.eMail = eMail;
        this.address = address;
        this.accountNumber = account;
        this.ifsc = ifsc;
    }

    public Beneficiary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }
}
