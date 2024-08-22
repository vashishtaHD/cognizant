# inventory_management.py

# Lists
product_list = ["Product A", "Product B", "Product C"]

def add_product(product_name):
    product_list.append(product_name)

def remove_product(product_name):
    if product_name in product_list:
        product_list.remove(product_name)

def update_product(index, new_name):
    product_list[index] = new_name

# Dictionaries
product_details = {
    "Product A": {"quantity": 10, "price": 9.99},
    "Product B": {"quantity": 20, "price": 14.99},
    "Product C": {"quantity": 5, "price": 7.50}
}

def add_product_details(product_name, quantity, price):
    product_details[product_name] = {"quantity": quantity, "price": price}

def update_product_details(product_name, quantity, price):
    if product_name in product_details:
        product_details[product_name]["quantity"] = quantity
        product_details[product_name]["price"] = price

def delete_product_details(product_name):
    if product_name in product_details:
        del product_details[product_name]

# Tuples
product_catalog = [
    ("Product A", 9.99),
    ("Product B", 14.99),
    ("Product C", 7.50)
]

# Sets
product_categories = {"Electronics", "Clothing", "Home Decor"}

def add_category(category):
    product_categories.add(category)

def remove_category(category):
    if category in product_categories:
        product_categories.remove(category)

# Combining Collections
def sort_products_by_price():
    return sorted(product_details.items(), key=lambda x: x[1]["price"])

def find_products_in_price_range(min_price, max_price):
    return set(product_name for product_name, details in product_details.items()
               if min_price <= details["price"] <= max_price)

# Example usage
print("Product List:", product_list)
add_product("Product D")
print("Updated Product List:", product_list)

print("Product Details:", product_details)
add_product_details("Product D", 15, 12.99)
print("Updated Product Details:", product_details)

print("Product Catalog:", product_catalog)

print("Product Categories:", product_categories)
add_category("Sports")
print("Updated Product Categories:", product_categories)

print("Products sorted by price:")
for product, details in sort_products_by_price():
    print(f"{product}: {details['price']}")

print("Products in price range (10.00 - 15.00):")
for product in find_products_in_price_range(10.00, 15.00):
    print(product)