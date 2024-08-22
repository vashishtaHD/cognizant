# 

import threading
import time

class Inventory:
    def __init__(self):
        self.items = {}

    def add_item(self, item, quantity):
        if item in self.items:
            self.items[item] += quantity
        else:
            self.items[item] = quantity

    def remove_item(self, item, quantity):
        if item in self.items:
            self.items[item] -= quantity
            if self.items[item] <= 0:
                del self.items[item]

    def check_stock_level(self, item):
        return self.items.get(item, 0)

    def save_to_file(self, filename):
        try:
            with open(filename, "w") as file:
                for item, quantity in self.items.items():
                    file.write(f"{item},{quantity}\n")
        except IOError:
            print(f"Error saving inventory to file: {filename}")

    def load_from_file(self, filename):
        try:
            with open(filename, "r") as file:
                for line in file:
                    item, quantity = line.strip().split(",")
                    self.add_item(item, int(quantity))
        except IOError:
            print(f"Error loading inventory from file: {filename}")

    def monitor_stock_levels(self, low_stock_threshold):
        while True:
            for item, quantity in self.items.items():
                if quantity <= low_stock_threshold:
                    print(f"Low stock alert: {item} has {quantity} remaining.")
            time.sleep(60)  # Check stock levels every minute

# Example usage
inventory = Inventory()
inventory.add_item("Product A", 50)
inventory.add_item("Product B", 20)
inventory.add_item("Product C", 10)

stock_monitoring_thread = threading.Thread(target=inventory.monitor_stock_levels, args=(15,))
stock_monitoring_thread.start()

inventory.save_to_file("inventory.txt")
inventory.remove_item("Product B", 15)
inventory.load_from_file("inventory.txt")