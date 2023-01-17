package ExpTransactions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionMain {

    public static DebitAccount getDebitAccountById(long id, List<DebitAccount> debitAccounts){
        DebitAccount dbAccount = null;
        for(DebitAccount d: debitAccounts){
            if(d.getId() == id){
                dbAccount = d;
            }
        }
        return dbAccount;
    }

    public static Beneficiary getBeneficiaryById(long id, List<Beneficiary> beneficiaries){
        Beneficiary bfAccount = null;
        for(Beneficiary df: beneficiaries){
            if(df.getId() == id){
                bfAccount = df;
            }
        }
        return bfAccount;
    }

    public static void main(String[] args) throws SQLException {
        int beneficiaryId = 1;
        int paymentsId = 1;
        int debitId = 1;

        List<Payments> paymentsList = new ArrayList<>();
        List<Beneficiary> beneficiaries = new ArrayList<>();
        List<DebitAccount> debitAccounts = new ArrayList<>();

        // db connection initiated.
        //Connection c = DBConnector.dbConnection();
        //c.close();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.Add Beneficiary\n2.Add Debit Account\n3.Fetch Balance\n4.Make Payment\n5.List Payments\n6.Multiple Beneficiary Payments\n7.List Beneficiaries\n8.List Debit Accounts\n9.Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("\n[INFO]: Add Beneficiary Details.");
                    System.out.println("Enter Beneficiary Details:-");
                    System.out.println("Name :");
                    String name = sc.next();
                    System.out.println("Phone :");
                    String phone = sc.next();
                    System.out.println("Email :");
                    String email = sc.next();
                    System.out.println("Address :");
                    String address = sc.next();
                    System.out.println("Account number :");
                    String accNumber = sc.next();
                    System.out.println("IFSC :");
                    String ifsc = sc.next();

                    beneficiaries.add(new Beneficiary(beneficiaryId, name, phone, email, address, accNumber,  ifsc));
                    beneficiaryId+=1;
                    break;
                case 2:
                    System.out.println("\n[INFO] Add Debit Account Details");
                    System.out.println("Enter Debit Account Details:-");
                    System.out.println("Account Type :");
                    String accountType = sc.next();
                    System.out.println("Account No :");
                    String accountNo = sc.next();
                    System.out.println("Bank Name :");
                    String bankName = sc.next();
                    System.out.println("ifsc :");
                    String debitIfsc = sc.next();
                    System.out.println("Balance :");
                    long balance = sc.nextLong();

                    debitAccounts.add(new DebitAccount(debitId, accountType, accountNo, bankName, debitIfsc, balance));
                    debitId+=1;
                    break;
                case 3:
                    System.out.println("[INFO]: Balance Information");
                    System.out.println("Enter Debit Account Id :");
                    int debId = sc.nextInt();
                    DebitAccount debitFetched = TransactionMain.getDebitAccountById(debId, debitAccounts);
                    if(debitFetched == null){
                        System.out.println("No records found. Please check id!");
                    }else {
                        System.out.println("Account Type :" + debitFetched.getAccountType());
                        System.out.println("Account No :" + debitFetched.getAccountNo());
                        System.out.println("Bank Name :" + debitFetched.getBankName());
                        System.out.println("Ifsc :" + debitFetched.getIfsc());
                        System.out.println("Balance :" + debitFetched.getBalance());
                    }
                    break;
                case 4:
                    System.out.println("Enter Receipt Bank Details:-");
                    System.out.println("Beneficiary id :");
                    int benId = sc.nextInt();

                    Beneficiary beneficiary = TransactionMain.getBeneficiaryById(benId, beneficiaries);
                    if(beneficiary == null){
                        System.out.println("Beneficiary Details not found! please check id or Try again!");
                        break;
                    }else {

                        System.out.println("Debit Account Id :");
                        int debAccId = sc.nextInt();

                        DebitAccount debitAccount = TransactionMain.getDebitAccountById(debAccId, debitAccounts);
                        if (debitAccount == null) {
                            System.out.println("Debit Account not found! please check id or Try again!");
                            break;
                        } else {

                            System.out.println("Amount :");
                            long amount = sc.nextLong();

                            if (amount > debitAccount.getBalance()) {
                                System.out.println("Not Enough Fund to Make Transaction!. Please choose another account with sufficient fund.");
                                break;
                            }else {
                                System.out.println("Description :");
                                String description = sc.next();
                                long newAmt = debitAccount.getBalance() - amount;
                                debitAccount.setBalance(newAmt);

                                paymentsList.add(new Payments(paymentsId, beneficiary.getId(), debitAccount.getId(), amount, description));
                                paymentsId+=1;
                                System.out.println("Payment Done.");
                            }
                        }
                    }

                    break;
                case 5:
                    System.out.println("[INFO] Payments History.");
                    for(Payments payHistory:paymentsList){
                        System.out.println("Id :" + payHistory.getId());
                        System.out.println("Beneficiary Id :" + payHistory.getBeneficiaryId());
                        System.out.println("Debit Account Id :" + payHistory.getDebitAccountId());
                        System.out.println("Transaction Amount :" + payHistory.getTransactionAmount());
                        System.out.println("Description :" + payHistory.getDescription());
                        System.out.println("--------------------------------------------");
                    }
                    break;
                case 6:
                    System.out.println("[INFO] Multiple Beneficiary Payments.");
                    System.out.println("Enter No. of Beneficiaries you have to pay :");
                    int noOfBeneficiaries = sc.nextInt();

                    System.out.println("Enter total No. of Amount you have to pay :");
                    long totalPayableAmount = sc.nextLong();

                    System.out.println("Debit Account Id :");
                    long debitAccId = sc.nextLong();

                    DebitAccount debitAccount = TransactionMain.getDebitAccountById(debitAccId, debitAccounts);

                    if(totalPayableAmount > debitAccount.getBalance()) {
                        System.out.println("Not Enough Fund to Make Transaction!. Please choose another account with sufficient fund.");
                        break;

                    }else {
                        for (int i = 0; i < noOfBeneficiaries; i++) {
                            System.out.println("Enter Beneficiary " + i + 1 + " id :");
                            int benfId = sc.nextInt();

                            Beneficiary beneficiary1 = TransactionMain.getBeneficiaryById(benfId, beneficiaries);
                            if(beneficiary1!= null){
                                System.out.println("Enter Amount for " + i + 1 + " Beneficiary with id " + beneficiary1.getId());
                                long amt = sc.nextInt();

                                System.out.println("Description :");
                                String description = sc.next();

                                long newAmt = debitAccount.getBalance() - amt;
                                debitAccount.setBalance(newAmt);

                                paymentsList.add(new Payments(paymentsId, beneficiary1.getId(), debitAccount.getId(), amt, description));
                                paymentsId+=1;

                                System.out.println("Payment Done.");

                            }else {
                                System.out.println("Beneficiary Details not found! please check id or Try again!");
                                break;
                            }



                        }
                    }
                    break;
                case 7:
                    System.out.println("[INFO] Beneficiaries:-");
                    if(beneficiaries.size() == 0){
                        System.out.println("No Records Found!");
                    }
                    for(Beneficiary b: beneficiaries){
                        System.out.println("Id :"+b.getId());
                        System.out.println("Name :"+b.getName());
                        System.out.println("Phone :"+b.getPhone());
                        System.out.println("Email :"+b.geteMail());
                        System.out.println("Address :"+b.getAddress());
                        System.out.println("Account No :"+b.getAccountNumber());
                        System.out.println("Ifsc :"+b.getIfsc());
                        System.out.println("-----------------------");
                    }
                    break;
                case 8:
                    System.out.println("[INFO] Debit Accounts:-");
                    if(debitAccounts.size() == 0){
                        System.out.println("No records Found!");
                    }
                    for(DebitAccount d: debitAccounts){
                        System.out.println("Id :" + d.getId());
                        System.out.println("Account type :" + d.getAccountType());
                        System.out.println("Account Number :" + d.getAccountNo());
                        System.out.println("Bank Name :" + d.getBankName());
                        System.out.println("Ifsc :" + d.getIfsc());
                        System.out.println("Balance :" + d.getBalance());
                        System.out.println("--------------------------");
                    }
                    break;
                case 9:
                    System.out.println("Exit! Bye");
                    System.exit(0);
                default:
                    System.out.println("please select valid options");
                    break;
            }
        }
    }
}
