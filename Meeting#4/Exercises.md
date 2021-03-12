## EXERCISE #1

César está buscando un nuevo departamento pero le interesa que el lugar que escoja esté cerca a los lugares que suele frecuentar. Tras investigar armó una lista de direcciones donde cada dirección cuenta con una lista de locales de interés. Cada local tiene el valor de true o false dependiendo de si está o no está presente en esa dirección. Con los datos recaudados se requiere una función que reciba una lista de lugares requeridos y que permita a César determinar desde qué dirección se tiene la menor distancia recorrida entre todos los lugares escogidos.

Consideraciones: 
* La lista de lugares por direccion tiene valores unicos
* La lista de lugares requeridos tiene valores unicos

Ejemplo:

Direcciones = {
  "addressList": [
    {
      "gym" : false,
      "school" : true,
      "office" : false
    },
    {
      "gym" : true,
      "school" : false,
      "office" : false
    },
    {
      "gym" : true,
      "school" : true,
      "office" : false
    },
    {
      "gym" : false,
      "school" : true,
      "office" : false
    },
    {
      "gym" : false,
      "school" : true,
      "office" : true
    }
  ]
}

Lugares Requeridos = {"school, "gym", "office"}