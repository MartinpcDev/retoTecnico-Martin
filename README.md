# retoTecnico-Martin

reto de java Spring Framework

## Ejecucion

Para ejecutar el programa se debe tener instalado maven y ejecutar el siguiente comando en la raiz
del proyecto

Listar los animales

```shell
mvn clean compile exec:java "-Dexec.mainClass=org.martin.SpringApp"
```

Agregar un animal

```shell
mvn clean compile exec:java "-Dexec.mainClass=org.martin.SpringApp" "-Dexec.args=Tigre terrestre grr"
```
