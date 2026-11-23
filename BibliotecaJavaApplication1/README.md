# Biblioteca con SQLite

Se subió este proyecto de biblioteca desarrollado en Java Swing y conectado a SQLite. Permite administrar libros y solicitudes.

## Abrir en NetBeans

Abre la carpeta `BibliotecaJavaApplication1` como proyecto existente. El controlador JDBC de SQLite está incluido en `libs/sqlite-jdbc-3.54.1.0.jar` y configurado con una ruta relativa en `nbproject/project.properties`.

La base de datos `biblioteca.db` contiene las tablas y los datos actuales. Si el archivo o las tablas no existen, la aplicación los crea al iniciar. SQLite no requiere usuario ni contraseña.
