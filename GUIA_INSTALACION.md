# 🚀 Guía para Ejecutar el Proyecto en Otra PC

## ✅ Requisitos Previos

Tu compañero necesita tener instalado:

1. **Docker Desktop** - [Descargar aquí](https://www.docker.com/products/docker-desktop/)
2. **Git** (para clonar el repositorio) - [Descargar aquí](https://git-scm.com/downloads)

---

## 📋 Pasos para Ejecutar el Proyecto

### **Paso 1: Clonar el Repositorio**

Abre una terminal (PowerShell o CMD) y ejecuta:

```bash
git clone <URL_DE_TU_REPOSITORIO>
cd avicola-logistica-backend/demo
```

> **Nota:** Reemplaza `<URL_DE_TU_REPOSITORIO>` con la URL de tu repositorio de GitHub/GitLab.

---

### **Paso 2: Iniciar Docker Desktop**

- Abre **Docker Desktop**
- Espera a que diga "Docker Desktop is running" (puede tardar 1-2 minutos)
- Verás un ícono de ballena 🐋 en la barra de tareas

---

### **Paso 3: Ejecutar el Proyecto**

En la terminal, dentro de la carpeta `demo`, ejecuta:

```bash
docker-compose up -d
```

**¡Eso es todo!** 🎉

---

## ⏱️ ¿Cuánto Tarda?

- **Primera vez:** 3-5 minutos (descarga imágenes y compila)
- **Siguientes veces:** 10-30 segundos

---

## ✅ Verificar que Funciona

### 1. Ver que los contenedores estén corriendo:

```bash
docker ps
```

Debes ver 2 contenedores:
- `avicola-backend` (puerto 8080)
- `avicola-mysql` (puerto 3307)

### 2. Ver los logs de la aplicación:

```bash
docker-compose logs -f app
```

Espera a ver el mensaje:
```
Started DemoApplication in X.XXX seconds
```

### 3. Probar la API:

Abre el navegador en: `http://localhost:8080`

Debes ver un error 403 (es normal, significa que Spring Security está activo).

---

## 🔧 Comandos Útiles

```bash
# Ver logs de la aplicación
docker-compose logs -f app

# Ver logs de MySQL
docker-compose logs -f mysql

# Detener todo
docker-compose down

# Reiniciar después de cambios en el código
docker-compose up --build -d

# Ver estado de los contenedores
docker ps
```

---

## 🗄️ Conectarse a MySQL

**Datos de conexión:**

```
Host: localhost
Puerto: 3307
Usuario: root
Contraseña: avicola123
Base de datos: avicola_db
```

**Desde terminal:**
```bash
docker exec -it avicola-mysql mysql -uroot -pavicola123 avicola_db
```

**Desde MySQL Workbench:**
- Crear nueva conexión con los datos de arriba
- ⚠️ **Importante:** El puerto es **3307**, no 3306

---

## 🐛 Solución de Problemas

### Problema: "Docker no está corriendo"
**Solución:** Abre Docker Desktop y espera a que inicie completamente.

### Problema: "Puerto 8080 ya en uso"
**Solución:** Detén cualquier aplicación que esté usando el puerto 8080:
```bash
# Ver qué está usando el puerto
netstat -ano | findstr :8080

# Detener el proceso (reemplaza PID con el número que aparece)
taskkill /PID <PID> /F
```

### Problema: "Puerto 3306 ya en uso"
**Solución:** No hay problema, el proyecto usa el puerto 3307 para evitar conflictos.

### Problema: "Error al compilar"
**Solución:** Asegúrate de tener la última versión del código:
```bash
git pull
docker-compose down
docker-compose up --build -d
```

---

## 📝 Resumen

**Tu compañero solo necesita:**

1. ✅ Instalar Docker Desktop
2. ✅ Clonar el repositorio
3. ✅ Ejecutar `docker-compose up -d`

**NO necesita instalar:**
- ❌ Java
- ❌ MySQL
- ❌ Gradle
- ❌ Ninguna otra dependencia

**Todo está en Docker** 🐳

---

## 🎯 Ventajas de Docker

- ✅ **Mismo entorno para todos** - No más "en mi máquina funciona"
- ✅ **Instalación en segundos** - Un solo comando
- ✅ **Sin conflictos** - Todo aislado en contenedores
- ✅ **Fácil de actualizar** - Solo hacer `git pull` y `docker-compose up --build`
- ✅ **Funciona en Windows, Mac y Linux** - Sin cambios

---

## 📞 Soporte

Si tu compañero tiene problemas, puede:

1. Ver los logs: `docker-compose logs`
2. Verificar que Docker esté corriendo: `docker ps`
3. Reiniciar todo: `docker-compose down` y luego `docker-compose up -d`

---

## 🔄 Actualizar el Proyecto

Cuando hagas cambios en el código:

```bash
# Tu compañero ejecuta:
git pull                      # Descargar cambios
docker-compose down           # Detener contenedores
docker-compose up --build -d  # Reconstruir y ejecutar
```

---

## 📚 Archivos Importantes

- `README.md` - Documentación general del proyecto
- `docker-compose.yml` - Configuración de Docker
- `Dockerfile` - Instrucciones para construir la imagen
- `MYSQL_CONEXION.md` - Guía para conectarse a MySQL

---

¡Listo! Con esta guía tu compañero podrá ejecutar el proyecto sin problemas. 🚀
