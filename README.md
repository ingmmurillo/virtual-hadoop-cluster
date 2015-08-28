# Clúster Virtual de Hadoop con la distribución de Cloudera CDH5
Esta configuración está basada en el repositorio de [**DandyDev**](https://github.com/DandyDev/virtual-hadoop-cluster.git) y corre sobre la distribución de Cloudera [**CDH5**](http://www.cloudera.com/content/cloudera/en/products-and-services/cloudera-enterprise/cloudera-manager.html). Es útil para comenzar con Hadoop y empezar a conocer el framework.

## Especificaciones
El clúster consiste de 4 nodos:
* Master node con 4GB de RAM (Aquí corre el NameNode, Hue, ResourceManager etc. después de instalar los servicios de Hadoop)
* 3 Esclavos con 2GB de RAM cada uno (Aquí corren los DataNodes)

Como se puede apreciar, se requiere al menos de 10GB de memoria RAM para correr el clúster. Sin embargo, es posible editar el archivo Vagrantfile y reducir la memoria o el número de nodos.

## Pre-Requisitos
Antes de correr el provisionamiento del clúster es necesario tener instalado:
* [**VirtualBox**](https://www.virtualbox.org/)
* [**Vagrant**](http://www.vagrantup.com/)

## Instrucciones de Provisionamiento
Instalar [**Vagrant Hostmanager plugin**](https://github.com/smdahlen/vagrant-hostmanager)
```sh-session
vagrant plugin install vagrant-hostmanager
```
Correr el provisionamiento
```sh-session
vagrant up
```
En un browser abrir la consola [Cloudera Manager web console](http://vm-cluster-node1:7180)

## Instrucciones de Instalación
* En la consola web ingresar con el usuario **admin**, contraseña **admin**
* Seleccionar la opción **Cloudera Express**
* En la búsqueda de nodos. Digitar **vm-cluster-node[1-4]** sí se tienen los 4 nodos provisionados
* **Paso 1:** seleccionar la opción de remesas con CDH
* **Paso 2:** instalar el kit de desarrollo SE de Oracle
* **Paso 3:** crear un usuario por cada paquete instalado, de preferencia no marcar la opción de usuario único
* **Paso 4:** para el login, utilizar el usuario **vagrant** con contraseña **vagrant** para todos los nodos
* En la sección de **Configuración de servicios a instalar** seleccionar **Núcleo con HBase**
* Dejar por defecto los roles de cada aplicación
* En la sección de configuración de bases de datos, utilizar la opción de **bases de datos incrustadas**
* Esperar a que la instalación termine y a divertirse :-.)

## Problemas conocidos
Sí el servicio de **Cloudera Management Service** no inicia, ejecutar la siguiente secuencia de comandos:
```sh-session
vagrant ssh master
service cloudera-scm-server stop
service cloudera-scm-server start
```

Es importante cambiar la siguiente configuración para evitar problemas de notificación del status de los nodos en **Cloudera Manager.**
* Acceder a los nodos esclavos provisionados [slave1, slave2, slave3] y ejecutar lo siguiente:
```sh-session
vagrant ssh slave1
sudo sysctl -w vm.swappiness=0
sudo vi /etc/sysctl.conf
```
* Agregar la siguiente línea al final del archivo **/etc/sysctl.conf**:
```properties
vm.swappiness = 0
```
* **NOTA:** Hacer éste mismo procedimiento para todos los nodos esclavos que se tenga en el clúster. Para verificar el cambio ejecutar **cat /proc/sys/vm/swappiness**

##Variables de Configuración Importantes
Sí se tienen menos nodos (Data Nodes) configurados en el provisionamiento, es conveniente cambiar el factor de replicación de **HDFS**:
```properties
dfs.replication = NroDataNodes
```

Es importante configurar correctamente los recursos del cluster para **YARN**, configurar las variables:
```properties
yarn.nodemanager.resource.memory-mb = NroMegaBytesCluster
yarn.nodemanager.resource.cpu-vcores = NroProcesadoresCluster
```
