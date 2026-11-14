# Compilar este proyecto con Maven

Este `pom.xml` está dentro de `base_datos`, así que Maven solo compila los archivos
Java de esta carpeta. No incluye los demás ejercicios del repositorio.

## Configuración

- `maven.compiler.release`: compila para Java 11.
- `sourceDirectory`: indica que los `.java` están directamente aquí.
- `includes`: limita la compilación a los `.java` de esta carpeta.
- `mysql-connector-j`: agrega el driver JDBC que necesita `DriverManager`.
- `exec-maven-plugin`: permite ejecutar `base_datos.Main` con Maven.

## Uso

Abre una terminal dentro de `base_datos` y ejecuta:

```bash
mvn compile
```

Para iniciar el programa:

```bash
mvn exec:java
```

La primera ejecución necesita internet para descargar Maven y el driver JDBC. La
base de datos `iud_academico` también debe existir en MySQL. Configura usuario y
contraseña en el entorno antes de ejecutar, para no guardar credenciales en el código:

```bash
export DB_USER=root
export DB_PASSWORD='tu_contraseña'
mvn exec:java
```

`DB_USER` es opcional (por defecto usa `root`); define `DB_PASSWORD` con la contraseña
de tu instalación de MySQL.
