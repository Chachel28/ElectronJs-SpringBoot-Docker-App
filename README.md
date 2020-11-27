### Pasos para dockerizar el Back
>1. Crear el jar del proyecto
>~~~
>mvn clean package
>~~~
>2. Crear una imagen con este jar desde la ruta /Back con el comando 
>~~~	
>docker build -t back .
>~~~	
>3. Desde el directorio /docker usar el comando
>~~~
>docker-compose up
>~~~
>4. Abrir una conexion a la bbdd y ejecutar el .sql

Ahora siempre que queramos iniciar la API, solo tendremos que ir a la ruta /docker y usar

~~~
docker-compose up
~~~
