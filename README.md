# Clúster Virtual de Hadoop con la distribución de Cloudera CDH5
Esta configuración está basada en el repositorio de [**DandyDev**](https://github.com/DandyDev/virtual-hadoop-cluster.git) y corre sobre la distribución de Cloudera [**CDH5**](http://www.cloudera.com/content/cloudera/en/products-and-services/cloudera-enterprise/cloudera-manager.html).

## Especificaciones
El clúster consiste de 4 nodos:
* Master node con 4GB de RAM (Aquí corre el NameNode, Hue, ResourceManager etc. después de instalar los servicios de Hadoop)
* 3 Esclavos con 2GB de RAM cada uno (Aquí corren los DataNodes)
Como se puede apreciar, se requiere al menos de 10GB de memoria RAM para correr el clúster. Sin embargo, es posible editar el archivo Vagrantfile y reducir la memoria o el número de nodos.

## Pre-Requisitos
Antes de correr el provisionamiento del clúster es necesario tener instalado:
* [**VirtualBox**](https://www.virtualbox.org/)
* [**Vagrant**](http://www.vagrantup.com/)

## Instrucciones
Instalar [**Vagrant Hostmanager plugin**](https://github.com/smdahlen/vagrant-hostmanager)
```bash
$ vagrant plugin install vagrant-hostmanager
```
Correr el provisionamiento
```bash
$ vagrant up
```

En un browser abrir la consola [Cloudera Manager web console](http://vm-cluster-node1:7180)
