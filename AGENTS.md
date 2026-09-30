# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de una API REST para gestión de productos y pedidos**.

| | |
|---|---|
| Tema | Arquitectura Empresarial con Spring Boot |
| Nivel | junior-l3 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | capas estándar (controller-service-repository) |
| Tiempo estimado | 40 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.4.0
- org.springframework.boot:spring-boot-starter-security 3.4.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.4.0
- io.jsonwebtoken:jjwt-api 0.12.5
- io.jsonwebtoken:jjwt-impl 0.12.5
- io.jsonwebtoken:jjwt-jackson 0.12.5
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.6.0
- org.projectlombok:lombok 1.18.34
- jakarta.validation:jakarta.validation-api 3.1.0
- org.hibernate.validator:hibernate-validator 8.0.1.Final
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core 5.12.0
- org.mockito:mockito-junit-jupiter 5.12.0
- com.h2database:h2 2.3.230

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Modelado de datos y relaciones**: Modelo de datos con entidades y relaciones definidas.
- **Fase 2 — Implementación de controladores REST**: Controladores REST implementados y funcionando.
- **Fase 3 — Autenticación y autorización con JWT**: Autenticación y autorización implementadas y funcionando.
- **Fase 4 — Documentación automática con OpenAPI y Swagger UI**: Documentación automática de la API accesible en /api-docs.
- **Fase 5 — Manejo centralizado de errores**: Manejo centralizado de errores implementado y funcionando.
- **Fase 6 — Validación de entradas**: Validación de entradas implementada y funcionando.
- **Fase 7 — Paginación y ordenamiento**: Paginación y ordenamiento implementados y funcionando.
- **Fase 8 — Pruebas unitarias y de integración**: Pruebas unitarias y de integración implementadas y funcionando.
- **Fase 9 — Containerización con Docker**: Aplicación containerizada y funcionando en Docker.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `docker/Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- [ ] `src/main/java/com/ecommerce/config/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/main/java/com/ecommerce/security/JwtTokenUtil.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (125)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/ecommerce/dto/ProductDto.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/dto/OrderDto.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/dto/CustomerDto.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/controller/ProductController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/ecommerce/model/Product.java` — `Order.getProducts`
      Se invoca `getProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/model/Order.java` — `Product.getOrders`
      Se invoca `getOrders` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/model/Customer.java` — `Order.setCustomer`
      Se invoca `setCustomer` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/model/Customer.java` — `Product.getOrders`
      Se invoca `getOrders` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.getUsernameFromToken`
      Se invoca `getUsernameFromToken` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java` — `JwtTokenUtil.getRoleFromToken`
      Se invoca `getRoleFromToken` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.getAllProducts`
      Se invoca `getAllProducts` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.getProductById`
      Se invoca `getProductById` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.getProductsByCategory`
      Se invoca `getProductsByCategory` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/ProductController.java` — `ProductService.searchProducts`
      Se invoca `searchProducts` sobre `ProductService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getAllOrders`
      Se invoca `getAllOrders` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getOrderById`
      Se invoca `getOrderById` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.updateOrder`
      Se invoca `updateOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.deleteOrder`
      Se invoca `deleteOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getOrdersByCustomerId`
      Se invoca `getOrdersByCustomerId` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/OrderController.java` — `OrderService.getOrdersByStatus`
      Se invoca `getOrdersByStatus` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.createCustomer`
      Se invoca `createCustomer` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.getCustomerById`
      Se invoca `getCustomerById` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.getAllCustomers`
      Se invoca `getAllCustomers` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.updateCustomer`
      Se invoca `updateCustomer` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.deleteCustomer`
      Se invoca `deleteCustomer` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/controller/CustomerController.java` — `CustomerService.searchCustomersByEmail`
      Se invoca `searchCustomersByEmail` sobre `CustomerService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.save`
      Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.findById`
      Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setName`
      Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setDescription`
      Se invoca `setDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setPrice`
      Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setStock`
      Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setCategory`
      Se invoca `setCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.existsById`
      Se invoca `existsById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.deleteById`
      Se invoca `deleteById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `ProductRepository.findAll`
      Se invoca `findAll` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getId`
      Se invoca `getId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getName`
      Se invoca `getName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getDescription`
      Se invoca `getDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getPrice`
      Se invoca `getPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getStock`
      Se invoca `getStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.getCategory`
      Se invoca `getCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/ProductService.java` — `Product.setId`
      Se invoca `setId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setCustomerId`
      Se invoca `setCustomerId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setProducts`
      Se invoca `setProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setTotalQuantity`
      Se invoca `setTotalQuantity` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setTotalAmount`
      Se invoca `setTotalAmount` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setOrderDate`
      Se invoca `setOrderDate` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.setStatus`
      Se invoca `setStatus` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `OrderRepository.save`
      Se invoca `save` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `OrderRepository.findById`
      Se invoca `findById` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getStatus`
      Se invoca `getStatus` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getProducts`
      Se invoca `getProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `OrderRepository.findAll`
      Se invoca `findAll` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `ProductRepository.findById`
      Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Product.getStock`
      Se invoca `getStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Product.getName`
      Se invoca `getName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Product.setStock`
      Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `ProductRepository.save`
      Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getId`
      Se invoca `getId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getCustomerId`
      Se invoca `getCustomerId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getTotalQuantity`
      Se invoca `getTotalQuantity` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getTotalAmount`
      Se invoca `getTotalAmount` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/OrderService.java` — `Order.getOrderDate`
      Se invoca `getOrderDate` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.findAll`
      Se invoca `findAll` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.findById`
      Se invoca `findById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setPassword`
      Se invoca `setPassword` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getPassword`
      Se invoca `getPassword` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getRole`
      Se invoca `getRole` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setRole`
      Se invoca `setRole` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.save`
      Se invoca `save` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setName`
      Se invoca `setName` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getEmail`
      Se invoca `getEmail` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setEmail`
      Se invoca `setEmail` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setAddress`
      Se invoca `setAddress` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.setPhone`
      Se invoca `setPhone` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.existsById`
      Se invoca `existsById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.deleteById`
      Se invoca `deleteById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `CustomerRepository.count`
      Se invoca `count` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getId`
      Se invoca `getId` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getName`
      Se invoca `getName` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getAddress`
      Se invoca `getAddress` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getPhone`
      Se invoca `getPhone` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/ecommerce/service/CustomerService.java` — `Customer.getOrders`
      Se invoca `getOrders` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setId`
      Se invoca `setId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setName`
      Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setDescription`
      Se invoca `setDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setPrice`
      Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setStock`
      Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `Product.setCategory`
      Se invoca `setCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `ProductRepository.save`
      Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `ProductRepository.findAll`
      Se invoca `findAll` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java` — `ProductRepository.findById`
      Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Customer.setId`
      Se invoca `setId` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Customer.setName`
      Se invoca `setName` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Customer.setEmail`
      Se invoca `setEmail` sobre `Customer`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setId`
      Se invoca `setId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setName`
      Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setPrice`
      Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Product.setStock`
      Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setId`
      Se invoca `setId` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setCustomer`
      Se invoca `setCustomer` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setProducts`
      Se invoca `setProducts` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setTotalQuantity`
      Se invoca `setTotalQuantity` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setTotalAmount`
      Se invoca `setTotalAmount` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setOrderDate`
      Se invoca `setOrderDate` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `Order.setStatus`
      Se invoca `setStatus` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `CustomerRepository.findById`
      Se invoca `findById` sobre `CustomerRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `ProductRepository.findById`
      Se invoca `findById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderRepository.save`
      Se invoca `save` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderRepository.findAll`
      Se invoca `findAll` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderRepository.findById`
      Se invoca `findById` sobre `OrderRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java` — `OrderService.deleteOrder`
      Se invoca `deleteOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `ProductRepository.deleteAll`
      Se invoca `deleteAll` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setName`
      Se invoca `setName` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setDescription`
      Se invoca `setDescription` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setPrice`
      Se invoca `setPrice` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setStock`
      Se invoca `setStock` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.setCategory`
      Se invoca `setCategory` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `ProductRepository.save`
      Se invoca `save` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `Product.getId`
      Se invoca `getId` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java` — `ProductRepository.existsById`
      Se invoca `existsById` sobre `ProductRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (31)

- `pom.xml`
- `src/main/java/com/ecommerce/EcommerceApplication.java`
- `src/main/resources/application.properties`
- `src/main/java/com/ecommerce/dto/ProductDto.java`
- `src/main/java/com/ecommerce/dto/OrderDto.java`
- `src/main/java/com/ecommerce/dto/CustomerDto.java`
- `src/main/java/com/ecommerce/model/Product.java`
- `src/main/java/com/ecommerce/model/Order.java`
- `src/main/java/com/ecommerce/model/Customer.java`
- `src/main/java/com/ecommerce/repository/ProductRepository.java`
- `src/main/java/com/ecommerce/repository/OrderRepository.java`
- `src/main/java/com/ecommerce/repository/CustomerRepository.java`
- `src/main/java/com/ecommerce/config/OpenApiConfig.java`
- `src/main/java/com/ecommerce/config/SecurityConfig.java`
- `src/main/java/com/ecommerce/security/JwtAuthenticationFilter.java`
- `src/main/java/com/ecommerce/security/JwtTokenUtil.java`
- `src/main/java/com/ecommerce/controller/ProductController.java`
- `src/main/java/com/ecommerce/controller/OrderController.java`
- `src/main/java/com/ecommerce/controller/CustomerController.java`
- `src/main/java/com/ecommerce/exception/GlobalExceptionHandler.java`
- `src/main/java/com/ecommerce/exception/ResourceNotFoundException.java`
- `src/main/java/com/ecommerce/exception/InvalidInputException.java`
- `src/main/java/com/ecommerce/service/ProductService.java`
- `src/main/java/com/ecommerce/service/OrderService.java`
- `src/main/java/com/ecommerce/service/CustomerService.java`
- `src/test/java/com/ecommerce/unit/ProductServiceUnitTest.java`
- `src/test/java/com/ecommerce/unit/OrderServiceUnitTest.java`
- `src/test/java/com/ecommerce/integration/ProductControllerIntegrationTest.java`
- `docker/Dockerfile`
- `docker/.dockerignore`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/ecommerce`
- `src/main/java/com/ecommerce/config`
- `src/main/java/com/ecommerce/controller`
- `src/main/java/com/ecommerce/dto`
- `src/main/java/com/ecommerce/exception`
- `src/main/java/com/ecommerce/model`
- `src/main/java/com/ecommerce/repository`
- `src/main/java/com/ecommerce/security`
- `src/main/java/com/ecommerce/service`
- `src/main/resources`
- `src/test/java/com/ecommerce`
- `src/test/java/com/ecommerce/integration`
- `src/test/java/com/ecommerce/unit`
- `docker`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar (controller-service-repository)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Desarrollar una API REST empresarial con Spring Boot 3 para la gestión de productos y pedidos en un e-commerce. El sistema debe incluir: arquitectura en capas con controladores REST, servicios de negocio y repositorios JPA; modelado de datos con entidades Product, Order y Customer usando relaciones OneToMany y ManyToMany con Hibernate; autenticación y autorización basada en JWT con Spring Security, incluyendo roles de ADMIN y USER con acceso diferenciado por endpoint; documentación automática con OpenAPI 3.0 y Swagger UI accesible en /api-docs; manejo centralizado de errores con @ControllerAdvice y respuestas estandarizadas en formato JSON; validación de entradas con Bean Validation usando @Valid, @NotNull y @Size; paginación y ordenamiento de resultados en los endpoints de listado usando Pageable; pruebas unitarias con JUnit 5 y Mockito cubriendo la capa de servicio al menos al 80%; pruebas de integración con @SpringBootTest verificando los flujos principales; y containerización con Docker usando Dockerfile multi-stage optimizado para producción. El desarrollador debe implementar el módulo completo desde la capa de persistencia hasta los controladores REST, aplicando principios SOLID y clean code en cada capa.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
