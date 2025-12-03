# Guía Rápida: Conectar a MySQL en Docker

## 📋 Información de Conexión

**Host:** localhost  
**Puerto:** 3307 (¡Importante! No es 3306)  
**Usuario:** root  
**Contraseña:** avicola123  
**Base de datos:** avicola_db

---

## Opción 1: Conectar desde Terminal (Recomendado)

### Paso 1: Abrir PowerShell y ejecutar:

```powershell
docker exec -it avicola-mysql mysql -uroot -pavicola123 avicola_db
```

### Paso 2: Una vez dentro de MySQL, puedes ejecutar consultas:

```sql
-- Ver todas las tablas
SHOW TABLES;

-- Ver usuarios registrados
SELECT * FROM usuarios;

-- Ver productos
SELECT * FROM productos;

-- Ver clientes
SELECT * FROM clientes;

-- Ver pedidos
SELECT * FROM pedidos;

-- Salir de MySQL
EXIT;
```

---

## Opción 2: Conectar con MySQL Workbench

### Paso 1: Abrir MySQL Workbench

### Paso 2: Crear nueva conexión con estos datos:

- **Connection Name:** Avicola Docker
- **Hostname:** localhost
- **Port:** 3307 ⚠️ (NO usar 3306)
- **Username:** root
- **Password:** avicola123 (Click en "Store in Keychain" para guardarla)

### Paso 3: Click en "Test Connection"

Si sale exitoso, click en "OK" y luego doble click en la conexión.

### Paso 4: Seleccionar la base de datos

En el panel izquierdo verás `avicola_db`. Haz doble click para usarla.

### Paso 5: Ejecutar consultas

En el editor SQL puedes ejecutar:

```sql
USE avicola_db;

SELECT * FROM usuarios;
SELECT * FROM productos;
SELECT * FROM clientes;
SELECT * FROM pedidos;
```

---

## 🔍 Consultas Útiles

```sql
-- Ver estructura de una tabla
DESCRIBE usuarios;

-- Contar registros
SELECT COUNT(*) FROM usuarios;

-- Ver último usuario registrado
SELECT * FROM usuarios ORDER BY id DESC LIMIT 1;

-- Ver todos los datos con formato
SELECT 
    id,
    username,
    rol,
    activo
FROM usuarios;
```

---

## ⚠️ Importante

- El puerto es **3307**, no 3306 (porque ya tienes MySQL local en 3306)
- La contraseña es **avicola123**
- El contenedor debe estar corriendo (verifica con `docker ps`)

---

## 🐛 Solución de Problemas

### Si no puedes conectar:

1. Verifica que Docker esté corriendo:
   ```powershell
   docker ps
   ```
   Debes ver `avicola-mysql` en la lista.

2. Si no está corriendo, inicia los contenedores:
   ```powershell
   docker-compose up -d
   ```

3. Verifica el puerto:
   ```powershell
   docker ps | Select-String "3307"
   ```
   Debes ver `0.0.0.0:3307->3306/tcp`
