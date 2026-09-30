# Implementación de una API REST para gestión de productos y pedidos

Debes desarrollar una API REST empresarial para un e-commerce que gestione productos y pedidos. El sistema debe incluir una arquitectura en capas con controladores REST, servicios de negocio y repositorios JPA. Utiliza relaciones OneToMany y ManyToMany con Hibernate para modelar entidades Product, Order y Customer. Implementa autenticación y autorización basada en JWT con Spring Security, incluyendo roles de ADMIN y USER con acceso diferenciado por endpoint. Documenta automáticamente la API con OpenAPI 3.0 y Swagger UI. Maneja errores centralizados con @ControllerAdvice y valida entradas con Bean Validation. Aplica paginación y ordenamiento en los endpoints de listado. Realiza pruebas unitarias y de integración, y containeriza la aplicación con Docker.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Arquitectura Empresarial con Spring Boot |
| **Nivel** | junior-l3 |
| **Tipo** | practical |
| **Tiempo estimado** | 40 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Modelado de datos y relaciones

**Objetivo:** Definir y modelar las entidades Product, Order y Customer con sus relaciones OneToMany y ManyToMany.

**Tiempo estimado:** 8 horas

**Instrucciones:**

- Identifica las entidades y sus relaciones en el dominio del e-commerce.
- Modela las entidades con las relaciones adecuadas.
- Verifica que las relaciones cumplen con los requisitos del dominio.

**Entregable:** Modelo de datos con entidades y relaciones definidas.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda las diferencias entre relaciones OneToMany y ManyToMany.
- Considera los atributos necesarios para cada entidad.

</details>

### Fase 2: Implementación de controladores REST

**Objetivo:** Crear controladores REST para las operaciones CRUD de productos y pedidos.

**Tiempo estimado:** 8 horas

**Instrucciones:**

- Define los endpoints necesarios para las operaciones CRUD.
- Implementa los controladores REST utilizando Spring Boot.
- Verifica que los controladores funcionan correctamente y devuelven los datos esperados.

**Entregable:** Controladores REST implementados y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda utilizar anotaciones adecuadas para los controladores.
- Verifica la correcta respuesta de los endpoints.

</details>

### Fase 3: Autenticación y autorización con JWT

**Objetivo:** Implementar autenticación y autorización basada en JWT con Spring Security.

**Tiempo estimado:** 8 horas

**Instrucciones:**

- Configura Spring Security para usar JWT.
- Define roles de ADMIN y USER con acceso diferenciado por endpoint.
- Verifica que la autenticación y autorización funcionan correctamente.

**Entregable:** Autenticación y autorización implementadas y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda configurar los filtros de seguridad necesarios.
- Verifica que los roles tienen el acceso correcto a los endpoints.

</details>

### Fase 4: Documentación automática con OpenAPI y Swagger UI

**Objetivo:** Documentar automáticamente la API con OpenAPI 3.0 y Swagger UI.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Configura OpenAPI 3.0 para documentar la API.
- Habilita Swagger UI para visualizar la documentación.
- Verifica que la documentación está completa y accesible en /api-docs.

**Entregable:** Documentación automática de la API accesible en /api-docs.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda incluir toda la información necesaria en la documentación.
- Verifica que Swagger UI muestra la documentación correctamente.

</details>

### Fase 5: Manejo centralizado de errores

**Objetivo:** Implementar manejo centralizado de errores con @ControllerAdvice.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Crea una clase de manejo de errores centralizada con @ControllerAdvice.
- Define respuestas estandarizadas en formato JSON para los errores.
- Verifica que los errores son manejados correctamente y devueltos en el formato esperado.

**Entregable:** Manejo centralizado de errores implementado y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda utilizar anotaciones adecuadas para el manejo de errores.
- Verifica que las respuestas de error están en el formato correcto.

</details>

### Fase 6: Validación de entradas

**Objetivo:** Implementar validación de entradas con Bean Validation.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Utiliza anotaciones de Bean Validation como @Valid, @NotNull y @Size.
- Verifica que las entradas son validadas correctamente y se devuelven errores adecuados.
- Aplica validación en los controladores REST.

**Entregable:** Validación de entradas implementada y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda utilizar las anotaciones adecuadas para la validación.
- Verifica que las entradas son validadas correctamente.

</details>

### Fase 7: Paginación y ordenamiento

**Objetivo:** Implementar paginación y ordenamiento en los endpoints de listado.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Utiliza Pageable para implementar paginación y ordenamiento.
- Verifica que los resultados son paginados y ordenados correctamente.
- Aplica paginación y ordenamiento en los endpoints de listado.

**Entregable:** Paginación y ordenamiento implementados y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda utilizar Pageable para paginación y ordenamiento.
- Verifica que los resultados son paginados y ordenados correctamente.

</details>

### Fase 8: Pruebas unitarias y de integración

**Objetivo:** Realizar pruebas unitarias y de integración para la capa de servicio.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Escribe pruebas unitarias con JUnit 5 y Mockito para la capa de servicio.
- Escribe pruebas de integración con @SpringBootTest para verificar los flujos principales.
- Verifica que las pruebas cubren al menos el 80% de la capa de servicio.
- Verifica que las pruebas de integración cubren los flujos principales.

**Entregable:** Pruebas unitarias y de integración implementadas y funcionando.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda utilizar JUnit 5 y Mockito para las pruebas unitarias.
- Verifica que las pruebas cubren la capa de servicio y los flujos principales.

</details>

### Fase 9: Containerización con Docker

**Objetivo:** Containerizar la aplicación con Docker.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Crea un Dockerfile multi-stage optimizado para producción.
- Verifica que la aplicación se ejecuta correctamente en un contenedor Docker.
- Aplica principios SOLID y clean code en cada capa.

**Entregable:** Aplicación containerizada y funcionando en Docker.

<details>
<summary>Pistas de conocimiento</summary>

- Recuerda utilizar un Dockerfile multi-stage optimizado para producción.
- Verifica que la aplicación se ejecuta correctamente en un contenedor Docker.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las entidades y relaciones en el dominio del e-commerce?
- **paraQueSirve**: ¿Para qué sirven los controladores REST en la API?
- **comoSeUsa**: ¿Cómo se usa JWT para autenticación y autorización en Spring Security?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar paginación y ordenamiento?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un Dockerfile multi-stage para la containerización?

## Criterios de Evaluacion

- Modelo de datos con entidades y relaciones definidas.
- Controladores REST implementados y funcionando.
- Autenticación y autorización basada en JWT con Spring Security.
- Documentación automática de la API accesible en /api-docs.
- Manejo centralizado de errores con @ControllerAdvice.
- Validación de entradas con Bean Validation.
- Paginación y ordenamiento en los endpoints de listado.
- Pruebas unitarias y de integración para la capa de servicio.
- Aplicación containerizada y funcionando en Docker.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
