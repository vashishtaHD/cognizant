# 

class Employee:
    def __init__(self, name, hours_worked, hourly_rate):
        self.name = name
        self.hours_worked = hours_worked
        self.hourly_rate = hourly_rate

    def calculate_pay(self):
        regular_pay = self.hours_worked * self.hourly_rate
        overtime_hours = max(0, self.hours_worked - 40)
        overtime_pay = overtime_hours * (self.hourly_rate * 1.5)
        return regular_pay + overtime_pay

class Manager(Employee):
    def __init__(self, name, hours_worked, hourly_rate, bonus):
        super().__init__(name, hours_worked, hourly_rate)
        self.bonus = bonus

    def calculate_pay(self):
        total_pay = super().calculate_pay()
        return total_pay + self.bonus

# Example usage
employee = Employee("John Doe", 45, 25.0)
print(f"Employee's total pay: ${employee.calculate_pay():.2f}")

manager = Manager("Jane Smith", 50, 35.0, 1000.0)
print(f"Manager's total pay: ${manager.calculate_pay():.2f}")