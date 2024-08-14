def calculate_pay(hours, rate):
    return hours * rate
hours_worked = 40
hourly_rate = 15
total_pay = calculate_pay(hours_worked, hourly_rate)
print(f"Total pay: ${total_pay:.2f}")
