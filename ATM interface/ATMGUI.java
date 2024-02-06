import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient funds
        } else {
            balance -= amount;
            return true; // Withdrawal successful
        }
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void withdraw(double amount) {
        if (userAccount.withdraw(amount)) {
            showMessage("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
        } else {
            showMessage("Insufficient funds. Withdrawal failed.");
        }
    }

    public void deposit(double amount) {
        userAccount.deposit(amount);
        showMessage("Deposit successful. Updated balance: $" + userAccount.getBalance());
    }

    public void checkBalance() {
        showMessage("Current balance: $" + userAccount.getBalance());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ATM Message", JOptionPane.INFORMATION_MESSAGE);
    }
}

class ATMGUI extends JFrame {
    private ATM atm;

    public ATMGUI() {
        setTitle("ATM Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();

        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        BankAccount userAccount = new BankAccount(1000); // Initial balance: $1000
        atm = new ATM(userAccount);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 20, 120, 25);
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 60, 120, 25);
        panel.add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(10, 100, 120, 25);
        panel.add(balanceButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter withdrawal amount:");
                if (amountString != null && !amountString.isEmpty()) {
                    double amount = Double.parseDouble(amountString);
                    atm.withdraw(amount);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter deposit amount:");
                if (amountString != null && !amountString.isEmpty()) {
                    double amount = Double.parseDouble(amountString);
                    atm.deposit(amount);
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.checkBalance();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMGUI();
            }
        });
    }
}
