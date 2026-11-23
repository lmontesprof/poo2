# Biblioteca (Java Swing + SQLite)

Proyecto Java de NetBeans para administrar libros y solicitudes. Usa SQLite embebido; no requiere usuario ni contraseña.

## Abrir en NetBeans

Abre la carpeta `BibliotecaJavaApplication1` como proyecto existente. El controlador SQLite está incluido en `libs/sqlite-jdbc-3.54.1.0.jar` y configurado con una ruta relativa en `nbproject/project.properties`.

La base de datos `biblioteca.db` contiene las tablas y datos actuales. La conexión también crea el archivo y las tablas automáticamente si no existen.
