package ExpTransactions;

public class DebitAccount {
    private int id;
    private String accountType;
    private String accountNo;
    private String bankName;
    private String ifsc;
    private long balance;

    public DebitAccount(int id, String accountType, String accountNo, String bankName, String ifsc, long balance) {
        this.id = id;
        this.accountType = accountType;
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.balance = balance;
    }

    public DebitAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
