# Tareas

![Static Badge](https://img.shields.io/badge/Estado%20-%20Terminado%20-%20green)

## Introducción
Proyecto personal creado para mostrar los conocimientos sobre Java y Spring Boot.
El proyecto ofrece al usuario una lista de tareas, a las cuales se las puede agregar mas tareas, 
eliminarlas o actualizarlas

## Tipo de proyecto
Proyecto individual

## Tecnologías usadas
- Java
- Spring
- Thymeleaf
- Maven
- MySQL
- HTML
- CSS

## Estructura

```
Tareas
├─ .gitignore
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ tareas
   │  │     └─ tareas
   │  │        ├─ dao
   │  │        │  ├─ TareaDao.java
   │  │        │  └─ UsuarioDao.java
   │  │        ├─ domain
   │  │        │  ├─ Tarea.java
   │  │        │  └─ Usuario.java
   │  │        ├─ RestController.java
   │  │        ├─ servicio
   │  │        │  ├─ TareaServicio.java
   │  │        │  ├─ TareaServicioImp.java
   │  │        │  ├─ UsuarioServicio.java
   │  │        │  └─ UsuarioServicioImp.java
   │  │        └─ TareasApplication.java
   │  └─ resources
   │     ├─ application.properties
   │     ├─ static
   │     │  └─ estilo
   │     │     ├─ inicio.css
   │     │     ├─ registro.css
   │     │     └─ tarea.css
   │     └─ templates
   │        ├─ inicio.html
   │        ├─ registro.html
   │        └─ tarea.html
   └─ test
      └─ java
         └─ tareas
            └─ tareas
               └─ TareasApplicationTests.java

```


## Instalación 
Para poder usar el proyecto es necesario tener instalado Java.

## Uso
Simplemente corra el archivo TareasApplication.

> [!NOTE]
> La base de datos a la que esta conectado el proyecto es lenta. Porfavor tenga paciencia.
