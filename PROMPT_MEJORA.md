# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `docker/Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `src/main/java/com/ecommerce/config/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/main/java/com/ecommerce/security/JwtTokenUtil.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/ecommerce/dto/ProductDto.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/dto/OrderDto.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/dto/CustomerDto.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/controller/ProductController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/controller/OrderController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/ecommerce/model/Product.java` — `Order.getProducts`: Se invoca `getProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/model/Order.java` — `Product.getOrders`: Se invoca `getOrders` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/model/Customer.java` — `Order.setCustomer`: Se invoca `setCustomer` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/model/Customer.java` — `Product.getOrders`: Se invoca `getOrders` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.getUsernameFromToken`: Se invoca `getUsernameFromToken` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.getRoleFromToken`: Se invoca `getRoleFromToken` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.getAllProducts`: Se invoca `getAllProducts` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.getProductById`: Se invoca `getProductById` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.getProductsByCategory`: Se invoca `getProductsByCategory` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.searchProducts`: Se invoca `searchProducts` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getAllOrders`: Se invoca `getAllOrders` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getOrderById`: Se invoca `getOrderById` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.updateOrder`: Se invoca `updateOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.deleteOrder`: Se invoca `deleteOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getOrdersByCustomerId`: Se invoca `getOrdersByCustomerId` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getOrdersByStatus`: Se invoca `getOrdersByStatus` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.createCustomer`: Se invoca `createCustomer` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.getCustomerById`: Se invoca `getCustomerById` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.getAllCustomers`: Se invoca `getAllCustomers` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.updateCustomer`: Se invoca `updateCustomer` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.deleteCustomer`: Se invoca `deleteCustomer` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.searchCustomersByEmail`: Se invoca `searchCustomersByEmail` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.save`: Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.findById`: Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setName`: Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setDescription`: Se invoca `setDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setPrice`: Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setStock`: Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setCategory`: Se invoca `setCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.existsById`: Se invoca `existsById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.deleteById`: Se invoca `deleteById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.findAll`: Se invoca `findAll` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getId`: Se invoca `getId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getName`: Se invoca `getName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getDescription`: Se invoca `getDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getPrice`: Se invoca `getPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getStock`: Se invoca `getStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getCategory`: Se invoca `getCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setId`: Se invoca `setId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setCustomerId`: Se invoca `setCustomerId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setProducts`: Se invoca `setProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setTotalQuantity`: Se invoca `setTotalQuantity` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setTotalAmount`: Se invoca `setTotalAmount` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setOrderDate`: Se invoca `setOrderDate` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setStatus`: Se invoca `setStatus` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `OrderRepository.save`: Se invoca `save` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `OrderRepository.findById`: Se invoca `findById` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getStatus`: Se invoca `getStatus` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getProducts`: Se invoca `getProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `OrderRepository.findAll`: Se invoca `findAll` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `ProductRepository.findById`: Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Product.getStock`: Se invoca `getStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Product.getName`: Se invoca `getName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Product.setStock`: Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `ProductRepository.save`: Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getId`: Se invoca `getId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getCustomerId`: Se invoca `getCustomerId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getTotalQuantity`: Se invoca `getTotalQuantity` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getTotalAmount`: Se invoca `getTotalAmount` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getOrderDate`: Se invoca `getOrderDate` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.findAll`: Se invoca `findAll` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.findById`: Se invoca `findById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setPassword`: Se invoca `setPassword` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getPassword`: Se invoca `getPassword` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getRole`: Se invoca `getRole` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setRole`: Se invoca `setRole` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.save`: Se invoca `save` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setName`: Se invoca `setName` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getEmail`: Se invoca `getEmail` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setEmail`: Se invoca `setEmail` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setAddress`: Se invoca `setAddress` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setPhone`: Se invoca `setPhone` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.existsById`: Se invoca `existsById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.deleteById`: Se invoca `deleteById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.count`: Se invoca `count` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getId`: Se invoca `getId` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getName`: Se invoca `getName` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getAddress`: Se invoca `getAddress` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getPhone`: Se invoca `getPhone` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getOrders`: Se invoca `getOrders` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setId`: Se invoca `setId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setName`: Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setDescription`: Se invoca `setDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setPrice`: Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setStock`: Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setCategory`: Se invoca `setCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `ProductRepository.save`: Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `ProductRepository.findAll`: Se invoca `findAll` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `ProductRepository.findById`: Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Customer.setId`: Se invoca `setId` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Customer.setName`: Se invoca `setName` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Customer.setEmail`: Se invoca `setEmail` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setId`: Se invoca `setId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setName`: Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setPrice`: Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setStock`: Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setId`: Se invoca `setId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setCustomer`: Se invoca `setCustomer` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setProducts`: Se invoca `setProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setTotalQuantity`: Se invoca `setTotalQuantity` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setTotalAmount`: Se invoca `setTotalAmount` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setOrderDate`: Se invoca `setOrderDate` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setStatus`: Se invoca `setStatus` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `CustomerRepository.findById`: Se invoca `findById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `ProductRepository.findById`: Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderRepository.save`: Se invoca `save` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderRepository.findAll`: Se invoca `findAll` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderRepository.findById`: Se invoca `findById` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderService.deleteOrder`: Se invoca `deleteOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `ProductRepository.deleteAll`: Se invoca `deleteAll` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setName`: Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setDescription`: Se invoca `setDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setPrice`: Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setStock`: Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setCategory`: Se invoca `setCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `ProductRepository.save`: Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.getId`: Se invoca `getId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `ProductRepository.existsById`: Se invoca `existsById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Desarrollar una API REST empresarial con Spring Boot 3 para la gestión de productos y pedidos en un e-commerce. El sistema debe incluir: arquitectura en capas con controladores REST, servicios de negocio y repositorios JPA; modelado de datos con entidades Product, Order y Customer usando relaciones OneToMany y ManyToMany con Hibernate; autenticación y autorización basada en JWT con Spring Security, incluyendo roles de ADMIN y USER con acceso diferenciado por endpoint; documentación automática con OpenAPI 3.0 y Swagger UI accesible en /api-docs; manejo centralizado de errores con @ControllerAdvice y respuestas estandarizadas en formato JSON; validación de entradas con Bean Validation usando @Valid, @NotNull y @Size; paginación y ordenamiento de resultados en los endpoints de listado usando Pageable; pruebas unitarias con JUnit 5 y Mockito cubriendo la capa de servicio al menos al 80%; pruebas de integración con @SpringBootTest verificando los flujos principales; y containerización con Docker usando Dockerfile multi-stage optimizado para producción. El desarrollador debe implementar el módulo completo desde la capa de persistencia hasta los controladores REST, aplicando principios SOLID y clean code en cada capa.

### Reto
- Tema: Arquitectura Empresarial con Spring Boot
- Seniority: junior-l3
- Tipo: practical
- Título: Implementación de una API REST para gestión de productos y pedidos
- Tiempo estimado: 40 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Modelado de datos y relaciones — objetivo: Definir y modelar las entidades Product, Order y Customer con sus relaciones OneToMany y ManyToMany. — entregable (NO resolver): Modelo de datos con entidades y relaciones definidas.
- Fase 2: Implementación de controladores REST — objetivo: Crear controladores REST para las operaciones CRUD de productos y pedidos. — entregable (NO resolver): Controladores REST implementados y funcionando.
- Fase 3: Autenticación y autorización con JWT — objetivo: Implementar autenticación y autorización basada en JWT con Spring Security. — entregable (NO resolver): Autenticación y autorización implementadas y funcionando.
- Fase 4: Documentación automática con OpenAPI y Swagger UI — objetivo: Documentar automáticamente la API con OpenAPI 3.0 y Swagger UI. — entregable (NO resolver): Documentación automática de la API accesible en /api-docs.
- Fase 5: Manejo centralizado de errores — objetivo: Implementar manejo centralizado de errores con @ControllerAdvice. — entregable (NO resolver): Manejo centralizado de errores implementado y funcionando.
- Fase 6: Validación de entradas — objetivo: Implementar validación de entradas con Bean Validation. — entregable (NO resolver): Validación de entradas implementada y funcionando.
- Fase 7: Paginación y ordenamiento — objetivo: Implementar paginación y ordenamiento en los endpoints de listado. — entregable (NO resolver): Paginación y ordenamiento implementados y funcionando.
- Fase 8: Pruebas unitarias y de integración — objetivo: Realizar pruebas unitarias y de integración para la capa de servicio. — entregable (NO resolver): Pruebas unitarias y de integración implementadas y funcionando.
- Fase 9: Containerización con Docker — objetivo: Containerizar la aplicación con Docker. — entregable (NO resolver): Aplicación containerizada y funcionando en Docker.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.ecommerce</groupId>
    <artifactId>ecommerce-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ecommerce-api</name>
    <description>API REST para gestión de productos y pedidos</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi.version>2.6.0</springdoc-openapi.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.5</version>
            <scope>runtime</scope>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>

        <!-- Bean Validation -->
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>8.0.1.Final</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.3.230</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

// === ARCHIVO: src/main/java/com/ecommerce/EcommerceApplication.java ===
package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración de Spring
spring.application.name=ecommerce-api

# Configuración de la base de datos H2
spring.datasource.url=jdbc:h2:mem:ecommerce_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración de Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración de H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuración de JWT
jwt.secret=MiClaveSecretaParaJWT1234567890
jwt.expiration=86400000

# Configuración de OpenAPI/Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.operationsSorter=alpha
springdoc.swagger-ui.doc-expansion=none

// === ARCHIVO: src/main/java/com/ecommerce/dto/ProductDto.java ===
package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "DTO para la entidad Producto")
public class ProductDto {
    @Schema(description = "Identificador único del producto", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Laptop Gamer")
    private String name;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Descripción detallada del producto", example = "Laptop con procesador i7 y 16GB de RAM")
    private String description;

    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    @Schema(description = "Precio del producto", example = "999.99")
    private BigDecimal price;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Min(value = 0, message = "La cantidad en stock no puede ser negativa")
    @Schema(description = "Cantidad disponible en stock", example = "50")
    private Integer stock;

    @NotBlank(message = "La categoría del producto es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String category;

    @Schema(description = "Lista de IDs de pedidos en los que aparece el producto")
    private List<Long> orderIds;
}

// === ARCHIVO: src/main/java/com/ecommerce/dto/OrderDto.java ===
package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "DTO para la entidad Pedido")
public class OrderDto {
    @Schema(description = "Identificador único del pedido", example = "1")
    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Schema(description = "ID del cliente que realizó el pedido", example = "1")
    private Long customerId;

    @NotEmpty(message = "El pedido debe contener al menos un producto")
    @Schema(description = "Lista de IDs de productos incluidos en el pedido")
    private List<Long> productIds;

    @NotNull(message = "La cantidad total de productos es obligatoria")
    @Min(value = 1, message = "La cantidad total debe ser al menos 1")
    @Schema(description = "Cantidad total de productos en el pedido", example = "2")
    private Integer totalQuantity;

    @NotNull(message = "El monto total del pedido es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto total debe ser mayor que 0")
    @Schema(description = "Monto total del pedido", example = "1999.98")
    private BigDecimal totalAmount;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2024-05-20T10:30:00")
    private LocalDateTime orderDate;

    @NotBlank(message = "El estado del pedido es obligatorio")
    @Size(max = 20, message = "El estado no puede exceder 20 caracteres")
    @Schema(description = "Estado actual del pedido", example = "PENDING")
    private String status;
}

// === ARCHIVO: src/main/java/com/ecommerce/dto/CustomerDto.java ===
package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "DTO para la entidad Cliente")
public class CustomerDto {
    @Schema(description = "Identificador único del cliente", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
    private String name;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    @Schema(description = "Correo electrónico del cliente", example = "juan.perez@example.com")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Schema(description = "Contraseña del cliente", example = "securePassword123")
    private String password;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    @Schema(description = "Dirección de envío del cliente", example = "Calle Principal 123, Ciudad")
    private String address;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    @Schema(description = "Número de teléfono del cliente", example = "+1234567890")
    private String phone;

    @Schema(description = "Lista de IDs de pedidos realizados por el cliente")
    private List<Long> orderIds;

    @NotBlank(message = "El rol del cliente es obligatorio")
    @Size(max = 20, message = "El rol no puede exceder 20 caracteres")
    @Schema(description = "Rol del cliente en el sistema", example = "USER")
    private String role;
}

// === ARCHIVO: src/main/java/com/ecommerce/model/Product.java ===
package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 100)
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "product_orders",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.getProducts().add(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.getProducts().remove(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setProduct(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setProduct(null);
    }

    public void reduceStock(Integer quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
        } else {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + this.name);
        }
    }

    public void increaseStock(Integer quantity) {
        this.stock += quantity;
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/model/Order.java ===
package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(length = 50, nullable = false)
    @Builder.Default
    private String status = "PENDING";

    @PrePersist
    protected void onCreate() {
        if (orderDate == null) {
            orderDate = LocalDateTime.now();
        }
        if (totalQuantity == null) {
            totalQuantity = 0;
        }
        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getOrders().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getOrders().remove(this);
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        recalculateTotals();
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
        recalculateTotals();
    }

    public void recalculateTotals() {
        this.totalQuantity = items.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
        this.totalAmount = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirm() {
        this.status = "CONFIRMED";
    }

    public void cancel() {
        this.status = "CANCELLED";
    }

    public void complete() {
        this.status = "COMPLETED";
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/model/Customer.java ===
package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 500)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 20, nullable = false)
    @Builder.Default
    private String role = "USER";

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "customer_products",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Builder.Default
    private List<Product> favoriteProducts = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCustomer(null);
    }

    public void addFavoriteProduct(Product product) {
        favoriteProducts.add(product);
        product.getOrders().add(null);
    }

    public void removeFavoriteProduct(Product product) {
        favoriteProducts.remove(product);
    }

    public boolean hasRole(String role) {
        return this.role.equalsIgnoreCase(role);
    }

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/repository/ProductRepository.java ===
package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);

    Page<Product> findByCategory(String category, Pageable pageable);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    List<Product> findByStockLessThan(Integer stock);

    Page<Product> findByStockLessThan(Integer stock, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findProductsInStock();

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    Page<Product> findProductsInStock(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stock > 0")
    List<Product> findAvailableByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stock > 0")
    Page<Product> findAvailableByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.orders o WHERE o.id = :orderId")
    List<Product> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category = :category")
    Long countByCategory(@Param("category") String category);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllCategories();

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    Page<Product> findAllOrderByPriceAsc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    Page<Product> findAllOrderByPriceDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.stock ASC")
    Page<Product> findAllOrderByStockAsc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.stock DESC")
    Page<Product> findAllOrderByStockDesc(Pageable pageable);

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.name = :name AND p.id <> :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);
}

// === ARCHIVO: src/main/java/com/ecommerce/repository/OrderRepository.java ===
package com.ecommerce.repository;

import com.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    List<Order> findByStatus(String status);

    Page<Order> findByStatus(String status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.status = :status")
    List<Order> findByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.status = :status")
    Page<Order> findByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Page<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN o.products p WHERE p.id = :productId")
    List<Order> findByProductId(@Param("productId") Long productId);

    @Query("SELECT o FROM Order o JOIN o.products p WHERE p.id = :productId")
    Page<Order> findByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.orderDate >= :since")
    List<Order> findRecentOrdersByCustomer(@Param("customerId") Long customerId, @Param("since") LocalDateTime since);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") String status);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.customer.id = :customerId")
    Long countByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    Page<Order> findAllOrderByOrderDateDesc(Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.orderDate ASC")
    Page<Order> findAllOrderByOrderDateAsc(Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.totalAmount DESC")
    Page<Order> findAllOrderByTotalAmountDesc(Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.totalAmount ASC")
    Page<Order> findAllOrderByTotalAmountAsc(Pageable pageable);

    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countOrdersByStatus();

    @Query("SELECT o.customer.id, SUM(o.totalAmount) FROM Order o WHERE o.status = 'COMPLETED' GROUP BY o.customer.id")
    List<Object[]> findTotalSpentByCustomer();

    @Query("SELECT o FROM Order o WHERE o.status = 'COMPLETED' AND o.orderDate >= :startDate")
    List<Order> findCompletedOrdersSince(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT MAX(o.totalAmount) FROM Order o WHERE o.customer.id = :customerId")
    Optional<Double> findMaxOrderAmountByCustomer(@Param("customerId") Long customerId);
}

// === ARCHIVO: src/main/java/com/ecommerce/repository/CustomerRepository.java ===
package com.ecommerce.repository;

import com.ecommerce.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByRole(String role);

    Page<Customer> findByRole(String role, Pageable pageable);

    List<Customer> findByNameContaining(String name);

    Page<Customer> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Customer> searchByName(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Customer> searchByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.address LIKE %:city%")
    List<Customer> findByCity(@Param("city") String city);

    @Query("SELECT c FROM Customer c WHERE c.address LIKE %:city%")
    Page<Customer> findByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.phone LIKE %:phone%")
    List<Customer> findByPhoneContaining(@Param("phone") String phone);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.id = :orderId")
    Optional<Customer> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.status = :status")
    List<Customer> findCustomersWithOrderStatus(@Param("status") String status);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.status = :status")
    Page<Customer> findCustomersWithOrderStatus(@Param("status") String status, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.role = :role")
    Long countByRole(@Param("role") String role);

    boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = :email AND c.id <> :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

    @Query("SELECT c FROM Customer c WHERE c.role = 'USER' ORDER BY c.name ASC")
    Page<Customer> findAllUsersOrderByName(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.role = 'ADMIN'")
    List<Customer> findAllAdmins();

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.orderDate >= :since")
    List<Customer> findActiveCustomersSince(@Param("since") java.time.LocalDateTime since);

    @Query("SELECT c FROM Customer c JOIN c.orders o GROUP BY c.id HAVING COUNT(o) >= :minOrders")
    List<Customer> findCustomersWithMinOrders(@Param("minOrders") Long minOrders);

    @Query("SELECT c.role, COUNT(c) FROM Customer c GROUP BY c.role")
    List<Object[]> countCustomersByRole();

    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain%")
    List<Customer> findByEmailDomain(@Param("domain") String domain);

    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain%")
    Page<Customer> findByEmailDomain(@Param("domain") String domain, Pageable pageable);
}

// === ARCHIVO: src/main/java/com/ecommerce/config/OpenApiConfig.java ===
package com.ecommerce.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .version("1.0.0")
                        .description("API REST para gestión de productos y pedidos de un e-commerce. "
                                + "Incluye autenticación JWT y autorización basada en roles.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("dev@ecommerce.com")
                                .url("https://www.ecommerce.com")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT para autenticación. "
                                        + "Obtén el token mediante el endpoint /api/auth/login")));
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/config/SecurityConfig.java ===
package com.ecommerce.config;

import com.ecommerce.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/api/products/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/orders/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/customers/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java ===
package com.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (StringUtils.hasText(token) && jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            String role = jwtTokenUtil.getRoleFromToken(token);

            List<SimpleGrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/security/JwtTokenUtil.java ===
package com.ecommerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtExpirationMs = 86400000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/controller/ProductController.java ===
package com.ecommerce.controller;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Gestión de Productos", description = "Endpoints para operaciones CRUD de productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Listar productos", description = "Obtiene una lista paginada de todos los productos")
    public ResponseEntity<Page<ProductDto>> getAllProducts(
            @Parameter(description = "Número de página (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo de ordenamiento") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Dirección de ordenamiento") @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductDto> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto específico por su identificador")
    public ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "ID del producto a buscar") @PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en el sistema")
    public ResponseEntity<ProductDto> createProduct(
            @Parameter(description = "Datos del producto a crear") @Valid @RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente")
    public ResponseEntity<ProductDto> updateProduct(
            @Parameter(description = "ID del producto a actualizar") @PathVariable Long id,
            @Parameter(description = "Nuevos datos del producto") @Valid @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema por su ID")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID del producto a eliminar") @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Buscar por categoría", description = "Obtiene productos filtrados por categoría")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(
            @Parameter(description = "Nombre de la categoría") @PathVariable String category) {
        List<ProductDto> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar productos", description = "Busca productos por nombre o descripción")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @Parameter(description = "Término de búsqueda") @RequestParam String q) {
        List<ProductDto> products = productService.searchProducts(q);
        return ResponseEntity.ok(products);
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar stock", description = "Actualiza la cantidad en stock de un producto")
    public ResponseEntity<ProductDto> updateStock(
            @Parameter(description = "ID del producto") @PathVariable Long id,
            @Parameter(description = "Nueva cantidad en stock") @RequestParam Integer quantity) {
        ProductDto updatedProduct = productService.updateStock(id, quantity);
        return ResponseEntity.ok(updatedProduct);
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/controller/OrderController.java ===
package com.ecommerce.controller;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Gestión de Pedidos", description = "Endpoints para operaciones CRUD de pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Obtiene una lista paginada de todos los pedidos")
    public ResponseEntity<Page<OrderDto>> getAllOrders(
            @Parameter(description = "Número de página (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo de ordenamiento") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Dirección de ordenamiento") @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OrderDto> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Retorna un pedido específico por su identificador")
    public ResponseEntity<OrderDto> getOrderById(
            @Parameter(description = "ID del pedido a buscar") @PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    @Operation(summary = "Crear pedido", description = "Crea un nuevo pedido en el sistema")
    public ResponseEntity<OrderDto> createOrder(
            @Parameter(description = "Datos del pedido a crear") @Valid @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido", description = "Actualiza los datos de un pedido existente")
    public ResponseEntity<OrderDto> updateOrder(
            @Parameter(description = "ID del pedido a actualizar") @PathVariable Long id,
            @Parameter(description = "Nuevos datos del pedido") @Valid @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido", description = "Elimina un pedido del sistema por su ID")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "ID del pedido a eliminar") @PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Buscar por cliente", description = "Obtiene todos los pedidos de un cliente específico")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(
            @Parameter(description = "ID del cliente") @PathVariable Long customerId) {
        List<OrderDto> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar por estado", description = "Obtiene pedidos filtrados por estado")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(
            @Parameter(description = "Estado del pedido") @PathVariable String status) {
        List<OrderDto> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Actualizar estado", description = "Actualiza el estado de un pedido")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @Parameter(description = "ID del pedido") @PathVariable Long id,
            @Parameter(description = "Nuevo estado del pedido") @RequestParam String status) {
        OrderDto updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/controller/CustomerController.java ===
package com.ecommerce.controller;

import com.ecommerce.dto.CustomerDto;
import com.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Gestión de Clientes", description = "Operaciones CRUD para la gestión de clientes del e-commerce")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente en el sistema con los datos proporcionados")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto created = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retrieves a specific customer by their unique identifier")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CustomerDto> getCustomerById(
            @Parameter(description = "ID único del cliente a buscar", required = true) @PathVariable Long id) {
        CustomerDto customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Retrieves a paginated list of all customers in the system")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(
            @Parameter(description = "Configuración de paginación y ordenamiento") @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<CustomerDto> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Updates an existing customer's information with the provided data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> updateCustomer(
            @Parameter(description = "ID único del cliente a actualizar", required = true) @PathVariable Long id,
            @Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updated = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Removes a customer from the system using their unique identifier")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "ID único del cliente a eliminar", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar clientes por email", description = "Retrieves customers matching the provided email pattern")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerDto>> searchCustomersByEmail(
            @Parameter(description = "Email o patrón de búsqueda", required = true) @RequestParam String email) {
        List<CustomerDto> customers = customerService.searchCustomersByEmail(email);
        return ResponseEntity.ok(customers);
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/exception/GlobalExceptionHandler.java ===
package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(
            InvalidInputException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing
                ));

        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos de entrada no son válidos")
                .path(request.getDescription(false).replace("uri=", ""))
                .validationErrors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Forbidden")
                .message("No tienes permisos para acceder a este recurso")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ha ocurrido un error inesperado en el sistema")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public ErrorResponse() {}

        public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String path;

            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                return new ErrorResponse(timestamp, status, error, message, path);
            }
        }
    }

    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> validationErrors;

        public ValidationErrorResponse() {
            super();
        }

        public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, 
                                        String message, String path, Map<String, String> validationErrors) {
            super(timestamp, status, error, message, path);
            this.validationErrors = validationErrors;
        }

        public Map<String, String> getValidationErrors() { return validationErrors; }
        public void setValidationErrors(Map<String, String> validationErrors) { this.validationErrors = validationErrors; }
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/exception/ResourceNotFoundException.java ===
package com.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.resourceName = "Resource";
        this.fieldName = "unknown";
        this.fieldValue = null;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/exception/InvalidInputException.java ===
package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para entradas inválidas en la aplicación.
 * Se utiliza cuando los datos proporcionados por el cliente no cumplen
 * con las validaciones de negocio o las restricciones definidas.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

    private final String field;
    private final Object rejectedValue;

    public InvalidInputException(String message) {
        super(message);
        this.field = null;
        this.rejectedValue = null;
    }

    public InvalidInputException(String message, String field) {
        super(message);
        this.field = field;
        this.rejectedValue = null;
    }

    public InvalidInputException(String message, String field, Object rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    @Override
    public String toString() {
        if (field != null && rejectedValue != null) {
            return String.format("InvalidInputException: %s [field=%s, rejectedValue=%s]", 
                getMessage(), field, rejectedValue);
        } else if (field != null) {
            return String.format("InvalidInputException: %s [field=%s]", getMessage(), field);
        }
        return super.toString();
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/service/ProductService.java ===
package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de negocio para la gestión de productos.
 * Encapsula toda la lógica de negocio relacionada con productos,
 * incluyendo validación, transformación entre entidades y DTOs,
 * y coordinación con el repositorio para operaciones de persistencia.
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Crea un nuevo producto en el sistema.
     * Valida que el nombre no esté vacío, que el precio sea positivo
     * y que el stock no sea negativo antes de persistir.
     */
    public ProductDto createProduct(ProductDto productDto) {
        validateProductData(productDto);
        
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        
        return mapToDto(savedProduct);
    }

    /**
     * Actualiza un producto existente identificado por su ID.
     * Si el producto no existe, lanza ResourceNotFoundException.
     * Valida los datos antes de actualizar.
     */
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado con ID: " + id, "id", id));

        validateProductData(productDto);

        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setCategory(productDto.getCategory());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    /**
     * Elimina un producto por su ID.
     * Lanza exception si el producto no existe.
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Producto no encontrado para eliminar con ID: " + id, "id", id);
        }
        productRepository.deleteById(id);
    }

    /**
     * Busca un producto por su ID.
     * Lanza ResourceNotFoundException si no se encuentra.
     */
    @Transactional(readOnly = true)
    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado con ID: " + id, "id", id));
        return mapToDto(product);
    }

    /**
     * Obtiene todos los productos con soporte de paginación.
     * El parámetro Pageable permite controlar página, tamaño y ordenamiento.
     */
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
            .map(this::mapToDto);
    }

    /**
     * Busca productos por categoría.
     * Retorna una lista vacía si no hay productos en esa categoría.
     */
    @Transactional(readOnly = true)
    public List<ProductDto> findProductsByCategory(String category) {
        return productRepository.findAll().stream()
            .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Busca productos cuyo precio está dentro de un rango.
     * Útil para funcionalidades de filtrado por precio.
     */
    @Transactional(readOnly = true)
    public List<ProductDto> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new InvalidInputException(
                "Los precios mínimo y máximo son obligatorios para el filtrado",
                "priceRange");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new InvalidInputException(
                "El precio mínimo no puede ser mayor que el máximo",
                "priceRange");
        }

        return productRepository.findAll().stream()
            .filter(p -> p.getPrice() != null)
            .filter(p -> p.getPrice().compareTo(minPrice) >= 0 
                && p.getPrice().compareTo(maxPrice) <= 0)
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Actualiza el stock de un producto.
     * Se utiliza cuando se procesa un pedido o se recibe inventario.
     */
    public ProductDto updateStock(Long id, Integer newStock) {
        if (newStock == null || newStock < 0) {
            throw new InvalidInputException(
                "El stock debe ser un número no negativo",
                "stock", newStock);
        }

        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado con ID: " + id, "id", id));

        product.setStock(newStock);
        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    /**
     * Valida los datos de un producto antes de persistir o actualizar.
     * Lanza InvalidInputException si los datos no son válidos.
     */
    private void validateProductData(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
            throw new InvalidInputException(
                "El nombre del producto es obligatorio",
                "name", productDto.getName());
        }

        if (productDto.getName().length() > 200) {
            throw new InvalidInputException(
                "El nombre del producto no puede exceder 200 caracteres",
                "name", productDto.getName());
        }

        if (productDto.getDescription() != null && productDto.getDescription().length() > 1000) {
            throw new InvalidInputException(
                "La descripción no puede exceder 1000 caracteres",
                "description");
        }

        if (productDto.getPrice() == null) {
            throw new InvalidInputException(
                "El precio del producto es obligatorio",
                "price");
        }

        if (productDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException(
                "El precio debe ser mayor que cero",
                "price", productDto.getPrice());
        }

        if (productDto.getPrice().compareTo(new BigDecimal("999999.99")) > 0) {
            throw new InvalidInputException(
                "El precio no puede exceder 999999.99",
                "price", productDto.getPrice());
        }

        if (productDto.getStock() == null) {
            throw new InvalidInputException(
                "El stock del producto es obligatorio",
                "stock");
        }

        if (productDto.getStock() < 0) {
            throw new InvalidInputException(
                "El stock no puede ser negativo",
                "stock", productDto.getStock());
        }

        if (productDto.getCategory() != null && productDto.getCategory().length() > 100) {
            throw new InvalidInputException(
                "La categoría no puede exceder 100 caracteres",
                "category");
        }
    }

    /**
     * Transforma una entidad Product a un ProductDto.
     * Copia los campos relevantes omitting las relaciones para evitar
     * problemas de serialización circular.
     */
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory());
        return dto;
    }

    /**
     * Transforma un ProductDto a una entidad Product.
     * Se usa para crear nuevas entidades o actualizar existentes.
     */
    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) {
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(productDto.getCategory());
        return product;
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/service/OrderService.java ===
package com.ecommerce.service;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Servicio de negocio para la gestión de pedidos.
 * Maneja la lógica de creación, actualización y consulta de pedidos,
 * incluyendo validación de productos, cálculo de totales y gestión de inventario.
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Crea un nuevo pedido en el sistema.
     * Valida que el cliente exista, que los productos sean válidos,
     * que haya suficiente stock y calcula el total del pedido.
     */
    public OrderDto createOrder(OrderDto orderDto) {
        validateOrderData(orderDto);

        List<Product> products = validateAndFetchProducts(orderDto.getProductIds());
        BigDecimal totalAmount = calculateTotalAmount(products);
        int totalQuantity = products.size();

        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setProducts(new HashSet<>(products));
        order.setTotalQuantity(totalQuantity);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        updateProductStock(products);

        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    /**
     * Actualiza el estado de un pedido existente.
     * Los estados válidos incluyen: PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED.
     */
    public OrderDto updateOrderStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Pedido no encontrado con ID: " + id, "id", id));

        validateStatus(newStatus);

        String currentStatus = order.getStatus();
        if ("CANCELLED".equals(currentStatus)) {
            throw new InvalidInputException(
                "No se puede modificar un pedido cancelado",
                "status", newStatus);
        }

        if ("DELIVERED".equals(currentStatus)) {
            throw new InvalidInputException(
                "No se puede modificar un pedido entregado",
                "status", newStatus);
        }

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return mapToDto(updatedOrder);
    }

    /**
     * Cancela un pedido si aún no ha sido enviado.
     * Restaura el stock de los productos asociados.
     */
    public OrderDto cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Pedido no encontrado con ID: " + id, "id", id));

        if ("SHIPPED".equals(order.getStatus()) || "DELIVERED".equals(order.getStatus())) {
            throw new InvalidInputException(
                "No se puede cancelar un pedido que ya ha sido enviado o entregado",
                "status", order.getStatus());
        }

        if ("CANCELLED".equals(order.getStatus())) {
            throw new InvalidInputException(
                "El pedido ya está cancelado",
                "status", order.getStatus());
        }

        restoreProductStock(order.getProducts());
        order.setStatus("CANCELLED");

        Order cancelledOrder = orderRepository.save(order);
        return mapToDto(cancelledOrder);
    }

    /**
     * Busca un pedido por su ID.
     * Lanza ResourceNotFoundException si no existe.
     */
    @Transactional(readOnly = true)
    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Pedido no encontrado con ID: " + id, "id", id));
        return mapToDto(order);
    }

    /**
     * Obtiene todos los pedidos con soporte de paginación.
     * Ordena por fecha de pedido de forma descendente por defecto.
     */
    @Transactional(readOnly = true)
    public Page<OrderDto> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
            .map(this::mapToDto);
    }

    /**
     * Busca pedidos de un cliente específico.
     * Útil para el historial de compras de un usuario.
     */
    @Transactional(readOnly = true)
    public List<OrderDto> findOrdersByCustomerId(Long customerId) {
        if (customerId == null) {
            throw new InvalidInputException(
                "El ID del cliente es obligatorio",
                "customerId", customerId);
        }

        return orderRepository.findAll().stream()
            .filter(o -> o.getCustomerId() != null 
                && o.getCustomerId().equals(customerId))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Busca pedidos por estado.
     * Útil para filtros en paneles de administración.
     */
    @Transactional(readOnly = true)
    public List<OrderDto> findOrdersByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException(
                "El estado del pedido es obligatorio",
                "status");
        }

        return orderRepository.findAll().stream()
            .filter(o -> o.getStatus() != null 
                && o.getStatus().equalsIgnoreCase(status))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Valida los datos de un pedido antes de procesarlo.
     */
    private void validateOrderData(OrderDto orderDto) {
        if (orderDto.getCustomerId() == null) {
            throw new InvalidInputException(
                "El ID del cliente es obligatorio",
                "customerId");
        }

        if (orderDto.getProductIds() == null || orderDto.getProductIds().isEmpty()) {
            throw new InvalidInputException(
                "El pedido debe contener al menos un producto",
                "productIds");
        }

        if (orderDto.getProductIds().size() > 100) {
            throw new InvalidInputException(
                "Un pedido no puede contener más de 100 productos",
                "productIds");
        }
    }

    /**
     * Valida que los productos existan y estén disponibles.
     * Retorna la lista de productos validados.
     */
    private List<Product> validateAndFetchProducts(List<Long> productIds) {
        Set<Long> uniqueIds = new HashSet<>(productIds);
        List<Product> products = new ArrayList<>();

        for (Long productId : uniqueIds) {
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Producto no encontrado con ID: " + productId, "productId", productId));

            if (product.getStock() == null || product.getStock() <= 0) {
                throw new InvalidInputException(
                    "Producto sin stock disponible: " + product.getName(),
                    "productId", productId);
            }

            products.add(product);
        }

        return products;
    }

    /**
     * Calcula el monto total del pedido sumando los precios de los productos.
     */
    private BigDecimal calculateTotalAmount(List<Product> products) {
        return products.stream()
            .filter(p -> p.getPrice() != null)
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Valida que el estado proporcionado sea válido.
     */
    private void validateStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException(
                "El estado del pedido es obligatorio",
                "status");
        }

        List<String> validStatuses = List.of(
            "PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED"
        );

        if (!validStatuses.contains(status.toUpperCase())) {
            throw new InvalidInputException(
                "Estado de pedido inválido. Estados válidos: " + validStatuses,
                "status", status);
        }
    }

    /**
     * Actualiza el stock de los productos restando las cantidades vendidas.
     */
    private void updateProductStock(List<Product> products) {
        for (Product product : products) {
            int newStock = product.getStock() - 1;
            product.setStock(Math.max(0, newStock));
            productRepository.save(product);
        }
    }

    /**
     * Restaura el stock de los productos al cancelar un pedido.
     */
    private void restoreProductStock(Set<Product> products) {
        for (Product product : products) {
            int restoredStock = product.getStock() + 1;
            product.setStock(restoredStock);
            productRepository.save(product);
        }
    }

    /**
     * Transforma una entidad Order a un OrderDto.
     * Incluye los IDs de los productos asociados.
     */
    private OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setTotalQuantity(order.getTotalQuantity());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());

        if (order.getProducts() != null) {
            List<Long> productIds = order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
            dto.setProductIds(productIds);
        }

        return dto;
    }
}

// === ARCHIVO: src/main/java/com/ecommerce/service/CustomerService.java ===
package com.ecommerce.service;

import com.ecommerce.dto.CustomerDto;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<CustomerDto> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(this::toDto);
    }

    public List<CustomerDto> findAllWithoutPagination() {
        return customerRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        return toDto(customer);
    }

    public CustomerDto findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con email: " + email));
    }

    public CustomerDto create(CustomerDto customerDto) {
        validateCustomerDto(customerDto);
        
        if (customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
            throw new InvalidInputException("El email ya está registrado: " + customerDto.getEmail());
        }

        Customer customer = toEntity(customerDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        
        if (customer.getRole() == null || customer.getRole().isEmpty()) {
            customer.setRole("USER");
        }

        Customer savedCustomer = customerRepository.save(customer);
        return toDto(savedCustomer);
    }

    public CustomerDto update(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        if (customerDto.getName() != null && !customerDto.getName().isEmpty()) {
            existingCustomer.setName(customerDto.getName());
        }
        if (customerDto.getEmail() != null && !customerDto.getEmail().isEmpty()) {
            if (!existingCustomer.getEmail().equals(customerDto.getEmail()) &&
                customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
                throw new InvalidInputException("El email ya está registrado: " + customerDto.getEmail());
            }
            existingCustomer.setEmail(customerDto.getEmail());
        }
        if (customerDto.getPassword() != null && !customerDto.getPassword().isEmpty()) {
            existingCustomer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        }
        if (customerDto.getAddress() != null) {
            existingCustomer.setAddress(customerDto.getAddress());
        }
        if (customerDto.getPhone() != null) {
            existingCustomer.setPhone(customerDto.getPhone());
        }
        if (customerDto.getRole() != null && !customerDto.getRole().isEmpty()) {
            existingCustomer.setRole(customerDto.getRole());
        }

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return toDto(updatedCustomer);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    public CustomerDto authenticate(String email, String password) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidInputException("Credenciales inválidas"));
        
        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new InvalidInputException("Credenciales inválidas");
        }
        
        return toDto(customer);
    }

    public boolean existsByEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    public long count() {
        return customerRepository.count();
    }

    private void validateCustomerDto(CustomerDto customerDto) {
        if (customerDto.getEmail() == null || customerDto.getEmail().isEmpty()) {
            throw new InvalidInputException("El email es obligatorio");
        }
        if (!customerDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("El formato del email es inválido");
        }
        if (customerDto.getPassword() == null || customerDto.getPassword().isEmpty()) {
            throw new InvalidInputException("La contraseña es obligatoria");
        }
        if (customerDto.getPassword().length() < 6) {
            throw new InvalidInputException("La contraseña debe tener al menos 6 caracteres");
        }
        if (customerDto.getName() == null || customerDto.getName().isEmpty()) {
            throw new InvalidInputException("El nombre es obligatorio");
        }
    }

    private CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());
        dto.setAddress(customer.getAddress());
        dto.setPhone(customer.getPhone());
        dto.setRole(customer.getRole());
        if (customer.getOrders() != null) {
            dto.setOrderIds(customer.getOrders().stream()
                    .map(order -> order.getId())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setRole(dto.getRole() != null ? dto.getRole() : "USER");
        return customer;
    }
}

// === ARCHIVO: src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java ===
package com.ecommerce.unit;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService Unit Tests")
class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("High-performance laptop");
        product.setPrice(new BigDecimal("1299.99"));
        product.setStock(10);
        product.setCategory("Electronics");

        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Laptop");
        productDto.setDescription("High-performance laptop");
        productDto.setPrice(new BigDecimal("1299.99"));
        productDto.setStock(10);
        productDto.setCategory("Electronics");
    }

    @Test
    @DisplayName("Should create a product successfully")
    void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto result = productService.createProduct(productDto);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("Should return all products")
    void testFindAllProducts() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> result = productService.findAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return product by id")
    void testFindProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDto result = productService.findProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when product not found")
    void testFindProductByIdNotFound() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findProductById(999L));
    }

    @Test
    @DisplayName("Should update product successfully")
    void testUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto result = productService.updateProduct(1L, productDto);

        assertNotNull(result);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("Should delete product successfully")
    void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    @DisplayName("Should find products by category")
    void testFindProductsByCategory() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findByCategory("Electronics")).thenReturn(products);

        List<ProductDto> result = productService.findProductsByCategory("Electronics");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findByCategory("Electronics");
    }
}

// === ARCHIVO: src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java ===
package com.ecommerce.unit;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService Unit Tests")
class OrderServiceUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderDto orderDto;
    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1299.99"));
        product.setStock(10);

        order = new Order();
        order.setId(1L);
        order.setCustomer(customer);
        order.setProducts(new HashSet<>(List.of(product)));
        order.setTotalQuantity(2);
        order.setTotalAmount(new BigDecimal("2599.98"));
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setCustomerId(1L);
        orderDto.setProductIds(List.of(1L));
        orderDto.setTotalQuantity(2);
        orderDto.setTotalAmount(new BigDecimal("2599.98"));
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setStatus("PENDING");
    }

    @Test
    @DisplayName("Should create an order successfully")
    void testCreateOrder() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto result = orderService.createOrder(orderDto);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Should return all orders")
    void testFindAllOrders() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> result = orderService.findAllOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return order by id")
    void testFindOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        OrderDto result = orderService.findOrderById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when order not found")
    void testFindOrderByIdNotFound() {
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.findOrderById(999L));
    }

    @Test
    @DisplayName("Should update order status successfully")
    void testUpdateOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto result = orderService.updateOrderStatus(1L, "COMPLETED");

        assertNotNull(result);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Should delete order successfully")
    void testDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).delete(order);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    @DisplayName("Should return orders by customer id")
    void testFindOrdersByCustomerId() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findByCustomerId(1L)).thenReturn(orders);

        List<OrderDto> result = orderService.findOrdersByCustomerId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByCustomerId(1L);
    }
}

// === ARCHIVO: src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java ===
package com.ecommerce.integration;


import com.ecommerce.controller.ProductController;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("ProductController Integration Tests")
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    private ProductDto productDto;
    private Product savedProduct;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");
        productDto.setPrice(new BigDecimal("99.99"));
        productDto.setStock(50);
        productDto.setCategory("Test Category");

        savedProduct = new Product();
        savedProduct.setName("Saved Product");
        savedProduct.setDescription("Saved Description");
        savedProduct.setPrice(new BigDecimal("199.99"));
        savedProduct.setStock(25);
        savedProduct.setCategory("Saved Category");
        savedProduct = productRepository.save(savedProduct);
    }

    @Test
    @DisplayName("Should create a product via POST /api/products")
    void testCreateProduct() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.price", is(99.99)))
                .andExpect(jsonPath("$.stock", is(50)))
                .andExpect(jsonPath("$.category", is("Test Category")));
    }

    @Test
    @DisplayName("Should return all products via GET /api/products")
    void testFindAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(savedProduct.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is("Saved Product")));
    }

    @Test
    @DisplayName("Should return product by id via GET /api/products/{id}")
    void testFindProductById() throws Exception {
        mockMvc.perform(get("/api/products/" + savedProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Saved Product")))
                .andExpect(jsonPath("$.price", is(199.99)));
    }

    @Test
    @DisplayName("Should return 404 for non-existent product")
    void testFindProductByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/products/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", notNullValue()));
    }

    @Test
    @DisplayName("Should update product via PUT /api/products/{id}")
    void testUpdateProduct() throws Exception {
        productDto.setName("Updated Product");
        productDto.setPrice(new BigDecimal("299.99"));

        mockMvc.perform(put("/api/products/" + savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedProduct.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Updated Product")))
                .andExpect(jsonPath("$.price", is(299.99)));
    }

    @Test
    @DisplayName("Should delete product via DELETE /api/products/{id}")
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/" + savedProduct.getId()))
                .andExpect(status().isNoContent());

        assertFalse(productRepository.existsById(savedProduct.getId()));
    }

    @Test
    @DisplayName("Should return products by category via GET /api/products/category/{category}")
    void testFindProductsByCategory() throws Exception {
        mockMvc.perform(get("/api/products/category/Saved Category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("Saved Category")));
    }

    @Test
    @DisplayName("Should return 400 for invalid product data")
    void testCreateProductWithInvalidData() throws Exception {
        ProductDto invalidDto = new ProductDto();
        invalidDto.setName("");
        invalidDto.setPrice(new BigDecimal("-10"));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }
}

// === ARCHIVO: docker/Dockerfile ===
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

// === ARCHIVO: docker/.dockerignore ===
.git
.gitignore
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/
*.class
*.log
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar
hs_err_pid*
replay_pid*
.idea
*.iml
*.ipr
*.iws
.project
.classpath
.settings
.vscode
.DS_Store
Thumbs.db
*.swp
*.bak
*.tmp
*.orig
*~
.env
.env.local
docker/
docker-compose*
README.md
docs/
notes/
*.md

// === ARCHIVO: README.md ===
# Ecommerce API

API REST para gestión de productos y pedidos de un e-commerce.

## Requisitos

- Java 21
- Maven 3.9+
- Docker (opcional)

## Compilación

```bash
mvn clean package
```

## Ejecución local

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## Docker

### Build

```bash
docker build -t ecommerce-api docker/
```

### Run

```bash
docker run -p 8080:8080 ecommerce-api
```

## Documentación

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Endpoints

- `GET /api/products` - Listar productos (paginado)
- `POST /api/products` - Crear producto
- `GET /api/products/{id}` - Obtener producto
- `PUT /api/products/{id}` - Actualizar producto
- `DELETE /api/products/{id}` - Eliminar producto
- `GET /api/orders` - Listar pedidos (paginado)
- `POST /api/orders` - Crear pedido
- `GET /api/orders/{id}` - Obtener pedido
- `PUT /api/orders/{id}` - Actualizar pedido
- `DELETE /api/orders/{id}` - Eliminar pedido
- `GET /api/customers` - Listar clientes (paginado)
- `POST /api/customers` - Crear cliente
- `GET /api/customers/{id}` - Obtener cliente
- `PUT /api/customers/{id}` - Actualizar cliente
- `DELETE /api/customers/{id}` - Eliminar cliente
- `POST /api/auth/login` - Autenticación

## Seguridad

La API usa JWT para autenticación. Los endpoints `/api/products`, `/api/orders` y `/api/customers` requieren autenticación.

Roles:
- ADMIN: acceso completo
- USER: lectura y creación

## Arquitectura

- **Controller**: maneja HTTP requests/responses
- **Service**: lógica de negocio
- **Repository**: acceso a datos JPA
- **Model**: entidades con relaciones Hibernate
- **DTO**: objetos de transferencia de datos
- **Security**: filtros JWT y configuración
- **Exception**: manejo centralizado de errores
```
