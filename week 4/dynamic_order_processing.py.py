# dynamic_order_processing.py

from abc import ABC, abstractmethod

class DiscountStrategy(ABC):
    @abstractmethod
    def apply_discount(self, order_amount):
        pass

class RegularDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.9  # 10% discount

class PremiumDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.8  # 20% discount

class VIPDiscount(DiscountStrategy):
    def apply_discount(self, order_amount):
        return order_amount * 0.7  # 30% discount

class Order:
    def __init__(self, customer_type, order_amount):
        self.customer_type = customer_type
        self.order_amount = order_amount

    def final_price(self):
        if self.customer_type == "regular":
            discount_strategy = RegularDiscount()
        elif self.customer_type == "premium":
            discount_strategy = PremiumDiscount()
        elif self.customer_type == "vip":
            discount_strategy = VIPDiscount()
        else:
            return self.order_amount

        return discount_strategy.apply_discount(self.order_amount)

# Example usage
regular_order = Order("regular", 100.0)
print(f"Regular customer final price: ${regular_order.final_price():.2f}")

premium_order = Order("premium", 100.0)
print(f"Premium customer final price: ${premium_order.final_price():.2f}")

vip_order = Order("vip", 100.0)
print(f"VIP customer final price: ${vip_order.final_price():.2f}")  