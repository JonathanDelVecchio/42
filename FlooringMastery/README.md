
# Flooring Mastery

## Requirements

### Use N-tier development and MVC principles in structuring code to handle products, taxes, and orders appropriately. Your application should follow the MVC pattern presented in the course. 

**Model:** Data Tranfer Objects (Order.java, Product.java, State.java) & Data Access Objects (DaoImpl.java)

**View**: View.java

**Controller**: Controller.java

**Service Layer**: ServiceLayerFileImpl.java

### Use unit tests and integration tests to ensure that your application's data layers and business logic (service layer) code is covered.

**Testing Service Layer**: ServiceLayerFileImplTest.java

**Testing Model**: Data Access Objects (DaoImplTest.java)

### The application will use three separate file formats for information.

**Orders:** Stores Order Data 

**Data:** Stores Product Infomation & Taxes (with States)

**Back Up** Stores DataExport information

### All orders are stored within an Orders folder. 

- **Achieved**

### A new order file is created for each sales day. The file name is guaranteed to be unique for that day because the date is part of the file name. 

- **Did Not Achieve**

### When creating new order files the file name should be in this format: Orders_MMDDYYYY.txt.

- **Achieved**

### The order file should contain a header row. Data rows will contain the following fields and should be in this order:
    1. OrderNumber – Integer 
    2. CustomerName – String 
    3. State – String
    3. TaxRate – BigDecimal 
    4. ProductType – String 
    5. Area – BigDecimal
    6. CostPerSquareFoot – BigDecimal 
    7. LaborCostPerSquareFoot – BigDecimal 
    8. MaterialCost – BigDecimal 
    9. LaborCost – BigDecimal
    10. Tax – BigDecimal
    11. Total – BigDecimal

- **Achieved All**

### Some of the order fields are calculations:
    1. MaterialCost = (Area * CostPerSquareFoot)
    2. LaborCost = (Area * LaborCostPerSquareFoot)
    3. Tax = (MaterialCost + LaborCost) * (TaxRate/100)
    4. Tax rates are stored as whole numbers
    5. Total = (MaterialCost + LaborCost + Tax)

- **Achieved All**

### The tax information can be found in Data/Taxes.txt. This file will contain the following fields:
    1. StateAbbreviation – String 
    2. StateName – String
    3. TaxRate – BigDecimal

- **Achieved All**

### The current product information can be found in Data/Products.txt. It contains the following fields:

    1. ProductType – String
    2. CostPerSquareFoot – BigDecimal 
    3. LaborCostPerSquareFoot – BigDecimal

- **Achieved All**

### The UI should start with a menu to prompt the user for what they would like to do:

    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * <<Flooring Program>>
    * 1. Display Orders
    * 2. Add An Order
    * 3. Edit An Order
    * 4. Remove An Order
    * 5. Export All Data
    * 6. Quit
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

- **Achieved** 

### Display orders will ask the user for a date and then display the orders for that date. If no orders exist for that date, it will display an error message and return the user to the main menu.

    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * <<Flooring Program>>
    * 1. Display Orders
    * 2. Add An Order
    * 3. Edit An Order
    * 4. Remove An Order
    * 5. Export All Data
    * 6. Quit
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Please enter one of the above choices:
    1
    Please enter order date: (Format:MM/dd/yyyy)
    06/03/2023
    Could not find file with date entered.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * <<Flooring Program>>
    * 1. Display Orders
    * 2. Add An Order
    * 3. Edit An Order
    * 4. Remove An Order
    * 5. Export All Data
    * 6. Quit
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    Please enter one of the above choices:

- **Achieved**

### To add an order will query the user for each piece of order data necessary:

    1. Order Date – Must be in the future
    2. Customer Name – May not be blank and is limited to
     characters [a-z][0-9] as well as periods and comma
     characters. "Acme, Inc." is a valid name.
    3. State – Entered states must be checked against the tax
    file. If the state does not exist in the tax file, we
    cannot sell there. If the tax file is modified to include 
    the state, it should be allowed without changing the
    application code.
    4. Product Type – Show a list of available products and
    pricing information to choose from. Again, if a product is
    added to the file it should show up in the application
    without a code change.
    5. Area – The area must be a positive decimal. Minimum
    order size is 100 sq ft.

- **1. Achieved** 
- **2. [a-z][0-9] Achieved, Comma did not Achieve** 
- **3. Achieved**
- **4. Achieved**
- **5. Achieved**

---

    Please enter one of the above choices:
    2
    Please enter the order date (Format 'MM/DD/YYYY, must be in
    the future):
    05/25/2023
    Please enter the customer name:
    Jack Smith
    Please enter the state for the order:
    Texas
    1. Carpet, Cost Per sqFt: $2.25, LaborCost Per sqFt: $2.10
    2. Laminate, Cost Per sqFt: $1.75, LaborCost Per sqFt: $2.10
    3. Tile, Cost Per sqFt: $3.50, LaborCost Per sqFt: $4.15
    4. Wood, Cost Per sqFt: $5.15, LaborCost Per sqFt: $4.75
    Please select from one of the above product choices:
    2
    Please enter the area of the order (Minimum 100 sq ft)
    120
---

### Additional Requirements
    
    The remaining fields are calculated from the user entry and
    the tax/product information in the files. Show a summary of
    the order once the calculations are completed and prompt
    the user as to whether they want to place the order (Y/N).
    If yes, the data will be added to in-memory storage. If no
    simply return to the main menu.
    
    The system should generate an order number for the user
    based on the next available order # (so if there are two
    orders and the max order number is 4, the next order number
    should be 5).

---
- ***Achieved***
---
    Order Date: 05/25/2023 Customer Name: Jack Smith
    State: Texas Product Type: Laminate
    Area: 120.0
    Material Cost: $210.000
    Labor Cost: $252.000
    Tax: $924.0445
    Total: $924.0445
    Do you want to place this order?: (Yes, Y, No, N)
    Y

### Edit will ask the user for a date and order number. If the order exists for that date, it will ask the user for each piece of order data but display the existing data. If the user enters something new, it will replace that data; if the user hits Enter without entering data, it will leave the existing data in place. For example:

    If the user enters a new name, the name will replace Ada
    Lovelace. If the user hits enter without entering any data
    it will leave the data as-is.

    Only certain data is allowed to be changed:
    CustomerName
    State
    ProductType
    Area

- **Achieved**
---

    Please enter one of the above choices:
    3
    Please enter order date: (Format:MM/dd/yyyy)
    05/25/2023
    Please enter the order#:
    1
    Current Name: Jack Smith, New Name: 
    Ana Smith
    Current State: Texas, New State: 
    Kentucky
    Current Product: Laminate, New Product: 
    1. Carpet, Cost Per sqFt: $2.25, LaborCost Per sqFt: $2.10
    2. Laminate, Cost Per sqFt: $1.75, LaborCost Per sqFt: $2.10
    3. Tile, Cost Per sqFt: $3.50, LaborCost Per sqFt: $4.15
    4. Wood, Cost Per sqFt: $5.15, LaborCost Per sqFt: $4.75
    Please select from one of the above product choices:
    2
    Current Area: 120.0, New Area: 
    130

### For removing an order, the system should ask for the date and order number. If it exists, the system should display the order information and prompt the user if they are sure. If yes, it should be removed from the list.

- ***Achieved**
---
    Please enter one of the above choices:
    4
    Please enter order date: (Format:MM/dd/yyyy)
    05/25/2023
    Please enter the order#:
    1

### When the user selects this option, the system should export all existing data to a file that exists independently of the application itself.

- ***Achieved**
---

    Please enter one of the above choices:
    5
    Export Successful!

### This step is optional. Complete this step only after all other steps work as required.

### Selecting this option should save all active orders files to a file called DataExport.txt within a Backup folder. Exporting the order data multiple times should overwrite the data within the file with the latest active order information. However, unlike other order files, an order's line item in this DataExport file should also include the date in MM-DD-YYYY format, and the file's header should reflect this addition.

- **Achieved**