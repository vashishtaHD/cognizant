def apply_discount(order_amount):
    if order_amount > 100:
        discount = order_amount * 0.1
        return order_amount - discount
    else:
        return order_amount
order_amount = 150
final_price = apply_discount(order_amount)
print(f"Original order amount: ${order_amount:.2f}")
print(f"Final price after discount: ${final_price:.2f}")