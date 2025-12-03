# Script de Prueba de APIs - Avícola Logística Backend

Write-Host "=== Prueba de APIs - Backend Avícola ===" -ForegroundColor Cyan
Write-Host ""

# Configuración
$baseUrl = "http://localhost:8080"
$headers = @{'Content-Type' = 'application/json'}

# Función para hacer requests
function Test-Endpoint {
    param(
        [string]$Method,
        [string]$Url,
        [string]$Body = $null,
        [hashtable]$Headers = @{'Content-Type' = 'application/json'}
    )
    
    try {
        Write-Host "Testing: $Method $Url" -ForegroundColor Yellow
        
        if ($Body) {
            $response = Invoke-RestMethod -Uri $Url -Method $Method -Headers $Headers -Body $Body -TimeoutSec 10
        } else {
            $response = Invoke-RestMethod -Uri $Url -Method $Method -Headers $Headers -TimeoutSec 10
        }
        
        Write-Host "✓ SUCCESS" -ForegroundColor Green
        $response | ConvertTo-Json -Depth 3
        Write-Host ""
        return $response
    }
    catch {
        Write-Host "✗ ERROR: $($_.Exception.Message)" -ForegroundColor Red
        Write-Host "Status: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Red
        Write-Host ""
        return $null
    }
}

# 1. Registrar Usuario
Write-Host "1. Registrando usuario..." -ForegroundColor Cyan
$registerBody = @{
    username = "admin"
    password = "admin123"
    rol = "Admin"
    activo = $true
} | ConvertTo-Json

$user = Test-Endpoint -Method POST -Url "$baseUrl/api/auth/register" -Body $registerBody

# 2. Login
Write-Host "2. Iniciando sesión..." -ForegroundColor Cyan
$loginBody = @{
    username = "admin"
    password = "admin123"
} | ConvertTo-Json

$token = Test-Endpoint -Method POST -Url "$baseUrl/api/auth/login" -Body $loginBody

if ($token) {
    $authHeaders = @{
        'Content-Type' = 'application/json'
        'Authorization' = "Bearer $token"
    }
    
    # 3. Crear Producto
    Write-Host "3. Creando producto..." -ForegroundColor Cyan
    $productoBody = @{
        nombre = "Pollo Entero"
        unidad = "kg"
        precio = 15.50
        stock = 100
    } | ConvertTo-Json
    
    $producto = Test-Endpoint -Method POST -Url "$baseUrl/api/productos" -Body $productoBody -Headers $authHeaders
    
    # 4. Listar Productos
    Write-Host "4. Listando productos..." -ForegroundColor Cyan
    Test-Endpoint -Method GET -Url "$baseUrl/api/productos" -Headers $authHeaders
    
    # 5. Crear Cliente
    Write-Host "5. Creando cliente..." -ForegroundColor Cyan
    $clienteBody = @{
        nombre = "Juan Perez"
        direccion = "Av. Principal 123"
        telefono = "999888777"
        tipo = "minorista"
        zona = "Norte"
    } | ConvertTo-Json
    
    $cliente = Test-Endpoint -Method POST -Url "$baseUrl/api/clientes" -Body $clienteBody -Headers $authHeaders
    
    # 6. Listar Clientes
    Write-Host "6. Listando clientes..." -ForegroundColor Cyan
    Test-Endpoint -Method GET -Url "$baseUrl/api/clientes" -Headers $authHeaders
    
    # 7. Crear Pedido
    if ($cliente -and $producto) {
        Write-Host "7. Creando pedido..." -ForegroundColor Cyan
        $pedidoBody = @{
            clienteId = $cliente.id
            productoId = $producto.id
            cantidad = 10
            estado = "PENDIENTE"
        } | ConvertTo-Json
        
        Test-Endpoint -Method POST -Url "$baseUrl/api/pedidos" -Body $pedidoBody -Headers $authHeaders
        
        # 8. Listar Pedidos
        Write-Host "8. Listando pedidos..." -ForegroundColor Cyan
        Test-Endpoint -Method GET -Url "$baseUrl/api/pedidos" -Headers $authHeaders
    }
}

Write-Host ""
Write-Host "=== Pruebas Completadas ===" -ForegroundColor Cyan
