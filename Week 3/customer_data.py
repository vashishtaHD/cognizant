
customer_data = {
    'Vash1': 120,
    'Vash2': 75,
    'Vash3': 90
}

def update_purchase(customer_data, name, amount):
    customer_data[name] = amount

update_purchase(customer_data, 'Vash4', 100)

print(customer_data)
