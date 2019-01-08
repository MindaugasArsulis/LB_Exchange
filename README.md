# Lietuvos Bankas Exchange rates
Assignment. Program extracts xml files from www.lb.lt and shows currency rates in command line.

## How it works
All class files have their own public_static_void_main method and they run command lines. Scanners have been used for data entering.

### ReadAllCurrency.java
This class shows all the currency rates, depending on the date. Date is entered in a command line.
Date format must be, for ex.: 2019-01-01. There could be no output from entered day, because currency rates on LB website is not entered on weekends.

### ReadSpecificCurrency.java
This class shows specific currency rates, depending on two dates(from and till). Currency name and dates are entered in a command line.
Currency format must be, for ex.: CZK. Date format must be, for ex.: 2019-01-01

### ReadCurrencyChange.java
This class shows specific currencies difference between currency rates, which were on entered dates. Currency name and dates are entered in a command line.
Currency format must be, for ex.: CZK. Date format must be, for ex.: 2019-01-01

### Finishing notes
Program works from date 2014-09-30.
No measures have been taken to catch badly entered currency name or date.

