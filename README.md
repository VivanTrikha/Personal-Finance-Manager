# Personal Finance Manager
## Overview
The Personal Finance Manager is a simple desktop application built using Java and JavaFX that helps users manage their personal finances. It allows users to track their income, expenses, set a budget and view transaction history.

## Features
- User Registration & Login: Users can register an account and log in
- Add Income & Expenses: Keeping track of your earnings and expenses by adding transactions
- Set Monthly Budget: Users can set a monthly budget and monitor their spending to ensure they stay within their limits
- View Transaction History: Easily view all past transactions categorized by date, amount and category
- Budget Overview: See the total amount spent, remaining budget and if you are over budget
## Technologies Used
- Java 
- JavaFX for user interface
## Limitations and Areas of Improvement
- Security: The current implementation uses basic string-based authentication and the passwords are not encrypted.
- Data Persistence: Currently the application stores user data and transactions in memory, meaning all data is lost once the application is closed. Adding a database (e.g SQLite or MySQL) or file-based storage (e.g JSON) would improve this.
- Limited UI/UX: The current user interface is functional but very basic. Enhancements can include improved styling, more intuitive navigation etc.
- Error Handling: There is limited error handling. Currently the application may crash if incorrect inputs are provided and more robust exception handling should be added. 
