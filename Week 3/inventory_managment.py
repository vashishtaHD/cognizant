def check_inventory(inventory):
    for item, quantity in inventory:
        if quantity <= 0:
            print(f"Item '{item}' is out of stock.")

inventory = [('item1', 10), ('item2', 0), ('item3', 5)]
check_inventory(inventory)