# spring_microservices

Currency Conversion Service (via API Gateway):  
http://localhost:8765/currency-conversion/from/IDR/to/UAH/quantity/50000  
(UAH, IDR, 50000 are customizable)

Eureka:  
http://localhost:8761/

Zipkin:  
http://localhost:9411/zipkin/

Internal API

Currency Exchange Service (Used by currency conversion service):  
http://localhost:8000/currency-exchange/from/USD/to/UAH

Currency Conversion Service Direct:
http://localhost:8100/currency-conversion/from/IDR/to/UAH/quantity/50000
(UAH, IDR, 50000 are customizable)