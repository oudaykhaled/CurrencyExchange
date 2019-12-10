# Currency Exchanger - Sample App following MVVM and Clean Architecuture

Currency Exchanger is a sample application that help its users to easly convert from currency to another one.
This is project is made in MVVMi patterns and follows the Clean Architecture Book written by Robert Martin.

[![asciicast](https://github.com/oudaykhaled/CurrencyExchange/blob/master/thumb.png?raw=true)](https://drive.google.com/file/d/13-Yszv66vS_slKj5ppOv13AHUcs9A-gM/view)

# Overall Architecture
 
 1- View (Activities, Fragments, Views ...): Manage the UI according to its ViewModel
 2- ViewModel: Connect Views to one or more use cases.
 3- Model: Data transmitted accross all architectre components
 4- Usecases: hold the business rules
 5- Repository: Manage Data sources

# Tech

    1-  Kotlin
    2-  ViewModel with Live Data.
    3-  Coroutines
    4-  Dagger 2
    5-  RxJava/RxKotlin
    6-  Retrofit
    7-  JUnit
    8-  Espresso
    9-  Mochito

# Code Coverage

https://app.codacy.com/manual/oudaykhaled/CurrencyExchange/dashboard

![alt text](https://raw.githubusercontent.com/oudaykhaled/CurrencyExchange/master/Revolut-test-coverage-1.png)
![alt text](https://github.com/oudaykhaled/CurrencyExchange/blob/master/Revolut-test-coverage-2.png?raw=true)

