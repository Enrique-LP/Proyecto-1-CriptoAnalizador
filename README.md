# Analizador de Cifrado César

## Descripción
Este proyecto implementa un sistema de cifrado y descifrado usando el algoritmo César, con capacidades adicionales de análisis estadístico y fuerza bruta.

## Características
- 📝 Cifrado de archivos de texto
- 🔓 Descifrado con clave conocida
- 🔨 Descifrado por fuerza bruta
- 💾 Generación automática de archivo de propiedades

## Requisitos
- Java JDK 11 o superior
- IDE compatible con Java (IntelliJ IDEA recomendado)

## Estructura del Proyecto
```
CryptoAnalyzer/
├── src/              # Código fuente
├── JavaDoc/          # Documentación del código
├── files/            # Archivos de texto para pruebas
└── README.md         # Este archivo
```

## Uso
1. Ejecute la aplicación
2. Seleccione una de las siguientes opciones:
   - Cifrar archivo
   - Descifrar archivo con clave
   - Descifrar archivo por fuerza bruta
   - Análisis estadístico

## Ejemplos
### Cifrar un archivo
1. Seleccione opción 1
2. Ingrese el nombre del archivo (ej: files/input.txt)
3. Ingrese la clave numérica
4. Especifique el archivo de salida

### Análisis Estadístico
1. Seleccione opción 4
2. Ingrese el nombre del archivo cifrado
3. El programa detectará automáticamente el desplazamiento más probable

## Documentación
La documentación completa del código está disponible en la carpeta JavaDoc/

## Autor
[Tu Nombre]

## Licencia
Este proyecto está bajo la Licencia MIT