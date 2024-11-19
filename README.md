# Aplicación LiterAlura

![](https://api.visitorbadge.io/api/VisitorHit?user=matiasnm&repo=/matiasnm/aluraJavaChallenge2&countColor=%230e75b6)

![Badge en Desarollo](https://img.shields.io/badge/STATUS-FINALIZADO-green)

![LiterAlura](https://github.com/matiasnm/aluraJavaChallenge2/blob/main/README.png)

### **Descripción General**
Literalura es una aplicación para la gestión de libros diseñada para integrarse con la [API GutenDex](https://gutendex.com/books/). Permite a los usuarios buscar libros por título o autor, gestionar una base de datos de libros y autores, y explorar libros por idioma o autor. La aplicación ofrece una interfaz interactiva de línea de comandos (CLI) con salidas visualmente atractivas para una experiencia fluida.

---

## **Características**

1. **Buscar y Guardar Libros**  
   - Buscar libros por título o autor a través de la API GutenDex.
   - Guardar automáticamente los detalles del libro y el autor en una base de datos local.

2. **Listar Libros Registrados**  
   - Mostrar todos los libros almacenados en la base de datos.

3. **Listar Autores Registrados**  
   - Ver todos los autores guardados en la base de datos.

4. **Encontrar Autores Vivos por Año**  
   - Filtrar autores que estuvieron vivos en un año específico.

5. **Listar Libros por Idioma**  
   - Mostrar los idiomas disponibles de los libros y buscar libros en un idioma específico.

6. **Encontrar Libros por Autor**  
   - Recuperar libros escritos por un autor en particular.

7. **Experiencia CLI Colorida**  
   - Disfrutar de salidas visualmente atractivas con diferentes colores para categorías, errores e información.

---
## **Uso**

### **Opciones del Menú CLI**
La aplicación ofrece las siguientes opciones interactivas en el menú:

- **`1 - Buscar libros`**  
  Permite buscar libros a través de la API GutenDex utilizando un título o el nombre de un autor. Los detalles del libro y del autor se guardan automáticamente en la base de datos local.

- **`2 - Listar libros registrados`**  
  Muestra todos los libros que han sido almacenados previamente en la base de datos.

- **`3 - Listar autores registrados`**  
  Muestra una lista de todos los autores registrados en la base de datos.

- **`4 - Listar autores vivos por año`**  
  Filtra autores que estuvieron vivos en un año específico ingresado por el usuario.

- **`5 - Listar libros por idioma`**  
  Muestra una lista de idiomas registrados en los libros almacenados. Permite seleccionar un idioma para listar los libros correspondientes.

- **`6 - Buscar libros por autor`**  
  Permite seleccionar un autor registrado y muestra todos los libros relacionados con ese autor.

- **`0 - Salir`**  
  Finaliza la aplicación.

### **Ejemplo de Flujo de Trabajo**
1. **Buscar un libro:**  
   - Selecciona la opción `1`.
   - Ingresa un título o el nombre de un autor en el campo solicitado.
   - Si hay coincidencias, se muestran los detalles del libro. Este se guarda automáticamente en la base de datos junto con el autor.

2. **Listar libros registrados:**  
   - Selecciona la opción `2`.
   - La aplicación muestra una lista de todos los libros almacenados.

3. **Buscar libros por idioma:**  
   - Selecciona la opción `5`.
   - Elige un idioma de la lista proporcionada.
   - Observa los libros almacenados que están en el idioma seleccionado.

---

## **Componentes Principales**

### **Capa de Servicios**
La lógica principal de la aplicación se encuentra en la clase `LiteraluraService`. Sus responsabilidades incluyen:

- **Integración con la API GutenDex:**  
  Realiza solicitudes HTTP para buscar libros utilizando la clase `ApiConsumer`.

- **Gestión de la entrada del usuario:**  
  Recoge y procesa entradas mediante la clase `ScannerService`.

- **Interacción con la base de datos:**  
  Utiliza `BookRepository` y `AuthorRepository` para gestionar entidades `Book` y `Author`. Proporciona métodos para buscar, filtrar y guardar registros.

- **Manejo de resultados:**  
  La aplicación utiliza un sistema centralizado (`mostrarResultados`) para imprimir listas de libros o autores con un formato colorido.

### **Repositorios**
- **`BookRepository`**  
  Contiene consultas personalizadas para:
  - Buscar libros por idioma (`findByLanguages`).
  - Recuperar libros relacionados con un autor (`findByAuthors`).
  - Listar todos los idiomas registrados (`listLanguages`).

- **`AuthorRepository`**  
  Proporciona métodos para:
  - Buscar autores vivos en un año específico (`findByLivingInYear`).
  - Filtrar autores por nombres que contengan un texto ingresado.

### **Servicios Auxiliares**
- **`PrintService`**  
  Proporciona métodos para imprimir mensajes en la consola con diferentes colores y estilos.

- **`JsonConversor`**  
  Convierte datos JSON en objetos Java utilizando clases DTO como `SearchDto` y `BookDto`.

- **`ScannerService`**  
  Recoge la entrada del usuario de forma interactiva.