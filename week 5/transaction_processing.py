# transaction_processing.py

import datetime
import re

class InvalidTransactionError(Exception):
    pass

def validate_transaction(transaction):
    if not isinstance(transaction, dict):
        raise InvalidTransactionError("Transaction must be a dictionary.")
    
    required_fields = ['account_number', 'amount', 'type']
    for field in required_fields:
        if field not in transaction:
            raise InvalidTransactionError(f"Missing required field: {field}")
    
    if not re.match(r'^\d{10}$', transaction['account_number']):
        raise InvalidTransactionError("Invalid account number format. Must be 10 digits.")
    
    try:
        amount = float(transaction['amount'])
        if amount <= 0:
            raise InvalidTransactionError("Amount must be a positive number.")
    except ValueError:
        raise InvalidTransactionError("Invalid amount. Must be a number.")
    
    if transaction['type'] not in ['deposit', 'withdrawal']:
        raise InvalidTransactionError("Invalid transaction type. Must be 'deposit' or 'withdrawal'.")

def process_transaction(transaction):
    try:
        validate_transaction(transaction)
        # Process the transaction (simplified for this example)
        print(f"Processing {transaction['type']} of ${transaction['amount']} for account {transaction['account_number']}")
        # In a real application, you would update account balances, etc.
    except InvalidTransactionError as e:
        print(f"Error: {e}")
        log_error(str(e))
    except Exception as e:
        print(f"Unexpected error: {e}")
        log_error(f"Unexpected error: {e}")

def log_error(error_message):
    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    log_entry = f"{timestamp} - {error_message}\n"
    try:
        with open("transaction_errors.log", "a") as log_file:
            log_file.write(log_entry)
    except IOError:
        print("Warning: Unable to write to error log file.")

def get_transaction_input():
    transaction = {}
    transaction['account_number'] = input("Enter account number (10 digits): ")
    transaction['amount'] = input("Enter amount: ")
    transaction['type'] = input("Enter transaction type (deposit/withdrawal): ")
    return transaction

def main():
    while True:
        print("\nTransaction Processing System")
        print("1. Process a transaction")
        print("2. Exit")
        
        choice = input("Enter your choice (1-2): ")
        
        if choice == '1':
            transaction = get_transaction_input()
            process_transaction(transaction)
        elif choice == '2':
            print("Exiting the program...")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()