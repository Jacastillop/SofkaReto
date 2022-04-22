# SofkaReto
Solucion al reto tecnico Sofka desarrollado en Java en el entorno de desarrolo Apache NetBeans IDE 13 y MySql Workbench 8.0
## Instrucciones de ejecucion 
1. Bajar el repositorio de GitHub usando el boton "Download ZIP"
![gitP](https://user-images.githubusercontent.com/51030335/164621029-004c2070-6df1-4aa7-aaca-821050c9a49e.png)
2. importar el archivo .zip en ide NeNetBeans13 
![importZip](https://user-images.githubusercontent.com/51030335/164623504-a5fa39ab-ff92-4cd1-bd3f-d2fd2bbd2670.png)
3. Iniciar MySQL WorkBench y correr el script llamado questionBank.sql que se encuentra en la carpeta "SQL" del proyecto para crear 
la base de datos y la tabla
![mysql](https://user-images.githubusercontent.com/51030335/164646357-82a64133-1c30-408a-b409-5ded7d5422af.png)
4. Correr el archivo llamado "questionchallenge_answer.sql" que se encuentra en la carpeta "SQL" del proyecto para dar datos iniciales a 
la tabla answer
5. Realizar el paso anterior para los archivos "questionchallenge_question.sql" y "questionchallenge_player.sql"
6. NOTA:Si tu conexion a MySQL tiene una configuracion diferente , dirigete a la clase DBConexion y cambia los valores para:

```
USUARIO   ="root";
String PASSWORD  ="1234";
URL ="jdbc:mysql://localhost:3306/questionchallenge?useUnicode=true&characterEncoding=UTF-8";
```

![dbClass](https://user-images.githubusercontent.com/51030335/164643254-311f8935-a630-4c31-8014-e838c91e76d7.png)

8. En NetBeans, dar click sobre el boton "Run Project"



