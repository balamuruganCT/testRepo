package ExpTransactions;

public class Payments {
    private int id;
    private int beneficiaryId;
    private int debitAccountId;
    private long transactionAmount;
    private String description;

    public Payments(int id, int beneficiaryId, int debitAccountId, long transactionAmount, String description) {
        this.id = id;
        this.beneficiaryId = beneficiaryId;
        this.debitAccountId = debitAccountId;
        this.transactionAmount = transactionAmount;
        this.description = description;
    }

    public Payments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(int debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
