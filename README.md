# To Do Service

**To Do Service** es una API RESTful para la gestión de tareas. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre tareas, así como filtrarlas por prioridad y estado.

## Características

- **Crear, leer, actualizar y eliminar tareas.**
- **Filtrar tareas por prioridad y estado.**
- **Documentación de API generada automáticamente con Swagger.**

## Requisitos

- Java 17


### Base de Datos

La aplicación usa H2 Database por defecto para almacenamiento en memoria.

# Ejecutar la Aplicación

Para ejecutar la aplicación localmente:

1. Descarga las dependencias:
```bash
./gradlew build
```
2. Ejecuta la aplicación:
```bash
./gradlew bootRun
```
La aplicación se levantará en http://localhost:8080.

## Endpoints de la API
### Obtener Todas las Tareas
- **GET** '/tasks'
- **Descripción**: Recupera una lista de todas las tareas.

### Obtener Tarea por ID
- **GET** '/tasks/{id}'
- **Descripción**: Recupera una tarea específica por su ID.

### Crear Nueva Tarea
- **POST** '/tasks'
- **Descripción**: Crea una nueva tarea.

Cuerpo de la solicitud:
```json
{
  "title": "Título de la tarea",
  "description": "Descripción de la tarea",
  "priority": "HIGH" | "MEDIUM" | "LOW",
  "status": "TO_DO" | "IN_PROGRESS" | "COMPLETED"
}
```

### Actualizar Tarea
- **PUT** '/tasks/{id}'
- **Descripción**: Actualiza una tarea existente por su ID.

Cuerpo de la solicitud:
```json
{
  "title": "Título actualizado",
  "description": "Descripción actualizada",
  "priority": "HIGH" | "MEDIUM" | "LOW",
  "status": "TO_DO" | "IN_PROGRESS" | "COMPLETED"
}
```

### Eliminar Tarea
- **DELETE** /tasks/{id}
- **Descripción**: Elimina una tarea específica por su ID.

### Filtrar Tareas
- **GET** /tasks/filter
- **Descripción**: Filtra tareas por prioridad y estado.

Parámetros de consulta:

- '**priority**' (opcional): '**HIGH**', '**MEDIUM**', '**LOW**'
- '**status**' (opcional): '**TO_DO**', '**IN_PROGRESS**', '**COMPLETED**'

### Documentación de la API

La documentación de la API está disponible en Swagger UI en:

http://localhost:8080/swagger-ui.html
